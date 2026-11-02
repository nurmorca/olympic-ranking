package com.huawei.olympicranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huawei.olympicranking.data.entity.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Integer>{
    
}
