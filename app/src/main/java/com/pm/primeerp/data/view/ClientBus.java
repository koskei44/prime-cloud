package com.pm.primeerp.data.view;

public class ClientBus {

    private static ClientBus single_instance = null;

    private String name;
    private String age;
    private String sex;
    private String nhif;
    private String national_id;
    private String telephone;
    private String county;
    private String address;

    private boolean isPhotoTaken;

    private String kin_name;
    private String kin_telephone;
    private String kin_relationship;


    private ClientBus() {
    }

    public static ClientBus getInstance() {
        if (single_instance == null)
            single_instance = new ClientBus();

        return single_instance;
    }

    public static ClientBus getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(ClientBus single_instance) {
        ClientBus.single_instance = single_instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNhif() {
        return nhif;
    }

    public void setNhif(String nhif) {
        this.nhif = nhif;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPhotoTaken() {
        return isPhotoTaken;
    }

    public void setPhotoTaken(boolean photoTaken) {
        isPhotoTaken = photoTaken;
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
}
