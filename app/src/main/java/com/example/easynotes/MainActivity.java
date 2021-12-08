package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.w3c.dom.Node;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;

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
}