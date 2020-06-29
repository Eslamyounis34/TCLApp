package com.example.tclapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TechnicalInquiry extends Fragment  {

    Button send;
    EditText site,material,problem,email;
    ImageView capturedImage;
    Toolbar toolbar;
    TextView toolbatText;





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
        capturedImage=v.findViewById(R.id.takeimage);
        toolbar=v.findViewById(R.id.apptoolbar);
        toolbatText =v.findViewById(R.id.toolbaractivityname);

        toolbatText.setText("Technical Inquiry");



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmailText=email.getText().toString();
                Toast.makeText(getContext(), getEmailText, Toast.LENGTH_SHORT).show();
            }
        });








        return v;
    }


}
