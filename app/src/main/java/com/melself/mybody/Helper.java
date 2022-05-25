package com.melself.mybody;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Database.SaveTaskHelper;

public class Helper {
    static int selectDay;
    static String checkTaskName;
    static String checkTaskDesc;

    //Навигация по спискам
    static ArrayList<String> nameTask = new ArrayList<>();
    static ArrayList<String> descTask = new ArrayList<>();
    static ArrayList<Integer> D1 = new ArrayList<>();
    static ArrayList<Integer> D2 = new ArrayList<>();
    static ArrayList<Integer> D3 = new ArrayList<>();
    static ArrayList<Integer> D4 = new ArrayList<>();
    static ArrayList<Integer> D5 = new ArrayList<>();
    static ArrayList<Integer> D6 = new ArrayList<>();
    static ArrayList<Integer> D7 = new ArrayList<>();
    static int indexList;


    //Метод получения всех данных из БД
    public void getAllData(Context context){
        SQLiteOpenHelper tsk = new SaveTaskHelper(context);
        SQLiteDatabase db = tsk.getReadableDatabase();

        try {
            Cursor cursor = db.query("TASKS", null, null, null, null, null, null);

            while (cursor.moveToNext()){
                nameTask.add(cursor.getString(1));
                descTask.add(cursor.getString(2));
                D1.add(cursor.getInt(3));
                D2.add(cursor.getInt(4));
                D3.add(cursor.getInt(5));
                D4.add(cursor.getInt(6));
                D5.add(cursor.getInt(7));
                D6.add(cursor.getInt(8));
                D7.add(cursor.getInt(9));
            }
            db.close();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Метод для вывода имени упражнения
    public String getNameTask(Context context, int i){
        String name = null;
        SQLiteOpenHelper tsk = new SaveTaskHelper(context);
        SQLiteDatabase db = tsk.getReadableDatabase();
        try {
            Cursor cursor = db.query("TASKS", null, null, null, null, null, null);

            while (cursor.moveToNext()){
                name = Helper.nameTask.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    //Метод для вывода описания упражнения
    public String getDescTask(Context context, int i){
        String desc = null;
        SQLiteOpenHelper tsk = new SaveTaskHelper(context);
        SQLiteDatabase db = tsk.getReadableDatabase();
        try {
            Cursor cursor = db.query("TASKS", null, null, null, null, null, null);

            while (cursor.moveToNext()){
                desc = Helper.descTask.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desc;
    }

    public String getNameTaskHome(Context context, String input){
        String name = null;
        String temp;
        SQLiteOpenHelper tsk = new SaveTaskHelper(context);
        SQLiteDatabase db = tsk.getReadableDatabase();
        try {
            Cursor cursor = db.query("TASKS", null, null, null, null, null, null);

            while (cursor.moveToNext()){
                temp = cursor.getString(1);
                if(temp.equals(input)){
                    name = temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getDescTaskHome(Context context, String input){
        String name;
        String desc = null;
        String temp;
        SQLiteOpenHelper tsk = new SaveTaskHelper(context);
        SQLiteDatabase db = tsk.getReadableDatabase();
        try {
            Cursor cursor = db.query("TASKS", null, null, null, null, null, null);

            while (cursor.moveToNext()){
                name = cursor.getString(1);
                temp = cursor.getString(2);
                if(name.equals(input)){
                    desc = temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desc;
    }
}
