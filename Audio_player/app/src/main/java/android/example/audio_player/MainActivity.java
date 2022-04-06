package android.example.audio_player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void start(View view){
        if(mediaPlayer==null)
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);

        mediaPlayer.start();
    }
    public void pause(View view){

        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }

    }
    

}