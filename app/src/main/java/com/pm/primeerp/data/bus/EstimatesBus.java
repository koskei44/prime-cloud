package com.pm.primeerp.data.bus;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class EstimatesBus {
    private static EstimatesBus single_instance = null;

    private EstimatesBus() {
    }

    public static EstimatesBus getInstance() {
        if (single_instance == null)
            single_instance = new EstimatesBus();

        return single_instance;
    }

    public static EstimatesBus getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(EstimatesBus single_instance) {
        EstimatesBus.single_instance = single_instance;
    }

    private String name;
    private String value;
    private String quotationdate;
    private String expirydate;
    private String description;
    private boolean isActive = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getQuotationdate() {
        return quotationdate;
    }

    public void setQuotationdate(String quotationdate) {
        this.quotationdate = quotationdate;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
