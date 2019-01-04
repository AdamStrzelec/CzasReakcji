package com.example.user.czasreakcji;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioGroup = findViewById(R.id.radioGroup);

        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                System.out.println("tekst: " + radioButton.getText());
                if(radioButton.getText().equals("Poziom łatwy (10 zadań)")){
                    Levels.CURRENT_LEVEL = Levels.EASY_LEVEL;
                }
                else if(radioButton.getText().equals("Poziom średni (15 zadań)")){
                    Levels.CURRENT_LEVEL = Levels.MEDIUM_LEVEL;
                }
                else if(radioButton.getText().equals("Poziom trudny (20 zadań)")){
                    Levels.CURRENT_LEVEL = Levels.HIGH_LEVEL;
                }
                System.out.println("poziom: " + Levels.CURRENT_LEVEL);
            }
        });
    }

}
