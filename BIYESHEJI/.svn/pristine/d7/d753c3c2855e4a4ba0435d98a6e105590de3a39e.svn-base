package com.example.ayou.jk_takeout.mine.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.mine.db.DBOpenHelper;
import com.example.ayou.jk_takeout.mine.model.User;
import com.example.ayou.jk_takeout.mine.view.AccountDetail;
import com.example.ayou.jk_takeout.mine.view.LoginActivity;
import com.example.ayou.jk_takeout.mine.view.MyMoney;
import com.example.ayou.jk_takeout.mine.view.SettingActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by kongweiwei on 2017/4/23.
 */
//我的界面
public class MineFragment extends Fragment {
    @BindView(R.id.iv_minefragment_setting)
    ImageView mIvMinefragmentSetting;
    @BindView(R.id.tv_minefragment_register_or_login)
    TextView mTvMinefragmentRegisterOrLogin;
    @BindView(R.id.iv_minefragment_back)
    ImageView mIvMinefragmentBack;
    @BindView(R.id.rl_minefragment_mine_money)
    RelativeLayout mRlMinefragmentMineMoney;
    @BindView(R.id.rl_minefragment_mine_scores)
    RelativeLayout mRlMinefragmentMineScores;
    @BindView(R.id.rl_minefragment_click)
    RelativeLayout mRlMinefragmentClick;
    @BindView(R.id.rl_minefragment_shop)
    RelativeLayout mRlMinefragmentShop;
    Unbinder unbinder;
    @BindView(R.id.tv_minefragment_enjoy_priority)
    TextView mTvMinefragmentEnjoyPriority;
    @BindView(R.id.iv_fragment_money)
    ImageView mIvFragmentMoney;
    @BindView(R.id.tv_fragment_money)
    TextView mTvFragmentMoney;
    @BindView(R.id.iv_fragment_scores)
    ImageView mIvFragmentScores;
    @BindView(R.id.tv_fragment_scores)
    TextView mTvFragmentScores;
    @BindView(R.id.ll_minefragment_two)
    LinearLayout mLlMinefragmentTwo;
    @BindView(R.id.iv_minefragment_click)
    ImageView mIvMinefragmentClick;
    @BindView(R.id.tv_minefragment_click)
    TextView mTvMinefragmentClick;
    @BindView(R.id.iv_minefragment_shop)
    ImageView mIvMinefragmentShop;
    @BindView(R.id.tv_minefragment_shop)
    TextView mTvMinefragmentShop;
    @BindView(R.id.ll_minefragment_three)
    LinearLayout mLlMinefragmentThree;

    //获取数据库
    private DBOpenHelper mDBOpenHelper;
    //判断是否之前登录过
    private SharedPreferences mPreferences;
    private Boolean isLogin;

    public MineFragment() {
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // 调用构造方法不会造成onCreate方法的运行
        mDBOpenHelper = new DBOpenHelper(getActivity());
        mPreferences = getActivity().getSharedPreferences("usersInfo", getActivity().MODE_PRIVATE);
        isLogin = mPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            ArrayList<User> list = mDBOpenHelper.getAllData();
            User user = list.get(0);
            String name = user.getName().toString().trim();
            //设置用户名
            mTvMinefragmentRegisterOrLogin.setText(name);
            mTvMinefragmentEnjoyPriority.setText("欢迎回来");
        }
        //如果数据库不为空
//      if(!TextUtils.isEmpty(mDBOpenHelper.getAllData().toString())){
//           ArrayList<User> list = mDBOpenHelper.getAllData();
//               User user = list.get(0);
//           String name = user.getName().toString().trim();
//            //设置用户名
//            mTvMinefragmentRegisterOrLogin.setText(name);
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_minefragment_setting, R.id.tv_minefragment_register_or_login, R.id.iv_minefragment_back, R.id.rl_minefragment_mine_money, R.id.rl_minefragment_mine_scores, R.id.rl_minefragment_click, R.id.rl_minefragment_shop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_minefragment_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                //getActivity().finish();
                break;
            case R.id.tv_minefragment_register_or_login:
                if (isLogin) {
                    startActivity(new Intent(getActivity(), AccountDetail.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                //getActivity().finish();
                break;
            case R.id.iv_minefragment_back:
                if (isLogin) {
                    startActivity(new Intent(getActivity(), AccountDetail.class));
                    //getActivity().finish();
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //getActivity().finish();
                }
                break;
            case R.id.rl_minefragment_mine_money:  //我的余额
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyMoney.class));
                    //getActivity().finish();
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //getActivity().finish();
                }
                break;
            case R.id.rl_minefragment_mine_scores:
                //TODO 我的积分

                break;
            case R.id.rl_minefragment_click:
                //TODO  每日签到
                if (isLogin) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("每日签到")
                            .setMessage("您已经连续签到0天，明日签到可获得1积分")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setCancelable(false).show();
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //getActivity().finish();
                }
                break;
            case R.id.rl_minefragment_shop:
                //TODO 积分商城
                if (isLogin) {
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //getActivity().finish();
                }
                break;
        }
    }
}
