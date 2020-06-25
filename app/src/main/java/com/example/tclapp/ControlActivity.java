package com.example.tclapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ControlActivity extends AppCompatActivity {


    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    TextView toolbarTitle;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.home:
                    fragment=new HomeFragment();
                    switchFragment(fragment);
                    toolbarTitle.setText("Home");
                    return true;
                case R.id.setting:
                    fragment=new SettingFragment();
                    toolbarTitle.setText("Settings");
                    switchFragment(fragment);
                    return true;

                case R.id.inquiry:
                    fragment=new TechnicalInquiry();
                    toolbarTitle.setText("Technical Inquiry");
                    switchFragment(fragment);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);


        BottomNavigationView mBottomnanigation=(BottomNavigationView)findViewById(R.id.bottomBar);
        mBottomnanigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbarTitle=findViewById(R.id.toolbaractivityname);
        toolbar=findViewById(R.id.apptoolbar);

    }

    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerlayout, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
