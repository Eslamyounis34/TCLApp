package com.example.tclapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecommendationParent {
    @SerializedName("RecommendationsActivity")
    private RecommendationParent parent;
    @SerializedName("data")
    private ArrayList<RecommenctaionModel> modelList;

    public RecommendationParent getParent() {
        return parent;
    }

    public void setParent(RecommendationParent parent) {
        this.parent = parent;
    }

    public ArrayList<RecommenctaionModel> getModelList() {
        return modelList;
    }

    public void setModelList(ArrayList<RecommenctaionModel> modelList) {
        this.modelList = modelList;
    }
}
