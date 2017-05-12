package com.example.akki.prou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AskQuestionActivity extends AppCompatActivity {

    EditText e1,e2;
    String h,q,n,uid;
    PreferenceData pf;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        Button b1;

        b1= (Button)findViewById(R.id.button);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText3);

        Toolbar tb=(Toolbar)findViewById(R.id.toolbar);
        tb.setTitleTextColor(0XFFFFFFFF);

        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Ask Your Question");
        tb.setNavigationIcon(R.mipmap.icon_back_button);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                h=e1.getText().toString();
                q=e2.getText().toString();
                n=pf.getLoggedInUserName(AskQuestionActivity.this);
                uid=pf.getLoggedInUserId(AskQuestionActivity.this);

                if(h.isEmpty() || q.isEmpty() || n.isEmpty())
                {
                    Toast.makeText(AskQuestionActivity.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }

                else{
                    upload();
                    e1.setText("");
                    e2.setText("");
                }

            }
        });


    }


      void upload() {
          DbTaskSend db=new DbTaskSend(AskQuestionActivity.this);
          db.execute(h,q,n,uid);
          finish();
    }

}
