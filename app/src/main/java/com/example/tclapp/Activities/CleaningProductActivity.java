package com.example.tclapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tclapp.R;
import com.example.tclapp.adapters.ProductsAdapter;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.ProductsParent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CleaningProductActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ProductsAdapter adapter;
    private String id;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView back;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_product);



        mRecyclerView = (RecyclerView)findViewById(R.id.cl_product_recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar=findViewById(R.id.custom_app_toolbar);
        toolbarTitle = findViewById(R.id.toolbaractivityname);
        back = findViewById(R.id.backicon);



        toolbarTitle.setText("Cleaning Recommendtion Product");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            // and get whatever type user account id is
        }
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://appadmin.tclgcc.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final RetrofitApi apiInterface = retrofit.create(RetrofitApi.class);
        Call<ProductsParent> call = apiInterface.getproduct(id);
        call.enqueue(new Callback<ProductsParent>() {
            @Override
            public void onResponse(Call<ProductsParent> call, Response<ProductsParent> response) {

                adapter = new ProductsAdapter(getApplicationContext(),response.body().getProductmodelList());
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ProductsParent> call, Throwable t) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
