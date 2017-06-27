package com.example.ayou.jk_takeout.mine.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.view.MainActivity;
import com.example.ayou.jk_takeout.mine.db.DBOpenHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MessageLoginActivity extends AppCompatActivity {

    @BindView(R.id.iv_messageLogin_back)
    ImageView mIvMessageLoginBack;
    @BindView(R.id.et_messageLogin_phoneNumber)
    EditText mEtMessageLoginPhoneNumber;
    @BindView(R.id.et_messageLogin_word_code)
    EditText mEtMessageLoginWordCode;
    @BindView(R.id.iv_messageLogin_code)
    ImageView mIvMessageLoginCode;
    @BindView(R.id.et_messageLogin_messageCode)
    EditText mEtMessageLoginMessageCode;
    @BindView(R.id.bt_messageLogin_login)
    Button mBtMessageLoginLogin;
    @BindView(R.id.bt_messageLogin_obtain_code)
    Button mBtMessageLoginObtainCode;
    @BindView(R.id.tv_messageLogin_describe)
    TextView mTvMessageLoginDescribe;
    private DBOpenHelper mDBOpenHelper;
    private SharedPreferences mPreferences;
    //产生的验证码
    private String realCode;
    private String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_login);
        ButterKnife.bind(this);
        mDBOpenHelper = new DBOpenHelper(this);

        //将验证码用图片的形式显示出来
        mIvMessageLoginCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    @OnClick({R.id.iv_messageLogin_back, R.id.iv_messageLogin_code, R.id.bt_messageLogin_login, R.id.bt_messageLogin_obtain_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_messageLogin_back:
               finish();//销毁当前页面
                break;
            case R.id.iv_messageLogin_code://改变随机验证码的生成
                mIvMessageLoginCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.bt_messageLogin_obtain_code:  //获取验证码
                //获取用户注册的手机号
                mPhoneNumber = mEtMessageLoginPhoneNumber.getText().toString().trim();
                //得到验证码
                SMSSDK.getVoiceVerifyCode("86", mPhoneNumber); //得到语音验证码
                break;
            case R.id.bt_messageLogin_login: //验证并登录
                //获取随机验证码
                String phoneCode = mEtMessageLoginWordCode.getText().toString().toLowerCase();
                //获取语音验证码
                String messageCode = mBtMessageLoginObtainCode.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", mPhoneNumber, messageCode);

                if(!TextUtils.isEmpty(mPhoneNumber)&&!TextUtils.isEmpty(phoneCode)&&!TextUtils.isEmpty(messageCode)){
                     if(phoneCode.equals(realCode)){
                         mDBOpenHelper.add(mPhoneNumber,"123456");
                         mPreferences = getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
                         //要往SharedPreference中存入东西，需要获取它的编辑器
                         SharedPreferences.Editor edit = mPreferences.edit();
                         edit.putBoolean("isLogin", true);//改变标志
                         edit.commit();//提交编辑器
                         Toast.makeText(this, phoneCode + "验证码正确，注册成功", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(this, MainActivity.class);
                         intent.putExtra("index",2);
                         startActivity(intent);
                         finish();
                     }else {
                         Toast.makeText(this, phoneCode + "验证错误，注册失败", Toast.LENGTH_SHORT).show();
                     }
                }else {
                    Toast.makeText(this, "注册并登录失败，请重新尝试！！！", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(mEventHandler);//注销掉EventHandler
    }

    //短信验证的EventHandler
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
                            Toast.makeText(MessageLoginActivity.this, country + ":" + phone, Toast.LENGTH_SHORT).show();
                        }
                    });
                    //TODO 把手机号 交给咱们自己的服务器，去设置密码，注册账号。。。
                }
            }
        }
    };

}
