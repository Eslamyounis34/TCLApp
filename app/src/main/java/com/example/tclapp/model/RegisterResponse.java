package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private User user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
