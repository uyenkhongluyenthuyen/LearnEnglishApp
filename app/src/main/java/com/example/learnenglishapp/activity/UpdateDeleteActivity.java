package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.database.TuVungDB;
import com.example.learnenglishapp.model.TuVung;

import java.util.ArrayList;
import java.util.List;

public class UpdateDeleteActivity extends AppCompatActivity {
    private EditText edtTuTA, edtPhienAm, edtNghiaTV;
    private Spinner spinnerTuLoai;
    private String[] listTuLoai;
    private ArrayAdapter<String> adapterTuLoai;
    private Button btnUpdate, btnDelete ;
    private ImageView imgBack, imgAnh;
    private TuVungDB tuVungDB;

    private int id; //  selectedWordID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        anhxa();
        tuVungDB = new TuVungDB(this, "EnglishDatabase", null, 1);

        listTuLoai = new String[]{"Động từ", "Danh từ", "Tính từ"};
        adapterTuLoai = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listTuLoai);
        // Thiết lập dropdown layout
        adapterTuLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Gán adapter cho Spinner
        spinnerTuLoai.setAdapter(adapterTuLoai);
        event();

        //getData()
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b != null){
            id = b.getInt("Id");
            String word = b.getString("Word");
            String pronunciation = b.getString("Pronunciation");
            String meaning = b.getString("Meaning");
            String wordType = b.getString("WordType");
            String image = b.getString("Image");

            edtTuTA.setText(word);
            edtPhienAm.setText(pronunciation);
            edtNghiaTV.setText(meaning);
            int position = getPosition(wordType);
            if(position!=-1){
                spinnerTuLoai.setSelection(position);
            }

            int drawerId = this.getResources().getIdentifier(image, "drawable", this.getPackageName());
            Glide.with(this).load(drawerId).into(imgAnh);
        }
    }

    //get position cua TuLoai
    public int getPosition(String tuloai){
        int position=-1;
        for (int i =0; i< listTuLoai.length; i++){
            if(listTuLoai[i].trim().toUpperCase().equals(tuloai.trim().toUpperCase())){
                position = i;
                break;
            }
        }
        return position;
    }

    private void event() {
        //imgBack click
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateDeleteActivity.this, HocTuVungActivity.class);
                startActivity(intent);
            }
        });
        //btnUpdate click
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = edtTuTA.getText().toString();
                String pronunciation = edtPhienAm.getText().toString();
                String meaning = edtNghiaTV.getText().toString();
                String wordType = spinnerTuLoai.getSelectedItem().toString();
                String image = "w_anhmacdinh";
                TuVung tuVung = new TuVung(id, word, pronunciation, meaning, wordType, image);
                tuVungDB.updateWord(id, tuVung) ;
                Intent intent = new Intent(UpdateDeleteActivity.this, HocTuVungActivity.class);
                startActivity(intent);
            }
        });

        //btnDelete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuVungDB.deleteWord(id);
                Intent intent = new Intent(UpdateDeleteActivity.this, HocTuVungActivity.class);
                startActivity(intent);
            }
        });

    }

    private void anhxa() {
        edtTuTA = findViewById(R.id.edtTuTA);
        edtPhienAm = findViewById(R.id.edtPhienAm);
        edtNghiaTV = findViewById(R.id.edtNghiaTV);
        spinnerTuLoai = findViewById(R.id.spinnerTuLoai);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        imgBack = findViewById(R.id.imgBack);
        imgAnh = findViewById(R.id.imgAnh);
    }
}