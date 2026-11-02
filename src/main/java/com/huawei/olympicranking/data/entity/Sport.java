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
@Table(name="sports")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable=false)
    private Integer id;

    @Column(name="name", nullable=false)
    private String sportsName;

    @OneToMany(mappedBy = "sport", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Medal> medalList;

    public Sport() {
    }

    public Sport(String sportsName) {
        this.sportsName = sportsName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public List<Medal> getMedalList() {
        return medalList;
    }

    public void setMedalList(List<Medal> medalList) {
        this.medalList = medalList;
    }
    
}
