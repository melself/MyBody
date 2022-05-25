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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NameFragment extends Fragment {

    Button nextBtn5;
    ImageButton backBtn5;
    EditText inputName;

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
        View view = inflater.inflate(R.layout.fragment_name, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        inputName = view.findViewById(R.id.inputName);
        nextBtn5 = view.findViewById(R.id.nextBtn5);
        backBtn5 = view.findViewById(R.id.backBtn5);

        inputName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputName.getText().toString().isEmpty()){
                    nextBtn5.setVisibility(View.VISIBLE);
                }
                else {
                    nextBtn5.setVisibility(View.GONE);
                }
            }
        });

        nextBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = inputName.getText().toString();
                myRef.child("Users").child(user.getUid()).child("name").setValue(nameStr);

                Fragment fragment = new WeightFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.replace(R.id.frameTakeInfoFragment, fragment);
                ft.commit();
            }
        });

        backBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SexFragment();
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