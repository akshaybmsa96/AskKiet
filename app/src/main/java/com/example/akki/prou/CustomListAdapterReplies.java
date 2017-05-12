package com.example.akki.prou;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Akki on 14-04-2017.
 */

public class CustomListAdapterReplies extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> reply;
    private final ArrayList<String> name;

    public CustomListAdapterReplies(Activity context, ArrayList<String> reply, ArrayList<String> name) {
        super(context, R.layout.reply_layout, reply);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.reply=reply;
        this.name=name;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.reply_layout, null,true);

        TextView r = (TextView) rowView.findViewById(R.id.heading);
        TextView by = (TextView) rowView.findViewById(R.id.textView3);
        String data=reply.get(position);
        String data1=name.get(position);

        r.setText(data);
        by.setText(data1);
        return rowView;
    };

}
