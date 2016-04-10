package com.windog.databasepractice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    EditText account;
    EditText password;
    CheckBox rememberPw;
    Button login;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        rememberPw = (CheckBox) findViewById(R.id.rememberPassword);
        login = (Button) findViewById(R.id.login);
        boolean isRemembered = preferences.getBoolean("rememberPw",false);

        String a = String.valueOf(isRemembered);
        Toast.makeText(LoginActivity.this,a,Toast.LENGTH_LONG).show();
        if(isRemembered){
            String accounttext = preferences.getString("account","");
            String pass = preferences.getString("password","");

            account.setText(accounttext);
            account.setSelection(accounttext.length());
            password.setText(pass);
            rememberPw.setChecked(true);
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accounText = account.getText().toString();
                String pass = password.getText().toString();
                if(accounText.equals("admin") && pass.equals("123456")){  //判断账号密码是否正确
                    editor = preferences.edit();
                    if(rememberPw.isChecked()){  //判断复选框是否选中
                        editor.putBoolean("isRemembered",true);
                        editor.putString("account",accounText);
                        editor.putString("password",pass);
                    }else {
                        editor.clear();  //清除editor的所有内容
                    }
                    editor.commit();   //一定记得commit！！！
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
//                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误，请重试！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
