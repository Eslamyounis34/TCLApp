package com.example.tclapp.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tclapp.R;
import com.example.tclapp.adapters.ActivityRecycle;
import com.example.tclapp.adapters.MaterailsAdapter;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.MaterialsParent;
import com.example.tclapp.model.RecommendationParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CleaningActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView back;
    private RecyclerView mRecyclerView,materialsRecycle;
    private ActivityRecycle adapter;
    private MaterailsAdapter adapter2;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);
        mRecyclerView = (RecyclerView)findViewById(R.id.activity_recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        materialsRecycle = (RecyclerView)findViewById(R.id.materials_recycle);
        materialsRecycle.setLayoutManager(new LinearLayoutManager(this));
        toolbar=findViewById(R.id.custom_app_toolbar);
        toolbarTitle = findViewById(R.id.toolbaractivityname);
        back = findViewById(R.id.backicon);

        toolbarTitle.setText("Cleaning Recommendations");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://appadmin.tclgcc.com/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final RetrofitApi apiInterface = retrofit.create(RetrofitApi.class);
        Call<RecommendationParent> call = apiInterface.getInfo();
        call.enqueue(new Callback<RecommendationParent>() {
            @Override
            public void onResponse(Call<RecommendationParent> call, Response<RecommendationParent> response) {
                adapter = new ActivityRecycle(getApplicationContext(),response.body().getParent().getModelList());
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RecommendationParent> call, Throwable t) {

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            id = intent.getStringExtra("id");
            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl("http://appadmin.tclgcc.com")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            final RetrofitApi apiInterface1 = retrofit2.create(RetrofitApi.class);
            Call<MaterialsParent> call1 = apiInterface1.getCleaningMaterials(id);
            call1.enqueue(new Callback<MaterialsParent>() {
                @Override
                public void onResponse(Call<MaterialsParent> call, Response<MaterialsParent> response) {
                    adapter2 = new MaterailsAdapter(getApplicationContext(),response.body().getMparent().getMaterialmodelList());
                    materialsRecycle.setAdapter(adapter2);
                }

                @Override
                public void onFailure(Call<MaterialsParent> call, Throwable t) {

                }
            });

        }
    };

}

