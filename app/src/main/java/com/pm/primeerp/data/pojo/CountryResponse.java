package com.pm.primeerp.data.pojo;


import java.util.ArrayList;

public class CountryResponse {
    private String success;
    private String message;
    private ArrayList<CountryData> data;

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

    public ArrayList<CountryData> getData() {
        return data;
    }

    public void setData(ArrayList<CountryData> data) {
        this.data = data;
    }
}
