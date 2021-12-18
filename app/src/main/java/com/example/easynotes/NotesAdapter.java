package com.example.easynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> listNotes;

    public NotesAdapter() {
        listNotes = new ArrayList<>();
    }

    public void setListNotes(List<Note> listNotes) {
        this.listNotes = listNotes;
        notifyDataSetChanged();
    }

    public List<Note> getListNotes() {
        return listNotes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_activity, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = listNotes.get(position);
        holder.twTitle.setText(note.getTitle());
        holder.twDiscription.setText(note.getDiscription());
        holder.twDayOfWeek.setText(note.getDayOfWeek());

        int nColor;
        switch (note.getPriority()){
            case R.id.radioButtonHight: nColor = ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_red_light);
            break;
            case R.id.radioButtonMedium: nColor = ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_orange_light);
            break;
            default:
                nColor = ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_green_light);
        }
        holder.twTitle.setBackgroundColor(nColor);
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private final TextView twTitle;
        private final TextView twDiscription;
        private final TextView twDayOfWeek;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            twTitle = itemView.findViewById(R.id.textViewTitle);
            twDiscription = itemView.findViewById(R.id.textViewDiscription);
            twDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
        }
    }
}
