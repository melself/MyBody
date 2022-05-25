package com.melself.mybody;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import Database.SaveTaskHelper;

public class TaskChekFragment extends Fragment {

    TextView tvNameTask, tvDescTask;
    Button confAddBtm;
    ImageButton backToSelect;
    ImageView imageTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_chek, container, false);

        tvNameTask = view.findViewById(R.id.tvNameTask);
        tvDescTask = view.findViewById(R.id.tvDescTask);
        confAddBtm = view.findViewById(R.id.confAddBtn);
        backToSelect = view.findViewById(R.id.backToSelect);
        imageTask = view.findViewById(R.id.imageTask);

        SQLiteOpenHelper tsk = new SaveTaskHelper(getContext());
        SQLiteDatabase db = tsk.getReadableDatabase();

        tvNameTask.setText(Helper.checkTaskName);
        tvDescTask.setText(Helper.checkTaskDesc);
        imageTask.setImageResource(Helper.checkIMG);

        backToSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SelectTaskFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainFrameForFragments, fragment);
                ft.commit();
            }
        });

        confAddBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Helper.selectDay == 1){
                    SaveTaskHelper d1 = new SaveTaskHelper(getContext());
                    d1.updateD1(db, Helper.checkTaskName, 1);
                }
                else if(Helper.selectDay == 2){
                    SaveTaskHelper d2 = new SaveTaskHelper(getContext());
                    d2.updateD2(db, Helper.checkTaskName, 1);
                }
                else if(Helper.selectDay == 3){
                    SaveTaskHelper d3 = new SaveTaskHelper(getContext());
                    d3.updateD3(db, Helper.checkTaskName, 1);
                }
                else if(Helper.selectDay == 4){
                    SaveTaskHelper d4 = new SaveTaskHelper(getContext());
                    d4.updateD4(db, Helper.checkTaskName, 1);
                }
                else if(Helper.selectDay == 5){
                    SaveTaskHelper d5 = new SaveTaskHelper(getContext());
                    d5.updateD5(db, Helper.checkTaskName, 1);
                }
                else if(Helper.selectDay == 6){
                    SaveTaskHelper d6 = new SaveTaskHelper(getContext());
                    d6.updateD6(db, Helper.checkTaskName, 1);
                }
                else if(Helper.selectDay == 7){
                    SaveTaskHelper d7 = new SaveTaskHelper(getContext());
                    d7.updateD7(db, Helper.checkTaskName, 1);
                }
            }
        });

        return view;
    }
}