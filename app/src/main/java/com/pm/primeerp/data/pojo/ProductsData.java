package com.pm.primeerp.data.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class ProductsData extends AuditModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int products_id;
    private String value;
    private String name;
    private String description;

    public int getProducts_id() {
        return products_id;
    }

    public void setProducts_id(int products_id) {
        this.products_id = products_id;
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
