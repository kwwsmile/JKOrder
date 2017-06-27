package com.example.ayou.jk_takeout.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.view.MainActivity;
import com.example.ayou.jk_takeout.mine.db.DBOpenHelper;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class UpdatePasswordActivity extends AppCompatActivity {

    private EditText mMessageCode, mPassword, mRePassword;
    private Button mSendMessageCode, mResertPassword;
    private String mPhoneNUmber;
    private DBOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        Intent intent = getIntent();
        mPhoneNUmber = intent.getStringExtra("phoneNUmber");
        //初始化控件
        initView();

        SMSSDK.registerEventHandler(mEventHandler);
    }

    private void initView() {
        mMessageCode = (EditText) findViewById(R.id.et_UpdatePasswordActivity_sendMessage);
        mPassword = (EditText) findViewById(R.id.et_UpdatePasswordActivity_setting_password);
        mRePassword = (EditText) findViewById(R.id.et_UpdatePasswordActivity_reConfire);
        mSendMessageCode = (Button) findViewById(R.id.bt_UpdatePasswordActivity_send);
        mResertPassword = (Button) findViewById(R.id.bt_UpdatePasswordActivity_resert_login);
        db = new DBOpenHelper(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_UpdatePasswordActivity_send:
                SMSSDK.getVoiceVerifyCode("86", mPhoneNUmber); //得到语音验证码
                break;

            case R.id.bt_UpdatePasswordActivity_resert_login:
                String messageCode = mMessageCode.getText().toString().trim();//获取输入的语音验证码
                String password = mPassword.getText().toString().trim();//获取设置的密码
                String confirePassword = mRePassword.getText().toString().trim();//获取再次确认输入的密码
                if(!TextUtils.isEmpty(messageCode)&& !TextUtils.isEmpty(password)&&!TextUtils.isEmpty(confirePassword)){
                    SMSSDK.submitVerificationCode("86", mPhoneNUmber, messageCode);
                    if(password.equals(confirePassword)){
                        db.update(password);
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("index", 2);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(this, "两次密码填写不一致，请重新填写", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "您输入的内容不完整，请填写完整", Toast.LENGTH_SHORT).show();
                }



                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(mEventHandler);//注销掉EventHandler
    }

    EventHandler mEventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            super.afterEvent(event, result, data);

            if (result == SMSSDK.RESULT_ERROR) {
                // 请求失败时
            } else if (result == SMSSDK.RESULT_COMPLETE) {
                // 请求完成时
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    // 提交验证码成功
                    HashMap<String, Object> map = (HashMap<String, Object>) data;
                    final String country = (String) map.get("country");
                    final String phone = (String) map.get("phone");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UpdatePasswordActivity.this, country + ":" + phone, Toast.LENGTH_SHORT).show();
                        }
                    });
                    //TODO 把手机号 交给咱们自己的服务器，去设置密码，注册账号。。。
                }
            }
        }
    };
}
