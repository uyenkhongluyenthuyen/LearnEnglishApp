package com.example.learnenglishapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUserName,edtPassWord;
    private Button btnLogin;
    private TextView tv_register;
    public static String FULL_NAME;
    public static String EMAIL;
    public static String USERNAME;
    public static String PASSWORD;

    public static String getFullName() {
        return FULL_NAME;
    }

    public static void setFullName(String fullName) {
        FULL_NAME = fullName;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static void setEMAIL(String EMAIL) {
        LoginActivity.EMAIL = EMAIL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        LoginActivity.USERNAME = USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        LoginActivity.PASSWORD = PASSWORD;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUserName() || !validatePassword()){

                }else {
                    checkUser();
                }
                
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public  boolean validateUserName(){
        String username = edtUserName.getText().toString();
        if(username.isEmpty()){
            edtUserName.setError("UserName cannot be empty");
            return false;
        }else{
            edtUserName.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String password = edtPassWord.getText().toString();
        if(password.isEmpty()){
            edtPassWord.setError("Password cannot be empty");
            return false;
        }else {
            edtPassWord.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = edtUserName.getText().toString().trim();
        String userPassword = edtPassWord.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = dataSnapshot.child("password").getValue(String.class);
                        String fullName = dataSnapshot.child("name").getValue(String.class);
                        String email = dataSnapshot.child("mail").getValue(String.class);

                        if(passwordFromDB.equals(userPassword)){
                            // Đăng nhập thành công
                            edtUserName.setError(null);
                            //luu lai thong tin user
                            FULL_NAME = fullName;
                            EMAIL = email;
                            USERNAME = userUsername;
                            PASSWORD = userPassword;
                            // Truyền dữ liệu sang MainActivity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            edtPassWord.setError("Invalid credentials");
                            edtPassWord.requestFocus();
                        }
                    }
                }else {
                    edtUserName.setError("User does no exist");
                    edtUserName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void anhxa() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        tv_register = findViewById(R.id.tv_register);
        btnLogin = findViewById(R.id.btnLogin);
    }


}