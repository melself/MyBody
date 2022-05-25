package com.melself.mybody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Helper helper = new Helper();
        helper.getAllData(this);

        replaceFragment(new HomeFragment());
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkGray));
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    if (Build.VERSION.SDK_INT >= 21) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkGray));
                    }
                    break;

                case R.id.statistics:
                    replaceFragment(new StatisticsFragment());
                    if (Build.VERSION.SDK_INT >= 21) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.dark));
                    }
                    break;

                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    if (Build.VERSION.SDK_INT >= 21) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.dark));
                    }
                    break;
            }
            return true;
        });
    }



    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.mainFrameForFragments, fragment);
        ft.commit();
    }
}