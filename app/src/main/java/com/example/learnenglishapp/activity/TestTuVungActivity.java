package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.database.TuVungDB;
import com.example.learnenglishapp.model.TuVung;

import java.util.ArrayList;
import java.util.List;

public class TestTuVungActivity extends AppCompatActivity {
    private ImageView imgHinh;
    private TextView txtQuestion;
    private EditText edtYourAnswer;
    private TextView tvScore;
    private TextView tvWord;
    private Button btnQuit;
    private Button btnNext;
    private List<TuVung> listTuVung;
    private TuVungDB wordDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tu_vung);
        anhxa();
        wordDb = new TuVungDB(this, "EnglishDatabase", null, 1);

        listTuVung = new ArrayList<>();
        listTuVung = wordDb.getAllWord();
        if(listTuVung!=null){
            displayListQuestion();
        }else{
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
        }


        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TestTuVungActivity.this, HocTuVungActivity.class);
                startActivity(intent);
            }
        });

    }


    private void displayQuestion(TuVung tuVung){
        //imgHinh.setImageResource(R.drawable.w_president);
        int drawableResourceId =this.getResources().getIdentifier(tuVung.getHinhAnh(), "drawable", getPackageName());
        Glide.with(this).load(drawableResourceId).into(imgHinh);
        txtQuestion.setText("("+tuVung.getTuLoai()+"):"+tuVung.getPhienAm()+" "+tuVung.getNghiaTV());

    }
    private int currentIndex = 0;
    public int score;

    private void displayListQuestion(){
        displayQuestion(listTuVung.get(currentIndex));
        score = 0;
        tvWord.setText("Word: "+String.valueOf(currentIndex+1)+"/"+listTuVung.size());
        int words = listTuVung.size();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check dap an
                String answer= listTuVung.get(currentIndex).getTuTA().toUpperCase();
                if(edtYourAnswer.getText().toString().toUpperCase().equals(answer)){
                    Toast.makeText(TestTuVungActivity.this, "Correct ^-^", Toast.LENGTH_SHORT).show();
                    score +=5;

                }else{
                    Toast.makeText(TestTuVungActivity.this, "Incorrect :(", Toast.LENGTH_SHORT).show();
                }

                // Tăng chỉ mục lên 1 để chuyển sang câu hỏi tiếp theo
                currentIndex++;
                // Kiểm tra xem chỉ mục có vượt quá số lượng câu hỏi trong danh sách không
                if (currentIndex < listTuVung.size()) {
                    // Nếu không, hiển thị câu hỏi tiếp theo
                    displayQuestion(listTuVung.get(currentIndex));
                    edtYourAnswer.setText("");
                    //set lai score
                    tvScore.setText("Score: "+ String.valueOf(score));
                    //set lai currentQuestion
                    tvWord.setText("Word: "+String.valueOf(currentIndex+1)+"/"+listTuVung.size());
                } else {
                    // Nếu đã hết câu hỏi, thì chuyển qua màn hình result
                    Intent intent = new Intent(TestTuVungActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("Score", score);
                    String questiontrue = String.valueOf(score/5 +"/"+listTuVung.size());
                    b.putString("QuestionTrue", questiontrue);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });
    }



    private void anhxa() {
        imgHinh = findViewById(R.id.imgHinh);
        txtQuestion = findViewById(R.id.txtQuestion);
        edtYourAnswer= findViewById(R.id.edtYourAnswer);
        tvScore=findViewById(R.id.tvScore);
        tvWord= findViewById(R.id.tvWord);
        btnQuit = findViewById(R.id.btnQuitHTV);
        btnNext=findViewById(R.id.btnNext);
    }
}