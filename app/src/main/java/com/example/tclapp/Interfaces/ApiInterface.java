package com.example.tclapp.Interfaces;

import com.example.tclapp.Models.CountryParent;
import com.example.tclapp.Models.MaterialsParent;
import com.example.tclapp.Models.ProductsParent;
import com.example.tclapp.Models.RecommendationParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("recommendation/showActivity?page=1")
    Call<RecommendationParent> getInfo();
    @GET("/api/recommendation/showMaterial?")
    Call<MaterialsParent> getMaterials(@Query("id")String id);
    @GET("/api/recommendation/show?")
    Call<ProductsParent> getproduct(@Query("product_category_id")String id);
    @POST("/api/consultant/consult?")
    Call<CountryParent> getCountry(@Query("country") String coutryName);

}
