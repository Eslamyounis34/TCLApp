package com.example.tclapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.UriMatcher;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.ProductList;
import com.example.tclapp.model.SelectedProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductAllDetails extends AppCompatActivity {

    TextView productdesc,saftey,howtouse;
    Toolbar toolbar;
    TextView toolBartitle;
    ImageView toolBarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_all_details);

        toolbar=findViewById(R.id.custom_app_toolbar);
        toolBartitle=findViewById(R.id.toolbaractivityname);
        toolBarBack=findViewById(R.id.backicon);
         productdesc=findViewById(R.id.longDesc);
         saftey=findViewById(R.id.safteyDesc);
         howtouse=findViewById(R.id.howtouseDesc);
        Intent i =getIntent();
        String getId=i.getExtras().getString("SelectedProductId");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Call<SelectedProduct> call=api.getSelectedProductData(getId);
        call.enqueue(new Callback<SelectedProduct>() {
            @Override
            public void onResponse(Call<SelectedProduct> call, Response<SelectedProduct> response) {
                Toast.makeText(ProductAllDetails.this, response.body().getAllDetails().getName(), Toast.LENGTH_SHORT).show();
                toolBartitle.setText(response.body().getAllDetails().getName());
                productdesc.setText(response.body().getAllDetails().getLong_desc());
                saftey.setText(response.body().getAllDetails().getSaftey());
                howtouse.setText(response.body().getAllDetails().getHowToUse());

            }

            @Override
            public void onFailure(Call<SelectedProduct> call, Throwable t) {

            }
        });
        toolBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
