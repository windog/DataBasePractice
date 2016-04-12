package com.windog.databasepractice.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by windog on 2016/4/12.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String  CREATE_BOOK = "CREATE TABLE Book(id INTEGER PRIMARY KEY autoincrement,author text,price REAL,pages INTEGER,name text)";
    Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"Create Succeeded",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
