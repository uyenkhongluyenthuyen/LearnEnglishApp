package com.example.learnenglishapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.learnenglishapp.model.TracNghiem;

import java.util.ArrayList;

public class TracNghiemDB extends SQLiteOpenHelper {
    public static final String SentenceTable= "SentenceTable";
    public static final String Id_Sentence = "Id";
    public static final String Sentence = "Sentence";
    public static final String AnswerA = "AnswerA";
    public static final String AnswerB = "AnswerB";
    public static final String AnswerC = "AnswerC";
    public static final String AnswerD = "AnswerD";
    public static final String CorrectAnswer = "CorrectAnswer";

    public TracNghiemDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Tạo bảng SentenceTable
        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + SentenceTable + "("
                + Id_Sentence + " INTEGER PRIMARY KEY, "
                + Sentence + " TEXT, "
                + AnswerA + " TEXT, "
                + AnswerB + " TEXT, "
                + AnswerC + " TEXT, "
                + AnswerD + " TEXT, "
                + CorrectAnswer + " TEXT)";
        // Thực thi lệnh SQL để tạo bảng
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //xoa bang da co
        sqLiteDatabase.execSQL("Drop table if exists " + SentenceTable);
        //tao lai
        onCreate(sqLiteDatabase);
    }

    public ArrayList<TracNghiem> getAllTracNghiem(){
        ArrayList<TracNghiem> list = new ArrayList<>();
        //cau lenh truy van
        String sql = "Select * from " + SentenceTable;
        //lay doi tuowng csdl sqlite
        SQLiteDatabase db = this.getReadableDatabase();
        //chay cau lenh truy van tra ve dang Cursor
        Cursor cursor = db.rawQuery(sql, null);
        //tao Arraylist de tra ve
        if(cursor != null){
            while (cursor.moveToNext()){
                TracNghiem sentence = new TracNghiem(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5),cursor.getString(6));
                list.add(sentence);

            }
        }
        return list;
    }

    public void addSentence(TracNghiem tracNghiem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id_Sentence, tracNghiem.getId());
        values.put(Sentence, tracNghiem.getCauTA());
        values.put(AnswerA, tracNghiem.getDa_A());
        values.put(AnswerB, tracNghiem.getDa_B());
        values.put(AnswerC, tracNghiem.getDa_C());
        values.put(AnswerD, tracNghiem.getDa_D());
        values.put(CorrectAnswer, tracNghiem.getDa_True());
        db.insert(SentenceTable, null, values);
    }
}
