package com.example.projetdevmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<ScoreData> {
    private final Context context;

    public MyArrayAdapter(Context context, ArrayList<ScoreData> values) {
        super(context, R.layout.cell_layout, values);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView = convertView;

        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.cell_layout, parent, false);
        }

        TextView lbNom = (TextView) cellView.findViewById(R.id.lbnomAff);
        TextView lbScore = (TextView) cellView.findViewById(R.id.lbscoreAff);
        TextView lbDate = (TextView) cellView.findViewById(R.id.lbdateAff);

        ScoreData s = getItem(position);
        lbNom.setText(String.valueOf(s.getName())+" : ");
        lbScore.setText(String.valueOf(s.getScore())+" coups ");
        lbDate.setText("Le : "+String.valueOf(s.getDate()));

        return cellView;
    }
}
