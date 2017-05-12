package com.example.akki.prou;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    PreferenceData pf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login=(Button)findViewById(R.id.btnSignIn);
        final EditText un=(EditText)findViewById(R.id.uname);
        final EditText ps=(EditText)findViewById(R.id.upass);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 TextInputLayout unl = (TextInputLayout) findViewById(R.id.lv2);
                 TextInputLayout upl = (TextInputLayout) findViewById(R.id.lv3);

                if(un.getText().toString().equals("") && ps.getText().toString().equals("")) {
                    if (un.getText().toString().equals("")) {
                        unl.setError("INVALID Username");
                        unl.setErrorEnabled(true);
                    } else {
                        unl.setErrorEnabled(false);
                    }
                    if (ps.getText().toString().equals("")) {
                        upl.setError("INVALID Password");
                        upl.setErrorEnabled(true);
                    } else {
                        upl.setErrorEnabled(false);
                    }
                }

                else if(!(un.getText().toString().equals("") && ps.getText().toString().equals("")) )
                {
                    DbTaskLogin db = new DbTaskLogin(LoginActivity.this,LoginActivity.this);
                    db.execute(un.getText().toString(),ps.getText().toString());
                }
            /*     if(un.getText().toString().equals("1402910016") && ps.getText().toString().equals("1234")) {
                     pf.setUserLoggedInStatus(LoginActivity.this,true);
                     Intent i=new Intent(LoginActivity.this, ViewQuestions.class);
                     i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     startActivity(i);

                 } */

            }
        });
    }
}
