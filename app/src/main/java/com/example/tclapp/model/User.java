package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("personal_phone")
    private String mobile;
    @SerializedName("password")
    private String pass;
    @SerializedName("id")
    private String id;
    @SerializedName("user_image")
    private String user_image;

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public User(String name, String email, String mobile, String pass) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.pass = pass;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
