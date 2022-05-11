package com.powerfulbenches.moexservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class StockPrice {
    private String figi;
    private BigDecimal price;
    private Currency currency;
}
