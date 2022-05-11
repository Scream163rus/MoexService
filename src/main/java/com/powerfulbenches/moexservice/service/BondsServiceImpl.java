package com.powerfulbenches.moexservice.service;

import com.powerfulbenches.moexservice.dto.*;
import com.powerfulbenches.moexservice.exception.BondsNotFoundException;
import com.powerfulbenches.moexservice.dto.StockDto;
import com.powerfulbenches.moexservice.model.StockPrice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BondsServiceImpl implements BondsService {

    private final BondsRepository bondsRepository;

    @Override
    public StocksDto getBondsFromMoex(TickersDto tickerDto) {
        List<String> tickers = tickerDto.getTickers();
        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(bondsRepository.getCorpBonds());
        allBonds.addAll(bondsRepository.getGovBonds());
        List<BondDto> findBonds = allBonds.stream().filter(bondDto -> tickers.contains(bondDto.getTicker()))
                .collect(Collectors.toList());
        List<StockDto> stocks = findBonds.stream().map(bonds -> new StockDto(bonds.getTicker(), bonds.getFigi(), "Bond", bonds.getCurrency(), "Moex"))
                .collect(Collectors.toList());
        return new StocksDto(stocks);
    }

    @Override
    public StocksPricesDto getPricesByFigi(FigiesDto figiesDto) {
        List<String> figies = new ArrayList<>(figiesDto.getFigies());
        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(bondsRepository.getGovBonds());
        allBonds.addAll(bondsRepository.getCorpBonds());
        figies.removeAll(allBonds.stream().map(BondDto::getFigi).collect(Collectors.toList()));
        if (!figies.isEmpty()) {
            throw new BondsNotFoundException(String.format("Bonds %s not found", figies));
        }
        List<StockPrice> stockPrices = allBonds.stream()
                .filter(bondDto -> figiesDto.getFigies().contains(bondDto.getFigi()))
                .map(bondDto -> new StockPrice(bondDto.getFigi(), bondDto.getPrice().multiply(BigDecimal.TEN), bondDto.getCurrency()))
                .collect(Collectors.toList());
        return new StocksPricesDto(stockPrices);
    }
}
