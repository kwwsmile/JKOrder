package com.example.ayou.jk_takeout.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.mine.db.DBOpenHelper1;
import com.example.ayou.jk_takeout.mine.model.AccountAddress;

import java.util.ArrayList;

//有收获地址的界面
public class AlreadyAddressActivity extends AppCompatActivity {

    private TextView firstline;//地址信息第一栏
    private TextView secondline;//地址信息第二栏
    private ImageView iv;//返回键
   //数据库
    private DBOpenHelper1 mHelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_address);

        initView();

//        Intent intent  = getIntent();
//        String name = intent.getStringExtra("name");
//        String phone = intent.getStringExtra("phone");
//        String location = intent.getStringExtra("location");
//        String detailloaction = intent.getStringExtra("detailLoaction");
//
//        firstline.setText(name+"(先生)"   +phone);
//        secondline.setText(location+detailloaction);

       ArrayList<AccountAddress> data = mHelper1.getDataByMethod();
       Log.i("kwww",data.toString());
        if(!TextUtils.isEmpty(data.toString())){
            AccountAddress address = data.get(0);
           String name = address.getName();String sex = address.getSex();
            String number = address.getPhoneNumber();
            String detail = address.getAddressDetail();
            String address1 = address.getAddress();
            firstline.setText(name+"("+sex+")"  +number);
            secondline.setText(address1+detail);
        }
    }

    private void initView() {
        firstline = (TextView) findViewById(R.id.tv_firstline);
        secondline = (TextView) findViewById(R.id.tv_secondline);
        iv = (ImageView) findViewById(R.id.iv_loginactivity_back);
        mHelper1 = new DBOpenHelper1(this);
    }

    public void onClick(View view) {
       switch (view.getId()){
           case R.id.iv_loginactivity_back:
               startActivity(new Intent(this,AccountDetail.class));
           break;
       }
    }
}
