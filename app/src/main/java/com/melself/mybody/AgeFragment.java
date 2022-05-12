package com.melself.mybody;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class AgeFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myRef;

    DatePicker datePicker;
    TextView dateTextView;
    Button nextBtn1;
    ImageButton backBtn1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        datePicker = view.findViewById(R.id.datePicker);
        dateTextView = view.findViewById(R.id.ageTxt);
        nextBtn1 = view.findViewById(R.id.nextBtn1);
        backBtn1 = view.findViewById(R.id.backBtn1);

        datePicker.init(2000, 02, 01, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);
                dateTextView.setText(Integer.toString(calculateAge(c.getTimeInMillis())));

                if (!dateTextView.getText().toString().equals("0")){
                    nextBtn1.setVisibility(View.VISIBLE);
                }
                else {
                    nextBtn1.setVisibility(View.GONE);
                }
            }
        });




        backBtn1.setOnClickListener(new View.OnClickListener() {
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

        nextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ageInt = Integer.parseInt(dateTextView.getText().toString());
                myRef.child("Users").child(user.getUid()).child("year").setValue(ageInt);

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

    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }

}