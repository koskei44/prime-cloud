package com.pm.primeerp.data.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pm.primeerp.data.pojo.ProductsData;
import com.pm.primeerp.data.pojo.TaxRatesData;

import java.util.List;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Dao
public interface TaxRatesDao {
    @Insert(onConflict = REPLACE)
    long insertpTaxRatesData(TaxRatesData taxRatesData);

    @Query("SELECT * FROM taxRatesData")
    List<TaxRatesData> getptaxRatesData();

    @Query("DELETE FROM taxRatesData")
    void deleteall();

    @Query("SELECT * FROM taxRatesData WHERE name= :name")
    List<TaxRatesData> getbyName(String name);
}
