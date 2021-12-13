package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddNoteActivity extends AppCompatActivity {

    EditText etTitle, etDiscrp;
    Spinner spinDay;
    RadioGroup rgPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }


    public void onClickAddBtn(View view) {
        Intent intent = new Intent();

        etTitle = findViewById(R.id.editTextTitle);
        etDiscrp = findViewById(R.id.editTextTextMultiLine);
        spinDay = findViewById(R.id.spinner);
        rgPriority = findViewById(R.id.radioGroup);

        intent.putExtra("title", etTitle.getText().toString());
        intent.putExtra("discript", etDiscrp.getText().toString());
        intent.putExtra("spinDay", spinDay.getSelectedItem().toString());
        intent.putExtra("priority", rgPriority.getCheckedRadioButtonId());


        setResult(RESULT_OK, intent);
        finish();
    }
}