package com.powerfulbenches.moexservice.service;

import com.powerfulbenches.moexservice.dto.BondDto;

import java.util.List;

public interface BondsRepository {
    List<BondDto> getGovBonds();
    List<BondDto> getCorpBonds();
}
