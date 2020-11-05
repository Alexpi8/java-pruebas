package com.alex.prueba.rest.dtos.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class PriceResponse {

    private Long productId;
    private Long brandId;
    private Integer rate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Float price;
    private String currency;

}
