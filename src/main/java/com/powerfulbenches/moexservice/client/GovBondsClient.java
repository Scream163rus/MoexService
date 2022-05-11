package com.powerfulbenches.moexservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${moex.bonds.government.url}", configuration = FeignConfiguration.class, value = "govBonds")
public interface GovBondsClient {
    @GetMapping
    String getGovBonds();
}
