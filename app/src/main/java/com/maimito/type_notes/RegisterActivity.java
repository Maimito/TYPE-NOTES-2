package com.maimito.type_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.material.textfield.TextInputEditText;
import com.maimito.type_notes.handler.ResponseHandler;
import com.maimito.type_notes.model.LoginModel;
import com.maimito.type_notes.model.RegisterModel;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText usernameReg;
    private TextInputEditText passwordReg;
    private TextInputEditText fullname;
    private ActionProcessButton regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameReg = (TextInputEditText) findViewById(R.id.username_reg);
        passwordReg = (TextInputEditText) findViewById(R.id.password_reg);
        fullname = (TextInputEditText) findViewById(R.id.fullname);
        regBtn = (ActionProcessButton) findViewById(R.id.reg_btn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = String.valueOf(usernameReg.getText()); //username
                String p = String.valueOf(passwordReg.getText()); //password
                String fn = String.valueOf(fullname.getText());

                RegisterModel lm = new ViewModelProvider(RegisterActivity.this).get(RegisterModel.class);
                boolean state = true;

                lm.register(u, p, fn).observe(RegisterActivity.this, new Observer<ResponseHandler>() {
                    @Override
                    public void onChanged(ResponseHandler responseHandler) {
                        Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });

    }
}