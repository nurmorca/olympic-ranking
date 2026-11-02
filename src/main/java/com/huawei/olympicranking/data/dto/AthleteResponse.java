package com.huawei.olympicranking.data.dto;

public record AthleteResponse(
    String athleteName,
    Long total,
    Long gold,
    Long silver,
    Long bronze
) {
    
}
