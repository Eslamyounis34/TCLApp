package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

public class LoggedUserData {
    @SerializedName("status")
    private String status;

    @SerializedName("0")
    private User data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
