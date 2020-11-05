package com.alex.prueba.core.services;

import com.alex.prueba.core.exceptions.PriceNotFoundException;
import com.alex.prueba.core.models.PriceModel;
import com.alex.prueba.domain.entities.PriceEntity;
import com.alex.prueba.domain.repositories.PricesRepository;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(JMockit.class)
public class PriceServiceTest {

    @Tested
    private PriceService priceService;

    @Injectable
    private PricesRepository pricesRepository;

    @Test
    public void testFindPrice() throws PriceNotFoundException {
        final LocalDateTime localDateTime = LocalDateTime.now();
        final Long productId = 1L;
        final Long brandId = 1L;

        final List<PriceEntity> list = Collections.singletonList(PriceEntity.builder().build());

        new Expectations() {{
            pricesRepository.findOneByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                localDateTime,
                localDateTime,
                productId,
                brandId
            );
            result = list;
            times = 1;
        }};

        final PriceModel priceModel = priceService.findPrice(localDateTime, productId, brandId);
        assertNotNull(priceModel);
    }

    @Test(expected = PriceNotFoundException.class)
    public void testFindPriceNotFound() throws PriceNotFoundException {
        final LocalDateTime localDateTime = LocalDateTime.now();
        final Long productId = 1L;
        final Long brandId = 1L;

        priceService.findPrice(localDateTime, productId, brandId);

        new Verifications() {{
            pricesRepository.findOneByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                localDateTime,
                localDateTime,
                productId,
                brandId
            );
            times = 1;
        }};
    }
}