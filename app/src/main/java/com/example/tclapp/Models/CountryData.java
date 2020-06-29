package com.example.tclapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryData {
    public ArrayList<CountryModel> getCountrymodelList() {
        return CountrymodelList;
    }

    public void setCountrymodelList(ArrayList<CountryModel> countrymodelList) {
        CountrymodelList = countrymodelList;
    }

    @SerializedName("data")
    private ArrayList<CountryModel> CountrymodelList= null;
}
