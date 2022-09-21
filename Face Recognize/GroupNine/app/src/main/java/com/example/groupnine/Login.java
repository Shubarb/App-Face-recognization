package com.example.groupnine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText emailedit,passedit;
    private ImageView imgView;
    private Button btnlogin, btnregis, btnex;
    private FirebaseAuth mAuth;
//    private static final String[] PRODUCTS = new String[]
//            {
//                    "Điện thoại XY",
//                    "Máy tính AZ",
//                    "Iphone", "Tai nghe", "Loa"
//            };
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth     = FirebaseAuth.getInstance();
        imgView   = findViewById(R.id.imgView);
        emailedit = findViewById(R.id.email);
        passedit  = findViewById(R.id.password);
        btnlogin  = findViewById(R.id.btnlogin);
        btnregis  = findViewById(R.id.btnregister);
        btnex     = findViewById(R.id.btnexit);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        ControlButton();
    }
    private void login() {
        String email,pass;
        email = emailedit.getText().toString();
        pass = passedit.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui lòng nhập Password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Đăng nhập không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void register() {
        Intent i = new Intent(Login.this,Register.class);
        startActivity(i);
    }
    private void ControlButton() {
        btnex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắc muốn thoát khỏi app");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
                builder.show();
            }

        });
    }

}
