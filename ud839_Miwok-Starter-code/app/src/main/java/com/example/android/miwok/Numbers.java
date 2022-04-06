package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> words= new ArrayList<>();
        words.add(new Word("एक","one",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("दो ","two",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("तीन ","three",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("चार ","four",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("पाँच ","five",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("छः ","six",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("सात ","seven",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("आठ ","eight",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("नौ ","nine",R.drawable.number_nine,R.raw.number_nine));







        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.word_list);

        listView.setAdapter(itemsAdapter);











    }
    @Override
    protected void onStop(){
        super.onStop();
        WordAdapter.releasemedia();
    }
}