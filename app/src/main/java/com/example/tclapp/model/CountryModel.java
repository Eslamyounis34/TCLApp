package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    @SerializedName("code")
    private  String code;
    @SerializedName("name")
    private  String name;
    @SerializedName("email")
    private  String email;
    @SerializedName("phone")
    private  String phone;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
