package com.pm.primeerp.data.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pm.primeerp.data.model.Currency;
import com.pm.primeerp.data.pojo.CurrencyData;

import java.util.List;


@Dao
public interface CurrencyDao {
    @Insert(onConflict = REPLACE)
    long insertCurrency(CurrencyData currencyData);

    @Query("SELECT * FROM currencyData")
    List<CurrencyData> getCurrencies();

    @Query("DELETE FROM currencyData")
    void deleteall();

    @Query("SELECT * FROM currencyData WHERE description= :name")
    List<CurrencyData> getbyName(String name);
}
