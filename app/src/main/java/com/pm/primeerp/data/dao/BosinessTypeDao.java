package com.pm.primeerp.data.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pm.primeerp.data.model.BusinessType;
import com.pm.primeerp.data.pojo.Data;

import java.util.List;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Dao
public interface BosinessTypeDao {
    @Insert(onConflict = REPLACE)
    long insertRecords(Data data);

    @Query("SELECT * FROM data")
    List<Data> getBusinessType();

    @Query("DELETE FROM data")
    void deleteall();

    @Query("SELECT * FROM data WHERE name= :name")
    List<Data> getbyName(String name);
}

