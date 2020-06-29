package com.example.tclapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.Fragments.HomeFragment;
import com.example.tclapp.Fragments.SettingFragment;
import com.example.tclapp.Fragments.TechnicalInquiry;
import com.example.tclapp.Fragments.UserProfileFragment;
import com.example.tclapp.R;
import com.example.tclapp.model.SaveSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static com.example.tclapp.model.PreferencesUtility.LOGGED_IN_PREF;

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
                    //toolbarTitle.setText("Home");
                    return true;
                case R.id.setting:
                    fragment=new SettingFragment();
                    //toolbarTitle.setText("Settings");
                    switchFragment(fragment);
                    return true;

                case R.id.inquiry:
                    fragment=new TechnicalInquiry();
                   // toolbarTitle.setText("Technical Inquiry");
                    switchFragment(fragment);
                    return true;

                case R.id.profile:
                    fragment=new UserProfileFragment();
                    // toolbarTitle.setText("Technical Inquiry");
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

        String name= SaveSharedPreference.getUserName(getApplicationContext());
        String email= SaveSharedPreference.getUserEmail(getApplicationContext());
        String phone= SaveSharedPreference.getUserPhone(getApplicationContext());

        toolbarTitle=findViewById(R.id.toolbaractivityname);
        toolbar=findViewById(R.id.apptoolbar);
        mBottomnanigation.setSelectedItemId(R.id.home);

        Toast.makeText(this, "welcome "+name+email+phone, Toast.LENGTH_SHORT).show();



    }

    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerlayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
