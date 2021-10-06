package com.maimito.type_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.maimito.type_notes.adapter.AdapterView;
import com.maimito.type_notes.handler.Conf;
import com.maimito.type_notes.model.AddNotesModel;
import com.maimito.type_notes.model.DeleteNotesModel;
import com.maimito.type_notes.model.EditNotesModel;
import com.maimito.type_notes.model.NotesListItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditNotesActivity extends AppCompatActivity {
    private String id, title, content, datenow;
    private int position;
    private TextInputEditText titleEdit;
    private TextInputEditText contentEdit;
    private EditNotesModel editNotesModel;
    private DeleteNotesModel deleteNotesModel;
    private Date c = Calendar.getInstance().getTime();
    private Conf conf;
    private AdapterView adapterView;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);


        id = getIntent().getStringExtra(Conf.UNIVERSAL_NOTE_ID);
        title = getIntent().getStringExtra(Conf.UNIVERSAL_NOTE_TITLE);
        content = getIntent().getStringExtra(Conf.UNIVERSAL_NOTE_CONTENT);
        position = getIntent().getStringExtra("position");
        editNotesModel = new ViewModelProvider(this).get(EditNotesModel.class);

        titleEdit = (TextInputEditText) findViewById(R.id.titleEdit);
        contentEdit = (TextInputEditText) findViewById(R.id.contentEdit);

        titleEdit.setText(title);
        contentEdit.setText(content);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save_button:
                id = getIntent().getStringExtra("id");
                title = String.valueOf(titleEdit.getText());
                content = String.valueOf(contentEdit.getText());
                datenow = sdf.format(c);
                editNotesModel = new ViewModelProvider(this).get(EditNotesModel.class);

                editNotesModel.postAddNotes(title, content, datenow, id)
                        .observe(EditNotesActivity.this, noteResponse -> {
                            Toast.makeText(EditNotesActivity.this, "Catatan diubah", Toast.LENGTH_SHORT).show();
                        });
                Toast.makeText(EditNotesActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.delete_button:
                id = getIntent().getStringExtra("id");
                deleteNotesModel = new ViewModelProvider(this).get(DeleteNotesModel.class);
                deleteNotesModel.postDeleteNotes(id).observe(EditNotesActivity.this, noteResponse -> {
                    Toast.makeText(EditNotesActivity.this, "Catatan dihapus", Toast.LENGTH_SHORT).show();
                });
                Toast.makeText(EditNotesActivity.this, "Deleted", Toast.LENGTH_SHORT).show();

                this.finish();
                adapterView.delete(position);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}