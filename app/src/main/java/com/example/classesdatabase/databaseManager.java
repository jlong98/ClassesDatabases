package com.example.classesdatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class databaseManager extends SQLiteOpenHelper {
    public databaseManager(Context context) { super(context, "ClassesDB", null, 1); }

    public void onCreate(SQLiteDatabase db){
        String sql ="create table ClassesTable (id integer primary key autoincrement, title text, teacher text, semester text)";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
    public void insert(String className, String teacherName, String semester){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into ClassesTable values(";
        sql+= "null, '"+className+"', '"+teacherName+"', '"+semester+"')";
        db.execSQL(sql);
        db.close();
    }
    public ArrayList<String> getTitles(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from ClassesTable";
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            String title = cursor.getString(1);
            list.add(title);
        }
        db.close();
        return list;
    }
    public ArrayList<String> getD(String teacherName){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from ClassesTable where teacher = '"+teacherName+"'";
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            String title = cursor.getString(1);
            list.add(title);
        }
        db.close();
        return list;
    }
    public void updateByTitle(String title, String teacher){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update ClassesTable set teacher = '"+teacher+"' where title = '"+title+"' ";
        db.execSQL(sql);
        db.close();
    }

    public String[] get(String className){
        String[] entry = new String[3];
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from ClassesTable where title = '"+className+"'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String title = cursor.getString(1);
            String teacher = cursor.getString(2);
            String semester = cursor.getString(3);
            entry[0]=title;
            entry[1]=teacher;
            entry[2]=semester;
        }
        db.close();
        return entry;
    }
    public void deleteByName(String name){
        SQLiteDatabase db = getWritableDatabase();
        String data = "delete from ClassesTable where title = '"+name+"'";
        db.execSQL(data);
    }


}
