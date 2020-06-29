package com.example.tclapp.model;

import com.example.tclapp.Activities.ProcedureActivity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProcedureParent {

    @SerializedName("ProceduresActivity")
    private ProcedureParent parent;
    @SerializedName("data")
    private List<ProcedureModel>modelList;


    public ProcedureParent getParent() {
        return parent;
    }

    public void setParent(ProcedureParent parent) {
        this.parent = parent;
    }

    public List<ProcedureModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<ProcedureModel> modelList) {
        this.modelList = modelList;
    }
}
