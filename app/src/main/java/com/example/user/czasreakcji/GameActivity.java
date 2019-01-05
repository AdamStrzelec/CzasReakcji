package com.example.user.czasreakcji;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button correctButon;
    private Button incorrectButon;
    private TextView colorName;
    private TextView level;
    private ProgressBar progressBar;
    private Colors colors;
    private Random random;
    private int generatedColorName;
    private int generatedColorValue;
    private int points;
    private boolean gameEnded = false;
    public int counter = 0;

    public List<Integer> counters = new ArrayList<>();

    public static final String CORRECT_ANSWERS_COUNT = "com.example.user.czasreakcji.CORRECT_ANSWERS_COUNT";
    public static final String AVERAGE_TIME = "com.example.user.czasreakcji.AVERAGE_TIME";
    public static final String SLOWEST_ANSWER = "com.example.user.czasreakcji.SLOWEST_ANSWER";
    public static final String FASTEST_ANSWER = "com.example.user.czasreakcji.FASTEST_ANSWER";

    public MediaPlayer correctMP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //timer = (TextView) findViewById(R.id.timer);
        //final TextView timer = (TextView) findViewById(R.id.timer);
        correctButon = (Button) findViewById(R.id.correctButton);
        correctButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                //System.out.println("timer: " + counter);

            }
        });

        incorrectButon = (Button) findViewById(R.id.incorrectButton);
        incorrectButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        colorName = (TextView) findViewById(R.id.colorName);
        level = (TextView) findViewById(R.id.level);

        colors = new Colors();

        random = new Random();

        points = 0;

        newTask();
    }

    public void checkAnswer(boolean isCorrect){
        System.out.println(isCorrect);
        colorName.setText("lolo");
        int colorNr = 2;
        colorName.setTextColor(Color.rgb(colors.colorsTab[colorNr].getColorValueR(),colors.colorsTab[colorNr].getColorValueG(),colors.colorsTab[colorNr].getColorValueB()));
        //colorName.setTextColor(Color.rgb(255,0,0));
        //System.out.println(colors.colorsTab[0]);
        if(counter>=100){
            openResultWindow();
        }

        if(generatedColorName==generatedColorValue){
            if(isCorrect){
                this.playCorrectSound();
                points++;
                counters.add(counter);
                if(points==Levels.CURRENT_LEVEL){
                    openResultWindow();
                }
            }
            else openResultWindow();
        }else{
            if(!isCorrect){
                this.playCorrectSound();
                points++;
                counters.add(counter);
                if(points==Levels.CURRENT_LEVEL){
                    openResultWindow();
                }
            }

            else openResultWindow();
        }
        System.out.println("punkty" + points);
        System.out.println("counterro: " + counter);

        newTask();
    }

    public void newTask(){
        if(Levels.CURRENT_LEVEL==10){
            level.setText("Poziom łatwy");
        }
        else if(Levels.CURRENT_LEVEL==15){
            level.setText("Poziom średni");
        }
        else{
            level.setText("Poziom trudny");
        }
        generatedColorName = random.nextInt(colors.colorsTab.length);
        generatedColorValue = random.nextInt(colors.colorsTab.length);
        colorName.setText(colors.colorsTab[generatedColorName].getColorName());
        colorName.setTextColor(Color.rgb(colors.colorsTab[generatedColorValue].getColorValueR(),colors.colorsTab[generatedColorValue].getColorValueG(),colors.colorsTab[generatedColorValue].getColorValueB()));

        System.out.println("counter przed: " + counter);
        counter = 0;

        final TextView timer = (TextView) findViewById(R.id.timer);
        if(gameEnded==false) {
            new CountDownTimer(10000, 100) {
                public void onTick(long millisUntilFinished) {
                    timer.setText(String.valueOf(counter));
                    if(counter<=100) {
                        counter++;
                        progressBar.setProgress(counter);
                    }

                    if(counter>=100){
                        gameEnded = true;
                        System.out.println("game ended " + gameEnded);
                        //checkAnswer(false);
                    }




                }

                public void onFinish() {

                }
            }.start();
        }
        System.out.println("counter po: "+ counter);
    }

    public void playCorrectSound() {
        this.correctMP = MediaPlayer.create(this, R.raw.correct);
        this.correctMP.start();
        this.correctMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            };
        });
    }

    public void openResultWindow(){

        int sum = 0;
        int avg = 0;
        int min = 0;
        int max = 0;
        if(counters.size()!=0) {
            min = counters.get(0);
            max = counters.get(0);
        }
        for(Integer i : counters){
            System.out.println("czasy: " + i);
            sum += i;
            if(i<min)min=i;
            if(i>max)max=i;
        }

        if(points>0)avg = sum/points;
        else avg=0;

        //System.out.println("suma: " + sum + " average: " + avg + " najszybsza odpowiedz: " + min + " najwolniejsza opowiedz: " + max);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(CORRECT_ANSWERS_COUNT, points);
        intent.putExtra(AVERAGE_TIME, avg);
        intent.putExtra(SLOWEST_ANSWER, max);
        intent.putExtra(FASTEST_ANSWER, min);
        startActivity(intent);
        finish();
    }

}
