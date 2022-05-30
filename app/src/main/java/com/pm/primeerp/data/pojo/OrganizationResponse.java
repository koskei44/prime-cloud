package com.pm.primeerp.data.pojo;

import java.util.ArrayList;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class OrganizationResponse {
    private String success;
    private String message;
    private ArrayList<OrganizationData> data;

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

    public ArrayList<OrganizationData> getData() {
        return data;
    }

    public void setData(ArrayList<OrganizationData> data) {
        this.data = data;
    }
}
