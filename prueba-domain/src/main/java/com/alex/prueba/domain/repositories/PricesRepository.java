package com.alex.prueba.domain.repositories;

import com.alex.prueba.domain.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PricesRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findOneByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long productId,
        Long brandId
    );

}
