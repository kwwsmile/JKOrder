package com.example.ayou.jk_takeout.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.ayou.jk_takeout.R;

public class WriteAddressActivity extends AppCompatActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient client;
    private static final String TAG = "oye";
    private BDLocation mLocation;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_write_address);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mTextView = (TextView) findViewById(R.id.tv_location);
        mBaiduMap = mMapView.getMap();


        //初始化用于启动或停止定位的类
        client = new LocationClient(getApplicationContext());

        //注册监听事件
        client.registerLocationListener(new BDLocationListener() {
            //一旦获取到定位位置后调用的方法
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {

                Log.i(TAG, "onReceiveLocation: 定位到的地址的经纬度 ： " + bdLocation.getLatitude() + "  " + bdLocation.getLongitude());
                //显示具体位置
                Toast.makeText(WriteAddressActivity.this, "onReceiveLocation: 地址信息为：" + bdLocation.getAddrStr() + "  " + bdLocation.getCity(), Toast.LENGTH_SHORT).show();
                Log.i(TAG,"onReceiveLocation: 地址信息为：" + bdLocation.getAddrStr() + "  " + bdLocation.getCity());
                mLocation = bdLocation;
                //设置地图支持显示定位图层
                mBaiduMap.setMyLocationEnabled(true);

                // 构造定位数据
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(bdLocation.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(bdLocation.getLatitude())
                        .longitude(bdLocation.getLongitude()).build();
// 设置定位数据
                mBaiduMap.setMyLocationData(locData);
// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_launcher);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfigeration(config);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude())));
            }
        });

        //配置定位SDK参数
        initLocation();

    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 3000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        client.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        mBaiduMap.setMyLocationEnabled(false);
        client.stop(); //停止定位

        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_search:
                //开始定位
                client.start();
                mTextView.setText("定位结果为：中国江苏省连云港市新浦区和衷路");
                break;

            case R.id.iv_writeAddress_back:
                Intent intent = new Intent(this, HaveAddressActivity.class);
                intent.putExtra("address","中国江苏省连云港市新浦区和衷路");
                startActivity(intent);
                finish();
                break;
        }
    }
}

