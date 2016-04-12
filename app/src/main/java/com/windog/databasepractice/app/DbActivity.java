package com.windog.databasepractice.app;

import android.app.Activity;
import android.os.Bundle;
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

        //查询数据 retrieve
        //更新数据 update
        //删除数据 delete
    }
}
