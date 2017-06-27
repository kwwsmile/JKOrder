package com.example.ayou.jk_takeout.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.mine.db.DBOpenHelper;
import com.example.ayou.jk_takeout.mine.model.User;

import java.util.ArrayList;

//找回密码的界面
public class FindPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mInputAccount; //输入的账户名
    private EditText mInputCode;    //输入的验证码
    private ImageView mImageViewCode; //验证码的图片
    //产生的验证码
    private String realCode;
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        //初始化控件
        initView();

        //将验证码用图片的形式显示出来
        mImageViewCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
        mImageViewCode.setOnClickListener(this);

    }

    private void initView() {
       mInputAccount = (EditText) findViewById(R.id.et_findPasswordactivity_account);
       mInputCode = (EditText) findViewById(R.id.et_findPasswordactivity_input);
       mImageViewCode = (ImageView) findViewById(R.id.iv_findPasswordactivity_image);
       mDBOpenHelper = new DBOpenHelper(this);
    }

    public void onClick(View view) {
       switch (view.getId()){
           case R.id.iv_findPasswordactivity_image:
               mImageViewCode.setImageBitmap(Code.getInstance().createBitmap());
               realCode = Code.getInstance().getCode().toLowerCase();
           break;

           case R.id.bt_findPasswordactivity_next:
               String mInputAccount = this.mInputAccount.getText().toString().trim();//获取输入的账户名
               String mInputCode = this.mInputCode.getText().toString().trim();//获取输入的验证码
               if(!TextUtils.isEmpty(mInputAccount)&& !TextUtils.isEmpty(mInputCode)){
                   ArrayList<User> data = mDBOpenHelper.getDataByMethod();
                   User user = data.get(0);
                   //如果输入的账户名和验证码都正确的情况下
                   if(mInputAccount.equals(user.getName())&&mInputCode.equals(realCode)){
                       //跳转到重置密码的界面
                       Intent intent = new Intent(this,UpdatePasswordActivity.class);
                       intent.putExtra("phoneNUmber",mInputAccount);
                       startActivity(intent);
                   }else {
                       Toast.makeText(this, "账户名或者密码输入错误，请重新输入", Toast.LENGTH_SHORT).show();
                   }
               }else{
                   Toast.makeText(this, "请输入账户名或者验证码", Toast.LENGTH_SHORT).show();
               }
               break;

           case R.id.iv_findPasswordactivity_back:
           finish();//结束当前页面
           break;
       }
    }
}
