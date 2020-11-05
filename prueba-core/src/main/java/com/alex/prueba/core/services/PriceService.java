package com.alex.prueba.core.services;

import com.alex.prueba.core.exceptions.PriceNotFoundException;
import com.alex.prueba.core.models.PriceModel;
import com.alex.prueba.domain.entities.PriceEntity;
import com.alex.prueba.domain.repositories.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    @Autowired
    private PricesRepository pricesRepository;

    public PriceModel findPrice(LocalDateTime date, long productId, long brandId) throws PriceNotFoundException {
        return pricesRepository.findOneByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            date,
            date,
            productId,
            brandId
        ).stream()
            .findFirst()
            .map(this::toModel)
            .orElseThrow(() -> new PriceNotFoundException("Prices not found"));
    }

    private PriceModel toModel(PriceEntity priceEntity) {
        return PriceModel.builder()
            .id(priceEntity.getId())
            .brandId(priceEntity.getBrandId())
            .startDate(priceEntity.getStartDate())
            .endDate(priceEntity.getEndDate())
            .priceList(priceEntity.getPriceList())
            .productId(priceEntity.getProductId())
            .priority(priceEntity.getPriority())
            .price(priceEntity.getPrice())
            .curr(priceEntity.getCurr())
            .build();
    }

}
