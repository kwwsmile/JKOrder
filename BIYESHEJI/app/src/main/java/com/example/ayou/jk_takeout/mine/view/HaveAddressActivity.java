package com.example.ayou.jk_takeout.mine.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.mine.db.DBOpenHelper1;

//新增地址界面
public class HaveAddressActivity extends AppCompatActivity {

    private EditText mName;//联系人姓名
    private EditText mPhone;//联系人电话
    private EditText loaction;//定位坐标
    private EditText detailAddress;//详细地址
    private CheckBox mCheckBoxMan, mCheckBoxWoman;//男士，女士
    private String mAddress;//定位到的地址

    private SharedPreferences sp;//标识位

    private DBOpenHelper1 mDBOpenHelper;//数据库

    private String sex = "男士";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_address);

        Intent intent = getIntent();
        //获取定位到的地址
        mAddress = intent.getStringExtra("address");

        //初始化数据源
        initView();

        if (!TextUtils.isEmpty(mAddress)) {
            loaction.setText(mAddress);
        }
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.et_contactName);
        mPhone = (EditText) findViewById(R.id.et_Phone);
        loaction = (EditText) findViewById(R.id.tv_haveaddressactivity_location_right);
        detailAddress = (EditText) findViewById(R.id.et_haveaddressactivity_detail_location);
        mCheckBoxMan = (CheckBox) findViewById(R.id.ck_left);
        mCheckBoxWoman = (CheckBox) findViewById(R.id.ck_right);
        mDBOpenHelper = new DBOpenHelper1(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_haveaddressactivity_location_right:
                startActivity(new Intent(this, WriteAddressActivity.class));
                break;

            case R.id.bt_sure:
                String name = mName.getText().toString();//获取输入的联系人姓名
                String phone = mPhone.getText().toString();//获取输入的联系人的手机号
                String location = loaction.getText().toString();//输入的定位地址
                String detail_address = detailAddress.getText().toString();//获取输入的详细地址
                //判断输入的信息都不为空
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(detail_address) && !TextUtils.isEmpty(location)) {

                    sp = getSharedPreferences("ObtainAddress", Context.MODE_PRIVATE);
                    //要往SharedPreference中存入东西，需要获取它的编辑器
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("isAddAddress", false);//改变标志
                    edit.commit();//提交编辑器

                    mDBOpenHelper.add(name,sex,phone,location,detail_address);

                    Intent intent = new Intent(HaveAddressActivity.this, AlreadyAddressActivity.class);
//                    intent.putExtra("name", name);
//                    intent.putExtra("phone", phone);
//                    intent.putExtra("location", location);
//                    intent.putExtra("detailLoaction", detail_address);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
