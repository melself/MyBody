package com.melself.mybody;

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


public class WeightFragment extends Fragment {

    Button nextBtn2;
    ImageButton backBtn2;
    EditText inputWeight;

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
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        inputWeight = view.findViewById(R.id.inputWeight);
        nextBtn2 = view.findViewById(R.id.nextBtn2);
        backBtn2 = view.findViewById(R.id.backBtn2);

        inputWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputWeight.getText().toString().isEmpty()){
                    nextBtn2.setVisibility(View.VISIBLE);
                }
                else {
                    nextBtn2.setVisibility(View.GONE);
                }
            }
        });

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int weightInt = Integer.parseInt(inputWeight.getText().toString());
                myRef.child("Users").child(user.getUid()).child("weight").setValue(weightInt);
                Fragment fragment = new GrowthFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.replace(R.id.frameTakeInfoFragment, fragment);
                ft.commit();
            }
        });

        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AgeFragment();
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