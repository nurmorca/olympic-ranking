package com.huawei.olympicranking.service;

import java.util.List;

import com.huawei.olympicranking.data.dto.AthleteRequest;
import com.huawei.olympicranking.data.dto.AthleteResponse;
import com.huawei.olympicranking.data.dto.CountryRequest;
import com.huawei.olympicranking.data.dto.MedalRequest;
import com.huawei.olympicranking.data.dto.MedalResponse;
import com.huawei.olympicranking.data.dto.SportRequest;
import com.huawei.olympicranking.data.dto.SportResponse;
import com.huawei.olympicranking.data.entity.Athlete;
import com.huawei.olympicranking.data.entity.Country;
import com.huawei.olympicranking.data.entity.Medal;
import com.huawei.olympicranking.data.entity.Sport;

public interface MedalService {

    List<MedalResponse> getOverallRankings();

    //added later based on documentation
    List<AthleteResponse> getAthleteRankings();
    List<SportResponse> getSportRankings();

    Medal insertMedal(MedalRequest request);
    Athlete insertAthlete(AthleteRequest request);
    Sport insertSport(SportRequest request);
    Country insertCountry(CountryRequest request);
}
