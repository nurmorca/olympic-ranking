package com.huawei.olympicranking.data.dto;

public record MedalResponse(
    String countryCode,
    Long goldCount,
    Long silverCount,
    Long bronzeCount
) {
    
}
