package com.pm.primeerp.data.pojo;

import java.util.ArrayList;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class RaxRatesResponse {
    private String success;
    private String message;
    private ArrayList<TaxRatesData> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<TaxRatesData> getData() {
        return data;
    }

    public void setData(ArrayList<TaxRatesData> data) {
        this.data = data;
    }
}
