package com.example.tclapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;
import com.example.tclapp.adapters.ProductAdapter;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.ProcedureMaterialParent;
import com.example.tclapp.model.SearchResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResult extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTitle;
    ImageView toolbarBackIcon;

    RecyclerView searchRecycler;
    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        searchRecycler=findViewById(R.id.searchRecyclerview);

        toolbar=findViewById(R.id.custom_app_toolbar);
        toolbarTitle=findViewById(R.id.toolbaractivityname);
        toolbarBackIcon=findViewById(R.id.backicon);

        toolbarTitle.setText("Search Result");


        toolbarBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent i= getIntent();
        String getSearchText=i.getExtras().getString("SearchText");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<SearchResponse> materialCall = api.searchResult(getSearchText);
        materialCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
               // Toast.makeText(SearchResult.this, response.body().getData().getData().get(0).getName(), Toast.LENGTH_SHORT).show();
                adapter=new ProductAdapter(getApplicationContext(),response.body().getData().getData());
                searchRecycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
