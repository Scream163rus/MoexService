package com.powerfulbenches.moexservice.dto;


import com.powerfulbenches.moexservice.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    String ticker;
    String figi;
    String type;
    Currency currency;
    String source;
}
