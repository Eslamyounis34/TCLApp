package com.example.tclapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.RegisterResponse;
import com.example.tclapp.model.SaveSharedPreference;
import com.example.tclapp.model.UserLogin;
import com.example.tclapp.model.UserLoginData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static com.example.tclapp.model.PreferencesUtility.LOGGED_IN_PREF;

public class MainActivity extends AppCompatActivity {
  Button login,signUp;
  Toolbar toolbar;
  TextView toolbarTitle;

  EditText emailET,passwordET;
  ConstraintLayout layout;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.login_button);
        signUp=findViewById(R.id.signup_button);
        toolbar=findViewById(R.id.apptoolbar);
        toolbarTitle=findViewById(R.id.toolbaractivityname);
        emailET=findViewById(R.id.login_email_edittext);
        passwordET=findViewById(R.id.login_password_edittext);
        layout=findViewById(R.id.loginlayout);

        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), ControlActivity.class);
            startActivity(intent);
        } else {
            layout.setVisibility(View.VISIBLE);
        }

        toolbarTitle.setText("Login");

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String getEmail=emailET.getText().toString();
                String getpass=passwordET.getText().toString();

                if (getEmail.isEmpty()||getpass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }else
                {
                    final UserLogin userLogin=new UserLogin(getEmail,getpass);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(RetrofitApi.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();



                    RetrofitApi api = retrofit.create(RetrofitApi.class);
                    Call<UserLoginData> call=api.loginUser(userLogin);

                    mProgressDialog = new ProgressDialog(MainActivity.this);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    call.enqueue(new Callback<UserLoginData>() {
                        @Override
                        public void onResponse(Call<UserLoginData> call, Response<UserLoginData> response) {

                            mProgressDialog.dismiss();

                            String s=response.body().getUser().getName();
                            String userEmail=response.body().getUser().getEmail();
                            String userPHone=response.body().getUser().getMobile();
                            String userId=response.body().getUser().getId();

                            Log.e("CkeckMobile",userPHone);

                            SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
//                            SaveSharedPreference.setUserName(getApplicationContext(),s);
//                            SaveSharedPreference.setUserEmail(getApplicationContext(),userEmail);
//                            SaveSharedPreference.setUserPhone(getApplicationContext(),userPHone);
                            SaveSharedPreference.setUserId(getApplicationContext(),userId);


                            Intent intent = new Intent(getApplicationContext(), ControlActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);                        }

                        @Override
                        public void onFailure(Call<UserLoginData> call, Throwable t) {
                            mProgressDialog.dismiss();


                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,ControlActivity.class));
    }
}
