package com.melself.mybody;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GrowthFragment extends Fragment {

    Button nextBtn3;
    ImageButton backBtn3;
    EditText inputGrowth;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_growth, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        inputGrowth = view.findViewById(R.id.inputGrowth);
        nextBtn3 = view.findViewById(R.id.nextBtn3);
        backBtn3 = view.findViewById(R.id.backBtn3);

        inputGrowth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputGrowth.getText().toString().isEmpty()){
                    nextBtn3.setVisibility(View.VISIBLE);
                }
                else {
                    nextBtn3.setVisibility(View.GONE);
                }
            }
        });

        nextBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int growthInt = Integer.parseInt(inputGrowth.getText().toString());
                myRef.child("Users").child(user.getUid()).child("growth").setValue(growthInt);
                Intent openMainActivity = new Intent(getContext(), MainActivity.class);
                startActivity(openMainActivity);
            }
        });

        backBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new WeightFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.replace(R.id.frameTakeInfoFragment, fragment);
                ft.commit();
            }
        });

        return view;
    }
}