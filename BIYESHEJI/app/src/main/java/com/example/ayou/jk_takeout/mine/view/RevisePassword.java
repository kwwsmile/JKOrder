package com.example.ayou.jk_takeout.mine.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.mine.db.DBOpenHelper;
import com.example.ayou.jk_takeout.mine.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RevisePassword extends AppCompatActivity {

    @BindView(R.id.iv_reviseactivity_back)
    ImageView mIvReviseactivityBack;
    @BindView(R.id.et_reviseactivity_old_password)
    EditText mEtReviseactivityOldPassword;
    @BindView(R.id.et_reviseactivity_new_password)
    EditText mEtReviseactivityNewPassword;
    @BindView(R.id.et_reviseactivity_review)
    EditText mEtReviseactivityReview;
    @BindView(R.id.bt_revise_review)
    Button mBtReviseReview;

    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_password);
        ButterKnife.bind(this);
        mDBOpenHelper = new DBOpenHelper(this);
    }

    @OnClick({R.id.iv_reviseactivity_back, R.id.bt_revise_review})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_reviseactivity_back:
                finish();//销毁当前页面
                break;
            case R.id.bt_revise_review:
                //获取到输入的原始密码，新密码，确认在此输入的新密码
                String oldPassword = mEtReviseactivityOldPassword.getText().toString().trim();
                String newPassword = mEtReviseactivityNewPassword.getText().toString().trim();
                String reviewPassword = mEtReviseactivityReview.getText().toString().trim();

                ArrayList<User> data = mDBOpenHelper.getDataByMethod();
                String password = data.get(0).getPassword();
                if (!TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(reviewPassword)) {
                    //如果三个输入框中的密码都不为空的情况下
                    if(password.equals(oldPassword)&& newPassword.equals(reviewPassword)){
                       //如果原始密码和要输入的原始密码相同，并且输入的新密码和再次确认的密码相同
                           mDBOpenHelper.update(newPassword);
                        ArrayList<User> dataByMethod = mDBOpenHelper.getDataByMethod();
                        String password1 = dataByMethod.get(0).getPassword();
                       // Toast.makeText(RevisePassword.this, "password1............."+password1, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(this, "修改失败，请确认密码填写是否正确", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
