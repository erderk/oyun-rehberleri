package com.example.oyunrehberleri;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView gameContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        gameContent = findViewById(R.id.gameContent);
        Intent intent = getIntent();
        String title = intent.getStringExtra("titleOfGame");
        String content = intent.getStringExtra("contentOfGame");

        getSupportActionBar().setTitle(title);
        gameContent.setText(content);
        gameContent.setMovementMethod(new ScrollingMovementMethod());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
