package com.mika.newcode.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.mika.newcode.R;
import com.mika.newcode.adapters.ListAdapter;
import com.mika.newcode.database.CheckInDao;
import com.mika.newcode.models.User;
import com.mika.newcode.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SearchListActivity extends Activity {
    private ListView userListView;
    private HashMap<User, List<User>> menuChilds;
    private List<User> users;
    private ListAdapter userListAdapter;
    private CheckInDao checkInDao;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        checkInDao=new CheckInDao(this);
        userListView = (ListView) this.findViewById(R.id.search_lv);

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                User selectedUser=users.get(position);
                Bundle bundle=new Bundle();
                bundle.putInt(Constants.KEY_USER_INFO,selectedUser.getUid());

                Intent intent=new Intent(SearchListActivity.this, UserDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                //checkInDao.printData(CheckInDao.TABLE_ROLE);//=========
            }
        });
        final EditText searchText= (EditText)this.findViewById(R.id.actionbar_search_input);
        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    doSearch(searchText.getText().toString());
                    return true;
                }
                return false;
            }
        });
       /* userListView.setOnChildClickListener(new userListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(userListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.v("222","child Click: "+groupPosition+","+childPosition);
                return false;
            }
        });
*/
        //设置按钮
       /* Button mButtonSetting = (Button) findViewById(R.id.btn_list_setting);
        mButtonSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MeetingListActivity.this.finish();
                Intent resultIntent = new Intent(MeetingListActivity.this,OptionActivity.class);
                startActivity(resultIntent);
            }
        });*/

    }

    private void doSearch(String keyword){

        initListView(checkInDao.search(keyword));
    }

    private void selectItem(int groupPosition){
        User selectedMeeting=users.get(groupPosition);
        String meetingName=selectedMeeting.getName();

        Intent resultIntent = new Intent(SearchListActivity.this,MipcaActivityCapture.class);
        Bundle bundle = new Bundle();
        bundle.putString("meetingName", meetingName);
       // bundle.putParcelable("bitmap", barcode);
        resultIntent.putExtras(bundle);
        //this.setResult(RESULT_OK, resultIntent);
        startActivity(resultIntent);
    }


    private void initListView(List<User> content) {
        if(userListAdapter==null){
            users=new ArrayList<User>();
            users.addAll(content);
            userListAdapter = new ListAdapter(this,R.layout.row_user,users);
            userListView.setAdapter(userListAdapter);
        }else{
            users.clear();
            users.addAll(content);
            userListAdapter.notifyDataSetChanged();
        }
    }

}