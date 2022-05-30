package com.pm.primeerp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int productId;
    private int organizationId;
    private int productCategoryId;
    private String value;
    private String name;
    private String description;
    private boolean isstocked;
    private boolean ispurchased;
    private boolean issold;
    private boolean isbom;
    private ArrayList<Integer> taxRates;
    private int currencyId;
    private int price;
    private boolean active;


    public Product(int productId, int organizationId, int productCategoryId, String value, String name, String description, boolean isstocked, boolean ispurchased, boolean issold, boolean isbom, ArrayList<Integer> taxRates, int currencyId, int price, boolean active) {
        this.productId = productId;
        this.organizationId = organizationId;
        this.productCategoryId = productCategoryId;
        this.value = value;
        this.name = name;
        this.description = description;
        this.isstocked = isstocked;
        this.ispurchased = ispurchased;
        this.issold = issold;
        this.isbom = isbom;
        this.taxRates = taxRates;
        this.currencyId = currencyId;
        this.price = price;
        this.active = active;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
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

    public boolean isIsstocked() {
        return isstocked;
    }

    public void setIsstocked(boolean isstocked) {
        this.isstocked = isstocked;
    }

    public boolean isIspurchased() {
        return ispurchased;
    }

    public void setIspurchased(boolean ispurchased) {
        this.ispurchased = ispurchased;
    }

    public boolean isIssold() {
        return issold;
    }

    public void setIssold(boolean issold) {
        this.issold = issold;
    }

    public boolean isIsbom() {
        return isbom;
    }

    public void setIsbom(boolean isbom) {
        this.isbom = isbom;
    }

    public ArrayList<Integer> getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(ArrayList<Integer> taxRates) {
        this.taxRates = taxRates;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
