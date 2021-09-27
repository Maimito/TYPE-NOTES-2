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

public class DeleteNotesModel extends ViewModel {
    private String TAG = "retrofit";
    private MutableLiveData<ErrorHandler> errorHandlerMutableLiveData;

    public LiveData<ErrorHandler> postDeleteNotes(String id){
        if (errorHandlerMutableLiveData == null){
            errorHandlerMutableLiveData = new MutableLiveData<>();
            PostDeleteNotes(id);
        }

        return errorHandlerMutableLiveData;
    }

    private void PostDeleteNotes(String id){
        DBService dbService = DBConnect.getDB();
        dbService.postDeleteNotes(id).enqueue(new Callback<ErrorHandler>() {
            @Override
            public void onResponse(Call<ErrorHandler> call, Response<ErrorHandler> response) {
                Log.d(TAG, "PostDeleteNotes Success (" + response.body() + ")");
            }

            @Override
            public void onFailure(Call<ErrorHandler> call, Throwable t) {
                Log.d(TAG, "PostDeleteNotes Failed (" + t.getMessage() + ")");
            }
        });
    }

}
