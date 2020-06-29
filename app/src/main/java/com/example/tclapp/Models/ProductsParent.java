package com.example.tclapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductsParent {
    @SerializedName("data")
    private ArrayList<ProdcutsModel> productmodelList;

    public ArrayList<ProdcutsModel> getProductmodelList() {
        return productmodelList;
    }

    public void setProductmodelList(ArrayList<ProdcutsModel> productmodelList) {
        this.productmodelList = productmodelList;
    }
}
