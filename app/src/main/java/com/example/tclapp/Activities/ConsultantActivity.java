package com.example.tclapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tclapp.R;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.CountryParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultantActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbarTitle;
    private Spinner countriesSpinner;
    private EditText postalCode;
    private Button show;
    private ImageView back;
    private TextView country,postal;
    String countryN;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant);
        country = findViewById(R.id.country);
        postal = findViewById(R.id.postalcode_txt);
        toolbar=findViewById(R.id.apptoolbar);
        toolbar=findViewById(R.id.custom_app_toolbar);
        toolbarTitle = findViewById(R.id.toolbaractivityname);
        back = findViewById(R.id.backicon);
        toolbarTitle.setText("Consultant Nearby");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        countriesSpinner = (Spinner)findViewById(R.id.countries_names);
        postalCode = (EditText)findViewById(R.id.postalcode_edit);
        show = (Button)findViewById(R.id.show_consultant);
        final String[] countriesNames = getResources().getStringArray(R.array.countries);
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,countriesNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapter);
        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (parent.getId() == R.id.countries_names) {
                    countryN = parent.getSelectedItem().toString();
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mProgressDialog = new ProgressDialog(ConsultantActivity.this);
                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setMessage("Loading...");
                            mProgressDialog.setCanceledOnTouchOutside(false);
                            mProgressDialog.show();

                            Retrofit retrofit2 = new Retrofit.Builder()
                                    .baseUrl("http://appadmin.tclgcc.com")
                                    .addConverterFactory(GsonConverterFactory.create()).build();
                            final RetrofitApi apiInterface = retrofit2.create(RetrofitApi.class);
                            Call<CountryParent> call = apiInterface.getCountry(countryN);
                            call.enqueue(new Callback<CountryParent>() {
                                @Override
                                public void onResponse(Call<CountryParent> call, Response<CountryParent> response) {
                                    mProgressDialog.dismiss();
                                    if(response.body().getcParent().getCountrymodelList().get(position).getCode().equals(null)){
                                        postalCode.setText("No Code");
                                    }
                                    else {
                                        mProgressDialog.dismiss();
                                        postalCode.setText(response.body().getcParent().getCountrymodelList().get(position).getCode());

                                        Intent intent = new Intent(getApplicationContext(),CountryInfoActivity.class);
                                        intent.putExtra("country",countryN);
                                        startActivity(intent);
                                    }
                                }
                                @Override
                                public void onFailure(Call<CountryParent> call, Throwable t) {
                                }
                            });
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
