package com.pm.primeerp.data.pojo;

import java.util.ArrayList;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class Summary {
    private int id;
    private String email;
    private String username;
    private String name;
    private ArrayList<Roles> roles;
    private ArrayList<Organizations> organizations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Roles> getRoles() {
        return roles;
    }

    public ArrayList<Organizations> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(ArrayList<Organizations> organizations) {
        this.organizations = organizations;
    }

    public void setRoles(ArrayList<Roles> roles) {
        this.roles = roles;
    }
}
