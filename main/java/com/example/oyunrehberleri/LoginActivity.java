package com.example.oyunrehberleri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText editTextMail, editTextPass;
    Button loginButton, signupButton;
    FirebaseAuth firebaseAuth;
    Intent intentToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        editTextMail = findViewById(R.id.editTextMail);
        editTextPass = findViewById(R.id.editTextPass);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        intentToHome = new Intent(LoginActivity.this,MainActivity.class);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextMail.getText().toString();
                String pass = editTextPass.getText().toString();
                if(email.isEmpty()){
                    editTextMail.setError("E-Mail alanı boş bırakılamaz!");
                    editTextMail.requestFocus();
                } else if (pass.isEmpty()){
                    editTextPass.setError("Şifre alanı boş bırakılamaz!");
                    editTextPass.requestFocus();
                } else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"E-Mail ve Şifre alanları boş bırakılamaz!",Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pass.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this,"Kayıt başarısız! Lütfen tekrar deneyin.",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this,"Başarıyla kayıt oldunuz!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this,"Oops! Bir şeyler ters gitti.",Toast.LENGTH_SHORT).show();
                }
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextMail.getText().toString();
                String pass = editTextPass.getText().toString();
                if(email.isEmpty()){
                    editTextMail.setError("E-Mail alanı boş bırakılamaz!");
                    editTextMail.requestFocus();
                } else if (pass.isEmpty()){
                    editTextPass.setError("Şifre alanı boş bırakılamaz!");
                    editTextPass.requestFocus();
                } else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"E-Mail ve Şifre alanları boş bırakılamaz!",Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pass.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this,"Giriş başarısız! Lütfen tekrar deneyin.",Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(intentToHome);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this,"Oops! Bir şeyler ters gitti.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
