package com.mika.newcode.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;
import com.mika.newcode.R;
import com.mika.newcode.builders.DynamicContentTaskBuilder;
import com.mika.newcode.database.CheckInDao;
import com.mika.newcode.models.AllData;
import com.mika.newcode.models.Gift;
import com.mika.newcode.models.GiftRole;
import com.mika.newcode.models.Meeting;
import com.mika.newcode.models.Role;
import com.mika.newcode.models.User;
import com.mika.newcode.models.UserRole;
import com.mika.newcode.network.request.GetDynamicContent;
import com.mika.newcode.network.request.TaskListener;
import com.mika.newcode.utils.Constants;

import org.apache.http.client.methods.HttpGet;

import java.util.ArrayList;
import java.util.List;


public class OptionActivity extends Activity {
    private CheckInDao checkInDao;
    private DialogState currentState=null;

    public enum DialogState{
        Clear,
        Download,
        Upload,
    }

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

        ImageView clearButton = (ImageView) this.findViewById(R.id.option_clear_iv);
        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //clearDB();
                currentState=DialogState.Clear;
                showDialog();
            }
        });



        ImageView uploadButton = (ImageView) this.findViewById(R.id.option_upload_iv);
        uploadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentState=DialogState.Upload;
                showDialog();
            }
        });

        ImageView downloadButton = (ImageView) this.findViewById(R.id.option_download_iv);
        downloadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentState=DialogState.Download;
                showDialog();
            }
        });

        ImageView searchButton = (ImageView) this.findViewById(R.id.option_search_iv);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionActivity.this, SearchListActivity.class));
            }
        });

        ImageView meetingButton = (ImageView) this.findViewById(R.id.option_meeting_iv);
        meetingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionActivity.this, MeetingListActivity.class));
            }
        });

        checkInDao=new CheckInDao(getApplicationContext());

    }


    //clear database
    private void clearDB(){
        checkInDao.deleteAll();
    }

    //update database
    private void uploadDB(){
        Log.v("222", "updateDB");

    }

    //import database
    private void downloadDB(){
        clearDB();
        checkInDao.createTable();
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
        //meeting
        List<Meeting> meetings = result.getMeetings();
        for (Meeting meeting:meetings) {
            checkInDao.insertToMeeting(meeting.getMid(),meeting.getName());
        }
        //user
        List<User> users = result.getUsers();
        for (User aUser:users) {
            checkInDao.insertToUser(aUser.getUid(), aUser.getName(), aUser.getCompany(),aUser.getAccount(),aUser.getMail(),aUser.getMobilePhone());
        }
        //user_role
        List<UserRole> userRoles = result.getUserRoles();
        for (UserRole aUserRole:userRoles) {
            checkInDao.insertToUserRole(aUserRole.getRid(), aUserRole.getUid());
        }
        //gift_role
        List<GiftRole> giftRoles = result.getGiftRoles();
        for (GiftRole giftRole:giftRoles) {
            checkInDao.insertToGiftRole(giftRole.getGid(), giftRole.getRid());
        }
        //gift
        List<Gift> gifts = result.getGifts();
        for (Gift gift:gifts) {
            checkInDao.insertToGift(gift.getGid(), gift.getName(),gift.getNumber(),gift.getImg_path());
        }
        //role
        List<Role> roles = result.getRoles();
        for (Role role:roles) {
            checkInDao.insertToRole(role.getRid(), role.getName());
        }

        /*List<Event> events = result.getEvents();
        for (Event aEvent:events) {
            checkInDao.insertToEvent(aEvent.getEid(),aEvent.getUid(),aEvent.getJoined(),aEvent.getJoined_time(),aEvent.getMid());
        }*/

        Toast.makeText(OptionActivity.this,getResources().getString(R.string.download_success),Toast.LENGTH_LONG).show();
    }


    private void showDialog(){
        String title,content="";
        if(currentState==DialogState.Clear){
            title="";
            content=getString(R.string.confirm_clear);
        }else if(currentState==DialogState.Upload){
            title="";
            content=getString(R.string.confirm_download);
        }else if(currentState==DialogState.Download){
            title="";
            content=getString(R.string.confirm_download);
        }


        new AlertDialog.Builder(OptionActivity.this)
                //.setTitle(title)
                .setMessage(content)
                .setPositiveButton("是", pListener)
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        currentState=null;
                    }
                })
                .show();
    }

    private DialogInterface.OnClickListener pListener=new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(currentState==DialogState.Clear){
                clearDB();
            }else if(currentState==DialogState.Upload){
                uploadDB();
            }else if(currentState==DialogState.Download){
                downloadDB();
            }
        }
    };

}