package com.maimito.type_notes.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maimito.type_notes.api.DBConnect;
import com.maimito.type_notes.api.DBService;
import com.maimito.type_notes.handler.ResponseHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditNotesModel extends ViewModel {
    private String TAG = "retrofit";
    private MutableLiveData<ResponseHandler> errorHandlerMutableLiveData;

    public LiveData<ResponseHandler> postAddNotes(String note_title, String note_content, String date_modified, String id){
        if (errorHandlerMutableLiveData == null){
            errorHandlerMutableLiveData = new MutableLiveData<>();
            PostEditNotes(note_title, note_content, date_modified, id);
        }

        return errorHandlerMutableLiveData;
    }

    private void PostEditNotes(String note_title, String note_content, String date_modified, String id){
        DBService dbService = DBConnect.getDB();
        dbService.postEditNotes(note_title, note_content, date_modified, id).enqueue(new Callback<ResponseHandler>() {
            @Override
            public void onResponse(Call<ResponseHandler> call, Response<ResponseHandler> response) {
                Log.d(TAG, "PostEditNotes Success (" + response.body() + ")");
            }

            @Override
            public void onFailure(Call<ResponseHandler> call, Throwable t) {
                Log.d(TAG, "PostEditNotes Failed (" + t.getMessage() + ")");
            }
        });
    }
}
