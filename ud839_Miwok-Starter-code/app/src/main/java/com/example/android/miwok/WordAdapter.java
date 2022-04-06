package com.example.android.miwok;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    int colors;
    static MediaPlayer mediaPlayer;
    WordAdapter(Activity context, ArrayList<Word> words, int color){
        super(context, 0, words);
        this.colors=color;

    }

    
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word word = getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.text_view_1);
        nameTextView.setText(word.getEnglisWord());
        TextView nameTextView2 = (TextView) listItemView.findViewById(R.id.text_view_2);
        nameTextView2.setText(word.getmiwokWord());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageview);
        View background = listItemView.findViewById(R.id.linearlayout2);
        int color = ContextCompat.getColor(getContext(), colors);
        background.setBackgroundColor(color);
        View background2 = listItemView.findViewById(R.id.Button);
        background2.setBackgroundColor(color);
        if (word.has_image()) {
            imageView.setImageResource(word.getImageResourceID());
        } else {
            imageView.setVisibility(View.GONE);
        }



        ImageButton button = listItemView.findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(getContext(), word.get_audioID());
                    mediaPlayer.start();

                }
            });



      
        return listItemView;
    }
    public static void releasemedia(){
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
    }
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mediaPlayer.release();
            }
        }
    };
}
