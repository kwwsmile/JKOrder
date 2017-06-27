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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.iv_registeractivity_back)
    ImageView mIvRegisteractivityBack;
    @BindView(R.id.rl_registeractivity_top)
    RelativeLayout mRlRegisteractivityTop;
    @BindView(R.id.iv_registeractivity_messagecode)
    Button mIvRegisteractivityMessagecode;
    @BindView(R.id.et_registeractivity_password)
    EditText mEtRegisteractivityPassword;
    @BindView(R.id.rl_registeractivity_bottom)
    RelativeLayout mRlRegisteractivityBottom;
    @BindView(R.id.ll_registeractivity_body)
    LinearLayout mLlRegisteractivityBody;
    //public static final String TAG = MainActivity.class.getName();
    private ImageView iv_showCode;
    private EditText et_phoneCode;
    private EditText et_phoneNum;
    //产生的验证码
    private String realCode;
    private Button mBut_toSetCode;
    private String mPhoneNumber;
    private EditText mMessageCode;
    private DBOpenHelper mDBOpenHelper;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //初始化控件
        initView();

        //设置button的监听事件
        setListener();

        //将验证码用图片的形式显示出来
        iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
        iv_showCode.setOnClickListener(this);

        SMSSDK.registerEventHandler(mEventHandler);
    }

    private void setListener() {
        mBut_toSetCode.setOnClickListener(this);
    }

    private void initView() {
        et_phoneCode = (EditText) findViewById(R.id.et_registeractivity_phoneCodes);
        mBut_toSetCode = (Button) findViewById(R.id.bt_registeractivity_register);
        iv_showCode = (ImageView) findViewById(R.id.iv_registeractivity_showCode);
        et_phoneNum = (EditText) findViewById(R.id.et_registeractivity_phoneNumber);
        mMessageCode = (EditText) findViewById(R.id.et_registeractivity_messagecode);
        mDBOpenHelper = new DBOpenHelper(this);
    }

    @OnClick({R.id.iv_registeractivity_back, R.id.iv_registeractivity_messagecode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_registeractivity_back: //注销当前页面
                finish();
                break;
            case R.id.iv_registeractivity_messagecode:  //获取短信验证码
                //获取用户注册的手机号
                mPhoneNumber = et_phoneNum.getText().toString().trim();
                //得到验证码
                //SMSSDK.getVerificationCode("86", mPhoneNumber);
                SMSSDK.getVoiceVerifyCode("86", mPhoneNumber); //得到语音验证码
                break;
            case R.id.iv_registeractivity_showCode:    //改变随机验证码的生成
                iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                //Log.v(TAG,"realCode"+realCode);
                break;
            case R.id.bt_registeractivity_register:    //注册用户
                //获取随机验证码
                String phoneCode = et_phoneCode.getText().toString().toLowerCase();

                //获取用户输入的密码
                String password = mEtRegisteractivityPassword.getText().toString().trim();
                //获取语音验证码
                String messageCode = mMessageCode.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", mPhoneNumber, messageCode);
//                String msg = "生成的验证码："+realCode+"输入的验证码:"+phoneCode;
//                Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
                if (!TextUtils.isEmpty(mPhoneNumber) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phoneCode) ) { //如果手机号，密码。随机生成码不为空的情况下
                    if (phoneCode.equals(realCode)) {
                        //将用户名和密码加入到数据库中
                        mDBOpenHelper.add(mPhoneNumber, password);
                        mPreferences = getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
                        //要往SharedPreference中存入东西，需要获取它的编辑器
                        SharedPreferences.Editor edit = mPreferences.edit();
                        edit.putBoolean("isLogin", true);//改变标志
                        edit.commit();//提交编辑器
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("index", 2);
                        startActivity(intent);
                        finish();
                        Toast.makeText(this, phoneCode + "验证码正确，注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, phoneCode + "验证错误，注册失败", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, phoneCode + "验证错误，注册失败", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(RegisterActivity.this, country + ":" + phone, Toast.LENGTH_SHORT).show();
                        }
                    });
                    //TODO 把手机号 交给咱们自己的服务器，去设置密码，注册账号。。。
                }
            }
        }
    };
}
