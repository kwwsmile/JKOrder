package com.example.ayou.jk_takeout.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ayou.jk_takeout.R;

//没有地址所展示的界面
public class NoAddressActvity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_address_actvity);
    }

   //新增地址的点击事件
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.bt_haveNoAddress:
      startActivity(new Intent(this,HaveAddressActivity.class));
           break;
       }
    }
}
