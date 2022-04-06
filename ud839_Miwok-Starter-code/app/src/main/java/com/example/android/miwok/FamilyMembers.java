package com.example.android.miwok;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> words= new ArrayList<>();
        words.add(new Word("पापा ","father",R.drawable.family_father, R.raw.family_father));
        words.add(new Word("माँ ","mother",R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("बेटा ","son",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("बेटी ","daughter",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("भाई ","brother",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("बहन  ","sister",R.drawable.family_older_sister,R.raw.family_older_sister));
        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.word_list);

        listView.setAdapter(itemsAdapter);


    }
    @Override
    protected void onStop(){
        super.onStop();
        WordAdapter.releasemedia();
    }

}