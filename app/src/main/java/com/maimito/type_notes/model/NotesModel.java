package com.maimito.type_notes.model;

import android.content.Context;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maimito.type_notes.api.DBConnect;
import com.maimito.type_notes.api.DBService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesModel extends ViewModel {
	Context context;
	private String TAG = "retrofit";
	private MutableLiveData<List<NotesListItem>> notesMLD;
	private List<NotesListItem> notesListItems;
	private AdapterView adapterView;

	public LiveData<List<NotesListItem>> getNotes(){
		if (notesMLD == null){
			notesMLD = new MutableLiveData<List<NotesListItem>>();
			loadNotes();
		}

		return notesMLD;
	}

	private void loadNotes(){
		DBService dbService = DBConnect.getDB();
		dbService.getNotes().enqueue(new Callback<List<NotesListItem>>() {
			@Override
			public void onResponse(Call<List<NotesListItem>> call, Response<List<NotesListItem>> response) {
				if (response.isSuccessful()){
					notesMLD.setValue(response.body());
					Log.d(TAG, "onResponse: "+response.body());
				} else {
					Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<List<NotesListItem>> call, Throwable t) {
				Log.d(TAG, "onFailure (gagal koneksi): " + t.getLocalizedMessage() + "|" + t.getMessage());
			}
		});
	}

	/* public List<NotesModelItem> getNotesModel(){
		return notesModel;
	} */
}