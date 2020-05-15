package com.example.oyunrehberleri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GameAdapter adapter;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    Button button, buttonOut, buttonNot;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] titles = getResources().getStringArray(R.array.game_titles);
        String[] contents = getResources().getStringArray(R.array.game_contents);
        int[] images = {R.drawable.bannerlord,R.drawable.fallout4,R.drawable.gtav,R.drawable.kingdomcome,
        R.drawable.payday2,R.drawable.rdr2,R.drawable.skyrim,R.drawable.witcher3};

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GameAdapter(this,titles,contents,images);
        recyclerView.setAdapter(adapter);

        button = findViewById(R.id.button);
        buttonOut = findViewById(R.id.buttonOut);
        buttonNot = findViewById(R.id.buttonNot);
        firebaseAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        buttonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"Çıkış yapıldı!",Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
        buttonNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null) {
                    button.setVisibility(View.GONE);
                    buttonNot.setVisibility(View.VISIBLE);
                    buttonOut.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(MainActivity.this,"Lütfen giriş yapın!",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
