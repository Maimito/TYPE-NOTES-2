package com.maimito.type_notes.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maimito.type_notes.api.DBConnect;
import com.maimito.type_notes.api.DBService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNotesModel extends ViewModel {
    private String TAG = "retrofit";
    private MutableLiveData<ErrorHandler> errorHandlerMutableLiveData;

    public LiveData<ErrorHandler> postAddNotes(String note_title, String note_content, String date_modified, String date_created){
        if (errorHandlerMutableLiveData == null){
            errorHandlerMutableLiveData = new MutableLiveData<>();
            PostAddNotes(note_title, note_content, date_modified, date_created);
        }

        return errorHandlerMutableLiveData;
    }

    private void PostAddNotes(String note_title, String note_content, String date_modified, String date_created){
        DBService dbService = DBConnect.getDB();
        dbService.postAddNotes(note_title, note_content, date_modified, date_created).enqueue(new Callback<ErrorHandler>() {
            @Override
            public void onResponse(Call<ErrorHandler> call, Response<ErrorHandler> response) {
                Log.d(TAG, "PostAddNotes Success (" + response.body() + ")");
            }

            @Override
            public void onFailure(Call<ErrorHandler> call, Throwable t) {
                Log.d(TAG, "PostAddNotes Failed (" + t.getMessage() + ")");
            }
        });
    }

}
