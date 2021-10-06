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
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.maimito.type_notes.adapter.SliderLoginAdapter;
import com.maimito.type_notes.handler.Conf;
import com.maimito.type_notes.handler.ResponseHandler;
import com.maimito.type_notes.model.LoginModel;
import com.maimito.type_notes.model.SliderList;
import com.smarteist.autoimageslider.SliderView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText username;
    private TextInputEditText password;
    private ActionProcessButton loginFlatButton, registerFlatButton;
    private LoginModel lm;
    private String uid, fullname;
    private Handler handler;
    private boolean state;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    private String u,p;
    Context context;
    private SliderList sliderList;

    String[] title ={"Create notes", "Modify notes"};
    String[] image = {"https://instagram.fcgk27-1.fna.fbcdn.net/v/t51.2885-15/sh0.08/e35/p640x640/241729582_395739688625845_2212968315757942974_n.jpg?_nc_ht=instagram.fcgk27-1.fna.fbcdn.net&_nc_cat=1&_nc_ohc=0nQOhqIym5cAX_yFnvB&edm=AP_V10EBAAAA&ccb=7-4&oh=c65f844d76b4034066a24b0bdf2f7b25&oe=61450E89&_nc_sid=4f375e",
                        "https://instagram.fcgk27-1.fna.fbcdn.net/v/t51.2885-15/sh0.08/e35/p640x640/241729582_395739688625845_2212968315757942974_n.jpg?_nc_ht=instagram.fcgk27-1.fna.fbcdn.net&_nc_cat=1&_nc_ohc=0nQOhqIym5cAX_yFnvB&edm=AP_V10EBAAAA&ccb=7-4&oh=c65f844d76b4034066a24b0bdf2f7b25&oe=61450E89&_nc_sid=4f375e"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginFlatButton = findViewById(R.id.login_btn);
        registerFlatButton = findViewById(R.id.goto_register);
        SliderView sliderView = findViewById(R.id.login_slider);
        List daftar = Data();
        sliderView.setSliderAdapter(new SliderLoginAdapter(getApplicationContext(), daftar));
        sp = getSharedPreferences("loginPreference", MODE_PRIVATE);
        CheckLogin();

        loginFlatButton.setOnClickListener(view -> {
            DoLogin();
        });

        registerFlatButton.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
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

    private List Data(){
        ArrayList arrayList = new ArrayList<>();
        for(int i = 0; i < title.length; i++){
            SliderList sliderList = new SliderList();
            sliderList.setTitle(title[i]);
            sliderList.setImage(image[i]);
            arrayList.add(sliderList);
        }
        return arrayList;
    }

}