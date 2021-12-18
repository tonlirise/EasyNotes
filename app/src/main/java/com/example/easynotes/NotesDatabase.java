package com.example.easynotes;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase  extends RoomDatabase {
    private static final String DB_NAME = "roommotes.db";
    private static final Object  LOCK = new Object();

    private static NotesDatabase notesDatabase;

    public static NotesDatabase getInstance(Context context){
        synchronized(LOCK) {
            if (notesDatabase == null) {
                notesDatabase = Room.databaseBuilder(context, NotesDatabase.class, DB_NAME).build();
            }
        }
        return notesDatabase;
    }

    public abstract NotesDao notesDao();
}