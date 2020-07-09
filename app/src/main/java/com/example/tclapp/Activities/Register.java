package com.example.tclapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.ProductList;
import com.example.tclapp.model.RegisterResponse;
import com.example.tclapp.model.SaveSharedPreference;
import com.example.tclapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    EditText name,email,mobile,password,retypepassword;
    Button register;
    //check if data not empty
    //check if password matches
    //check if pass >5 chars or not
    Toolbar toolbar;
    TextView toolbarTitle;
    ImageView toolbarBackIcon;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.usernameEt);
        mobile=findViewById(R.id.mobileEt);
        email=findViewById(R.id.emailEt);
        password=findViewById(R.id.passwordEt);
        retypepassword=findViewById(R.id.repasswordEt);
        register =findViewById(R.id.registerbt);
        toolbar=findViewById(R.id.custom_app_toolbar);
        toolbarTitle=findViewById(R.id.toolbaractivityname);
        toolbarBackIcon=findViewById(R.id.backicon);

        toolbarTitle.setText("Register");



        toolbarBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserName=name.getText().toString();
                String getemail=email.getText().toString();
                final String getMobile=mobile.getText().toString();
                String getPassword=password.getText().toString();
                String getRepassword=retypepassword.getText().toString();



                if(getUserName.isEmpty()||getemail.isEmpty()||getMobile.isEmpty()||getPassword.isEmpty()||getRepassword.isEmpty())
                {
                    Toast.makeText(Register.this, "Please fill all Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(getPassword.length()<6)
                    {
                        Toast.makeText(Register.this, "enter more than 5 chars", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        if(!getPassword.equals(getRepassword))
                        {
                            Toast.makeText(Register.this, "Your Password doesnot match , Try Again", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            User user =new User(getUserName,getemail,getMobile,getPassword);

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(RetrofitApi.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();



                            RetrofitApi api = retrofit.create(RetrofitApi.class);
                            Call<RegisterResponse> call=api.registerUser(user);
                            mProgressDialog = new ProgressDialog(Register.this);
                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setMessage("Loading...");
                            mProgressDialog.setCanceledOnTouchOutside(false);
                            mProgressDialog.show();
                            call.enqueue(new Callback<RegisterResponse>() {
                                @Override
                                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                                    String checkResponse=response.body().getStatus();


                                    if (checkResponse.equals("Data added successefuly"))
                                    {

                                        String getName=response.body().getUser().getName();
                                        String getEmail=response.body().getUser().getEmail();
                                        String getPhone=response.body().getUser().getMobile();
                                        String userId=response.body().getUser().getId();

                                        mProgressDialog.dismiss();


                                        Intent i=new Intent(Register.this,ControlActivity.class);
                                        SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
//                                        SaveSharedPreference.setUserName(getApplicationContext(),getName);
//                                        SaveSharedPreference.setUserEmail(getApplicationContext(),getEmail);
//                                        SaveSharedPreference.setUserPhone(getApplicationContext(),getPhone);
                                       SaveSharedPreference.setUserId(getApplicationContext(),userId);



                                        name.getText().clear();
                                        email.getText().clear();
                                        password.getText().clear();
                                        retypepassword.getText().clear();
                                        mobile.getText().clear();

                                        startActivity(i);
                                    }
                                    else if(checkResponse.equals("Your are registered"))
                                    {
                                        mProgressDialog.dismiss();
                                        Toast.makeText(Register.this, "This mail is already Registerd , Go back to login Screen", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                                    mProgressDialog.dismiss();

                                    Log.e("TESTRegister",t.getMessage());

                                }
                            });                        }
                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
