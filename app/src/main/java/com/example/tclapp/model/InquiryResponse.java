package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

public class InquiryResponse {
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
