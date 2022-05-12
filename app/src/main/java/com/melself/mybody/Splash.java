package com.melself.mybody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private int growProf, ageProf, weightProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null){
                    startActivity(new Intent(Splash.this, MainActivity.class));
                }
                else {
                    startActivity(new Intent(Splash.this, AuthActivity.class));
                }
            }
        }, 100);
    }

//    public int getGrowProfile(){
//        myRef.child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Object growFrom = snapshot.child(user.getUid()).child("grow").getValue();
//                growProf = (int) growFrom;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return growProf;
//    }
//
//    public int getWeightProfile(){
//        myRef.child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Object weightFrom = snapshot.child(user.getUid()).child("weight").getValue();
//                weightProf = (int) weightFrom;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return weightProf;
//    }
//
//    public int getAgeProfile(){
//        myRef.child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Object ageFrom = snapshot.child(user.getUid()).child("age").getValue();
//                ageProf = (int) ageFrom;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return ageProf;
//    }
}