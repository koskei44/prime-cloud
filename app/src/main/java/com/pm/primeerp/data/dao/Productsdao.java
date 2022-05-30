package com.pm.primeerp.data.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pm.primeerp.data.pojo.OrganizationData;
import com.pm.primeerp.data.pojo.ProductsData;

import java.util.List;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Dao
public interface Productsdao {
    @Insert(onConflict = REPLACE)
    long insertproductsData(ProductsData productsData);

    @Query("SELECT * FROM productsData")
    List<ProductsData> getproductsData();

    @Query("DELETE FROM productsData")
    void deleteall();

    @Query("SELECT * FROM productsData WHERE name= :name")
    List<ProductsData> getbyName(String name);
}
