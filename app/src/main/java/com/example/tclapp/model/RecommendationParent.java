package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecommendationParent {
    @SerializedName("RecommendationsActivity")
    private RecommendationParent parent;
    @SerializedName("data")
    private ArrayList<RecommendationModel> modelList;

    public RecommendationParent getParent() {
        return parent;
    }

    public void setParent(RecommendationParent parent) {
        this.parent = parent;
    }

    public ArrayList<RecommendationModel> getModelList() {
        return modelList;
    }

    public void setModelList(ArrayList<RecommendationModel> modelList) {
        this.modelList = modelList;
    }
}
