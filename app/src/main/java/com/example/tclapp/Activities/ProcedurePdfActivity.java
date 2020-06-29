package com.example.tclapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tclapp.R;

public class ProcedurePdfActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolBartitle;
    ImageView toolBarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_pdf);

        toolbar=findViewById(R.id.custom_app_toolbar);
        toolBartitle=findViewById(R.id.toolbaractivityname);
        toolBarBack=findViewById(R.id.backicon);

        toolBartitle.setText("Procedure Pdf");
        toolBarBack.setOnClickListener(new View.OnClickListener() {
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
}
