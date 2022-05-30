package com.pm.primeerp.data.pojo;

import java.util.ArrayList;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class CurrencyReponse {
    private String success;
    private String message;
    private ArrayList<CurrencyData> data;


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

    public ArrayList<CurrencyData> getData() {
        return data;
    }

    public void setData(ArrayList<CurrencyData> data) {
        this.data = data;
    }
}
