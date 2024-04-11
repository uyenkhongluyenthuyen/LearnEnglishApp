package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.learnenglishapp.Interface.IClickItemTuVungListener;
import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.adapter.TuVungAdapter;
import com.example.learnenglishapp.database.TuVungDB;
import com.example.learnenglishapp.model.TuVung;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class HocTuVungActivity extends AppCompatActivity {
    private ImageView imgBack;
    private RecyclerView rcvTuVung;
    private Button btnOnTap;
    private List<TuVung> listTuVung;
    private TuVungAdapter tuVungAdapter;

    private TuVungDB wordDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_tu_vung);

        anhxa();
        //tao database
        wordDb = new TuVungDB(this, "EnglishDatabase", null, 1);
        //them du lieu dau vao
        //addData();
        //wordDb.deleteWord(3);
        //sử dung GridLayoutManager đẻ set up số cột cho layout của rcv
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvTuVung.setLayoutManager(gridLayoutManager);

        listTuVung = new ArrayList<>();
        listTuVung = wordDb.getAllWord();
        tuVungAdapter = new TuVungAdapter(listTuVung, new IClickItemTuVungListener() {
            @Override
            public void onClickItemTuVung(TuVung tuVung) {

            }
        });
        rcvTuVung.setAdapter(tuVungAdapter);
        tuVungAdapter.notifyDataSetChanged();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HocTuVungActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnOnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HocTuVungActivity.this, TestTuVungActivity.class);
                startActivity(intent);
            }
        });
    }



    private void addData() {
        wordDb.AddWord(new TuVung(0, "President", "/ˈprezəˌdent/", "Tổng thống", "Danh từ", "w_president"));
        wordDb.AddWord(new TuVung(1, "Customer", "/ˈkəstəmər/", "Khách hàng", "Danh từ", "w_customer"));
        wordDb.AddWord(new TuVung(2, "Item", "/ˈīdəm/", "Món hàng", "Danh từ", "w_item"));
        wordDb.AddWord(new TuVung(3, "Advise", "/ədˈvīz/", "Khuyên bảo", "Động từ", "w_advise"));
        wordDb.AddWord(new TuVung(4, "Beautiful", "/ˈbyo͞odəfəl/", "Xinh đẹp", "Tính từ", "w_beautiful"));
        wordDb.AddWord(new TuVung(5, "Run", "/rən/", "Chạy", "Động từ", "w_run"));
    }

    private void anhxa() {
        imgBack = findViewById(R.id.imgVBackDSTV);
        rcvTuVung = findViewById(R.id.rcvTuVung);
        btnOnTap = findViewById(R.id.btnOnTap);
    }



}