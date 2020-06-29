package com.example.tclapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tclapp.Adapters.CountryAdapter;
import com.example.tclapp.Interfaces.ApiInterface;
import com.example.tclapp.Models.CountryParent;
import com.example.tclapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);
        Bundle extras = getIntent().getExtras();
            countryName = extras.getString("country");
        toolbar=findViewById(R.id.apptoolbar);
        toolbarTitle=findViewById(R.id.toolbaractivityname);
        toolbarTitle.setText("Consultant Nearby Info");
        back= findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.country_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://appadmin.tclgcc.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final ApiInterface apiInterface = retrofit2.create(ApiInterface.class);
        Call<CountryParent> call = apiInterface.getCountry(countryName);
        call.enqueue(new Callback<CountryParent>() {
            @Override
            public void onResponse(Call<CountryParent> call, Response<CountryParent> response) {
                adapter = new CountryAdapter(getApplicationContext(),response.body().getcParent().getCountrymodelList());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CountryParent> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
