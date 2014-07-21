package com.mika.newcode.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hugo on 7/11/14.
 */
public class CheckInDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME    = "checkin.db";
    private static final int    DB_VERSION = 1;

    private static final String CREATE_MEETING_TABLE =
            "CREATE TABLE "+ CheckInDao.TABLE_MEETING +
                    "("+ CheckInDao.KEY_MEETING_ID +" INTEGER PRIMARY KEY, "
                    + CheckInDao.KEY_MEETING_NAME + " text"+
                    ");";

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE "+ CheckInDao.TABLE_USER +
                    "("+ CheckInDao.KEY_USER_ID + " INTEGER PRIMARY KEY, "
                    + CheckInDao.KEY_USER_NAME  + " text, "
                    + CheckInDao.KEY_USER_COMPANY + " text "
                    +");";

    private static final String CREATE_EVENT_TABLE =
            "CREATE TABLE "+ CheckInDao.TABLE_EVENT +
                    "("+ CheckInDao.KEY_EVENT_ID + " INTEGER PRIMARY KEY, "
                    + CheckInDao.KEY_JOINED_TIME  + " text, "
                    + CheckInDao.KEY_JOINED + " text, "
                    + CheckInDao.KEY_USER_ID + " INTEGER, "
                    + CheckInDao.KEY_MEETING_ID + " INTEGER "
                    +");";

    public CheckInDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteTable(db,CheckInDao.TABLE_USER);
        deleteTable(db,CheckInDao.TABLE_MEETING);
        deleteTable(db,CheckInDao.TABLE_EVENT);
        onCreate(db);
    }

    public void createTable(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_EVENT_TABLE);
        db.execSQL(CREATE_MEETING_TABLE);
    }

    public void deleteTable(SQLiteDatabase db,String $table_name){
        db.execSQL("DROP TABLE IF EXISTS " + $table_name);
    }
}