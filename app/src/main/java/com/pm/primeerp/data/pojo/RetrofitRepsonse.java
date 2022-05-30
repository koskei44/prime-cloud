package com.pm.primeerp.data.pojo;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class RetrofitRepsonse {
    private String success;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
