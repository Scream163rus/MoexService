package com.powerfulbenches.moexservice.dto;

import com.powerfulbenches.moexservice.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BondDto {
    private String ticker;
    private String figi;
    private BigDecimal price;
    private Currency currency;
}
