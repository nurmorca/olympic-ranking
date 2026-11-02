package com.huawei.olympicranking.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.huawei.olympicranking.exception.DuplicateMedalException;
import com.huawei.olympicranking.exception.ResourceNotFoundException;
import com.huawei.olympicranking.repository.AthleteRepository;
import com.huawei.olympicranking.repository.CountryRepository;
import com.huawei.olympicranking.repository.MedalRepository;
import com.huawei.olympicranking.repository.SportRepository;

import jakarta.transaction.Transactional;

@Service
public class MedalServiceImpl implements MedalService {

    private final AthleteRepository athleteRepo;
    private final CountryRepository countryRepo;
    private final MedalRepository medalRepo;
    private final SportRepository sportRepo;

    
    @Autowired
    public MedalServiceImpl(AthleteRepository athleteRepo, CountryRepository countryRepo, MedalRepository medalRepo,
            SportRepository sportRepo) {
        this.athleteRepo = athleteRepo;
        this.countryRepo = countryRepo;
        this.medalRepo = medalRepo;
        this.sportRepo = sportRepo;
    }

    @Override
    public List<MedalResponse> getOverallRankings() {
        List<Medal> initialList = medalRepo.findAll();

        if(initialList == null || initialList.isEmpty()) {
           throw new ResourceNotFoundException(Medal.class.toString());
        }

        Map<String,Map<String,Long>> countList = initialList.stream()
        .collect(
            Collectors.groupingBy(Medal::getCountryCode, 
           Collectors.groupingBy(Medal::getMedalType,
            Collectors.counting())
        ));

        // MAP
        // COUNTRY CODE : TR 
        // SILVER 5, GOLD 3, BRONZE 2

        List<MedalResponse> medalResponseList = new ArrayList<>();
        for (String str : countList.keySet()) {
            MedalResponse medalResponse = new MedalResponse(str, countList.get(str).getOrDefault(Medal.MEDAL_GOLD, 0L), countList.get(str).getOrDefault(Medal.MEDAL_SILVER, 0L),
            countList.get(str).getOrDefault(Medal.MEDAL_BRONZE, 0L));
            medalResponseList.add(medalResponse);
        }

        List<MedalResponse> sortedResponseList = medalResponseList.stream()
        .sorted(Comparator.comparing(
            MedalResponse::goldCount, Comparator.reverseOrder()
        ).thenComparing(MedalResponse::silverCount, Comparator.reverseOrder()).thenComparing(MedalResponse::bronzeCount, Comparator.reverseOrder()).thenComparing(MedalResponse::countryCode)
    ).toList();

       return sortedResponseList;
    }

    @Override
    @Transactional 
    public Medal insertMedal(MedalRequest request) {
        if (medalRepo.existsByAthleteNameAndMedalTypeAndSportName(request.athleteName(), request.medalType(), request.sportName())) {
            throw new DuplicateMedalException();
        }
        Medal medal = new Medal(request.medalType(), request.sportName(), request.countryCode(), request.athleteName());
        return medalRepo.save(medal);
    }

    @Override
    @Transactional 
    public Athlete insertAthlete(AthleteRequest request) {
       Athlete athlete = new Athlete(request.name(), request.countryCode());
       return athleteRepo.save(athlete);
    }

    @Override
    @Transactional 
    public Sport insertSport(SportRequest request) {
       Sport sport = new Sport(request.sportName());
       return sportRepo.save(sport);
    }

    @Override
    @Transactional 
    public Country insertCountry(CountryRequest request) {
        Country country = new Country(request.countryCode(), request.countryName());

        return countryRepo.save(country);
    }

    @Override
    public List<AthleteResponse> getAthleteRankings() {
      List<Athlete> initialList = athleteRepo.findAll();

        if(initialList == null || initialList.isEmpty()) {
           throw new ResourceNotFoundException(Athlete.class.toString());
        }
    
        Map<String,Map<String,Long>> athleteList = initialList.stream()
        .collect(
            Collectors.groupingBy(Athlete::getAthleteName, 
            Collectors.flatMapping(
            ath -> ath.getMedalList().stream(),
            Collectors.groupingBy(
                m -> m.getMedalType(),
                Collectors.counting()))));
        
    List<AthleteResponse> athleteResponseList = new ArrayList<>();
    for (String str : athleteList.keySet()) {
        AthleteResponse response = new AthleteResponse(str, 
            (athleteList.get(str).getOrDefault(Medal.MEDAL_BRONZE, 0L) + athleteList.get(str).getOrDefault(Medal.MEDAL_GOLD, 0L) + athleteList.get(str).getOrDefault(Medal.MEDAL_SILVER, 0L)),
             athleteList.get(str).getOrDefault(Medal.MEDAL_GOLD, 0L),
             athleteList.get(str).getOrDefault(Medal.MEDAL_SILVER, 0L),
             athleteList.get(str).getOrDefault(Medal.MEDAL_BRONZE, 0L)
        );
        athleteResponseList.add(response);
    }
    
    List<AthleteResponse> sortedResponseList = athleteResponseList.stream()
        .sorted(Comparator.comparing(
            AthleteResponse::total, Comparator.reverseOrder()
        ).thenComparing(AthleteResponse::gold, Comparator.reverseOrder()).thenComparing(AthleteResponse::silver, Comparator.reverseOrder()).thenComparing(AthleteResponse::bronze, Comparator.reverseOrder()).thenComparing(AthleteResponse::athleteName)
    ).toList();

      return sortedResponseList;
    }


    @Override
    public List<SportResponse> getSportRankings() {
        List<Sport> initialList = sportRepo.findAll();

         if(initialList == null || initialList.isEmpty()) {
           throw new ResourceNotFoundException(Sport.class.toString());
        }

        Map<String, Long> countList = initialList.stream()
        .collect(
            Collectors.groupingBy(Sport::getSportsName, 
            Collectors.counting()
        ));

        List<SportResponse> sportResponseList = new ArrayList<>();
        for (String str : countList.keySet()) {
            SportResponse response = new SportResponse(countList.get(str), str);
            sportResponseList.add(response);
        }

        List<SportResponse> sortedResponseList = sportResponseList.stream()
            .sorted(Comparator.comparing(SportResponse::totalMedal, Comparator.reverseOrder()).thenComparing(SportResponse::sportName)).toList();


     return sortedResponseList;
    }
    
}
