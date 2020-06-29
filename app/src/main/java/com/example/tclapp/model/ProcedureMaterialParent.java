package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProcedureMaterialParent {
    @SerializedName("data")
    private ProcedureMaterialData data;

    public ProcedureMaterialData getData() {
        return data;
    }

    public void setData(ProcedureMaterialData data) {
        this.data = data;
    }
}


