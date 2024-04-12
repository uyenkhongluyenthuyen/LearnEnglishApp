package com.example.learnenglishapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglishapp.R;
import com.example.learnenglishapp.model.HelperClass;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtFullName, edtMail, edtUserName, edtPassword;
    private Button btnSignUp;
    private TextView tv_login;

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        anhxa();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String fullname= edtFullName.getText().toString();
                String mail = edtMail.getText().toString();
                String username = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                HelperClass helperClass= new HelperClass(fullname, mail, username, password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignUpActivity.this, "You have signup successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        edtFullName = findViewById(R.id.edtFullName);
        edtMail = findViewById(R.id.edtEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassWord);
        btnSignUp = findViewById(R.id.btnSignUp);
        tv_login = findViewById(R.id.tv_login);
    }
}