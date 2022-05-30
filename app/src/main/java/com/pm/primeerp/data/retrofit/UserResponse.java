package com.pm.primeerp.data.retrofit;

public class UserResponse {

    private String user_id;
    private String email;
    private String token;
    private String firstName;
    private String lastName;

    public UserResponse(String user_id, String email, String token, String firstName, String lastName) {
        this.user_id = user_id;
        this.email = email;
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
