package com.example.tclapp.Activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tclapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ConsultantActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant);
        toolbar=findViewById(R.id.apptoolbar);
        toolbarTitle=findViewById(R.id.toolbaractivityname);

        toolbarTitle.setText("Consultant Nearby");
    }
}