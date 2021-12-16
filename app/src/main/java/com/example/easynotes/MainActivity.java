package com.example.easynotes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    ArrayList<Note> arrNotes;
    NotesDatabase notesDatabase;

    ActivityResultLauncher<Intent> mActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bundle bundle = result.getData().getExtras();

                        String sTitle = bundle.getString("title");
                        String sDiscript = bundle.getString("discript");
                        String sSpinDay = bundle.getString("spinDay");
                        int nPriority = bundle.getInt("priority", 0);

                        Note note = new Note(sTitle, sDiscript, sSpinDay, nPriority);
                        notesDatabase.notesDao().insertNote(note);

                        refreshArrayNotes();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesDatabase = NotesDatabase.getInstance(this);
        arrNotes = new ArrayList<>();
        notesAdapter = new NotesAdapter(arrNotes);

        recyclerView = findViewById(R.id.recyclerViewNotes);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeFromDB(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        refreshArrayNotes();
    }

    public void onClickAddBtn(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        mActivityForResult.launch(intent);
    }

    private void refreshArrayNotes(){
        List<Note> listNote = notesDatabase.notesDao().getAllNotes();
        arrNotes.clear();
        arrNotes.addAll(listNote);
        notesAdapter.notifyDataSetChanged();
    }

    void removeFromDB(int position){
        Note note = arrNotes.get(position);
        notesDatabase.notesDao().deleteNote(note);
        refreshArrayNotes();
    }
}