package com.example.easynotes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true )
    private int Id;

    private String Title;
    private String Discription;
    private String DayOfWeek;
    private int Priority;

    public Note(int Id, String Title, String Discription, String DayOfWeek, int Priority) {
        this.Id = Id;
        this.Title = Title;
        this.Discription = Discription;
        this.DayOfWeek = DayOfWeek;
        this.Priority = Priority;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDiscription() {
        return Discription;
    }

    public String getDayOfWeek() {
        return DayOfWeek;
    }

    public int getPriority() {
        return Priority;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public void setDayOfWeek(String dayOfWeek) {
        DayOfWeek = dayOfWeek;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    @Ignore
    public Note(String sTitle, String sDiscription, String sDayOfWeek, int nPriority) {
        this.Title = sTitle;
        this.Discription = sDiscription;
        this.DayOfWeek = sDayOfWeek;
        this.Priority = nPriority;
    }

}
