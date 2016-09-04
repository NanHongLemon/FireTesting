package com.example.lemon.firetesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lemon on 2016/9/4.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FireTest.db";
    public static final String TABLE_NAME = "FireTopic_table";
    public static final String TOPIC_COLUMN = "TOPIC";
    public static final String ANSWERS_COLUMN = "ANSWERS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TOPIC TEXT NOT NULL, ANSWERS TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean insertData(String Topic, String Answers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TOPIC_COLUMN, Topic);
        contentValues.put(ANSWERS_COLUMN, Answers);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
