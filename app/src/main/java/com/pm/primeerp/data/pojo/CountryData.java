package com.pm.primeerp.data.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class CountryData extends AuditModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int c_country_id;
    private String name;
    private String description;
    private String countrycode;
    private String language;

    public int getC_country_id() {
        return c_country_id;
    }

    public void setC_country_id(int c_country_id) {
        this.c_country_id = c_country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
