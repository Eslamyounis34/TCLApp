package com.example.tclapp.Activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tclapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProcedureActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbarTitle;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure);
        toolbar=findViewById(R.id.apptoolbar);
        toolbarTitle=findViewById(R.id.toolbaractivityname);

        toolbarTitle.setText("Cleaning Procedure");
    }
}
