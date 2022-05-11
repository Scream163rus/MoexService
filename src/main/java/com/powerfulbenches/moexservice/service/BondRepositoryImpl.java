package com.powerfulbenches.moexservice.service;

import com.powerfulbenches.moexservice.client.CorporateBondsClient;
import com.powerfulbenches.moexservice.client.GovBondsClient;
import com.powerfulbenches.moexservice.dto.BondDto;
import com.powerfulbenches.moexservice.exception.LimitRequestException;
import com.powerfulbenches.moexservice.parser.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BondRepositoryImpl implements BondsRepository {

    private final Parser<BondDto> bondDtoParser;

    private final CorporateBondsClient corporateBondsClient;
    private final GovBondsClient govBondsClient;

    @Override
    @Cacheable(value = "govBonds")
    public List<BondDto> getGovBonds() {
        String govBondsXml = govBondsClient.getGovBonds();
        List<BondDto> parse = bondDtoParser.parse(govBondsXml);
        if(parse == null){
            throw new LimitRequestException("");
        }
        return parse;
    }

    @Override
    @Cacheable(value = "corpBonds")
    public List<BondDto> getCorpBonds() {
        String corporateBonds = corporateBondsClient.getCorporateBonds();
        List<BondDto> parse = bondDtoParser.parse(corporateBonds);
        if(parse == null){
            throw new LimitRequestException("");
        }
        return parse;
    }
}
