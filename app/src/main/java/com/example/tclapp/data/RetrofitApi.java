package com.example.tclapp.data;

import android.text.GetChars;

import com.example.tclapp.model.ProcedureMaterialParent;
import com.example.tclapp.model.ProcedureParent;
import com.example.tclapp.model.ProductList;
import com.example.tclapp.model.RegisterResponse;
import com.example.tclapp.model.SearchResponse;
import com.example.tclapp.model.SelectedProduct;
import com.example.tclapp.model.User;
import com.example.tclapp.model.UserLogin;
import com.example.tclapp.model.UserLoginData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitApi {

    String BASE_URL ="http://appadmin.tclgcc.com";


    @GET("/api/procedure/showActivity")
    Call<ProcedureParent>getProceduresActivities();

    @GET("/api/procedure/show?")
    Call<ProcedureMaterialParent>getMaterials(@Query("category_id") String category_id);

    @GET("/api/product/show?")
    Call<ProductList>getProducts(@Query("page")int pageNumber);

    @GET("/api/product/details?")
    Call<SelectedProduct>getSelectedProductData(@Query("id")String id);

    @POST("/api/user/register")
    Call<RegisterResponse>registerUser(@Body User user);

    @POST("api/user/login")
    Call<UserLoginData>loginUser(@Body UserLogin user);



    @POST("/api/ProductSearch")
    Call<SearchResponse>searchResult( @Query("search_name")  String name);




}
