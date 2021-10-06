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

public class LoginModel extends ViewModel {
    private String TAG = "retrofit";
    private MutableLiveData<ResponseHandler> errorHandlerMutableLiveData;
    private String uid;

    public LiveData<ResponseHandler> getUserLogin(String username, String password){
        if (errorHandlerMutableLiveData == null){
            errorHandlerMutableLiveData = new MutableLiveData<ResponseHandler>();
            loadLogin(username, password);
        }

        return errorHandlerMutableLiveData;
    }

    private void loadLogin(String username, String password){
        DBService dbService = DBConnect.getDB();
        dbService.getUserLogin(username, password).enqueue(new Callback<ResponseHandler>() {
            @Override
            public void onResponse(Call<ResponseHandler> call, Response<ResponseHandler> response) {
                if (response.isSuccessful()){
                    errorHandlerMutableLiveData.setValue(response.body());
                    Log.d(TAG, "Login success (" + response.body() + ")");
                }

            }

            @Override
            public void onFailure(Call<ResponseHandler> call, Throwable t) {
                Log.d(TAG, "Login Failed (" + t.getMessage() + ")");
            }
        });
    }

    public String getUid(){
        return uid;
    }

}
