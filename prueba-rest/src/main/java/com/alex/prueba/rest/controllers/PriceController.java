package com.alex.prueba.rest.controllers;

import com.alex.prueba.core.exceptions.PriceNotFoundException;
import com.alex.prueba.core.models.PriceModel;
import com.alex.prueba.core.services.PriceService;
import com.alex.prueba.rest.dtos.response.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceResponse> findPrice(
        @RequestParam("date") String date,
        @RequestParam("productId") Long productId,
        @RequestParam("brandId") Long brandId
    ) throws PriceNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        return new ResponseEntity<>(toPriceResponse(priceService.findPrice(dateTime, productId, brandId)), HttpStatus.OK);
    }

    private PriceResponse toPriceResponse(PriceModel priceModel) {
        return PriceResponse.builder()
            .productId(priceModel.getProductId())
            .brandId(priceModel.getBrandId())
            .rate(priceModel.getPriceList())
            .startDate(priceModel.getStartDate())
            .endDate(priceModel.getEndDate())
            .price(priceModel.getPrice())
            .currency(priceModel.getCurr())
            .build();
    }
}
