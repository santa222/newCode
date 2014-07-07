package com.mika.newcode.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.mika.newcode.R;
import com.mika.newcode.builders.DynamicContentTaskBuilder;
import com.mika.newcode.models.AllData;
import com.mika.newcode.network.request.GetDynamicContent;
import com.mika.newcode.network.request.TaskListener;
import com.mika.newcode.utils.Constants;

import org.apache.http.client.methods.HttpGet;


public class ResultActivity extends Activity {
    /**
     * 显示扫描结果
     */
    private TextView result_tv ;
    //获取数据成功
    private TaskListener<AllData> onTaskListener = new TaskListener<AllData>() {
        @Override
        public void onTaskSuccess(AllData result) {
            Log.v("222","success");
            if (result != null) {
                updateData(result);
                //insertCategories(result.getMobileMenus());

            }
        }

        @Override
        public void onTaskFailure(Exception e) {

        }

        @Override
        public void onTaskCancelled() {

        }

        @Override
        public void onTaskTimeOut() {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();
        //显示扫描到的内容
        result_tv = (TextView) findViewById(R.id.result_txt);
        result_tv.setText(bundle.getString("result"));

        Button checkInButton = (Button) findViewById(R.id.result_checkin_btn);
        checkInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*GetDynamicContent<AllData> mobileMenusTask = new DynamicContentTaskBuilder<AllData>()
                        .withContext(ResultActivity.this)
                        .withPath(Constants.URL_PATH)
                        .withMethod(HttpGet.METHOD_NAME)
                        .withTaskListener(onTaskListener)
                        .withType(new TypeToken<AllData>() {
                        }.getType())
                        .build();
                mobileMenusTask.execute();*/
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


}
