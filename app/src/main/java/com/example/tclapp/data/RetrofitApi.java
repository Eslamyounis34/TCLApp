package com.example.tclapp.data;

import android.text.GetChars;

import com.example.tclapp.model.CountryParent;
import com.example.tclapp.model.InquiryResponse;
import com.example.tclapp.model.LoggedUserData;
import com.example.tclapp.model.MaterialsParent;
import com.example.tclapp.model.ProcedureMaterialParent;
import com.example.tclapp.model.ProcedureParent;
import com.example.tclapp.model.ProductList;
import com.example.tclapp.model.ProductsParent;
import com.example.tclapp.model.RecommendationParent;
import com.example.tclapp.model.RegisterResponse;
import com.example.tclapp.model.SearchResponse;
import com.example.tclapp.model.SelectedProduct;
import com.example.tclapp.model.UpdateUserResponse;
import com.example.tclapp.model.User;
import com.example.tclapp.model.UserLogin;
import com.example.tclapp.model.UserLoginData;
import com.example.tclapp.model.UserUpdatedData;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @Multipart
    @POST("/api/user/editProfile")
    Call<UpdateUserResponse>getUpdatedUserData(@Part("user_id") String id,
                                               @Part MultipartBody.Part image  );


    @Multipart
    @POST("/api/postInquiry")
    Call<InquiryResponse>getInquiryResponse(@Query("site")  String site,@Query("material")String material
                                            ,@Part MultipartBody.Part image
            ,@Query("email")String email,@Query("description")String desc);

    @POST("/api/postInquiry")
    Call<InquiryResponse>getInquiryResponsewithoutimage(@Query("site")  String site,@Query("material")String material
            ,@Query("email")String email,@Query("description")String desc);

    @POST("/api/user/editProfile")
    Call<UpdateUserResponse>getUpdatedUserName(@Query("user_id")String id,@Query("name")String name,@Query("email")String email,@Query("personal_phone")String phone);

    @POST("/api/user/editProfile")
    Call<UpdateUserResponse>getUpdatedUserEmail(@Query("user_id")String id,@Query("email")String email,@Query("name")String name,@Query("personal_phone")String phone);

    @POST("/api/user/editProfile")
    Call<UpdateUserResponse>getUpdatedUserPhone(@Query("user_id")String id,@Query("personal_phone")String phone,@Query("name")String name,@Query("email")String email);

    @Multipart
    @POST("/api/user/editProfile")
    Call<UpdateUserResponse>getUpdatedUserImage(@Query("user_id")String id,@Part MultipartBody.Part imagename,@Query("name")String name,@Query("email")String email,@Query("personal_phone")String phone);

    @GET("/api/user/show?")
    Call<LoggedUserData>getLoggedUserData(@Query("id")String userId);





    @GET("recommendation/showActivity?")
    Call<RecommendationParent> getInfo(@Query("page")int page);
    @GET("/api/recommendation/showMaterial?")
    Call<MaterialsParent> getCleaningMaterials(@Query("id")String id);
    @GET("/api/recommendation/show?")
    Call<ProductsParent> getproduct(@Query("product_category_id")String id);
    @POST("/api/consultant/consult?")
    Call<CountryParent> getCountry(@Query("country") String coutryName);




}
