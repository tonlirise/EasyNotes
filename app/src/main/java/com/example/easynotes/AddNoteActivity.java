package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }


    public void onClickAddBtn(View view) {
        Intent intent = new Intent();
        intent.putExtra("test", "this is test");
        setResult(RESULT_OK, intent);
        finish();
    }
}