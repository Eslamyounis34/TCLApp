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
import com.example.tclapp.adapters.CountryAdapter;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.CountryParent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView back;
    String countryName;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);



        Bundle extras = getIntent().getExtras();
        countryName = extras.getString("country");

        toolbar=findViewById(R.id.custom_app_toolbar);
        toolbarTitle = findViewById(R.id.toolbaractivityname);
        back = findViewById(R.id.backicon);
        toolbarTitle.setText("Consultant Nearby Info");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.country_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressDialog = new ProgressDialog(CountryInfoActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://appadmin.tclgcc.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final RetrofitApi apiInterface = retrofit2.create(RetrofitApi.class);
        Call<CountryParent> call = apiInterface.getCountry(countryName);
        call.enqueue(new Callback<CountryParent>() {
            @Override
            public void onResponse(Call<CountryParent> call, Response<CountryParent> response) {
                mProgressDialog.dismiss();
                adapter = new CountryAdapter(getApplicationContext(),response.body().getcParent().getCountrymodelList());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CountryParent> call, Throwable t) {
                mProgressDialog.dismiss();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
