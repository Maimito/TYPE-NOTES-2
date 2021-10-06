package com.maimito.type_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.baoyz.widget.PullRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maimito.type_notes.adapter.AdapterView;
import com.maimito.type_notes.handler.Conf;
import com.maimito.type_notes.handler.ResponseHandler;
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
    private ResponseHandler responseHandler;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uid = getIntent().getStringExtra(Conf.UNIVERSAL_UID);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_notes);
        rvNotes = (RecyclerView) findViewById(R.id.rv_notes);
        List<NotesListItem> notesListItems = new ArrayList<>();
        rvNotes.setAdapter(adapterView);
        rvNotes.setHasFixedSize(true);
        rvNotes.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        getData();

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNotesActivity.class);
            startActivity(intent);
        });

    }

    public void getData(){

        notesModel = new ViewModelProvider(this).get(NotesModel.class);
        notesModel.getNotes(uid ).observe(this, notesListItems -> {
            adapterView = new AdapterView(MainActivity.this, notesListItems);
            rvNotes.setAdapter(adapterView);
        });
    }

}