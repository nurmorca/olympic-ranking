package com.huawei.olympicranking.data.dto;

import jakarta.validation.constraints.NotBlank;

public record MedalRequest(
    @NotBlank String medalType,
    @NotBlank String countryCode,
    @NotBlank String athleteName,
    @NotBlank String sportName
){
    
}
