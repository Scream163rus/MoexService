package com.powerfulbenches.moexservice.service;

import com.powerfulbenches.moexservice.dto.FigiesDto;
import com.powerfulbenches.moexservice.dto.StocksDto;
import com.powerfulbenches.moexservice.dto.StocksPricesDto;
import com.powerfulbenches.moexservice.dto.TickersDto;

public interface BondsService {
    StocksDto getBondsFromMoex(TickersDto tickerDto);

    StocksPricesDto getPricesByFigi(FigiesDto figisDto);
}
