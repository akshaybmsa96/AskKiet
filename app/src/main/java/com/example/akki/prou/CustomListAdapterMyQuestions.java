package com.example.akki.prou;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Akki on 18-04-2017.
 */

public class CustomListAdapterMyQuestions extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> heading;
    private final ArrayList<String> date;

    public CustomListAdapterMyQuestions(Activity context, ArrayList<String> heading, ArrayList<String> date) {
        super(context, R.layout.my_question_layout, heading);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.heading=heading;
        this.date=date;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_question_layout, null,true);

        TextView title = (TextView) rowView.findViewById(R.id.heading);
        TextView dat = (TextView) rowView.findViewById(R.id.dateid);
        String data=heading.get(position);
        String data1=date.get(position);

        title.setText(data);
        dat.setText(data1);
        return rowView;
    };

}
