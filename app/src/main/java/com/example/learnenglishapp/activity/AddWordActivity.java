package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.database.TuVungDB;
import com.example.learnenglishapp.model.TuVung;

import java.util.ArrayList;
import java.util.List;

public class AddWordActivity extends AppCompatActivity {
    private EditText edtTuTA, edtPhienAm, edtNghiaTV;
    private Spinner spinnerTuLoai;
    private String[] listTuLoai;
    private ArrayAdapter<String> adapterTuLoai;
    private Button btnAdd;
    private ImageView imgBack;
    private List<TuVung> listTuVung;
    private TuVungDB tuVungDB;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        anhxa();
        tuVungDB = new TuVungDB(this, "EnglishDatabase", null, 1);
        listTuVung = new ArrayList<>();
        listTuVung = tuVungDB.getAllWord();
        listTuLoai = new String[]{"Động từ", "Danh từ", "Tính từ"};
        adapterTuLoai = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listTuLoai);
        // Thiết lập dropdown layout
        adapterTuLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Gán adapter cho Spinner
        spinnerTuLoai.setAdapter(adapterTuLoai);
        event();

    }

    private void event() {
        //imgBack click
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //btnAdd click
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = edtTuTA.getText().toString();
                String phienAm = edtPhienAm.getText().toString();
                String nghiaTv = edtNghiaTV.getText().toString();
                //String tuLoai = edtTuLoai.getText().toString();
                String tuLoai = spinnerTuLoai.getSelectedItem().toString();
                if(word.trim().equals("") || phienAm.trim().equals("") || nghiaTv.trim().equals("") || tuLoai.trim().equals("")){
                    Toast.makeText(AddWordActivity.this, "Fill in full information", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!listTuVung.isEmpty()) {
                    TuVung lastItem = listTuVung.get(listTuVung.size() - 1);
                    int lastItemId = lastItem.getId();
                    // Sử dụng lastItemId ở đây
                    int newId = lastItemId +1;
                    TuVung newWord = new TuVung(newId, word, phienAm, nghiaTv, tuLoai,"w_anhmacdinh");
                    tuVungDB.AddWord(newWord);
                    Toast.makeText(AddWordActivity.this, "Add successfull", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddWordActivity.this, "Add fail", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(AddWordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        
    }

    private void anhxa() {
        edtTuTA = findViewById(R.id.edtTuTA);
        edtPhienAm = findViewById(R.id.edtPhienAm);
        edtNghiaTV = findViewById(R.id.edtNghiaTV);
        spinnerTuLoai = findViewById(R.id.spinnerTuLoai);
        btnAdd = findViewById(R.id.btnAdd);
        imgBack = findViewById(R.id.imgBack);
    }
}