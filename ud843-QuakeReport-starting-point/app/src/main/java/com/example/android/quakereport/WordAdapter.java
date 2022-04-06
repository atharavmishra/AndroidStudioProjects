package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word word = (Word) getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        nameTextView.setText((word.getMag()));
        TextView nameTextView2 = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView cityTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        String loc = word.getCity();
        if(loc.contains("of")) {
            String a[] = loc.split("of");
            nameTextView2.setText(a[0]+"of");
            cityTextView.setText(a[1]);
        }
        else{


            cityTextView.setText(word.getCity());
            nameTextView2.setText("near the");
        }
        TextView nameTextView3 = (TextView) listItemView.findViewById(R.id.date);
        nameTextView3.setText(word.getDate());
        TextView timetextview=(TextView) listItemView.findViewById(R.id.time);
        timetextview.setText(word.getTime());
        return listItemView;

    }
}

