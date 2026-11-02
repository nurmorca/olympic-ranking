package com.huawei.olympicranking.data.dto;

import jakarta.validation.constraints.NotBlank;

public record CountryRequest(
    @NotBlank String countryCode,
    @NotBlank String countryName
) {
    
}
