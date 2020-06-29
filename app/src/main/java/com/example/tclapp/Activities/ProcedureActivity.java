package com.example.tclapp.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;
import com.example.tclapp.adapters.ProcedureActivityAdapter;
import com.example.tclapp.adapters.ProcedureMaterialApapter;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.ProcedureMaterialParent;
import com.example.tclapp.model.ProcedureModel;
import com.example.tclapp.model.ProcedureParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProcedureActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbarTitle;
    RecyclerView activityRecyclerView,materialRecyclerView;
    ProcedureActivityAdapter mAdapter;
    ImageView toolbariconback;
    ProcedureMaterialApapter materialAdapter;
    List<ProcedureModel>procedureModels=new ArrayList<>();

    private String getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure);
        toolbar=findViewById(R.id.procedure_custom_app_toolbar);
        toolbarTitle=findViewById(R.id.proceduretoolbaractivityname);
        toolbariconback=findViewById(R.id.procedurebackicon);
        activityRecyclerView=findViewById(R.id.leftActivityRecycler);
        materialRecyclerView=findViewById(R.id.rightmaterialRecycler);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Call<ProcedureParent> call = api.getProceduresActivities();

        call.enqueue(new Callback<ProcedureParent>() {
            @Override
            public void onResponse(Call<ProcedureParent> call, Response<ProcedureParent> response) {

                  mAdapter=new ProcedureActivityAdapter(getApplicationContext(),response.body().getParent().getModelList());
                  activityRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ProcedureParent> call, Throwable t) {

            }
        });
        toolbariconback.setOnClickListener(new View.OnClickListener() {
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

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String qty = intent.getStringExtra("quantity");
            getData=intent.getStringExtra("quantity");
          //  Toast.makeText(ProcedureActivity.this, getData, Toast.LENGTH_SHORT).show();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitApi api = retrofit.create(RetrofitApi.class);

            Call<ProcedureMaterialParent> materialCall = api.getMaterials(getData);
            materialCall.enqueue(new Callback<ProcedureMaterialParent>() {
                @Override
                public void onResponse(Call<ProcedureMaterialParent> call, Response<ProcedureMaterialParent> response) {

                    materialAdapter=new ProcedureMaterialApapter(getApplicationContext(),response.body().getData().getData());
                    materialRecyclerView.setAdapter(materialAdapter);

                    Log.e("TESSSST",response.body().getData().getData().get(0).getTitle());
                    Toast.makeText(ProcedureActivity.this, response.body().getData().getData().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ProcedureMaterialParent> call, Throwable t) {
                    Log.e("TESSSST",t.getMessage());

                }
            });

        }
    };
}
