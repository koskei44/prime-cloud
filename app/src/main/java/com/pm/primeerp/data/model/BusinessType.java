package com.pm.primeerp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class BusinessType {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int businessType_id;
    private String name;
    private  String  code;
    private  String  nationality;


}
