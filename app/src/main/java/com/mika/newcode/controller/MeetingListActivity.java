package com.mika.newcode.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
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
import java.util.HashMap;
import java.util.List;


public class MeetingListActivity extends Activity {
    private ExpandableListView expandableListView;
    private HashMap<Meeting, List<Meeting>> menuChilds;
    private List<Meeting> menu;
    private ExpandableDrawerListAdapter drawerAdapter;
    private CheckInDao checkInDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        checkInDao=new CheckInDao(this);
        expandableListView = (ExpandableListView) this.findViewById(R.id.list_expandableListView);
        getMeetingData();
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (menuChilds.get(menu.get(groupPosition)).size() == 0) {
                    v.setPressed(true);
                    selectItem(groupPosition);
                    return true;
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.v("222","child Click: "+groupPosition+","+childPosition);
                return false;
            }
        });

        //设置按钮
        Button mButtonSetting = (Button) findViewById(R.id.btn_list_setting);
        mButtonSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MeetingListActivity.this.finish();
                Intent resultIntent = new Intent(MeetingListActivity.this,OptionActivity.class);
                startActivity(resultIntent);
            }
        });

    }

    private void selectItem(int groupPosition){
        Meeting selectedMeeting=menu.get(groupPosition);
        String meetingName=selectedMeeting.getName();


        Intent resultIntent = new Intent(MeetingListActivity.this,MipcaActivityCapture.class);
        Bundle bundle = new Bundle();
        bundle.putString("meetingName", meetingName);
       // bundle.putParcelable("bitmap", barcode);
        resultIntent.putExtras(bundle);
        //this.setResult(RESULT_OK, resultIntent);
        startActivity(resultIntent);
    }

    private void getMeetingData(){
        menu=new ArrayList<Meeting>();
        menuChilds=new HashMap<Meeting, List<Meeting>>();

        menu=checkInDao.getAllMeetings();
        for (int i = 0; i < menu.size(); i++) {
            menuChilds.put(menu.get(i), new ArrayList<Meeting>());
        }

        drawerAdapter = new ExpandableDrawerListAdapter(MeetingListActivity.this,menu, menuChilds);
        expandableListView.setAdapter(drawerAdapter);
       /* if (resultContent == null || resultContent.size() == 0) {
            // getListView().setEmptyView(findView(getView(), R.id.fragment_list_poi_empty_prompt));
        } else {
            //  getListView().setEmptyView(null);
            drawerAdapter.notifyDataSetChanged();
        }*/
    }


}