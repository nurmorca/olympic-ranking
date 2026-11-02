package com.huawei.olympicranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huawei.olympicranking.data.entity.Athlete;

@Repository 
public interface AthleteRepository extends JpaRepository<Athlete, Integer>{
    
}
