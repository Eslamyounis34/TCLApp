package com.example.tclapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tclapp.Activities.CleaningActivity;
import com.example.tclapp.Activities.ConsultantActivity;
import com.example.tclapp.Activities.MainActivity;
import com.example.tclapp.Activities.ProcedureActivity;
import com.example.tclapp.Activities.ProductsActivity;
import com.example.tclapp.R;
import com.example.tclapp.model.SaveSharedPreference;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private Button productsBtn,cleaningBtn,procedureBtn,consultantsBtn;
    Toolbar toolbar;
    TextView toolbatText;
    Button bt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        productsBtn = (Button)v.findViewById(R.id.prodcuts_button);
        cleaningBtn = (Button)v.findViewById(R.id.cleaning_button);
        procedureBtn = (Button)v.findViewById(R.id.producer_button);
        consultantsBtn = (Button)v.findViewById(R.id.consultants_button);
        bt=v.findViewById(R.id.logout);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreference.setLoggedIn(getContext(), false);

                startActivity(new Intent(getContext(), MainActivity.class));

            }
        });
        toolbar=v.findViewById(R.id.apptoolbar);
        toolbatText =v.findViewById(R.id.toolbaractivityname);

        toolbatText.setText("Home");
        productsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductsActivity.class);
                startActivity(intent);
            }
        });
        cleaningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CleaningActivity.class);
                startActivity(intent);
            }
        });
        procedureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProcedureActivity.class);
                startActivity(intent);
            }
        });
        consultantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConsultantActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
