package com.example.akki.prou;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akki on 10-04-2017.
 */

public class DbTaskQuesRec extends AsyncTask<String , Void , String> {

    Context ctx;
    String Jo,json_string;
    ProgressDialog mProgress;
    CustomListAdapterReplies ca;
    Activity a;
    ListView l;
    public ArrayList<String> replytext = new ArrayList<String>();
    public ArrayList<String> Name = new ArrayList<String>();
    public ArrayList<String> rid = new ArrayList<String>();
    TextView n , h ,q;
    public String title;
    public String name;
    public String question;
    DbTaskQuesRec(Context ctx, Activity a , String name , String title , String question , TextView n , TextView h , TextView q , ArrayList<String> replytext, ArrayList<String> Name, ArrayList<String> rid , ListView l)
    {
        this.ctx=ctx;
        this.l=l;
        this.title=title;
        this.name=name;
        this.question=question;
        this.n=n;
        this.a=a;
        this.h=h;
        this.q=q;
        this.replytext=replytext;
        this.Name=Name;
        this.rid=rid;
    }

    @Override
    protected String doInBackground(String... params) {

        String ip_url="http://shivtravel.ueuo.com/prou/get_question.php";

     //   String ip_url="http://10.0.3.2/prou/get_question.php";

        String qid=params[0];


        try {

            URL url = new URL(ip_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter br1=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("qid","UTF-8")+"="+URLEncoder.encode(qid,"UTF-8");
            br1.write(data);
            br1.flush();
            br1.close();
            OS.close();


            InputStream IS=httpURLConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(IS));
            StringBuilder sb=new StringBuilder();

            while((Jo=br.readLine())!=null)
            {
                sb.append(Jo+"\n");

            }

            br.close();
            IS.close();
            httpURLConnection.disconnect();
            String s=sb.toString().trim();
            //  Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            return s;

        }

        catch (Exception e) {
            e.printStackTrace();
        }


        return "Error";
    }

    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(ctx);
        mProgress.setMessage("Loading... ");
        mProgress.show();
        mProgress.setCancelable(false);
    }

    @Override
    protected void onPostExecute(String result) {
        json_string=result;

        JSONObject j;
        JSONArray jsonArray;
        int count = 0;
        String t,rrid,nm;
        ca = new CustomListAdapterReplies(a, replytext, Name);
        ca.clear();

        try {

            j = new JSONObject(json_string);
            jsonArray=j.getJSONArray("response");


                JSONObject jo=jsonArray.getJSONObject(0);
                JSONArray j1=jo.getJSONArray("respq");
            JSONObject jo1=j1.getJSONObject(0);

            title=jo1.getString("heading");
            name=jo1.getString("name");
            question=jo1.getString("question");

            n.setText(name);
            h.setText(title);
            q.setText(question);

            JSONArray j2=jo.getJSONArray("respr");

            while (count < j2.length()) {
                JSONObject jo2 = j2.getJSONObject(count);
                t = jo2.getString("replytext");
                nm = jo2.getString("name");
                rrid= jo2.getString("rid");

             //      Toast.makeText(ctx, nm , Toast.LENGTH_LONG).show();

                replytext.add(t);
                Name.add(nm);
                rid.add(rrid);

                count++;
            }

            l.setAdapter(ca);



                 // Toast.makeText(ctx, title, Toast.LENGTH_LONG).show();
                mProgress.dismiss();



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }
