package com.huawei.olympicranking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.huawei.olympicranking.service.MedalService;
import com.huawei.olympicranking.service.MedalServiceImpl;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/medals")
public class MedalController {

    private final MedalService service;

    @Autowired 
    public MedalController(MedalServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<MedalResponse>> getAllRankings() {
        List<MedalResponse> response = service.getOverallRankings();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/athlete-rankings")
    public ResponseEntity<List<AthleteResponse>> getAthleteRankings() {
        List<AthleteResponse> response = service.getAthleteRankings();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sport-rankings")
    public ResponseEntity<List<SportResponse>> getSportRankings() {
        List<SportResponse> response = service.getSportRankings();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/country")
    public ResponseEntity<Country> addCountry(@Valid @RequestBody CountryRequest request) {
        Country country = service.insertCountry(request);
        return ResponseEntity.ok(country);
    }

    @PostMapping("/sport")
    public ResponseEntity<Sport> addSport(@Valid @RequestBody SportRequest request) {
        Sport sport = service.insertSport(request);
        return ResponseEntity.ok(sport);
    }

    @PostMapping("/medal")
    public ResponseEntity<Medal> addMedal(@Valid @RequestBody MedalRequest request) {
        Medal medal = service.insertMedal(request);
        return ResponseEntity.ok(medal);
    }

    @PostMapping("/athlete")
    public ResponseEntity<Athlete> addAthlete(@Valid @RequestBody AthleteRequest request) {
        Athlete athlete = service.insertAthlete(request);
        return ResponseEntity.ok(athlete);
    }
    
}
