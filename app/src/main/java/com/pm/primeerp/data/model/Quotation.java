package com.pm.primeerp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class Quotation {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private int c_quotation_id;
        private String name;
        private String value;
        private String quotationdate;
        private String expirydate;
        private String description;
        private boolean isActive = true;
        ArrayList<Items> items;

        public Quotation(int c_quotation_id, String name, String value, String quotationdate, String expirydate, String description, boolean isActive, ArrayList<Items> items) {
                this.c_quotation_id = c_quotation_id;
                this.name = name;
                this.value = value;
                this.quotationdate = quotationdate;
                this.expirydate = expirydate;
                this.description = description;
                this.isActive = isActive;
                this.items = items;
        }

        public int getC_quotation_id() {
                return c_quotation_id;
        }

        public void setC_quotation_id(int c_quotation_id) {
                this.c_quotation_id = c_quotation_id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getValue() {
                return value;
        }

        public void setValue(String value) {
                this.value = value;
        }

        public String getQuotationdate() {
                return quotationdate;
        }

        public void setQuotationdate(String quotationdate) {
                this.quotationdate = quotationdate;
        }

        public String getExpirydate() {
                return expirydate;
        }

        public void setExpirydate(String expirydate) {
                this.expirydate = expirydate;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public boolean isActive() {
                return isActive;
        }

        public void setActive(boolean active) {
                isActive = active;
        }

        public ArrayList<Items> getItems() {
                return items;
        }

        public void setItems(ArrayList<Items> items) {
                this.items = items;
        }
}
