package com.example.tclapp.model;

import com.example.tclapp.Activities.ProductAllDetails;
import com.google.gson.annotations.SerializedName;

public class SelectedProduct {
    @SerializedName("product")
    private AllProductDetails allDetails;

    public AllProductDetails getAllDetails() {
        return allDetails;
    }

    public void setAllDetails(AllProductDetails allDetails) {
        this.allDetails = allDetails;
    }
}
