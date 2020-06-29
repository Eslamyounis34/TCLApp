package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

public class UserLogin {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String pass;

    public UserLogin(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
