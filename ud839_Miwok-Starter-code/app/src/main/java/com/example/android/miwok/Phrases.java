package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> words= new ArrayList<>();
        words.add(new Word("तुम कहाँ जा रहे हो ? ","Where are you going?",R.raw.phrase_where_are_you_going));
        words.add(new Word(" तुम्हारा नाम क्या है ?","What is your name?",R.raw.phrase_what_is_your_name));
        words.add(new Word(" मेरा नाम ...... है","My name is...",R.raw.phrase_my_name_is));
        words.add(new Word("तुम कैसा महसूस कर रहे हो ? ","How are you feeling?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("मैं अच्छा महसूस कर रहा हूँ ","I’m feeling good.",R.raw.phrase_im_feeling_good));
        words.add(new Word(" क्या तुम आ रहे हो ? ","Are you coming?",R.raw.phrase_are_you_coming));
        words.add(new Word(" हाँ मैं आ rha हूँ ","Yes, I’m coming.",R.raw.phrase_im_coming));
        words.add(new Word(" चलो चलते हैं  ","Let’s go",R.raw.phrase_lets_go));
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.word_list);

        listView.setAdapter(itemsAdapter);

    }
    @Override
    protected void onStop(){
        super.onStop();
        WordAdapter.releasemedia();
    }
}