package com.example.akki.prou;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionFull extends AppCompatActivity {

    public ArrayList<String> replytext = new ArrayList<String>();
    public ArrayList<String> Name = new ArrayList<String>();
    public ArrayList<String> rid = new ArrayList<String>();
    CustomListAdapterReplies ca;
    PreferenceData pf;
    ListView l;
    Button as;
    String qid,name,heading,question;
    TextView qb,head,q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_full);



        as=(Button)findViewById(R.id.answerbuttonid);
        l = (ListView) findViewById(R.id.listviewid);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        qb = (TextView) findViewById(R.id.questionby);
        q = (TextView) findViewById(R.id.question);
        head = (TextView) findViewById(R.id.head);
        setSupportActionBar(tb);

        getSupportActionBar().setTitle("Question");
        tb.setTitleTextColor(0XFFFFFFFF);
        tb.setNavigationIcon(R.mipmap.icon_back_button);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        qid = getIntent().getStringExtra("qid");
        name = new String();
        heading = new String();
        question = new String();

        as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QuestionFull.this,AddReplyActivity.class);
                i.putExtra("qid",qid);
                startActivity(i);
            }
        });

        DbTaskQuesRec db = new DbTaskQuesRec(this, this, name, heading, question, qb, head, q ,replytext,Name,rid,l);
        db.execute(qid);


        //  qb.setText(name);
        // q.setText(question);
        // head.setText(heading);

    }



    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
       refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==R.id.refresh)
        {
            refresh();
        }

        if (id == R.id.askq) {
            Intent i;
            i = new Intent(this, AskQuestionActivity.class);
            startActivity(i);
        }

        if(id==R.id.my_question)
        {
            Intent i;
            i = new Intent(this, MyQuestionsActivity.class);
            startActivity(i);
        }


        if (id == R.id.logout_id) {

            AlertDialog.Builder builder = new AlertDialog.Builder(QuestionFull.this);
            builder.setTitle("Log Out");
            builder.setMessage("Are You Sure?");
            builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent i = new Intent(QuestionFull.this, LoginActivity.class);
                    pf.setUserLoggedInStatus(QuestionFull.this,false);
                    pf.clearLoggedInEmailAddress(QuestionFull.this);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);


                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();

        }
        return super.onOptionsItemSelected(item);
    }

    void refresh()
    {
        DbTaskQuesRec db = new DbTaskQuesRec(this, this, name, heading, question, qb, head, q ,replytext,Name,rid,l);
        db.execute(qid);

    }
}
