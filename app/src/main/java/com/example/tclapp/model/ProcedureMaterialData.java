package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProcedureMaterialData {
    @SerializedName("data")
    private List<ProcedureMaterialModel> data = null;

    public List<ProcedureMaterialModel> getData() {
        return data;
    }

    public void setData(List<ProcedureMaterialModel> data) {
        this.data = data;
    }
}
