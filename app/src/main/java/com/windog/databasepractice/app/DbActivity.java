package com.windog.databasepractice.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.windog.databasepractice.R;
import com.windog.databasepractice.app.db.MyDatabaseHelper;

/**
 * Created by windog on 2016/4/12.
 */
public class DbActivity extends Activity {

    Button btnCreateDb;
    Button btnCreateData;
    Button btnRetrieveData;
    Button btnUpdate;
    Button btnDelete;
    MyDatabaseHelper dbHelper;
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblayout);

        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);

        //创建数据库
        btnCreateDb  = (Button) findViewById(R.id.btnCreateDatabase);
        btnCreateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();  //this function will create database，and invoke onCreate() in MyDatabaseHelper class
            }
        });

        //添加数据 create
        btnCreateData = (Button) findViewById(R.id.addData);
        btnCreateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
                String[] a = new String[]{"Dan Brown","16.96","454","The Dan Vinci code"};
                String[] b = new String[]{"windy","12.56","568","The messed up life"};
                db.execSQL("INSERT into book (author,price,pages,name) VALUES(?,?,?,?)",a);
                db.execSQL("INSERT into book (author,price,pages,name) VALUES(?,?,?,?)",b);
            }
        });

        //查询数据 retrieve
        btnRetrieveData = (Button)findViewById(R.id.RetrieveData);
        btnRetrieveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.rawQuery("select * from book",null);
                if(cursor.moveToFirst()){
                    do {
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));

                        Log.d("MMM",author);
                        Log.d("MMM",""+price);
                        Log.d("MMM",""+pages);
                        Log.d("MMM",name);

                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        //更新数据 update
        btnUpdate = (Button) findViewById(R.id.UpdateData);

        //删除数据 delete
        btnDelete = (Button) findViewById(R.id.DeleteData);
    }
}
