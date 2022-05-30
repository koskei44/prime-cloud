package com.pm.primeerp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class Items {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int c_quotation_item_id;
    private int m_product_id;
    private String quantity;
    private String amount;
    private boolean isActive = true;
    ArrayList<Integer> taxRates;


    public Items(int c_quotation_item_id, int m_product_id, String quantity, String amount, boolean isActive, ArrayList<Integer> taxRates) {
        this.c_quotation_item_id = c_quotation_item_id;
        this.m_product_id = m_product_id;
        this.quantity = quantity;
        this.amount = amount;
        this.isActive = isActive;
        this.taxRates = taxRates;
    }

    public ArrayList<Integer> getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(ArrayList<Integer> taxRates) {
        this.taxRates = taxRates;
    }

    public int getC_quotation_item_id() {
        return c_quotation_item_id;
    }

    public void setC_quotation_item_id(int c_quotation_item_id) {
        this.c_quotation_item_id = c_quotation_item_id;
    }

    public int getM_product_id() {
        return m_product_id;
    }

    public void setM_product_id(int m_product_id) {
        this.m_product_id = m_product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
