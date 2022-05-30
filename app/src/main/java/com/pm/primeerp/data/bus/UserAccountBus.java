package com.pm.primeerp.data.bus;

import com.pm.primeerp.data.view.ClientBus;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class UserAccountBus {
    private static UserAccountBus single_instance = null;

    private UserAccountBus() {
    }

    public static UserAccountBus getInstance() {
        if (single_instance == null)
            single_instance = new UserAccountBus();

        return single_instance;
    }

    public static UserAccountBus getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(UserAccountBus single_instance) {
        UserAccountBus.single_instance = single_instance;
    }



    private int businesstype;
    private int organizationType;
    private int currency;
    private int country;
    private String companyname;
    private String abbrvalue;
    private String tel;
    private String email;
    private String postaladdres;
    private String city;
    private String description;

    private String useremail;
    private String firstname;
    private String lastname;
    private String telephoneno;

    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephoneno() {
        return telephoneno;
    }

    public void setTelephoneno(String telephoneno) {
        this.telephoneno = telephoneno;
    }

    public int getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(int businesstype) {
        this.businesstype = businesstype;
    }

    public int getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(int organizationType) {
        this.organizationType = organizationType;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAbbrvalue() {
        return abbrvalue;
    }

    public void setAbbrvalue(String abbrvalue) {
        this.abbrvalue = abbrvalue;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostaladdres() {
        return postaladdres;
    }

    public void setPostaladdres(String postaladdres) {
        this.postaladdres = postaladdres;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
