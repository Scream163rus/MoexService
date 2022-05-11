package com.powerfulbenches.moexservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${moex.bonds.corporate.url}", configuration = FeignConfiguration.class, value = "corporateBonds")
public interface CorporateBondsClient {
    @GetMapping
    String getCorporateBonds();
}
