package com.example.android.miwok;

import android.media.MediaPlayer;

public class Word {
    private String English;
    private String Hindi;
    private static final int HAS_IMAGE=-1;
    private int ImageResourceID=HAS_IMAGE;
    private int audio_id;
    Word (String HindiWord, String EnglishWord, int img_id, int audio){
        this.English=EnglishWord;
        this.Hindi=HindiWord;
        this.audio_id=audio;
        this.ImageResourceID=img_id;



    }
    Word (String HindiWord, String EnglishWord, int audio){
        this.English=EnglishWord;
        this.Hindi=HindiWord;
        this.audio_id=audio;


    }
//    Word (String HindiWord, String EnglishWord, int imgid){
//        this.English=EnglishWord;
//        this.Hindi=HindiWord;
//        this.ImageResourceID=imgid;
//
//    }
    public String getmiwokWord(){
        return Hindi;

    }
    public String getEnglisWord(){

        return English;
    }
    public int getImageResourceID(){

        return ImageResourceID;
    }
    public boolean has_image(){

        return ImageResourceID!=HAS_IMAGE;
    }

    public int get_audioID(){

        return audio_id;
    }
}
