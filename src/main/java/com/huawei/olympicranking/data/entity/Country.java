package com.huawei.olympicranking.data.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable=false)
    private Integer id;

    @Column(name="code", nullable=false)
    private String countryCode;

    @Column(name="name", nullable=false)
    private String countryName;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    @JsonIgnore   
    private List<Athlete> athleteList;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    @JsonIgnore   
    private List<Medal> medalList;


  public Country() {
    
    }

    public Country(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Athlete> getAthleteList() {
        return athleteList;
    }

    public void setAthleteList(List<Athlete> athleteList) {
        this.athleteList = athleteList;
    }
}
