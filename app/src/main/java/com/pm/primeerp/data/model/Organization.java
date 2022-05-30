package com.pm.primeerp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
@Entity
public class Organization {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int organizationId;
    private String value;
    private String name;
    private String description;
    private int clientId;
    private int businessTypeId;
    private int  countryId;
    private int orgTypeId;
    private int currencyId;
    private String city;
    private String email;
    private String postalAddress;
    private String phone;
    private String website;
    private String logoUrl;
    private String addressLineOne;
    private String addressLineTwo;
    private boolean active =true;


    public Organization(int organizationId, String value, String name, String description, int clientId, int businessTypeId, int countryId, int orgTypeId, int currencyId, String city, String email, String postalAddress, String phone, String website, String logoUrl, String addressLineOne, String addressLineTwo, boolean active) {
        this.organizationId = organizationId;
        this.value = value;
        this.name = name;
        this.description = description;
        this.clientId = clientId;
        this.businessTypeId = businessTypeId;
        this.countryId = countryId;
        this.orgTypeId = orgTypeId;
        this.currencyId = currencyId;
        this.city = city;
        this.email = email;
        this.postalAddress = postalAddress;
        this.phone = phone;
        this.website = website;
        this.logoUrl = logoUrl;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(int businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getOrgTypeId() {
        return orgTypeId;
    }

    public void setOrgTypeId(int orgTypeId) {
        this.orgTypeId = orgTypeId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
