package com.mika.newcode.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mika.newcode.R;
import com.mika.newcode.database.CheckInDao;
import com.mika.newcode.models.AllData;
import com.mika.newcode.models.Gift;
import com.mika.newcode.models.User;
import com.mika.newcode.network.request.TaskListener;
import com.mika.newcode.utils.Constants;

import java.util.List;


public class UserDetailActivity extends Activity {
    private CheckInDao checkInDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        checkInDao=new CheckInDao(this);
        Bundle bundle = getIntent().getExtras();
        int uid=bundle.getInt(Constants.KEY_USER_INFO);

        User aUser=checkInDao.getUser(uid);
        int rid=checkInDao.getRoleID(uid);

        TextView email_tv = (TextView) findViewById(R.id.profile_email);
        TextView name_tv = (TextView) findViewById(R.id.profile_name);
        TextView phone_tv = (TextView) findViewById(R.id.profile_phone);
        TextView role_tv = (TextView) findViewById(R.id.profile_role);
        TextView gift_tv = (TextView) findViewById(R.id.profile_gift);
        TextView company_tv = (TextView) findViewById(R.id.profile_company);

        email_tv.setText("邮箱： "+aUser.getMail());
        name_tv.setText("姓名： "+aUser.getName());
        role_tv.setText("角色： "+checkInDao.getRoleName(rid));
        phone_tv.setText("电话： " + aUser.getMobilePhone());
        company_tv.setText("公司： "+aUser.getCompany());
        gift_tv.setText("礼品清单： "+getGifts(uid));

        ImageView checkInBtn=(ImageView)findViewById(R.id.profile_checkin);
        checkInBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkin();
            }
        });
    }



    /**
     * 存储数据至本地数据库
     * @param result 数据源
     */
 private void updateData(AllData result){
        if(result.getUsers().size()<=0)return;
        //updateUsers(result.getUser());
     Log.v("222","getuser: "+result.getUsers());
    }
   /*
    private void updateUsers(List<User> userList){
        List<User> users= new ArrayList<User>();
        users.addAll(userList);
    }*/

    private void checkin(){

    }


    private String getGifts(int rid){
        List<Gift> gifts=checkInDao.getGifts(13);//checkInDao.getGifts(rid);
        StringBuilder giftListStr=new StringBuilder();
        for(Gift aGift:gifts){
            giftListStr.append("\n").append("\r").append(aGift.getName()).append(" X ").append(aGift.getNumber());
        }
        return  giftListStr.toString();
    }


}
