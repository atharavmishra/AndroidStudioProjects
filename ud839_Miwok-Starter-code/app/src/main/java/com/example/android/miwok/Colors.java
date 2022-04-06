package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("लाल ", "red", R.drawable.color_red, R.raw.red));
        words.add(new Word("भूरा ", "blue", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("पीला ", "yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("हरा ", "green", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("काला ", "black", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("सफ़ेद ", "white", R.drawable.color_white, R.raw.color_white));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.word_list);

        listView.setAdapter(itemsAdapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        WordAdapter.releasemedia();
    }
}

