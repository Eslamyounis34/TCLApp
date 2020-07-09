package com.example.tclapp.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.Activities.ProductAllDetails;
import com.example.tclapp.Activities.Register;
import com.example.tclapp.R;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.InquiryResponse;
import com.example.tclapp.model.SelectedProduct;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class TechnicalInquiry extends Fragment  {

    Button send;
    EditText site,material,problem,email;
    ImageView capturedImage;
    Toolbar toolbar;
    TextView toolbatText,pickImage;

    private ProgressDialog mProgressDialog;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_technical_inquiry, container, false);

        send=v.findViewById(R.id.sendinquiry_bt);
        site=v.findViewById(R.id.siteedittext);
        material=v.findViewById(R.id.materialedittext);
        problem=v.findViewById(R.id.problemedittext);
        email=v.findViewById(R.id.inquiry_emailedittext);
        capturedImage=v.findViewById(R.id.selectedimage);
        toolbar=v.findViewById(R.id.apptoolbar);
        toolbatText =v.findViewById(R.id.toolbaractivityname);
        pickImage=v.findViewById(R.id.pickImagetx);

        toolbatText.setText("Technical Inquiry");

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmailText=email.getText().toString();
                String getSite=site.getText().toString();
                String getProblem=problem.getText().toString();
                String getMaterial=material.getText().toString();
                if(getSite.isEmpty()||getEmailText.isEmpty()||getMaterial.isEmpty()||getProblem.isEmpty())
                {
                    Toast.makeText(getContext(), "Please Fill All Data", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(getEmailText).matches())
                    {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(RetrofitApi.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        RetrofitApi api = retrofit.create(RetrofitApi.class);
                        Call<InquiryResponse>call=api.getInquiryResponsewithoutimage(getSite,getMaterial,getEmailText,getProblem);
                        mProgressDialog = new ProgressDialog(getContext());
                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.show();
                        call.enqueue(new Callback<InquiryResponse>() {
                            @Override
                            public void onResponse(Call<InquiryResponse> call, Response<InquiryResponse> response) {
                                if (response.code()==200)
                                {
                                    if (response.body().getStatus().equals("Sucess"))
                                    {
                                        mProgressDialog.dismiss();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setTitle("Done");
                                        builder.setMessage("Inquiry Sent successfully");

                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();

                                            }
                                        });
                                        builder.show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getContext(), "Error data", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<InquiryResponse> call, Throwable t) {
                                Log.e("CheckInquiry",t.getMessage());
                                mProgressDialog.dismiss();

                            }
                        });

                    }
                    else
                        Toast.makeText(getContext(), "Email Invalied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String getEmailText=email.getText().toString();
                    String getSite=site.getText().toString();
                    String getProblem=problem.getText().toString();
                    String getMaterial=material.getText().toString();
                    String s = getRealPathFromURI(mImageUri);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), s);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("pic", s, reqFile);


                    if(getSite.isEmpty()||getEmailText.isEmpty()||getMaterial.isEmpty()||getProblem.isEmpty())
                    {
                        Toast.makeText(getContext(), "Please Fill All Data", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(android.util.Patterns.EMAIL_ADDRESS.matcher(getEmailText).matches())
                        {
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(RetrofitApi.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            RetrofitApi api = retrofit.create(RetrofitApi.class);
                            Call<InquiryResponse> call=api.getInquiryResponse(getSite,getMaterial,body,getEmailText,getProblem);
                            mProgressDialog = new ProgressDialog(getContext());
                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setMessage("Loading...");
                            mProgressDialog.setCanceledOnTouchOutside(false);
                            mProgressDialog.show();
                            call.enqueue(new Callback<InquiryResponse>() {
                                @Override
                                public void onResponse(Call<InquiryResponse> call, Response<InquiryResponse> response) {
                                    if (response.code()==200)
                                    {
                                        if (response.body().getStatus().equals("Sucess"))
                                        {
                                            mProgressDialog.dismiss();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                            builder.setTitle("Done");
                                            // final TextView input = new TextView(getApplicationContext());
                                            builder.setMessage("Inquiry Sent successfully");

                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();

                                                }
                                            });
                                            builder.show();

                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<InquiryResponse> call, Throwable t) {

                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                                    Log.e("CheckInquiry",t.getMessage());
                                    mProgressDialog.dismiss();


                                }
                            });
                        }
                        else
                            Toast.makeText(getContext(), "Email Invalied", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            Picasso.get().load(mImageUri).into(capturedImage);
        }

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

}
