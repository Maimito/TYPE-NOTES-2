package com.maimito.type_notes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.maimito.type_notes.EditNotesActivity;
import com.maimito.type_notes.R;
import com.maimito.type_notes.handler.Conf;
import com.maimito.type_notes.model.NotesListItem;

import java.util.ArrayList;
import java.util.List;

public class AdapterView extends RecyclerView.Adapter<AdapterView.AdapterViewHolder> {
    private Context context;
    private List<NotesListItem> notesListItems;
    private Conf conf;

    public AdapterView(Context context, List<NotesListItem> notesListItemsItems){
        this.context = context;
        this.notesListItems = notesListItemsItems;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notes, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position){
        holder.note_title.setText(notesListItems.get(position).getNoteTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditNotesActivity.class);
                intent.putExtra(Conf.UNIVERSAL_NOTE_ID, notesListItems.get(position).getId());
                intent.putExtra(Conf.UNIVERSAL_NOTE_TITLE, notesListItems.get(position).getNoteTitle());
                intent.putExtra(Conf.UNIVERSAL_NOTE_CONTENT, notesListItems.get(position).getNoteContent());
                intent.putExtra(Conf.UNIVERSAL_DATE_MODIFIED, notesListItems.get(position).getDateModified());
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView note_title;
        CardView cardView;
        public AdapterViewHolder(@NonNull View itemView){
            super(itemView);

            note_title = itemView.findViewById(R.id.note_title_list);
            cardView = itemView.findViewById(R.id.cv_notes);
        }
    }

    @Override
    public int getItemCount() {
        return notesListItems.size();
    }
}
