package com.pm.primeerp.data.pojo;



import java.io.Serializable;

public abstract class AuditModel implements Serializable {

    private String createdAt;
    private String updatedAt;
    private boolean active;
    private String approvalStage;
    private boolean isforapproval;
    private boolean processing;
    private boolean processed;
    private boolean isapproved;
    private boolean rejected;
    private boolean ammend;
    private Long createdBy;
    private Long updatedBy;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getApprovalStage() {
        return approvalStage;
    }

    public void setApprovalStage(String approvalStage) {
        this.approvalStage = approvalStage;
    }

    public boolean isIsforapproval() {
        return isforapproval;
    }

    public void setIsforapproval(boolean isforapproval) {
        this.isforapproval = isforapproval;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isIsapproved() {
        return isapproved;
    }

    public void setIsapproved(boolean isapproved) {
        this.isapproved = isapproved;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public boolean isAmmend() {
        return ammend;
    }

    public void setAmmend(boolean ammend) {
        this.ammend = ammend;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }
}
