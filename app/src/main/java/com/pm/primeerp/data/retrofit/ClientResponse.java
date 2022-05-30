package com.pm.primeerp.data.retrofit;

public class ClientResponse {

    private String physician_id;
    private String kin_telephone;
    private String kin_relationship;
    private String client_nhif;
    private String client_sex;
    private String created_at;
    private String active;
    private String client_national_id;
    private String facility_name;
    private String created_by;
    private String client_id;
    private String record_id;
    private String client_age;
    private String facility_address;
    private String kin_name;
    private String client_telephone;
    private String client_county;
    private String client_address;
    private String client_name;

    public ClientResponse(String physician_id, String kin_telephone, String kin_relationship, String client_nhif, String client_sex, String created_at, String active, String client_national_id, String facility_name, String created_by, String client_id, String record_id, String client_age, String facility_address, String kin_name, String client_telephone, String client_county, String client_address, String client_name) {
        this.physician_id = physician_id;
        this.kin_telephone = kin_telephone;
        this.kin_relationship = kin_relationship;
        this.client_nhif = client_nhif;
        this.client_sex = client_sex;
        this.created_at = created_at;
        this.active = active;
        this.client_national_id = client_national_id;
        this.facility_name = facility_name;
        this.created_by = created_by;
        this.client_id = client_id;
        this.record_id = record_id;
        this.client_age = client_age;
        this.facility_address = facility_address;
        this.kin_name = kin_name;
        this.client_telephone = client_telephone;
        this.client_county = client_county;
        this.client_address = client_address;
        this.client_name = client_name;
    }

    public String getPhysician_id() {
        return physician_id;
    }

    public void setPhysician_id(String physician_id) {
        this.physician_id = physician_id;
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

    public String getClient_nhif() {
        return client_nhif;
    }

    public void setClient_nhif(String client_nhif) {
        this.client_nhif = client_nhif;
    }

    public String getClient_sex() {
        return client_sex;
    }

    public void setClient_sex(String client_sex) {
        this.client_sex = client_sex;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getClient_national_id() {
        return client_national_id;
    }

    public void setClient_national_id(String client_national_id) {
        this.client_national_id = client_national_id;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getClient_age() {
        return client_age;
    }

    public void setClient_age(String client_age) {
        this.client_age = client_age;
    }

    public String getFacility_address() {
        return facility_address;
    }

    public void setFacility_address(String facility_address) {
        this.facility_address = facility_address;
    }

    public String getKin_name() {
        return kin_name;
    }

    public void setKin_name(String kin_name) {
        this.kin_name = kin_name;
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

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}
