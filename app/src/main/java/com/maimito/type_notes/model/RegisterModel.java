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

public class RegisterModel extends ViewModel {
    private String TAG = "retrofit";
    private MutableLiveData<ResponseHandler> errorHandlerMutableLiveData;

    public LiveData<ResponseHandler> postAddNotes(String username, String password, String fullname){
        if (errorHandlerMutableLiveData == null){
            errorHandlerMutableLiveData = new MutableLiveData<>();
            PostRegister(username, password, fullname);
        }

        return errorHandlerMutableLiveData;
    }

    private void PostRegister(String username, String password, String fullname){
        DBService dbService = DBConnect.getDB();
        dbService.postRegister(username, password, fullname).enqueue(new Callback<ResponseHandler>() {
            @Override
            public void onResponse(Call<ResponseHandler> call, Response<ResponseHandler> response) {
                if (response.isSuccessful()){
                    errorHandlerMutableLiveData.setValue(response.body());
                    Log.d(TAG, "Register Success (" + response.body() + ")");
                } else {
                    errorHandlerMutableLiveData.setValue(response.body());
                    Log.d(TAG, "Register Unsuccessful (" + response.body() + ")");
                }
            }

            @Override
            public void onFailure(Call<ResponseHandler> call, Throwable t) {
                Log.d(TAG, "Register Failed (" + t.getMessage() + ")");
            }
        });
    }
}
