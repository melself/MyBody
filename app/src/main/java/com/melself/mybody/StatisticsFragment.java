package com.melself.mybody;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatisticsFragment extends Fragment {

    TextView genderStat;
    EditText growthStat, weightStat;
    Button updateStat;

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
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        growthStat = view.findViewById(R.id.editTextGrowth);
        genderStat = view.findViewById(R.id.genderStat);
        weightStat = view.findViewById(R.id.editTextWeight);
        updateStat = view.findViewById(R.id.updateStat);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        updateStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int weight = Integer.parseInt(weightStat.getText().toString());
                int growth = Integer.parseInt(growthStat.getText().toString());
                myRef.child("Users").child(user.getUid()).child("growth").setValue(growth);
                myRef.child("Users").child(user.getUid()).child("weight").setValue(weight);
                Toast.makeText(getContext(), "Статистика обновлена!", Toast.LENGTH_SHORT).show();
            }
        });

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object weightFrom = snapshot.child(user.getUid()).child("weight").getValue();
                weightStat.setText(weightFrom.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object growthFrom = snapshot.child(user.getUid()).child("growth").getValue();
                growthStat.setText(growthFrom.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object genderFrom = snapshot.child(user.getUid()).child("sex").getValue();
                genderStat.setText(genderFrom.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}