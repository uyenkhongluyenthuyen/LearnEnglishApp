package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUserName;
    private EditText edtPassWord;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtUserName.getText().toString();
                String pass = edtPassWord.getText().toString();

                if(name.trim().equals("") || pass.trim().equals("")){
                    Toast.makeText(LoginActivity.this, "Fill in UserName and Password", Toast.LENGTH_SHORT).show();
                } else if (name.equals("thuuyen") && pass.equals("123")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(LoginActivity.this, "UserName or Password are incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhxa() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        btnLogin = findViewById(R.id.btnLogin);
    }


}