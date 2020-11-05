package com.alex.prueba.rest.controllers;

import com.alex.prueba.rest.dtos.response.PriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private PriceController priceController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindPriceCase1() throws Exception {
        final String date = "2020-06-14 10:00";
        final Long productId = 35455L;
        final Long brandId = 1L;

        final float priceExpected = 35.5f;

        runIntegrationTest(date, productId, brandId, priceExpected);
    }

    @Test
    public void testFindPriceCase2() throws Exception {
        final String date = "2020-06-14 16:00";
        final Long productId = 35455L;
        final Long brandId = 1L;

        final float priceExpected = 25.45f;

        runIntegrationTest(date, productId, brandId, priceExpected);
    }

    @Test
    public void testFindPriceCase3() throws Exception {
        final String date = "2020-06-14 21:00";
        final Long productId = 35455L;
        final Long brandId = 1L;

        final float priceExpected = 35.5f;

        runIntegrationTest(date, productId, brandId, priceExpected);
    }

    @Test
    public void testFindPriceCase4() throws Exception {
        final String date = "2020-06-15 10:00";
        final Long productId = 35455L;
        final Long brandId = 1L;

        final float priceExpected = 30.5f;

        runIntegrationTest(date, productId, brandId, priceExpected);
    }

    @Test
    public void testFindPriceCase5() throws Exception {
        final String date = "2020-06-16 21:00";
        final Long productId = 35455L;
        final Long brandId = 1L;

        final float priceExpected = 38.95f;

        runIntegrationTest(date, productId, brandId, priceExpected);
    }

    private void runIntegrationTest(String date, Long productId, Long brandId, Float priceExpected) throws Exception {
        mockMvc.perform(
            get("/prices")
                .param("date", date)
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());

        final PriceResponse priceResponse = priceController.findPrice(date, productId, brandId).getBody();
        assertNotNull(Objects.requireNonNull(priceResponse).getPrice());
        assertEquals(priceExpected, priceResponse.getPrice());
    }
}