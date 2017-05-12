package com.example.akki.prou;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewQuestions extends AppCompatActivity {

    ListView l;
    public ArrayList<String> title=new ArrayList<String>();
    public ArrayList<String> name=new ArrayList<String>();
    public ArrayList<String> qid=new ArrayList<String>();
    PreferenceData pf;
    CustomListAdapterQuestions ca;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);
       // titl.add("yo");
       // nm.add("oku");

        Toolbar tb=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        getSupportActionBar().setTitle("Feed");
        tb.setTitleTextColor(0XFFFFFFFF);
        l=(ListView)findViewById(R.id.listviewid);


            refresh();
     //  DbTaskAllQuesRec db =new DbTaskAllQuesRec(this,l,this,name,title,qid);
       //db.execute();


        final EditText search;
        search=(EditText)findViewById(R.id.sedittext);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String topic=search.getText().toString();
                    performSearch(l,topic);
                    return true;
                }
                return false;
            }
        });


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String quid =qid.get(position);

               // Toast toast=Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                //toast.show();

                Intent intent=new Intent(ViewQuestions.this,QuestionFull.class);
                intent.putExtra("qid",quid);
                startActivity(intent);

            }
        });

    }

    void performSearch(ListView l,String topic) {   // search part here

            //  Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();

        DbTaskSearchQuesRec db =new DbTaskSearchQuesRec(this,l,this,name,title,qid);
        db.execute(topic);

    }

    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.refresh)
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

            AlertDialog.Builder builder = new AlertDialog.Builder(ViewQuestions.this);
            builder.setTitle("Log Out");
            builder.setMessage("Are You Sure?");
            builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent i = new Intent(ViewQuestions.this, LoginActivity.class);
                    pf.setUserLoggedInStatus(ViewQuestions.this,false);
                    pf.clearLoggedInEmailAddress(ViewQuestions.this);
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
        DbTaskAllQuesRec db =new DbTaskAllQuesRec(this,l,this,name,title,qid);
        db.execute();
    }
}