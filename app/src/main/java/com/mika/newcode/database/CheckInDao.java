package com.mika.newcode.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mika.newcode.models.Gift;
import com.mika.newcode.models.Meeting;
import com.mika.newcode.models.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hugo on 7/11/14.
 */
public class CheckInDao {

    public static final String TABLE_MEETING       = "meeting_table";
    public static final String TABLE_EVENT         = "event_table";
    public static final String TABLE_USER          = "user_table";
    public static final String TABLE_USER_ROLE     = "user_role_table";
    public static final String TABLE_GIFT_ROLE     = "gift_role_table";
    public static final String TABLE_GIFT           = "gift_table";
    public static final String TABLE_ROLE           = "role_table";

    public static final String KEY_MEETING_ID       = "mid";
    public static final String KEY_MEETING_NAME     = "meeting_name";
    public static final String KEY_JOINED           = "joined";
    public static final String KEY_JOINED_TIME      = "joined_time";

    public static final String KEY_USER_ID         = "uid";
    public static final String KEY_USER_NAME       = "user_name";
    public static final String KEY_USER_COMPANY      = "company";
    public static final String KEY_USER_ACCOUNT      = "account";
    public static final String KEY_USER_MAIL      = "mail";
    public static final String KEY_USER_PHONE      = "phone";

    public static final String KEY_EVENT_ID      = "eid";
    public static final String KEY_ROLE_ID      = "rid";
    public static final String KEY_ROLE_NAME      = "role_name";
    public static final String KEY_GIFT_ID      = "gid";
    public static final String KEY_GIFT_NAME      = "gift_name";
    public static final String KEY_GIFT_NUM      = "gift_num";
    public static final String KEY_GIFT_URL      = "gift_url";

    public static final String KEY_ID           = "_id";

    private static final String SEARCH_SECTION =
                    KEY_USER_ACCOUNT+" like ? or "+
                    KEY_USER_NAME+" like ? or "+
                    KEY_USER_COMPANY+" like ?";

    private static final String QUERY_BY_USER_ID_SECTION =KEY_USER_ID+" = ?";
    private static final String QUERY_BY_ROLE_ID_SECTION =KEY_ROLE_ID+" = ?";
    private static final String QUERY_BY_GIFT_ID_SECTION =KEY_GIFT_ID+" = ?";

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

    public void insertToUser(int uid,String name,String company,String account,String email,String phone) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_NAME, name);
        contentValues.put(KEY_USER_COMPANY, company);
        contentValues.put(KEY_USER_ID, uid);
        contentValues.put(KEY_USER_ACCOUNT, account);
        contentValues.put(KEY_USER_MAIL, email);
        contentValues.put(KEY_USER_PHONE, phone);
        db.insert(TABLE_USER, null, contentValues);

        close();
    }

    public void insertToEvent(int eid,int uid,int joined,String $joinedTime,int mid) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EVENT_ID, eid);
        contentValues.put(KEY_USER_ID, uid);
        contentValues.put(KEY_JOINED, joined);
        contentValues.put(KEY_JOINED_TIME, $joinedTime);
        contentValues.put(KEY_MEETING_ID, mid);

        db.insert(TABLE_EVENT, null, contentValues);

        close();
    }

    public void insertToMeeting(int mid,String name) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MEETING_NAME, name);
        contentValues.put(KEY_MEETING_ID, mid);

        db.insert(TABLE_MEETING, null, contentValues);

        close();
    }
    public void insertToUserRole(int rid,int uid) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_ID, uid);
        contentValues.put(KEY_ROLE_ID, rid);

        db.insert(TABLE_USER_ROLE, null, contentValues);

        close();
    }
    public void insertToGiftRole(int gid,int rid) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_GIFT_ID, gid);
        contentValues.put(KEY_ROLE_ID, rid);
        db.insert(TABLE_GIFT_ROLE, null, contentValues);
        close();
    }

    public void insertToGift(int gid,String gName,int gNum,String gURL) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_GIFT_ID, gid);
        contentValues.put(KEY_GIFT_NUM, gNum);
        contentValues.put(KEY_GIFT_NAME, gName);
        contentValues.put(KEY_GIFT_URL, gURL);
        db.insert(TABLE_GIFT, null, contentValues);
        close();
    }

    public void insertToRole(int rid,String rName) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ROLE_ID, rid);
        contentValues.put(KEY_ROLE_NAME, rName);
        db.insert(TABLE_ROLE, null, contentValues);
        close();
    }

    public void deleteAll(){
        open();
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_USER);
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_MEETING);
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_EVENT);
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_USER_ROLE);
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_GIFT_ROLE);
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_ROLE);
        getCheckInDatabaseHelperInstance().deleteTable(db,CheckInDao.TABLE_GIFT);
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

   public void printData1(String table){
        open();
        if(isTableExist(table)){
            Cursor cursor = db.query(table, null,null,null, null, null, null);
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
                    aMeeting.setName(cursor.getString(cursor.getColumnIndex(KEY_MEETING_NAME)));
                    meetingList.add(aMeeting);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        close();
       /* for (Meeting aMeeting:meetingList) {
            Log.v("222", "--getAllMeetings: " + aMeeting.getName());
        }*/
        return meetingList;
    }

    public List<User> search(String originalKeyword){
        List<User> users = new ArrayList<User>();
        open();
        if(isTableExist(TABLE_USER)){
            String keyword="%"+originalKeyword+"%";
            Cursor cursor = db.query(TABLE_USER, null,SEARCH_SECTION,new String[] {keyword,keyword,keyword}, null,null,null);

            if (cursor.moveToFirst()) {
                do {
                    User aUser = new User();
                    aUser.setCompany(cursor.getString(cursor.getColumnIndex(KEY_USER_COMPANY)));
                    aUser.setName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
                    aUser.setAccount(cursor.getString(cursor.getColumnIndex(KEY_USER_ACCOUNT)));
                    aUser.setMail(cursor.getString(cursor.getColumnIndex(KEY_USER_MAIL)));
                    aUser.setUid(cursor.getInt(cursor.getColumnIndex(KEY_USER_ID)));
                    users.add(aUser);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        close();
        /*for (User aUser:users) {
            Log.v("222", "--getAllUsers: " + aUser.getName()+","+aUser.getUid());
        }*/
        return users;


  }

    public User getUser(int uid){
        open();
        User aUser = new User();
        if(isTableExist(TABLE_USER)){
            Cursor cursor = db.query(TABLE_USER, null,QUERY_BY_USER_ID_SECTION,new String[] {String.valueOf(uid)}, null,null,null);

            if (cursor.moveToFirst()) {
                aUser = new User();
                aUser.setCompany(cursor.getString(cursor.getColumnIndex(KEY_USER_COMPANY)));
                aUser.setName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
                aUser.setAccount(cursor.getString(cursor.getColumnIndex(KEY_USER_ACCOUNT)));
                aUser.setMail(cursor.getString(cursor.getColumnIndex(KEY_USER_MAIL)));
                aUser.setMobilePhone(cursor.getString(cursor.getColumnIndex(KEY_USER_PHONE)));
            }
            cursor.close();
        }

        close();
        return  aUser;
    }

    public int getRoleID(int uid){
        int roleID=-1;
        open();
        if(isTableExist(TABLE_USER_ROLE)){
            Cursor cursor = db.query(TABLE_USER_ROLE, null,QUERY_BY_USER_ID_SECTION,new String[] {String.valueOf(uid)}, null,null,null);
            if (cursor.moveToFirst()) {
                roleID=cursor.getInt(cursor.getColumnIndex(KEY_ROLE_ID));
                Log.v("222","roleID: "+roleID);
            }
            cursor.close();
        }

        close();
        return  roleID;
    }

    public String getRoleName(int rid){
        String roleName="无";
        open();
        if(isTableExist(TABLE_ROLE)){
            Cursor cursor = db.query(TABLE_ROLE, null,QUERY_BY_ROLE_ID_SECTION,new String[] {String.valueOf(rid)}, null,null,null);
            if (cursor.moveToFirst()) {
                roleName=cursor.getString(cursor.getColumnIndex(KEY_ROLE_NAME));
                Log.v("222","roleName: "+roleName);
            }
            cursor.close();
        }
        close();
        return  roleName;
    }


    public List<Gift> getGifts(int rid){
        List<Gift> gifts = new ArrayList<Gift>();
        open();
        if(isTableExist(TABLE_GIFT_ROLE)){
            Cursor cursor = db.query(TABLE_GIFT_ROLE, null,QUERY_BY_ROLE_ID_SECTION,new String[] {String.valueOf(rid)}, null,null,null);

            if (cursor.moveToFirst()) {
                do {
                    int gid=cursor.getInt(cursor.getColumnIndex(KEY_GIFT_ID));
                    Log.v("222","gid: "+gid);

                    Gift aGift= new Gift();
                    if(isTableExist(TABLE_GIFT)){
                        Cursor gift_cursor = db.query(TABLE_GIFT, null,QUERY_BY_GIFT_ID_SECTION,new String[] {String.valueOf(gid)}, null,null,null);
                        if (gift_cursor.moveToFirst()) {
                            aGift.setName(gift_cursor.getString(gift_cursor.getColumnIndex(KEY_GIFT_NAME)));
                            aGift.setNumber(gift_cursor.getInt(gift_cursor.getColumnIndex(KEY_GIFT_NUM)));
                            Log.v("222","gid_name: "+aGift.getName());
                        }
                       gift_cursor.close();
                    }

                    gifts.add(aGift);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        close();

        return gifts;


    }

}
