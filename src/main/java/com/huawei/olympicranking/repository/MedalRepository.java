package com.huawei.olympicranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huawei.olympicranking.data.entity.Medal;

@Repository
public interface MedalRepository extends JpaRepository<Medal, Integer>{

    boolean existsByAthleteNameAndMedalTypeAndSportName(String athleteName, String medalType, String sportName);
    
}
