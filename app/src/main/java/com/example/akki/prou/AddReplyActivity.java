package com.example.akki.prou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddReplyActivity extends AppCompatActivity {
    EditText reply;
    PreferenceData pf;
    String qid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reply);

        Toolbar tb=(Toolbar)findViewById(R.id.toolbar);
        tb.setTitleTextColor(0XFFFFFFFF);
        reply=(EditText)findViewById(R.id.replytextid);

        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Add Answer");
        tb.setNavigationIcon(R.mipmap.icon_back_button);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       qid = getIntent().getStringExtra("qid");

        Button as=(Button)findViewById(R.id.button);
        as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(reply.getText().toString().equals(""))
                    {
                        Toast.makeText(AddReplyActivity.this,"INVALID Input",Toast.LENGTH_LONG).show();
                    }
                else {
                        String r = reply.getText().toString();
                        addreply(r,qid);
                    }

            }
        });
    }

    void addreply(String r,String qid) {

        DbTaskAnswer db=new DbTaskAnswer(this);
        db.execute(qid,r,pf.getLoggedInUserName(this),pf.getLoggedInUserId(this));
        Intent intent=new Intent(this,QuestionFull.class);
        intent.putExtra("qid",qid);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
