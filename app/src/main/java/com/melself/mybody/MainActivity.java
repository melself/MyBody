package com.melself.mybody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                case R.id.trainer:
                    replaceFragment(new TrainerFragment());
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