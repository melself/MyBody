package com.melself.mybody;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import Database.SaveTaskHelper;

public class ChekForHomeTask extends Fragment {

    TextView tvNameTask2, tvDescTask2;
    Button removeTaskBtn;
    ImageButton backToHome;
    ImageView imageTask2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chek_for_home_task, container, false);

        tvNameTask2 = view.findViewById(R.id.tvNameTask2);
        tvDescTask2 = view.findViewById(R.id.tvDescTask2);
        removeTaskBtn = view.findViewById(R.id.removeTaskBtn);
        backToHome = view.findViewById(R.id.backToHome);
        imageTask2 = view.findViewById(R.id.imageTask2);

        SQLiteOpenHelper tsk = new SaveTaskHelper(getContext());
        SQLiteDatabase db = tsk.getReadableDatabase();

        tvNameTask2.setText(Helper.checkTaskName);
        tvDescTask2.setText(Helper.checkTaskDesc);
        imageTask2.setImageResource(Helper.checkIMG);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainFrameForFragments, fragment);
                ft.commit();
            }
        });

        removeTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Helper.selectDay == 1){
                    System.out.println(Helper.checkTaskName);
                    SaveTaskHelper d1 = new SaveTaskHelper(getContext());
                    d1.updateD1(db, Helper.checkTaskName, 0);
                }
                else if(Helper.selectDay == 2){
                    SaveTaskHelper d2 = new SaveTaskHelper(getContext());
                    d2.updateD2(db, Helper.checkTaskName, 0);
                }
                else if(Helper.selectDay == 3){
                    SaveTaskHelper d3 = new SaveTaskHelper(getContext());
                    d3.updateD3(db, Helper.checkTaskName, 0);
                }
                else if(Helper.selectDay == 4){
                    SaveTaskHelper d4 = new SaveTaskHelper(getContext());
                    d4.updateD4(db, Helper.checkTaskName, 0);
                }
                else if(Helper.selectDay == 5){
                    SaveTaskHelper d5 = new SaveTaskHelper(getContext());
                    d5.updateD5(db, Helper.checkTaskName, 0);
                }
                else if(Helper.selectDay == 6){
                    SaveTaskHelper d6 = new SaveTaskHelper(getContext());
                    d6.updateD6(db, Helper.checkTaskName, 0);
                }
                else if(Helper.selectDay == 7){
                    SaveTaskHelper d7 = new SaveTaskHelper(getContext());
                    d7.updateD7(db, Helper.checkTaskName, 0);
                }
            }
        });

        return view;
    }
}