package com.example.tclapp.Activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;
import com.example.tclapp.adapters.ActivityRecycle;
import com.example.tclapp.adapters.MaterailsAdapter;
import com.example.tclapp.adapters.ProductAdapter;
import com.example.tclapp.data.EndlessRecyclerViewScrollListener;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.MaterialsParent;
import com.example.tclapp.model.Product;
import com.example.tclapp.model.ProductList;
import com.example.tclapp.model.RecommendationModel;
import com.example.tclapp.model.RecommendationParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
    LinearLayoutManager layoutManager;

    List<RecommendationModel> productList;


    private ProgressDialog mProgressDialog;
    private int  current_page=1;

    Retrofit retrofit = new Retrofit.Builder().
            baseUrl("http://appadmin.tclgcc.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build();
     RetrofitApi api = retrofit.create(RetrofitApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);
        mRecyclerView = (RecyclerView)findViewById(R.id.activity_recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        materialsRecycle = (RecyclerView)findViewById(R.id.materials_recycle);
        materialsRecycle.setLayoutManager(new LinearLayoutManager(this));
        toolbarTitle=findViewById(R.id.proceduretoolbaractivityname);
        back = findViewById(R.id.procedurebackicon);
        toolbarTitle.setText("Cleaning Recommendation");

        productList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(getApplicationContext());
        adapter=new ActivityRecycle(getBaseContext(),productList);

        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page ++;
                loadNextPage();
            }
        });







        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));


        Call<RecommendationParent> call = api.getInfo(current_page);
        mProgressDialog = new ProgressDialog(CleaningActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        call.enqueue(new Callback<RecommendationParent>() {
            @Override
            public void onResponse(Call<RecommendationParent> call, Response<RecommendationParent> response) {

                mProgressDialog.dismiss();
                productList.addAll(response.body().getParent().getModelList());
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<RecommendationParent> call, Throwable t) {
                mProgressDialog.dismiss();
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
                    if (response.body().getMparent().getMaterialmodelList().size()==0)
                    {

                        Toast.makeText(getApplicationContext(), "No Materials to load", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        adapter2 = new MaterailsAdapter(getApplicationContext(),response.body().getMparent().getMaterialmodelList());
                        materialsRecycle.setAdapter(adapter2);
                    }

                }

                @Override
                public void onFailure(Call<MaterialsParent> call, Throwable t) {
                }
            });
                }
    };
    private void loadNextPage()
    {
        Call<RecommendationParent> call=api.getInfo(current_page);
        call.enqueue(new Callback<RecommendationParent>() {
            @Override
            public void onResponse(Call<RecommendationParent> call, Response<RecommendationParent> response) {

                productList.addAll(response.body().getParent().getModelList());
                adapter.notifyItemRangeInserted(adapter.getItemCount(),productList.size()-1);
            }

            @Override
            public void onFailure(Call<RecommendationParent> call, Throwable t) {
                Log.e("TestProducts",t.getMessage());

            }
        });

    }

}

