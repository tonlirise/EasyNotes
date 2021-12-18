package com.example.easynotes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class NotesViewModel extends AndroidViewModel {
    private static NotesDatabase notesDatabase;
    private LiveData<List<Note>> listNote;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstance(getApplication());
        listNote = notesDatabase.notesDao().getAllNotes();
    }

    LiveData<List<Note>> getAllNotes(){
        return listNote;
    }

    void insertNote(Note note){
        new InsertTask().execute(note);
    }

    void deleteNote(Note note){
        new DeleteTask().execute(note);
    }

    private static class InsertTask extends AsyncTask<Note, Void, Void>{
        @Override
        protected Void doInBackground(Note... notes) {
            notesDatabase.notesDao().insertNote(notes[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Note, Void, Void>{
        @Override
        protected Void doInBackground(Note... notes) {
            notesDatabase.notesDao().deleteNote(notes[0]);
            return null;
        }
    }
}
