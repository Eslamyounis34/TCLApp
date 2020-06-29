package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductsParent {
    @SerializedName("data")
    private ArrayList<ProductsModel> productmodelList;

    public ArrayList<ProductsModel> getProductmodelList() {
        return productmodelList;
    }

    public void setProductmodelList(ArrayList<ProductsModel> productmodelList) {
        this.productmodelList = productmodelList;
    }
}
