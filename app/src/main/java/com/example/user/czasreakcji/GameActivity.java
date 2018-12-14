package com.example.user.czasreakcji;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button correctButon;
    private Button incorrectButon;
    private TextView colorName;
    private Colors colors;
    private Random random;
    private int generatedColorName;
    private int generatedColorValue;
    private int points;

    public static final String CORRECT_ANSWERS_COUNT = "com.example.user.czasreakcji.CORRECT_ANSWERS_COUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        correctButon = (Button) findViewById(R.id.correctButton);
        correctButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        incorrectButon = (Button) findViewById(R.id.incorrectButton);
        incorrectButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        colorName = (TextView) findViewById(R.id.colorName);

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

        if(generatedColorName==generatedColorValue){
            if(isCorrect)points++;
            else openResultWindow();
        }else{
            if(!isCorrect)points++;
            else openResultWindow();
        }
        System.out.println("punkty" + points);
        newTask();
    }

    public void newTask(){
        generatedColorName = random.nextInt(colors.colorsTab.length);
        generatedColorValue = random.nextInt(colors.colorsTab.length);
        colorName.setText(colors.colorsTab[generatedColorName].getColorName());
        colorName.setTextColor(Color.rgb(colors.colorsTab[generatedColorValue].getColorValueR(),colors.colorsTab[generatedColorValue].getColorValueG(),colors.colorsTab[generatedColorValue].getColorValueB()));
        System.out.println(generatedColorName + " lol " + generatedColorValue);
    }

    public void openResultWindow(){

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(CORRECT_ANSWERS_COUNT, points);
        startActivity(intent);
    }

}
