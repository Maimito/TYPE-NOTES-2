package com.maimito.type_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.maimito.type_notes.model.AddNotesModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNotesActivity extends AppCompatActivity {
    private TextInputEditText addNotesTitle;
    private TextInputEditText addNotesContent;
    private String title, content,datenow;
    private Date c = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private AddNotesModel addNotesModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        addNotesTitle = (TextInputEditText) findViewById(R.id.add_notes_title);
        addNotesContent = (TextInputEditText) findViewById(R.id.add_notes_content);
        addNotesModel = new ViewModelProvider(this).get(AddNotesModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save_button:
                postData();
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void postData(){
        title = String.valueOf(addNotesTitle.getText());
        content = String.valueOf(addNotesContent.getText());
        datenow = sdf.format(c);
        addNotesModel = new ViewModelProvider(this).get(AddNotesModel.class);
        Toast.makeText(this, title + "\n" + content + "\n" + datenow, Toast.LENGTH_LONG).show();

        addNotesModel.postAddNotes(title, content, datenow, datenow)
                .observe(AddNotesActivity.this, errorHandler -> {
                    Toast.makeText(AddNotesActivity.this, "Catatan disimpan", Toast.LENGTH_SHORT).show();
                });
        Toast.makeText(AddNotesActivity.this, "Saved", Toast.LENGTH_SHORT).show();
    }
}