package com.maimito.type_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.baoyz.widget.PullRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maimito.type_notes.adapter.AdapterView;
import com.maimito.type_notes.model.NotesList;
import com.maimito.type_notes.model.NotesListItem;
import com.maimito.type_notes.model.NotesModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNotes;
    private FloatingActionButton floatingActionButton;
    private AdapterView adapterView;
    private PullRefreshLayout pullRefreshLayout;
    private NotesModel notesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_notes);
        rvNotes = (RecyclerView) findViewById(R.id.rv_notes);
        List<NotesListItem> notesListItems = new ArrayList<>();
        rvNotes.setAdapter(adapterView);
        rvNotes.setHasFixedSize(true);
        rvNotes.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        getData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNotesActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getData(){

        notesModel = new ViewModelProvider(this).get(NotesModel.class);
        notesModel.getNotes().observe(this, new Observer<List<NotesListItem>>() {
            @Override
            public void onChanged(List<NotesListItem> notesListItems) {
                adapterView = new AdapterView(MainActivity.this, notesListItems);
                rvNotes.setAdapter(adapterView);
            }
        });
    }

}