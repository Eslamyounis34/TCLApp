package com.example.tclapp.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tclapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    Toolbar toolbar;
    TextView toolbatText;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user_profile, container, false);

        toolbar=v.findViewById(R.id.apptoolbar);
        toolbatText =v.findViewById(R.id.toolbaractivityname);

        toolbatText.setText("Profile");

        return v;
    }
}
