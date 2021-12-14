package com.example.easynotes;

import java.util.ArrayList;

public class Note {
    private int nId;
    private String sTitle;
    private String sDiscription;
    private String sDayOfWeek;
    private int nPriority;

    public Note(int nId, String sTitle, String sDiscription, String sDayOfWeek, int nPriority) {
        this.nId = nId;
        this.sTitle = sTitle;
        this.sDiscription = sDiscription;
        this.sDayOfWeek = sDayOfWeek;
        this.nPriority = nPriority;
    }

    public Note(ArrayList<Note> arrNotes) {
    }

    public int getnId() {
        return nId;
    }

    public String getsTitle() {
        return sTitle;
    }

    public String getsDiscription() {
        return sDiscription;
    }

    public String getsDayOfWeek() {
        return sDayOfWeek;
    }

    public int getnPriority() {
        return nPriority;
    }
}
