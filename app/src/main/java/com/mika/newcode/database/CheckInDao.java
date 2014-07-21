package com.mika.newcode.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mika.newcode.models.Meeting;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hugo on 7/11/14.
 */
public class CheckInDao {

    public static final String TABLE_MEETING     = "meeting_table";
    public static final String TABLE_EVENT    = "event_table";
    public static final String TABLE_USER     = "user_table";

    public static final String KEY_MEETING_ID         = "mid";
    public static final String KEY_MEETING_NAME   = "meeting_name";
    public static final String KEY_JOINED = "joined";
    public static final String KEY_JOINED_TIME  = "joined_time";

    public static final String KEY_USER_ID         = "uid";
    public static final String KEY_USER_NAME       = "user_name";
    public static final String KEY_USER_COMPANY      = "company";

    public static final String KEY_EVENT_ID      = "_id";


    public static final String KEY_INTAKE_DATE           = "triggered_date";
    private Context context;
    private SQLiteDatabase db;
    private static CheckInDatabaseHelper checkInDatabaseHelper;

    private CheckInDatabaseHelper getCheckInDatabaseHelperInstance(){
        if(checkInDatabaseHelper==null){
            checkInDatabaseHelper=new CheckInDatabaseHelper(context);
        }
        return checkInDatabaseHelper;
    }

    private void open(){
        db = getCheckInDatabaseHelperInstance().getWritableDatabase();
    }

    public CheckInDao(Context $context){
        context=$context;
    }

    private void close() {
        db.close();
    }

    public void insertToUser(int $id,String $name,String $company) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_NAME, $name);
        contentValues.put(KEY_USER_COMPANY, $company);
        contentValues.put(KEY_USER_ID, $id);

        db.insert(TABLE_USER, null, contentValues);

        close();
    }

    public void insertToEvent(int $eid,int $uid,int $joined,String $joinedTime,int $mid) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EVENT_ID, $eid);
        contentValues.put(KEY_USER_ID, $uid);
        contentValues.put(KEY_JOINED, $joined);
        contentValues.put(KEY_JOINED_TIME, $joinedTime);
        contentValues.put(KEY_MEETING_ID, $mid);

        db.insert(TABLE_EVENT, null, contentValues);

        close();
    }

    public void insertToMeeting(int $id,String $name) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MEETING_NAME, $name);
        contentValues.put(KEY_MEETING_ID, $id);

        db.insert(TABLE_MEETING, null, contentValues);

        close();
    }

    public void deleteAll(){
        open();
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_USER);
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_MEETING);
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_EVENT);
        close();
    }

    public void createTable(){
        open();
        getCheckInDatabaseHelperInstance().createTable(db);
        close();
    }
/*

    public void deleteByName(String[] name) {
        open();

        db.delete(TABLE_DAILY_INTAKE_NAME, KEY_PHOSPHORUS_FOOD_NAME + "=?", name);

        close();
    }*/

   public void printData(){
        open();
        if(isTableExist(TABLE_MEETING)){
            Cursor cursor = db.query(TABLE_MEETING, null,null,null, null, null, null);
            if(cursor.moveToFirst()){
                do{
                    Log.v("222",cursor.getString(0)+cursor.getString(1));
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        close();

    }

    private boolean isTableExist(String tableName){
        boolean isExist=false;
        Cursor c=db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='"+TABLE_MEETING+"'", null);
        if(c.moveToNext()){
            if (c.getInt(0)>0) {
                isExist = true;
            }
        }
        c.close();
        return isExist;
    }

    public List<Meeting> getAllMeetings() {
        List<Meeting> meetingList = new ArrayList<Meeting>();

        open();
        if(isTableExist(TABLE_MEETING)){
            Cursor cursor = db.query(TABLE_MEETING, null,null,null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Meeting aMeeting = new Meeting();
                    aMeeting.setMid(cursor.getInt(cursor.getColumnIndex(KEY_MEETING_ID)));
                    aMeeting.setName(String.valueOf(cursor.getInt(cursor.getColumnIndex(KEY_MEETING_NAME))));
                    meetingList.add(aMeeting);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        close();

        return meetingList;
    }

}
