package com.huawei.olympicranking.data.dto;

import jakarta.validation.constraints.NotBlank;

public record SportRequest(
    @NotBlank String sportName
) {
    
}
