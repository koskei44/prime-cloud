package com.pm.primeerp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ClientInfo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int client_id;
    private int physician_id;
    private String client_name;
    private String client_age;
    private String client_sex;
    private String client_nhif;
    private String client_national_id;
    private String client_telephone;
    private String client_county;
    private String client_address;

    private boolean client_photo;

    private String kin_name;
    private String kin_telephone;
    private String kin_relationship;

    private String facility_name;
    private String facility_address;

    private String latitude;
    private String longitude;

    private String remote_id;
    private boolean is_synced;

    public ClientInfo(int client_id, int physician_id, String client_name, String client_age, String client_sex, String client_nhif, String client_national_id, String client_telephone, String client_county, String client_address, boolean client_photo, String kin_name, String kin_telephone, String kin_relationship, String facility_name, String facility_address, String latitude, String longitude, String remote_id, boolean is_synced) {
        this.client_id = client_id;
        this.physician_id = physician_id;
        this.client_name = client_name;
        this.client_age = client_age;
        this.client_sex = client_sex;
        this.client_nhif = client_nhif;
        this.client_national_id = client_national_id;
        this.client_telephone = client_telephone;
        this.client_county = client_county;
        this.client_address = client_address;
        this.client_photo = client_photo;
        this.kin_name = kin_name;
        this.kin_telephone = kin_telephone;
        this.kin_relationship = kin_relationship;
        this.facility_name = facility_name;
        this.facility_address = facility_address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.remote_id = remote_id;
        this.is_synced = is_synced;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getPhysician_id() {
        return physician_id;
    }

    public void setPhysician_id(int physician_id) {
        this.physician_id = physician_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_age() {
        return client_age;
    }

    public void setClient_age(String client_age) {
        this.client_age = client_age;
    }

    public String getClient_sex() {
        return client_sex;
    }

    public void setClient_sex(String client_sex) {
        this.client_sex = client_sex;
    }

    public String getClient_nhif() {
        return client_nhif;
    }

    public void setClient_nhif(String client_nhif) {
        this.client_nhif = client_nhif;
    }

    public String getClient_national_id() {
        return client_national_id;
    }

    public void setClient_national_id(String client_national_id) {
        this.client_national_id = client_national_id;
    }

    public String getClient_telephone() {
        return client_telephone;
    }

    public void setClient_telephone(String client_telephone) {
        this.client_telephone = client_telephone;
    }

    public String getClient_county() {
        return client_county;
    }

    public void setClient_county(String client_county) {
        this.client_county = client_county;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public boolean isClient_photo() {
        return client_photo;
    }

    public void setClient_photo(boolean client_photo) {
        this.client_photo = client_photo;
    }

    public String getKin_name() {
        return kin_name;
    }

    public void setKin_name(String kin_name) {
        this.kin_name = kin_name;
    }

    public String getKin_telephone() {
        return kin_telephone;
    }

    public void setKin_telephone(String kin_telephone) {
        this.kin_telephone = kin_telephone;
    }

    public String getKin_relationship() {
        return kin_relationship;
    }

    public void setKin_relationship(String kin_relationship) {
        this.kin_relationship = kin_relationship;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    public String getFacility_address() {
        return facility_address;
    }

    public void setFacility_address(String facility_address) {
        this.facility_address = facility_address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRemote_id() {
        return remote_id;
    }

    public void setRemote_id(String remote_id) {
        this.remote_id = remote_id;
    }

    public boolean isIs_synced() {
        return is_synced;
    }

    public void setIs_synced(boolean is_synced) {
        this.is_synced = is_synced;
    }
}
