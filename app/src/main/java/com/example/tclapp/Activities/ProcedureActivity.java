package com.example.tclapp.Activities;

import android.app.ProgressDialog;
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

    private ProgressDialog mProgressDialog;


    private String getData;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitApi api = retrofit.create(RetrofitApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure);
        toolbar=findViewById(R.id.procedure_custom_app_toolbar);
        toolbarTitle=findViewById(R.id.proceduretoolbaractivityname);
        toolbariconback=findViewById(R.id.procedurebackicon);
        activityRecyclerView=findViewById(R.id.leftActivityRecycler);
        materialRecyclerView=findViewById(R.id.rightmaterialRecycler);

        toolbarTitle.setText("Cleaning Procedure");




        LocalBroadcastManager.getInstance(this).registerReceiver(mmMessageReceiver,
                new IntentFilter("custom-message"));


        Call<ProcedureParent> call = api.getProceduresActivities();
        mProgressDialog = new ProgressDialog(ProcedureActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        call.enqueue(new Callback<ProcedureParent>() {
            @Override
            public void onResponse(Call<ProcedureParent> call, Response<ProcedureParent> response) {

                mProgressDialog.dismiss();


                  mAdapter=new ProcedureActivityAdapter(getApplicationContext(),response.body().getParent().getModelList());
                  activityRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ProcedureParent> call, Throwable t) {
                mProgressDialog.dismiss();

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

    public BroadcastReceiver mmMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String qty = intent.getStringExtra("quantity");
            getData=intent.getStringExtra("quantity");
          //  Toast.makeText(ProcedureActivity.this, getData, Toast.LENGTH_SHORT).show();

            Call<ProcedureMaterialParent> materialCall = api.getMaterials(getData);
            materialCall.enqueue(new Callback<ProcedureMaterialParent>() {
                @Override
                public void onResponse(Call<ProcedureMaterialParent> call, Response<ProcedureMaterialParent> response) {


                    if (response.body().getData().getData().size()==0)
                    {
                        Toast.makeText(getApplicationContext(), "No data to load", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        materialAdapter=new ProcedureMaterialApapter(getApplicationContext(),response.body().getData().getData());
                        materialRecyclerView.setAdapter(materialAdapter);

                    }



                }

                @Override
                public void onFailure(Call<ProcedureMaterialParent> call, Throwable t) {


                    Log.e("TESSSST",t.getMessage());

                }
            });

        }
    };
}
