package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MaterialsParent {
    @SerializedName("RecommendationsMaterial")
    private MaterialsParent mparent;
    @SerializedName("data")
    private ArrayList<MaterialsModel> materialmodelList;

    public MaterialsParent getMparent() {
        return mparent;
    }

    public void setMparent(MaterialsParent mparent) {
        this.mparent = mparent;
    }

    public ArrayList<MaterialsModel> getMaterialmodelList() {
        return materialmodelList;
    }

    public void setMaterialmodelList(ArrayList<MaterialsModel> materialmodelList) {
        this.materialmodelList = materialmodelList;
    }
}
