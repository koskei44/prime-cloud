package com.pm.primeerp.data.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class CurrencyData extends AuditModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int c_currency_id;
    private String description;
    private String isoCode;
    private String cursymbol;

    public int getC_currency_id() {
        return c_currency_id;
    }

    public void setC_currency_id(int c_currency_id) {
        this.c_currency_id = c_currency_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getCursymbol() {
        return cursymbol;
    }

    public void setCursymbol(String cursymbol) {
        this.cursymbol = cursymbol;
    }
}
