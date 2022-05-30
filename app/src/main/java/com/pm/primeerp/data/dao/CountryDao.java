package com.pm.primeerp.data.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pm.primeerp.data.model.Country;
import com.pm.primeerp.data.pojo.CountryData;

import java.util.List;


@Dao
public interface CountryDao {
    @Insert(onConflict = REPLACE)
    long insertCountry(CountryData countryData);

    @Query("SELECT * FROM countryData")
    List<CountryData> getCountries();

    @Query("DELETE FROM countryData")
    void deleteall();

    @Query("SELECT * FROM countryData WHERE name= :name")
    List<CountryData> getbyName(String name);
}
