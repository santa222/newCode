package com.mika.newcode.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hugo on 7/11/14.
 */
public class CheckInDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME    = "phosphorus.db";
    private static final int    DB_VERSION = 1;

    private static final String CREATE_PHOSPHORUS_TABLE =
            "CREATE TABLE "+ CheckInDao.TABLE_PHOSPHORUS_NAME +
                    "("+ CheckInDao.KEY_PHOSPHORUS_ID +" INTEGER PRIMARY KEY, "
                    + CheckInDao.KEY_PHOSPHORUS_FOOD_GROUP + " text, "
                    + CheckInDao.KEY_PHOSPHORUS_FOOD_NAME  + " text, "
                    + CheckInDao.KEY_PHOSPHORUS_UNIT       + " text, "
                    + CheckInDao.KEY_PHOSPHORUS_LEVEL      + " text, "
                    + CheckInDao.KEY_PHOSPHORUS_FREQUENT   + " integer "
                    +");";

    private static final String CREATE_DAILY_INTAKE_TABLE =
            "CREATE TABLE "+ CheckInDao.TABLE_DAILY_INTAKE_NAME +
                    "("+ CheckInDao.KEY_PHOSPHORUS_ID + " INTEGER PRIMARY KEY, "
                    + CheckInDao.KEY_PHOSPHORUS_FOOD_NAME  + " text, "
                    + CheckInDao.KEY_PHOSPHORUS_LEVEL + " text "
                    +");";

    public CheckInDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(CREATE_PHOSPHORUS_TABLE);
        db.execSQL(CREATE_DAILY_INTAKE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS " + PhosphorusDao.TABLE_PHOSPHORUS_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CheckInDao.TABLE_DAILY_INTAKE_NAME);
        onCreate(db);
    }
}
