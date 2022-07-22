package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

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
        TextView magView = (TextView) listItemView.findViewById(R.id.magnitude);
        magView.setText((word.getMag()));
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
        double mag=Double.parseDouble(word.getMag());
        int magint = (int) Math.floor(mag);
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        switch(magint) {
            case 3:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude3));
                break;
            case 4:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude4));
                break;
            case 5:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude5));
            case 6:
                // Set the color on the magnitude circle
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude6));
                break;
            case 7:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude7));
                break;
            default:
                // code block
        }
        return listItemView;

    }
}

