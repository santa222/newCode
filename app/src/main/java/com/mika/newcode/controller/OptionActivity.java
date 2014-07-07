package com.mika.newcode.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mika.newcode.R;


public class OptionActivity extends Activity {
    private TextView resultTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

       Button clearButton = (Button) this.findViewById(R.id.option_clear_btn);
        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clearDB();
            }
        });

        Button updateButton = (Button) this.findViewById(R.id.option_update_btn);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateDB();
            }
        });

        final Button importButton = (Button) this.findViewById(R.id.option_import_btn);
        importButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                importDB();
            }
        });

    }


    //clear database
    private void clearDB(){
        Log.v("222","clearDB");
    }

    //update database
    private void updateDB(){
        Log.v("222","updateDB");
    }

    //import database
    private void importDB(){
        Log.v("222","updateDB");
    }


}