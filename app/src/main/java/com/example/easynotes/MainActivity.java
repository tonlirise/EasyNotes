package com.example.easynotes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.w3c.dom.Node;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;

    ActivityResultLauncher<Intent> mActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Log.d("test_res",intent.getStringExtra("test"));
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Note> arrNotes = new ArrayList<>();
        arrNotes.add(new Note("Покупки","Купить хлеб, молоко и сахар.","Понедельник", 3));
        arrNotes.add(new Note("Стомотолог","Записаться на приём к врачу","Понедельник", 1));
        arrNotes.add(new Note("Работа","Составить сводный отчёт за полугодие.","Вторник", 2));
        arrNotes.add(new Note("Спорт","Записаться в спортзал.","Среда", 1));
        arrNotes.add(new Note("Парикхмахерская","Сходить на стрижку в 12:00","Пятница", 2));
        arrNotes.add(new Note("Тестовая 1","Тестовая запись номер 1","Суббота", 1));
        arrNotes.add(new Note("Тестовая 2","Тестовая запись номер 2","Понедельник", 3));
        arrNotes.add(new Note("Тестовая 3","Тестовая запись номер 3","Вторник", 2));
        arrNotes.add(new Note("Тестовая 4","Тестовая запись номер 4","Среда", 1));
        arrNotes.add(new Note("Тестовая 5","Тестовая запись номер 5","Пятница", 2));

        notesAdapter = new NotesAdapter(arrNotes);

        recyclerView = findViewById(R.id.recyclerViewNotes);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickAddBtn(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        mActivityForResult.launch(intent);
    }

}