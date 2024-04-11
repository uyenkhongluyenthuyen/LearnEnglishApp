package com.example.learnenglishapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.learnenglishapp.model.TuVung;

import java.util.ArrayList;

public class TuVungDB extends SQLiteOpenHelper {

    public static final String TableName= "WordTable";
    public static final String Id = "Id";
    public static final String Word = "Word";
    public static final String PhienAm = "PhienAm";
    public static final String Meaning = "Meaning";
    public static final String TuLoai ="TuLoai";
    public static final String Image = "Image";

    public TuVungDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //tao cau sql de tao bang Table
        String sqlCreate = "Create table if not exists "+ TableName + "("
                + Id + " Integer Primary key, "
                + Word + " Text, "
                + PhienAm + " Text, "
                + Meaning + " Text, "
                + TuLoai + " Text, "
                + Image + " Text)";
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

    public ArrayList<TuVung> getAllWord(){
        ArrayList<TuVung> list = new ArrayList<>();
        //cau lenh truy van
        String sql = "Select * from " + TableName;
        //lay doi tuowng csdl sqlite
        SQLiteDatabase db = this.getReadableDatabase();
        //chay cau lenh truy van tra ve dang Cursor
        Cursor cursor = db.rawQuery(sql, null);
        //tao Arraylist de tra ve
        if(cursor != null){
            while (cursor.moveToNext()){
                TuVung word = new TuVung(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5));
                list.add(word);

            }
        }
        return list;
    }

    public void AddWord(TuVung word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, word.getId());
        value.put(Word, word.getTuTA());
        value.put(PhienAm, word.getPhienAm());
        value.put(Meaning, word.getNghiaTV());
        value.put(TuLoai, word.getTuLoai());
        value.put(Image, word.getHinhAnh());
        db.insert(TableName, null, value);
        db.close();
    }

    public void deleteWord(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From " + TableName + " Where ID=" +id;
        db.execSQL(sql);
        db.close();
    }
}
