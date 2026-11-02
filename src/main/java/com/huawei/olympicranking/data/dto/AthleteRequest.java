package com.huawei.olympicranking.data.dto;

import jakarta.validation.constraints.NotBlank;

public record AthleteRequest(
    @NotBlank String countryCode,
    @NotBlank String name
) {
    
}
