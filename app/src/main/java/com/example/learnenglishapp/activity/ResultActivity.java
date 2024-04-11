package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;

public class ResultActivity extends AppCompatActivity {
    private TextView tvQuestionTrue, tvPoint;
    private Button btnReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        anhxa();

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null){
            tvQuestionTrue.setText(b.getString("QuestionTrue"));
            tvPoint.setText(String.valueOf(b.getInt("Score")));
        }

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void anhxa() {
        tvQuestionTrue = findViewById(R.id.tvQuestionTrue);
        tvPoint = findViewById(R.id.tvPoint);
        btnReturn = findViewById(R.id.btnReturn);
    }
}