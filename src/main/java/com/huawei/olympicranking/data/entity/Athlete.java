package com.huawei.olympicranking.data.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable=false)
    private Integer id;

    @Column(name="athlete_name", nullable=false)
    private String athleteName;

    @Column(name="country", nullable=false)
    private String countryCode;

    @JoinColumn(name="country", referencedColumnName="code", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @OneToMany(mappedBy = "athlete", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Medal> medalList;

    public Athlete() {
    }


    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Athlete(String athleteName, String countryCode) {
        this.athleteName = athleteName;
        this.countryCode = countryCode;
    }



    public List<Medal> getMedalList() {
        return medalList;
    }

    public void setMedalList(List<Medal> medalList) {
        this.medalList = medalList;
    }

}
