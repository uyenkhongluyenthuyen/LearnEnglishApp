package com.example.learnenglishapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglishapp.MainActivity;
import com.example.learnenglishapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DetailAccoutActivity extends AppCompatActivity {

    private ImageView imgHome;
    private EditText edtFullname, edtEmail, edtUsername, edtPassword;
    private Button btnCapNhat;
    private TextView tvPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_accout);
        anhxa();
        fillData();

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailAccoutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = edtFullname.getText().toString();
                String email =  edtEmail.getText().toString();
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                updateUser(username,fullName,email,password);
            }
        });
    }

    public void updateUser(String username, String name, String email, String password) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query query = reference.orderByChild("username").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String key = dataSnapshot.getKey();

                        // Tạo một HashMap để lưu thông tin cập nhật
                        Map<String, Object> updateData = new HashMap<>();
                        updateData.put("name", name);
                        updateData.put("mail", email);
                        updateData.put("password", password);

                        // Cập nhật dữ liệu trong cơ sở dữ liệu
                        reference.child(key).updateChildren(updateData)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Cập nhật thành công
                                        Toast.makeText(DetailAccoutActivity.this, "User updated successfully", Toast.LENGTH_SHORT).show();
                                        LoginActivity.setFullName(name);
                                        LoginActivity.setEMAIL(email);
                                        LoginActivity.setUSERNAME(username);
                                        LoginActivity.setPASSWORD(password);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Xảy ra lỗi khi cập nhật
                                        Log.d("UpdateUser", "Failed to update user: " + e.getMessage());
                                        Toast.makeText(DetailAccoutActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                } else {
                    // Không tìm thấy người dùng với username cung cấp
                    Log.d("UpdateUser", "User with username " + username + " does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xảy ra lỗi khi truy vấn dữ liệu
                Log.d("UpdateUser", "Failed to query data: " + error.getMessage());
            }
        });
    }


    public void fillData(){
        String fullName = LoginActivity.getFullName();
        String email = LoginActivity.getEMAIL();
        String username = LoginActivity.getUSERNAME();
        String password = LoginActivity.getPASSWORD();

        edtFullname.setText(fullName);
        edtEmail.setText(email);
        edtUsername.setText(username);
        edtPassword.setText(password);

    }
    private void anhxa() {
        imgHome = findViewById(R.id.imgHOME);
        edtFullname = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassWord);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        tvPoint = findViewById(R.id.tvPoint);
    }


}