package com.powerfulbenches.moexservice.controller;

import com.powerfulbenches.moexservice.dto.FigiesDto;
import com.powerfulbenches.moexservice.dto.StocksDto;
import com.powerfulbenches.moexservice.dto.StocksPricesDto;
import com.powerfulbenches.moexservice.dto.TickersDto;
import com.powerfulbenches.moexservice.service.BondsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bonds")
@RequiredArgsConstructor
public class BondsController {
    private final BondsService bondsService;

    @PostMapping("/byTickers")
    public StocksDto getBondsByTickers(@RequestBody TickersDto tickerDto){
        return bondsService.getBondsFromMoex(tickerDto);
    }

    @PostMapping("/price")
    public StocksPricesDto getBondsByFigis(@RequestBody FigiesDto figisDto){
        return bondsService.getPricesByFigi(figisDto);
    }
}
