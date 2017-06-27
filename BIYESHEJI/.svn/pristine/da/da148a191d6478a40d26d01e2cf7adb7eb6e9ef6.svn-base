package com.example.ayou.jk_takeout.mine.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.ayou.jk_takeout.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的余额的挑战页我的充值
public class MyMoney extends AppCompatActivity {

    @BindView(R.id.iv_myMoney_back)
    ImageView mIvMyMoneyBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_money);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_myMoney_back)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_myMoney_back:
               finish();
            break;
        }
    }
}
