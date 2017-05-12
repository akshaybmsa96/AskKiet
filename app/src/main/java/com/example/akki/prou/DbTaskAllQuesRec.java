package com.example.akki.prou;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Akki on 04-03-2017.
 */

public class DbTaskAllQuesRec extends AsyncTask<Void,Void,String> {

    Context ctx;
    String Jo,json_string;
    ProgressDialog mProgress;
    
    public ArrayList<String> title;
    public ArrayList<String> name;
    public ArrayList<String> qid;
    ListView l ;
    CustomListAdapterQuestions ca;
    Activity a;
    DbTaskAllQuesRec(Context ctx , ListView l , Activity a, ArrayList<String> name,ArrayList<String> title,ArrayList<String> qid )
    {
        this.ctx=ctx;
        this.l=l;
        this.a=a;
        this.name=name;
        this.title=title;
        this.qid=qid;
    }

    @Override
    public String doInBackground(Void ...voids) {

        String ip_url="http://shivtravel.ueuo.com/prou/fetch_all.php";
        //  String ip_url="http://10.0.3.2/prou/fetch_all.php";

        try {

            URL url = new URL(ip_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
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
    public void onPreExecute() {
      //  super.onPreExecute();

        mProgress = new ProgressDialog(ctx);
        mProgress.setMessage("Loading... ");
        mProgress.show();
        mProgress.setCancelable(false);
    }

    @Override
    public void onPostExecute(String result) {
     //   Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();




            json_string = result;

            JSONObject j;
            JSONArray jsonArray;

            ca = new CustomListAdapterQuestions(a, title, name);
            ca.clear();
            String t, n ,id;
            int count = 0;
            try {

                j = new JSONObject(json_string);
                jsonArray = j.getJSONArray("response");

                while (count < jsonArray.length()) {
                    JSONObject jo = jsonArray.getJSONObject(count);
                    t = jo.getString("heading");
                    n = jo.getString("name");
                    id= jo.getString("qid");

                    //   Toast.makeText(ctx, t+" "+n , Toast.LENGTH_LONG).show();

                    title.add(t);
                    name.add(n);
                    qid.add(id);

                    count++;
                }

                l.setAdapter(ca);
                mProgress.dismiss();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        mProgress.dismiss();
    }
}
