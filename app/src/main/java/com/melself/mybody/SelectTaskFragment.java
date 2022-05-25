package com.melself.mybody;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import Database.SaveTaskHelper;

public class SelectTaskFragment extends Fragment {

    ListView listTask;
    ArrayAdapter<String> adapter;
    ArrayList<String> nameTasksArray = new ArrayList<>();

    ImageButton backToHome;

    Helper helper = new Helper();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_task, container, false);
        //Находим элементы из XML файла
        listTask = view.findViewById(R.id.listTask);
        backToHome = view.findViewById(R.id.backToHome);
        //Ставим адаптер на ListView
        adapter  = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, nameTasksArray);
        listTask.setAdapter(adapter);

        setTasks();

        //Слушатель на ListView
        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Helper.indexList = i;
                Helper.checkTaskName = helper.getNameTask(getContext(),Helper.indexList);
                Helper.checkTaskDesc = helper.getDescTask(getContext(),Helper.indexList);

                Fragment fragment = new TaskChekFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainFrameForFragments, fragment);
                ft.commit();
            }
        });

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 21) {
                    getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.darkGray));
                }
                Fragment fragment = new HomeFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainFrameForFragments, fragment);
                ft.commit();
            }
        });
        return view;
    }

    //Добавляем из БД все задания в список
    public void setTasks(){
        nameTasksArray.addAll(Helper.nameTask);
    }

}