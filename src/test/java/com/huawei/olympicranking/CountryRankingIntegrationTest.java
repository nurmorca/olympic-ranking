package com.huawei.olympicranking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.huawei.olympicranking.data.entity.Athlete;
import com.huawei.olympicranking.data.entity.Country;
import com.huawei.olympicranking.data.entity.Medal;
import com.huawei.olympicranking.data.entity.Sport;
import com.huawei.olympicranking.repository.AthleteRepository;
import com.huawei.olympicranking.repository.CountryRepository;
import com.huawei.olympicranking.repository.MedalRepository;
import com.huawei.olympicranking.repository.SportRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional  
public class CountryRankingIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private CountryRepository countryRepository;
    @Autowired private AthleteRepository athleteRepository;
    @Autowired private MedalRepository medalRepository;
    @Autowired private SportRepository sportRepository;

     @Test
    void shouldReturnCountriesRankedByGoldFirst() throws Exception {
        // Given: USA (2G, 1S), TUR (1G, 3S)
        Country usa = countryRepository.save(new Country("TUR", "Turkiye"));
        Country tur = countryRepository.save(new Country("USA", "United States of America"));

        Sport swimming = sportRepository.save(new Sport("swimming"));

        Athlete a1 = athleteRepository.save(new Athlete("Morca", "TUR"));
        Athlete a2 = athleteRepository.save(new Athlete("Nur", "USA"));

        medalRepository.save(new Medal(Medal.MEDAL_GOLD, "swimming", "TUR", "Morca"));
        medalRepository.save(new Medal(Medal.MEDAL_SILVER, "swimming", "TUR", "Morca"));

        medalRepository.save(new Medal(Medal.MEDAL_SILVER, "swimming", "USA", "Nur"));
        medalRepository.save(new Medal(Medal.MEDAL_BRONZE, "swimming", "USA", "Nur"));

        mockMvc.perform(get("/medals/ranking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].countryCode").value("TUR"))
                .andExpect(jsonPath("$[0].goldCount").value(1))
                .andExpect(jsonPath("$[1].countryCode").value("USA"))
                .andExpect(jsonPath("$[1].goldCount").value(0));
    }
    
}
