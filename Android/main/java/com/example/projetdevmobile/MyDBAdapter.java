package com.example.projetdevmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBAdapter {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "game_score.db";

    private static final String TABLE_SCORE = "table_score";
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_SCORE = "score";
    public static final String COL_DATE = "date";

    private static final String CREATE_DB =
            "create table " + TABLE_SCORE + " ("
                    + COL_ID + " integer primary key autoincrement, "
                    + COL_NOM + " text not null, "
                    + COL_SCORE + " integer, "
                    + COL_DATE + " text not null); ";

    private SQLiteDatabase mDB;
    private MyOpenHelper mOpenHelper;

    public MyDBAdapter(Context context) {
        mOpenHelper = new MyOpenHelper(context, DB_NAME,null, DB_VERSION);
    }

    public void open() {
        mDB = mOpenHelper.getWritableDatabase();
    }

    public void close() {
        mDB.close();
    }

    public ScoreData getScore(int id) throws SQLException {
        ScoreData score = null;

        Cursor c = mDB.query(TABLE_SCORE,
                new String [] {COL_ID, COL_NOM, COL_SCORE, COL_DATE},
                COL_ID + " = " + id, null, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            score = new ScoreData(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3));
        }
        c.close();

        return score;
    }

    public ArrayList<ScoreData> getAllScores() {
        ArrayList<ScoreData> scores = new ArrayList<ScoreData>();

        Cursor c =  mDB.query(TABLE_SCORE,
                new String[] {COL_ID, COL_NOM, COL_SCORE, COL_DATE},
                null, null, null, null, COL_SCORE);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            scores.add(
                    new ScoreData(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3)));
            c.moveToNext();
        }
        c.close();

        return scores;
    }

    public long insertScore(String nom, int score, String date) {
        ContentValues values = new ContentValues();
        values.put(COL_NOM, nom);
        values.put(COL_SCORE, score);
        values.put(COL_DATE, date);

        return mDB.insert(TABLE_SCORE, null, values);
    }

    public int removeScore(int id) {
        return mDB.delete(TABLE_SCORE, COL_ID + " = " + id, null);
    }

    /**
     * Private class MyOpenHelper
     */
    private class MyOpenHelper extends SQLiteOpenHelper {
        public MyOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory cursorFactory, int version) {
            super(context, name, cursorFactory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table " + TABLE_SCORE + ";");
            onCreate(db);
        }
    }
}
