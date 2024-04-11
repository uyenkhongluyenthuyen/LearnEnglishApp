package com.example.learnenglishapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.learnenglishapp.model.Cau;

import java.util.ArrayList;

public class SapXepCauDB extends SQLiteOpenHelper {
    public static final String TableName= "SentenceSortTable";
    public static final String Id = "Id";
    public static final String SentenceSort = "SentenceSort";
    public static final String Part1 = "Part1";
    public static final String Part2 = "Part2";
    public static final String Part3 = "Part3";
    public static final String Part4 = "Part4";

    public SapXepCauDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //tao cau sql de tao bang Table
        String sqlCreate = "Create table if not exists "+ TableName + "("
                + Id + " Integer Primary key, "
                + SentenceSort + " Text, "
                + Part1 + " Text, "
                + Part2 + " Text, "
                + Part3 + " Text, "
                + Part4 + " Text)";
        //chay cau lenh truy van de tao bang
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //xoa bang da co
        sqLiteDatabase.execSQL("Drop table if exists " + TableName);
        //tao lai
        onCreate(sqLiteDatabase);
    }

    public  ArrayList<Cau> getAllCau(){
        ArrayList<Cau> list = new ArrayList<>();
        String sql = "Select * from " +TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            while (cursor.moveToNext()){
                Cau cau = new Cau(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5));
                list.add(cau);
            }
        }
        return list;
    }
    public void addCau(Cau cau){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, cau.getId());
        value.put(SentenceSort, cau.getSentenceSort());
        value.put(Part1, cau.getPart1());
        value.put(Part2, cau.getPart2());
        value.put(Part3, cau.getPart3());
        value.put(Part4, cau.getPart4());
        db.insert(TableName, null, value);
        db.close();

    }

    public void deleteCau(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From " + TableName + " Where ID=" +id;
        db.execSQL(sql);
        db.close();
    }
}
