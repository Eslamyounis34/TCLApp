package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private UserUpdatedData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserUpdatedData getData() {
        return data;
    }

    public void setData(UserUpdatedData data) {
        this.data = data;
    }
}
