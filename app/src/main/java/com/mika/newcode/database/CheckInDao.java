package com.mika.newcode.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by hugo on 7/11/14.
 */
public class CheckInDao {

    public static final String TABLE_PHOSPHORUS_NAME     = "phosphorus_table";
    public static final String KEY_PHOSPHORUS_ID         = "_id";
    public static final String KEY_PHOSPHORUS_FOOD_GROUP = "food_group";
    public static final String KEY_PHOSPHORUS_FOOD_NAME  = "food_name";
    public static final String KEY_PHOSPHORUS_UNIT       = "unit";
    public static final String KEY_PHOSPHORUS_LEVEL      = "level";
    public static final String KEY_PHOSPHORUS_FREQUENT   = "frequent";

    public static final String TABLE_DAILY_INTAKE_NAME   = "daily_intake_table";
    public static final String KEY_INTAKE_DATE           = "triggered_date";
    private Context context;
    private SQLiteDatabase db;

    private void open(){
        db = new CheckInDatabaseHelper(context).getWritableDatabase();
    }

    public CheckInDao(Context $context){
        context=$context;
    }

    private void close() {
        db.close();
    }

    public void insertToDaily(String food_name,String level) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PHOSPHORUS_FOOD_NAME, food_name);
        contentValues.put(KEY_PHOSPHORUS_LEVEL, level);

        db.insert(TABLE_DAILY_INTAKE_NAME, null, contentValues);

        close();
    }


    public void deleteByName(String[] name) {
        open();

        db.delete(TABLE_DAILY_INTAKE_NAME, KEY_PHOSPHORUS_FOOD_NAME + "=?", name);

        close();
    }

   public void printData(){
       open();
        Cursor cursor = db.query(TABLE_DAILY_INTAKE_NAME, null,null,null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Log.v("222",cursor.getString(0)+cursor.getString(1));
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
/*
    public List<IntakeModel> getAllIntakes() {
        List<IntakeModel> intakeList = new ArrayList<IntakeModel>();

        open();

        Cursor cursor = db.query(TABLE_DAILY_INTAKE_NAME, null,null,null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                IntakeModel intake = new IntakeModel();
                intake.setTitle(cursor.getString(cursor.getColumnIndex(KEY_PHOSPHORUS_FOOD_NAME)));
                intake.setPhosphorus_level(String.valueOf(cursor.getInt(cursor.getColumnIndex(KEY_PHOSPHORUS_LEVEL))));
                intakeList.add(intake);
            } while (cursor.moveToNext());
        }

        cursor.close();
        close();

        return intakeList;
    }*/

}
