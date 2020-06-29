package com.example.tclapp.model;

import com.google.gson.annotations.SerializedName;

public class AllProductDetails {

    @SerializedName("category_id")
    private String category_id;
    @SerializedName("name")
    private String name;
    @SerializedName("short_desc")
    private String short_desc;
    @SerializedName("long_desc")
    private String long_desc;
    @SerializedName("safty")
    private String saftey;
    @SerializedName("how_to_use")
    private String howToUse;
    @SerializedName("where_to_use")
    private String whereToUse;
    @SerializedName("avaliable_packing")
    private String avaliablePaking;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getLong_desc() {
        return long_desc;
    }

    public void setLong_desc(String long_desc) {
        this.long_desc = long_desc;
    }

    public String getSaftey() {
        return saftey;
    }

    public void setSaftey(String saftey) {
        this.saftey = saftey;
    }

    public String getHowToUse() {
        return howToUse;
    }

    public void setHowToUse(String howToUse) {
        this.howToUse = howToUse;
    }

    public String getWhereToUse() {
        return whereToUse;
    }

    public void setWhereToUse(String whereToUse) {
        this.whereToUse = whereToUse;
    }

    public String getAvaliablePaking() {
        return avaliablePaking;
    }

    public void setAvaliablePaking(String avaliablePaking) {
        this.avaliablePaking = avaliablePaking;
    }
}
