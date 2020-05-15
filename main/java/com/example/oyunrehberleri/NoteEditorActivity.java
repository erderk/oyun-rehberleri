package com.example.oyunrehberleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
        noteID  = intent.getIntExtra("noteID", -1);

        if(noteID != -1) {
            editText.setText(NotesActivity.notes.get(noteID));
        } else {
            NotesActivity.notes.add("");
            noteID = NotesActivity.notes.size() - 1;
            NotesActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NotesActivity.notes.set(noteID, String.valueOf(s));
                NotesActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.oyunrehberleri", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(NotesActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
