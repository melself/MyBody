package com.melself.mybody;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import Database.SaveTaskHelper;


public class TrainerFragment extends Fragment {

    TextView nameTaskTxt, descTxt;
    Button change;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainer, container, false);

        nameTaskTxt = view.findViewById(R.id.nameTask);
        descTxt = view.findViewById(R.id.descTxt);
        change = view.findViewById(R.id.change);


        SQLiteOpenHelper star = new SaveTaskHelper(getContext());
        try {
            SQLiteDatabase db = star.getReadableDatabase();
            Cursor cursor = db.query("TASKS", null, null, null, null, null, null);

            while (cursor.moveToNext()){
                String nameTxt = cursor.getString(1);
                String descTxts = cursor.getString(3);
                if (nameTxt.equals("Жим ног")){
                    nameTaskTxt.setText(nameTxt);
                    descTxt.setText(descTxts);
                }

                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SaveTaskHelper st = new SaveTaskHelper(getContext());
                        st.updateD1(db, "Жим ног", 0);
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}