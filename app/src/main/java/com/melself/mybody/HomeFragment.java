package com.melself.mybody;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Database.SaveTaskHelper;


public class HomeFragment extends Fragment {

    ImageButton addBtn;
    Button d1, d2, d3, d4, d5, d6, d7;

    ListView myListTask;
    ArrayAdapter<String> myAdapter;

    TextView selectDayTxt;

    ArrayList<String> D1List = new ArrayList<>();
    ArrayList<String> D2List = new ArrayList<>();
    ArrayList<String> D3List = new ArrayList<>();
    ArrayList<String> D4List = new ArrayList<>();
    ArrayList<String> D5List = new ArrayList<>();
    ArrayList<String> D6List = new ArrayList<>();
    ArrayList<String> D7List = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        myListTask = view.findViewById(R.id.myListTask);
        addBtn = view.findViewById(R.id.addBtn);

        d1 = view.findViewById(R.id.d1);
        d2 = view.findViewById(R.id.d2);
        d3 = view.findViewById(R.id.d3);
        d4 = view.findViewById(R.id.d4);
        d5 = view.findViewById(R.id.d5);
        d6 = view.findViewById(R.id.d6);
        d7 = view.findViewById(R.id.d7);

        selectDayTxt = view.findViewById(R.id.selectDayTxt);

        ////////////////////

        addBtn.setVisibility(View.GONE);

        setListTask();
        openTask();

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn.setVisibility(View.VISIBLE);
                Helper.selectDay = 1;
                selectDayTxt.setText("Понедельник");
                myAdapter  = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, D1List);
                myListTask.setAdapter(myAdapter);
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn.setVisibility(View.VISIBLE);
                Helper.selectDay = 2;
                selectDayTxt.setText("Вторник");
                myAdapter  = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, D2List);
                myListTask.setAdapter(myAdapter);
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn.setVisibility(View.VISIBLE);
                Helper.selectDay = 3;
                selectDayTxt.setText("Среда");
                myAdapter  = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, D3List);
                myListTask.setAdapter(myAdapter);
            }
        });
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn.setVisibility(View.VISIBLE);
                Helper.selectDay = 4;
                selectDayTxt.setText("Четверг");
                myAdapter  = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, D4List);
                myListTask.setAdapter(myAdapter);
            }
        });
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn.setVisibility(View.VISIBLE);
                Helper.selectDay = 5;
                selectDayTxt.setText("Пятница");
                myAdapter  = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, D5List);
                myListTask.setAdapter(myAdapter);
            }
        });
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn.setVisibility(View.VISIBLE);
                Helper.selectDay = 6;
                selectDayTxt.setText("Суббота");
                myAdapter  = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, D6List);
                myListTask.setAdapter(myAdapter);
            }
        });
        d7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn.setVisibility(View.VISIBLE);
                Helper.selectDay = 7;
                selectDayTxt.setText("Воскресенье");
                myAdapter  = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, D7List);
                myListTask.setAdapter(myAdapter);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 21) {
                    getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.dark));
                }
                Fragment fragment = new SelectTaskFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainFrameForFragments, fragment);
                ft.commit();
            }
        });
        return view;
    }

    public void openTask(){
        Helper helper = new Helper();
        myListTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String inp = (String) myListTask.getItemAtPosition(i);

                Helper.indexList = i;
                Helper.checkTaskName = helper.getNameTaskHome(getContext(), inp);
                Helper.checkTaskDesc = helper.getDescTaskHome(getContext(),inp);
                Helper.checkIMG = helper.getIMGTaskHome(getContext(), inp);

                Fragment fragment = new ChekForHomeTask();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainFrameForFragments, fragment);
                ft.commit();
            }
        });
    }

    public void setListTask(){
        SQLiteOpenHelper chd = new SaveTaskHelper(getContext());
        try {
            SQLiteDatabase db = chd.getReadableDatabase();
            Cursor cursor = db.query("TASKS", null, null, null, null, null, null);

            while (cursor.moveToNext()){
                String tskName = cursor.getString(1);;
                String D1 = cursor.getString(3);
                String D2 = cursor.getString(4);
                String D3 = cursor.getString(5);
                String D4 = cursor.getString(6);
                String D5 = cursor.getString(7);
                String D6 = cursor.getString(8);
                String D7 = cursor.getString(9);
                if (D1.equals("1")){
                    D1List.add(tskName);
                }
                else if (D1.equals("0")){
                    D1List.remove(tskName);
                }
                if (D2.equals("1")){
                    D2List.add(tskName);
                }
                else if (D2.equals("0")){
                    D2List.remove(tskName);
                }
                if (D3.equals("1")){
                    D3List.add(tskName);
                }
                else if (D3.equals("0")){
                    D3List.remove(tskName);
                }
                if (D4.equals("1")){
                    D4List.add(tskName);
                }
                else if (D4.equals("0")){
                    D4List.remove(tskName);
                }
                if (D5.equals("1")){
                    D5List.add(tskName);
                }
                else if (D5.equals("0")){
                    D5List.remove(tskName);
                }
                if (D6.equals("1")){
                    D6List.add(tskName);
                }
                else if (D6.equals("0")){
                    D6List.remove(tskName);
                }
                if (D7.equals("1")){
                    D7List.add(tskName);
                }
                else if (D7.equals("0")){
                    D7List.remove(tskName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}