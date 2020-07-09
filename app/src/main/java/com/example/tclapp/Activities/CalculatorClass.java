package com.example.tclapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;

public class CalculatorClass extends AppCompatActivity {
    TextView tx,resulttx;

    Toolbar toolbar;
    TextView toolBartitle;
    ImageView toolBarBack;
    EditText consumptionet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_class);
        tx=findViewById(R.id.productnametx);
        toolbar=findViewById(R.id.custom_app_toolbar);
        toolBartitle=findViewById(R.id.toolbaractivityname);
        toolBarBack=findViewById(R.id.backicon);
        consumptionet=findViewById(R.id.consedit);
        resulttx=findViewById(R.id.result);

        toolBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolBartitle.setText("Consumption");
        Intent i=getIntent();
        final String metter=i.getExtras().getString("SendMeter");
        final String litter=i.getExtras().getString("SendLiter");
        String productName=i.getExtras().getString("ProductNAme");

        Toast.makeText(this, metter, Toast.LENGTH_SHORT).show();
        tx.setText(productName);


        if (metter==null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(CalculatorClass.this);
            builder.setTitle("Error");
            builder.setMessage("Meter And Liter Is NULL");
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
            consumptionet.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {




                }

                @Override
                public void afterTextChanged(Editable s) {


                    try {
                        int textCast=Integer.valueOf(s.toString());
                        int finalResult=textCast*Integer.valueOf(litter)/Integer.valueOf(metter);

                        resulttx.setText(String.valueOf(finalResult));

                    }catch (NumberFormatException e){}

                }
            });
        }








    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
