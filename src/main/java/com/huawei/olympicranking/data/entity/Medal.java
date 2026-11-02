package com.huawei.olympicranking.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="medal")
public class Medal {

    public static final String MEDAL_GOLD = "GOLD";
    public static final String MEDAL_SILVER = "SILVER";
    public static final String MEDAL_BRONZE = "BRONZE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable=false)
    private Integer id;

    @Column(name="medal_type", nullable=false)
    private String medalType;

    @Column(name="sport_name", nullable=false)
    private String sportName;

    @Column(name="country_code", nullable=false)
    private String countryCode;

    @Column(name="athlete", nullable=false)
    private String athleteName;

    @JoinColumn(name="sport_name", referencedColumnName="name", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Sport sport;

    @JoinColumn(name="country_code", referencedColumnName="code", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @JoinColumn(name="athlete", referencedColumnName="athlete_name", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Athlete athlete;

   
    public Medal() {
    }

    public Medal(String medalType, String sportName, String countryCode, String athleteName) {
        this.medalType = medalType;
        this.sportName = sportName;
        this.countryCode = countryCode;
        this.athleteName = athleteName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedalType() {
        return medalType;
    }

    public void setMedalType(String medalType) {
        this.medalType = medalType;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athlete_name) {
        this.athleteName = athlete_name;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    

    
}
