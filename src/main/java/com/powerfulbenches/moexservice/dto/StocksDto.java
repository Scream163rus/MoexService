package com.powerfulbenches.moexservice.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class StocksDto {
    List<StockDto> stocksDto;
}
