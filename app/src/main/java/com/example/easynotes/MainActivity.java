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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    ArrayList<Note> arrNotes;

    NotesDBHelper notesDBHelper;
    SQLiteDatabase sqLiteDatabase;

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

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, sTitle);
                        contentValues.put(NotesContract.NotesEntry.COLUMN_DISCRIPTION, sDiscript);
                        contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, sSpinDay);
                        contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, nPriority);

                        sqLiteDatabase.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);
                        refreshArrayNotes();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesDBHelper = new NotesDBHelper(this);
        sqLiteDatabase = notesDBHelper.getWritableDatabase();

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
        arrNotes.clear();
        Cursor cursor = sqLiteDatabase.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            int nIdIdx = cursor.getColumnIndex(NotesContract.NotesEntry._ID);
            int nId = cursor.getInt(nIdIdx);

            int nTitleIdx = cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE);
            String sTitle = cursor.getString(nTitleIdx);

            int nDiscripIdx = cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DISCRIPTION);
            String sDiscription = cursor.getString(nDiscripIdx);

            int nDayOfWeekIdx = cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK);
            String sDayOfWeekIdx = cursor.getString(nDayOfWeekIdx);

            int nPriorityIdx = cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY);
            int nPriority = cursor.getInt(nPriorityIdx);

            arrNotes.add(new Note(nId, sTitle, sDiscription, sDayOfWeekIdx, nPriority));
        }
        notesAdapter.notifyDataSetChanged();
    }

    void removeFromDB(int position){
        int nId = arrNotes.get(position).getnId();
        String [] arrStr = new String[] {Integer.toString(nId)};
        String sWhere = NotesContract.NotesEntry._ID + " =?";
        sqLiteDatabase.delete(NotesContract.NotesEntry.TABLE_NAME, sWhere, arrStr);
        refreshArrayNotes();
    }
}