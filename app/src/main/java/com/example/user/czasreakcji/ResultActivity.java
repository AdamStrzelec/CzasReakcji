package com.example.user.czasreakcji;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reslt);

        Intent intent = getIntent();
        correctAnswers = intent.getIntExtra(GameActivity.CORRECT_ANSWERS_COUNT, 0);

        TextView correctCount = (TextView) findViewById(R.id.correctCount);
        correctCount.setText(""+correctAnswers);
    }

}
