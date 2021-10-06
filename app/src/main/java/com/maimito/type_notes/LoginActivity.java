package com.maimito.type_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.dd.processbutton.FlatButton;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.material.textfield.TextInputEditText;
import com.maimito.type_notes.adapter.SliderLoginAdapter;
import com.maimito.type_notes.handler.Conf;
import com.maimito.type_notes.handler.ResponseHandler;
import com.maimito.type_notes.model.LoginModel;
import com.maimito.type_notes.model.SliderList;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText username;
    private TextInputEditText password;
    private ActionProcessButton loginFlatButton;
    private LoginModel lm;
    private String uid, fullname;
    private Handler handler;
    private boolean state;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    private String u,p;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginFlatButton = findViewById(R.id.login_btn);
        SliderView sliderView = findViewById(R.id.login_slider);
        sliderView.setSliderAdapter(new SliderLoginAdapter(LoginActivity.this));
        sp = getSharedPreferences("loginPreference", MODE_PRIVATE);
        CheckLogin();

        loginFlatButton.setOnClickListener(view -> {
            DoLogin();
        });

    }
    public void CheckLogin(){
        if (sp == null){
            sp = getSharedPreferences("loginPreference", MODE_PRIVATE);
        }
        u = sp.getString("username", "");
        p = sp.getString("password", "");

        if(u != null && !u.equals("")){
            if(p != null && !p.equals("")) {
                username.setText(u);
                password.setText(p);
            }
        }

    }

    public void DoLogin(){
        try{
            loginFlatButton.setMode(ActionProcessButton.Mode.ENDLESS);
            u = String.valueOf(username.getText()); //username
            p = String.valueOf(password.getText()); //password

            lm = new ViewModelProvider(LoginActivity.this).get(LoginModel.class);
            state = true;

            lm.getUserLogin(u, p).observe(LoginActivity.this, new Observer<ResponseHandler>() {
                @Override
                public void onChanged(ResponseHandler responseHandler) {
                    uid = responseHandler.getId();
                    fullname = responseHandler.getFullname();
                    state = responseHandler.isErrorState();
                }
            });

            handler = new Handler();
            handler.postDelayed(() -> {
                if(state){
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                } else {
                    if (uid != "" || uid != null){
                        try {
                            if (sp == null)
                                sp = getSharedPreferences("myPreferences", MODE_PRIVATE);

                            spEditor = sp.edit();
                            spEditor.putString("username", u);
                            spEditor.putString("password", p);
                            spEditor.commit();
                        } catch (Exception ex) {

                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(Conf.UNIVERSAL_UID, uid);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Welcome " + fullname, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }, 1000);



        } catch (Exception e){

        }
    }

}