package com.pm.primeerp.data.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class Data extends AuditModel{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ad_businesstype_id;
    private String value;
    private String name;
    private String description;

    public int getAd_businesstype_id() {
        return ad_businesstype_id;
    }

    public void setAd_businesstype_id(int ad_businesstype_id) {
        this.ad_businesstype_id = ad_businesstype_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
}
