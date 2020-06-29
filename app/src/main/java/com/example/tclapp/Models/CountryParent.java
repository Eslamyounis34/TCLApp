package com.example.tclapp.Models;

import com.google.gson.annotations.SerializedName;

public class CountryParent {
    @SerializedName("data")
    private CountryData cParent;

    public CountryData getcParent() {
        return cParent;
    }

    public void setcParent(CountryData cParent) {
        this.cParent = cParent;
    }
}
