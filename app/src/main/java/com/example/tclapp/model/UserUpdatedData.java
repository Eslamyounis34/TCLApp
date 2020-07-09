package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

public class UserUpdatedData {

    @SerializedName("id")
    private String id;
    @SerializedName("user_image")
    private String userImage;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("personal_phone")
    private String personalPhone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }
}
