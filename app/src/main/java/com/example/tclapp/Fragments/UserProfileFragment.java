package com.example.tclapp.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.Activities.MainActivity;
import com.example.tclapp.Activities.ProductAllDetails;
import com.example.tclapp.Activities.ProductsActivity;
import com.example.tclapp.R;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.LoggedUserData;
import com.example.tclapp.model.SaveSharedPreference;
import com.example.tclapp.model.SelectedProduct;
import com.example.tclapp.model.UpdateUserResponse;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    Toolbar toolbar;
    TextView toolbatText, userName, userEmail, userPhone, pickImage;
    Button logoutBt;
    CircleImageView capturedImage;

    TextView editEmail, editNAme, editPhone;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;

    String id = "";
    String edittext_text = "";
    String name = "";
    String email = "";
    String phone = "";
    String user_iamge_url="";
    private ProgressDialog mProgressDialog;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
    RetrofitApi api = retrofit.create(RetrofitApi.class);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);

        toolbar = v.findViewById(R.id.apptoolbar);
        toolbatText = v.findViewById(R.id.toolbaractivityname);
        userName = v.findViewById(R.id.loggedusername);
        userEmail = v.findViewById(R.id.loggeduseremail);
        userPhone = v.findViewById(R.id.loggeduserPhone);
        logoutBt = v.findViewById(R.id.LogoutButton);
        pickImage = v.findViewById(R.id.pickImagetx);
        capturedImage = v.findViewById(R.id.userImage);
        editEmail = v.findViewById(R.id.edituseremail);
        editNAme = v.findViewById(R.id.editusername);
        editPhone = v.findViewById(R.id.edituserphone);


        toolbatText.setText("Profile");
        logoutBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveSharedPreference.setLoggedIn(getContext(), false);
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        if(SaveSharedPreference.getLoggedStatus(getContext())==false) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Error");
            // final TextView input = new TextView(getApplicationContext());
            builder.setMessage("You Must Log In First ");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    Intent intent = new Intent(getContext(), ProductsActivity.class);
//                    startActivity(intent);
                    dialog.dismiss();
                }
            });
            builder.show();
        }else {
            id = SaveSharedPreference.getUserId(getContext());

            Call<LoggedUserData> call=api.getLoggedUserData(id);
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
            call.enqueue(new Callback<LoggedUserData>() {
                @Override
                public void onResponse(Call<LoggedUserData> call, Response<LoggedUserData> response) {

                    mProgressDialog.dismiss();

                    name=response.body().getData().getName();
                    email=response.body().getData().getEmail();
                    phone=response.body().getData().getMobile();
                    user_iamge_url=response.body().getData().getUser_image();

                    if (name==null)
                    {
                        userName.setText("No Data");

                    }
                    else {                    userName.setText(response.body().getData().getName());
                    }
                    if (user_iamge_url==null)
                    {
                        Toast.makeText(getContext(), "No user Image", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getContext(), "Image Exists", Toast.LENGTH_SHORT).show();
                    }


                    if (phone==null)
                    {
                        userPhone.setText("No Data");

                    }
                    else {
                        userPhone.setText(response.body().getData().getMobile());

                    }

                    if (email==null)
                    {
                        userEmail.setText("No Data");

                    }
                    else {
                        userEmail.setText(response.body().getData().getEmail());

                    }

                    Picasso.get().load(response.body().getData().getUser_image()).into(capturedImage);
            }

                @Override
                public void onFailure(Call<LoggedUserData> call, Throwable t) {
                    mProgressDialog.dismiss();

                }
            });
            editEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Update Email");
                    final EditText input = new EditText(getContext());

                    input.setInputType(InputType.TYPE_CLASS_TEXT );
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edittext_text = input.getText().toString();


                            Call<UpdateUserResponse> emailcall=api.getUpdatedUserEmail(id,edittext_text,name,phone);

                            emailcall.enqueue(new Callback<UpdateUserResponse>() {
                                @Override
                                public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                                    if (response.code()==200)
                                    {
                                        Toast.makeText(getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();

                                        // Log.e("CHECKRESPONSE",response.body().getData().getEmail());

                                        email=  response.body().getData().getEmail();
                                        userEmail.setText(email);

                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), "Invalied", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UpdateUserResponse> call, Throwable t) {

                                }
                            });

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                }
            });

            editNAme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Update Name");
                    final EditText input = new EditText(getContext());

                    input.setInputType(InputType.TYPE_CLASS_TEXT );
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edittext_text = input.getText().toString();


                            Call<UpdateUserResponse> namecall=api.getUpdatedUserName(id,edittext_text,email,phone);

                            namecall.enqueue(new Callback<UpdateUserResponse>() {
                                @Override
                                public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                                    if (response.code()==200)
                                    {
                                        Toast.makeText(getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();

                                        name=  response.body().getData().getName();
                                        userName.setText(name);

                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), "Invalied", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UpdateUserResponse> call, Throwable t) {

                                }
                            });

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                }
            });

            editPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Update Phone");
                    final EditText input = new EditText(getContext());

                    input.setInputType(InputType.TYPE_CLASS_TEXT );
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edittext_text = input.getText().toString();


                            Call<UpdateUserResponse> phonecall=api.getUpdatedUserPhone(id,edittext_text,name,email);

                            phonecall.enqueue(new Callback<UpdateUserResponse>() {
                                @Override
                                public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                                    if (response.code()==200)
                                    {
                                        Toast.makeText(getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();

                                        // Log.e("CHECKRESPONSE",response.body().getData().getEmail());

                                        phone=  response.body().getData().getPersonalPhone();
                                        userPhone.setText(phone);

                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), "Invalied", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UpdateUserResponse> call, Throwable t) {

                                }
                            });

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                }
            });

            pickImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFileChooser();
                }
            });


        }


        return v;
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

        private String getRealPathFromURI(Uri contentURI) {

        String thePath = "no-path-found";
        String[] filePathColumn = {MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = getContext().getContentResolver().query(contentURI, filePathColumn, null, null, null);
        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            thePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return  thePath;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(capturedImage);
            String s = getRealPathFromURI(mImageUri);
            Log.e("checkreqfile",s);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), s);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("user_image", s, reqFile);

                    Log.e("CHECKPARTYIMAGE",body.toString());


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(RetrofitApi.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RetrofitApi api = retrofit.create(RetrofitApi.class);
                    Call<UpdateUserResponse> call=api.getUpdatedUserImage(id,body,name,email,phone);
                    call.enqueue(new Callback<UpdateUserResponse>() {
                        @Override
                        public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                            if (response.code()==200)
                            {

                             //   Toast.makeText(getContext(), response.body().getStatus()+" Image", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getContext(), response.body().getData().getUserImage(), Toast.LENGTH_SHORT).show();

                            }
                            else
                                Toast.makeText(getContext(), "Wrong Call", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<UpdateUserResponse> call, Throwable t) {

                            Log.e("ERrorMSG",t.getMessage());
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
    }

}

