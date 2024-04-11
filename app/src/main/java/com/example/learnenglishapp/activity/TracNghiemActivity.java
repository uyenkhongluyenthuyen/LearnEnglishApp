package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.database.TracNghiemDB;
import com.example.learnenglishapp.database.TuVungDB;
import com.example.learnenglishapp.model.TracNghiem;

import java.util.ArrayList;
import java.util.List;

public class TracNghiemActivity extends AppCompatActivity {

    private TracNghiemDB tracNghiemDB;
    List<TracNghiem> listTracNghiem;
    private TextView tvQuestion, tvQuestionCount, tvScore;
    private RadioGroup rdoGChoice;

    private RadioButton op1,op2, op3, op4;

    private Button btnQuit, btnConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trac_nghiem);
        anhxa();
        //tao database
        tracNghiemDB = new TracNghiemDB(this, "TracNghiemDB", null, 1);
        listTracNghiem = new ArrayList<>();
        //add du lieu
        //addTracNghiem();
        //lay ra list cau hoi trac nghiem gan vao listTracNghiem
        listTracNghiem = tracNghiemDB.getAllTracNghiem();
        event();

    }

    private int score;
    private int currentIndex=0;
    private TracNghiem sentence;

    private RadioButton opTrue;
    private RadioButton op;
    private void event() {
        //xu li su kien click vao btnQuit
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TracNghiemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //set trang thai ban dau cua activity
        int slCau= listTracNghiem.size();
        score=0;
        displaySentence(currentIndex, slCau, score);
        //set su kien chon dap an
        rdoGChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                op = findViewById(checkedId);
            }
        });

        //xử lí sự kiện click vào btnConfirm
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lay cau tra loi
                String yourAnswer = op.getText().toString();
                // lay rdo chua cau tra loi dung
                if(getRadiobuttonId(sentence.getDa_True()) !=-1){
                    opTrue = findViewById(getRadiobuttonId(sentence.getDa_True()));
                }
                //hien thi ket qua ra man hinh
                if(yourAnswer.equals(sentence.getDa_True())){
                    score+=5;
                    Toast.makeText(TracNghiemActivity.this, "Correct ", Toast.LENGTH_SHORT).show();
                    op.setBackground(getDrawable(R.drawable.button_2));
                }else {
                    Toast.makeText(TracNghiemActivity.this, "Incorrect ", Toast.LENGTH_SHORT).show();
                    op.setBackground(getDrawable(R.drawable.button_1));
                    opTrue.setBackground(getDrawable(R.drawable.button_2));

                }
                //doi ba giay thuc hien sang cau tiep theo
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentIndex ++;
                        if(currentIndex<slCau){
                            //hien thi cac cau hoi len
                            displaySentence(currentIndex, slCau, score);
                            op.setChecked(false);
                            op.setBackground(getDrawable(R.drawable.bgbtn));
                            opTrue.setBackground(getDrawable(R.drawable.bgbtn));
                        }else {
                            //chuyen sang resultActivity
                            Intent intent = new Intent(TracNghiemActivity.this, ResultActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("Score", score);
                            String questiontrue = String.valueOf(score/5 +"/"+listTracNghiem.size());
                            b.putString("QuestionTrue", questiontrue);
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    }
                },3000);
            }
        });

    }

    public void displaySentence(int currentSentence, int slCau, int score){
        tvQuestionCount.setText(currentSentence+"/"+String.valueOf(slCau));
        tvScore.setText(String.valueOf(score));
        sentence = listTracNghiem.get(currentIndex);
        tvQuestion.setText(sentence.getCauTA());
        op1.setText(sentence.getDa_A());
        op2.setText(sentence.getDa_B());
        op3.setText(sentence.getDa_C());
        op4.setText(sentence.getDa_D());
    }

    //lay ra radio button chua dap an dung
    public int getRadiobuttonId(String daTure){
        int radioButtonCount = rdoGChoice.getChildCount();
        int selectedRadioButtonId = -1;

        for (int i = 0; i < radioButtonCount; i++) {
            RadioButton radioButton = (RadioButton) rdoGChoice.getChildAt(i);
            String radioButtonText = radioButton.getText().toString();

            // So sánh văn bản của RadioButton với văn bản bạn cần
            if (radioButtonText.equals(daTure)) {
                // Lấy ID của RadioButton nếu có sự khớp
                selectedRadioButtonId = radioButton.getId();
                break; // Dừng vòng lặp khi tìm thấy RadioButton khớp
            }
        }
        return selectedRadioButtonId;
    }
    private void addTracNghiem() {
        //add du
        tracNghiemDB.addSentence(new TracNghiem(0, "They are required to inform the human resources department when resigning due .......... a disagreement over company policy.",
                "to","by", "on", "for", "to"));
        tracNghiemDB.addSentence(new TracNghiem(1, "All the important files were organized first by color and .......... alphabetized by the title and name.",
                "since","then","here", "much", "then"));
        tracNghiemDB.addSentence(new TracNghiem(2, "She ... beautifull",
                "is", "are", "don't", "doesn't", "is"));
    }
    private void anhxa() {
        tvQuestion = findViewById(R.id.tvQuestion);
        rdoGChoice = findViewById(R.id.radiochoices);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        tvScore = findViewById(R.id.tvScore);
        btnQuit = findViewById(R.id.btnQuitTN);
        btnConfirm = findViewById(R.id.btnConfirm);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);
    }
}