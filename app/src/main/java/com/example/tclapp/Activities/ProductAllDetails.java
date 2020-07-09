package com.example.tclapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.UriMatcher;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.ProductList;
import com.example.tclapp.model.SaveSharedPreference;
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
    Button calculator;

    String liter="";
    String meter="";
    String productName="";
    RelativeLayout rv;
    private ProgressDialog mProgressDialog;


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
         calculator=findViewById(R.id.calculator_button);
         rv=findViewById(R.id.allproductDetailslayout);
       // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())==false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ProductAllDetails.this);
            builder.setTitle("Error");
            // final TextView input = new TextView(getApplicationContext());
            builder.setMessage("You Must Log In First ");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          onBackPressed();
                        }
                    });
            builder.show();
        }
        else {
            Intent i =getIntent();
            final String getId=i.getExtras().getString("SelectedProductId");

            mProgressDialog = new ProgressDialog(ProductAllDetails.this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();





            calculator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(ProductAllDetails.this,CalculatorClass.class);
                    i.putExtra("SendLiter",liter);
                    i.putExtra("SendMeter",meter);
                    i.putExtra("ProductNAme",productName);


                    startActivity(i);
                    Toast.makeText(ProductAllDetails.this, liter +"  "+meter, Toast.LENGTH_SHORT).show();
                }
            });




            Log.e("IDCHECK",getId);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitApi api = retrofit.create(RetrofitApi.class);
            Call<SelectedProduct> call=api.getSelectedProductData(getId);
            call.enqueue(new Callback<SelectedProduct>() {
                @Override
                public void onResponse(Call<SelectedProduct> call, Response<SelectedProduct> response) {

                    SelectedProduct sp=response.body();

                    if (sp.getAllDetails()==null)
                    {
                        mProgressDialog.dismiss();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ProductAllDetails.this);
                        builder.setTitle("Error");
                        // final TextView input = new TextView(getApplicationContext());
                        builder.setMessage("No data To Load");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onBackPressed();

                            }
                        });
                        builder.show();
                    }
                    else
                    {
                        mProgressDialog.dismiss();

                        Toast.makeText(ProductAllDetails.this, response.body().getAllDetails().getName(), Toast.LENGTH_SHORT).show();
                        toolBartitle.setText(response.body().getAllDetails().getName());
                        productdesc.setText(response.body().getAllDetails().getLong_desc());
                        saftey.setText(response.body().getAllDetails().getSaftey());
                        howtouse.setText(response.body().getAllDetails().getHowToUse());

                        liter=response.body().getAllDetails().getLiter();
                        meter=response.body().getAllDetails().getMeter();
                        productName=response.body().getAllDetails().getName();


                    }

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



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
