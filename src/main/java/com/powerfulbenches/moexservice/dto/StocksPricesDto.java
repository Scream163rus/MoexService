package com.powerfulbenches.moexservice.dto;

import com.powerfulbenches.moexservice.model.StockPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StocksPricesDto{
    List<StockPrice> stockPrices;
}
