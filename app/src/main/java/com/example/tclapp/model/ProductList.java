package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductList {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
