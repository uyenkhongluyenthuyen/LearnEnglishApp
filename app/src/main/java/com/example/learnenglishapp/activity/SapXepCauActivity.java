package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.database.SapXepCauDB;
import com.example.learnenglishapp.model.Cau;

import java.util.ArrayList;
import java.util.List;

public class SapXepCauActivity extends AppCompatActivity {
    private EditText edtDapAn;
    private TextView tvPart1, tvPart2, tvPart3, tvPart4;
    private TextView tvScore, tvQuestion;
    private Button btnQuit, btnConfirm;
    private SapXepCauDB cauDB;
    private List<Cau> listCau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sap_xep_cau);
        anhxa();

        listCau = new ArrayList<>();
        cauDB = new SapXepCauDB(this, "SapXepCauDB", null, 1);
        //addData();
        listCau = cauDB.getAllCau();
        slcau= listCau.size();

        displayQuestion(currentIndex);
        eventUnVisibility();
        eventClickbtnQuit();
        eventClickbtnConfirm();

    }

    private int currentIndex =0;
    private Cau cau;
    private int score =0;
    private int slcau;

    private String yourAnswer="";

    private void displayQuestion(int currentIndex) {
        cau = listCau.get(currentIndex);
        String sentence = cau.getSentenceSort();
        String part1 = cau.getPart1();
        String part2 = cau.getPart2();
        String part3 = cau.getPart3();
        String part4 = cau.getPart4();

        tvPart1.setText(part1);
        tvPart2.setText(part2);
        tvPart3.setText(part3);
        tvPart4.setText(part4);

        tvScore.setText(String.valueOf("Score: "+score));
        tvQuestion.setText("Question: "+String.valueOf(currentIndex+1)+"/"+ String.valueOf(slcau));

    }

    public boolean checkAnswer(String yourAnswer, String answerTrue){
        String a = yourAnswer.replaceAll("\\s+", "");
        String b = answerTrue.replaceAll("\\s+", "");
        if(a.toUpperCase().equals(b.toUpperCase())){
            return true;
        }
        return false;
    }
    private void eventUnVisibility() {
        tvPart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPart1.setVisibility(View.GONE);
                yourAnswer +=tvPart1.getText()+" ";
                edtDapAn.setText(yourAnswer);
            }
        });
        tvPart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPart2.setVisibility(View.GONE);
                yourAnswer +=tvPart2.getText()+" ";
                edtDapAn.setText(yourAnswer);
            }
        });
        tvPart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPart3.setVisibility(View.GONE);
                yourAnswer +=tvPart3.getText()+" ";
                edtDapAn.setText(yourAnswer);
            }
        });
        tvPart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPart4.setVisibility(View.GONE);
                yourAnswer +=tvPart4.getText()+" ";
                edtDapAn.setText(yourAnswer);
            }
        });
    }
    public void eventVisibility(){
        edtDapAn.setText("");
        edtDapAn.setTextColor(getColor(R.color.colorPrimary2));
        tvPart1.setVisibility(View.VISIBLE);
        tvPart2.setVisibility(View.VISIBLE);
        tvPart3.setVisibility(View.VISIBLE);
        tvPart4.setVisibility(View.VISIBLE);
    }

    private void eventClickbtnQuit() {
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SapXepCauActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void eventClickbtnConfirm() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check answer
                if(checkAnswer(yourAnswer, cau.getSentenceSort())==true){
                    Toast.makeText(SapXepCauActivity.this, "Correct ^^", Toast.LENGTH_SHORT).show();
                    edtDapAn.setTextColor(getColor(R.color.colorGreen));
                    score +=5;
                }else{
                    Toast.makeText(SapXepCauActivity.this, "Incorrect :(", Toast.LENGTH_SHORT).show();
                    edtDapAn.setTextColor(getColor(R.color.colorRed));
                }

                //next to sentence
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentIndex++;
                        if(currentIndex<slcau){
                            displayQuestion(currentIndex);
                            eventVisibility();
                            yourAnswer = "";
                        }else{
                            Intent intent = new Intent(SapXepCauActivity.this, ResultActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("Score", score);
                            String questiontrue = String.valueOf(score/5 +"/"+listCau.size());
                            b.putString("QuestionTrue", questiontrue);
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    }
                },3000);

            }
        });

    }

    private void addData() {
        cauDB.addCau(new Cau(0, "She loves to read books, especially those that transport her to different worlds",
                "especially those", "She loves to read books,", "that transport her","to different worlds"));
        cauDB.addCau(new Cau(1, "My brother and I decided to spring a surprise on my parents on their wedding anniversary",
                "decided to spring","on their wedding anniversary","My brother and I","a surprise on my parents"));
        cauDB.addCau(new Cau(2, "However hard Jay practiced playing the guitar, he could hardly perform any better",
                "Jay practiced playing the guitar,", "he could hardly", "However hard", "perform any better"));
    }

    private void anhxa() {
        edtDapAn = findViewById(R.id.edtDapAn);
        tvPart1 = findViewById(R.id.tvPart1);
        tvPart2 = findViewById(R.id.tvPart2);
        tvPart3 = findViewById(R.id.tvPart3);
        tvPart4 = findViewById(R.id.tvPart4);
        tvScore = findViewById(R.id.tvScore);
        tvQuestion = findViewById(R.id.tvQuestion);
        btnQuit = findViewById(R.id.btnQuit);
        btnConfirm = findViewById(R.id.btnConfirm);
    }
}