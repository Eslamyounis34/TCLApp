package com.example.tclapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.Activities.ConditionsOfUseActivity;
import com.example.tclapp.Activities.ImprintActivity;
import com.example.tclapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    TextView conditionsTx,imprintTx;
    ImageView conditionsIm,imprintIm;
    Toolbar toolbar;
    TextView toolbatText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_setting, container, false);

        conditionsTx=v.findViewById(R.id.condition_next_tx);
        imprintTx=v.findViewById(R.id.imprint_next_tx);
        imprintIm=v.findViewById(R.id.imprint_next_icon);
        conditionsIm=v.findViewById(R.id.conditions_next_icon);
        toolbar=v.findViewById(R.id.apptoolbar);
        toolbatText =v.findViewById(R.id.toolbaractivityname);

        toolbatText.setText("Settings");
        conditionsTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ConditionsOfUseActivity.class));
            }
        });

        conditionsIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ConditionsOfUseActivity.class));

            }
        });

        imprintTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ImprintActivity.class));
            }
        });
        imprintIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ImprintActivity.class));
            }
        });


        return v;
    }
}
