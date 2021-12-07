package com.example.easynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private ArrayList<Note> arrNotes;

    public NotesAdapter(ArrayList<Note> arrNotes) {
        this.arrNotes = arrNotes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_activity, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = arrNotes.get(position);
        holder.twTitle.setText(note.getsTitle());
        holder.twDiscription.setText(note.getsDiscription());
        holder.twDayOfWeek.setText(note.getsDayOfWeek());
        holder.twPriority.setText(String.valueOf(note.getnPriority()));
    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private TextView twTitle;
        private TextView twDiscription;
        private TextView twDayOfWeek;
        private TextView twPriority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            twTitle = itemView.findViewById(R.id.textViewTitle);
            twDiscription = itemView.findViewById(R.id.textViewDiscription);
            twDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            twPriority = itemView.findViewById(R.id.textViewPriority);
        }
    }
}
