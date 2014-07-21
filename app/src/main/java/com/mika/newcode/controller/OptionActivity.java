package com.mika.newcode.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.mika.newcode.R;
import com.mika.newcode.adapters.ExpandableDrawerListAdapter;
import com.mika.newcode.builders.DynamicContentTaskBuilder;
import com.mika.newcode.database.CheckInDao;
import com.mika.newcode.models.AllData;
import com.mika.newcode.models.Meeting;
import com.mika.newcode.network.request.GetDynamicContent;
import com.mika.newcode.network.request.TaskListener;
import com.mika.newcode.utils.Constants;

import org.apache.http.client.methods.HttpGet;

import java.util.ArrayList;
import java.util.List;


public class OptionActivity extends Activity {
   // private TextView resultTextView;
    private CheckInDao checkInDao;


    //获取数据成功
    private TaskListener<AllData> onTaskListener = new TaskListener<AllData>() {
        @Override
        public void onTaskSuccess(AllData result) {
            if (result != null) {
                storeData(result);

            }
        }

        @Override
        public void onTaskFailure(Exception e) { }

        @Override
        public void onTaskCancelled() {}

        @Override
        public void onTaskTimeOut() { }
    };

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

        checkInDao=new CheckInDao(getApplicationContext());

    }


    //clear database
    private void clearDB(){
        Log.v("222","clearDB");
        checkInDao.deleteAll();
    }

    //update database
    private void updateDB(){
        Log.v("222","updateDB");
    }

    //import database
    private void importDB(){
        clearDB();
        Log.v("222","updateDB");
        makeRequest();
    }

    private void makeRequest(){
        GetDynamicContent<AllData> mobileMenusTask = new DynamicContentTaskBuilder<AllData>()
                .withContext(OptionActivity.this)
                .withPath(Constants.URL_PATH)
                .withMethod(HttpGet.METHOD_NAME)
                .withTaskListener(onTaskListener)
                .withType(new TypeToken<AllData>() {
                }.getType())
                .build();
        mobileMenusTask.execute();
    }

    private void storeData(AllData result){
        List<Meeting> meetings = result.getMeetings();
        for (Meeting meeting:meetings) {
            checkInDao.insertToMeeting(meeting.getMid(),meeting.getName());
        }

        checkInDao.printData();
    }


}