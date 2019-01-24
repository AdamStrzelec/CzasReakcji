package com.example.user.czasreakcji;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {


    DatabaseHelper myDb;
    String stats;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        myDb = new DatabaseHelper(this);

        Cursor res = myDb.getAllData();

        if(res.getCount() == 0){
            //message
            showMessage("error", "nothoing found");
            return;
        }


        //StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()){
            stats += "Avg time: "+res.getString(1)+"\n";
            stats += "Level: "+res.getString(2)+"\n \n";
        }


        TextView tv = (TextView) findViewById(R.id.statsTextView);
        tv.setText(stats);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
