package android.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public int score_a =0;
public int score_b=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void three(View view){
        score_a+=3 ;
        displayForTeamA(score_a);

    }
    public void two(View view) {
        score_a+= 2;
        displayForTeamA(score_a);
    }
    public void Free(View view) {
        score_a+= 1;
        displayForTeamA(score_a);
    }
    public void three_b(View view){
        score_b+=3 ;
        displayForTeamB(score_b);

    }
    public void two_b(View view) {
        score_b+= 2;
        displayForTeamB(score_b);
    }
    public void Free_b(View view) {
        score_b+= 1;
        displayForTeamB(score_b);
    }
    public void reset_a(View view){
        score_a=0;
        displayForTeamA(score_a);
    }
    public void reset_b(View view){
        score_b=0;
        displayForTeamB(score_b);
    }



    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}