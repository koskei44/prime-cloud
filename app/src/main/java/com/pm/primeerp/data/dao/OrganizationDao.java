package com.pm.primeerp.data.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import com.pm.primeerp.data.pojo.CountryData;
import com.pm.primeerp.data.pojo.OrganizationData;

import java.util.List;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Dao
public interface OrganizationDao {
    @Insert(onConflict = REPLACE)
    long insertOrganization(OrganizationData organizationData);

    @Query("SELECT * FROM organizationData")
    List<OrganizationData> getOrganizationData();

    @Query("DELETE FROM organizationData")
    void deleteall();

    @Query("SELECT * FROM organizationData WHERE name= :name")
    List<OrganizationData> getbyName(String name);
}
