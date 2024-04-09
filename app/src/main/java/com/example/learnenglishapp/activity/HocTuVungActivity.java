package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.learnenglishapp.Interface.IClickItemTuVungListener;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.adapter.TuVungAdapter;
import com.example.learnenglishapp.model.TuVung;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class HocTuVungActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    String DATABASE_NAME="learnEnglish.db";
    private ImageView imgBack;
    private RecyclerView rcvTuVung;
    private Button btnOnTap;

    private List<TuVung> listTuVung;

    private SQLiteDatabase db;
    private TuVungAdapter tuVungAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_tu_vung);

        //Gọi hàm Copy CSDL từ assets vào thư mục Databases
        processCopy();
        //Mở CSDL lên để dùng
        database = openOrCreateDatabase("learnEnglish.db",MODE_PRIVATE, null);
        anhxa();
        //sử dung GridLayoutManager đẻ set up số cột cho layout của rcv
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvTuVung.setLayoutManager(gridLayoutManager);

        listTuVung = new ArrayList<>();
        tuVungAdapter = new TuVungAdapter(listTuVung, new IClickItemTuVungListener() {
            @Override
            public void onClickItemTuVung(TuVung tuVung) {

            }
        });
        rcvTuVung.setAdapter(tuVungAdapter);

        // Truy vấn CSDL và cập nhật hiển thị lên Listview
        Cursor c = database.query("tbTuVung",null,null,null,null,null,null);
        c.moveToFirst();
        TuVung tuVung;
        while (c.isAfterLast() == false)
        {
            tuVung = new TuVung(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4), c.getBlob(5));
            listTuVung.add(tuVung);
            c.moveToNext();
        }
        c.close();
        tuVungAdapter.notifyDataSetChanged();

    }

    private void anhxa() {
        imgBack = findViewById(R.id.imgVBackDSTV);
        rcvTuVung = findViewById(R.id.rcvTuVung);
        btnOnTap = findViewById(R.id.btnOnTap);
    }

    private void processCopy() {
//private app
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try{CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    public void CopyDataBaseFromAsset() {
// TODO Auto-generated method stub
        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);
// Path to the just created empty db
            String outFileName = getDatabasePath();
// if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
// Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
// transfer bytes from the inputfile to the outputfile
// Truyền bytes dữ liệu từ input đến output
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            myOutput.write(buffer);
// Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}