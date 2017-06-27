package com.example.ayou.jk_takeout.firstpage.config;

import com.example.ayou.jk_takeout.firstpage.model.bean_ad;
import com.example.ayou.jk_takeout.firstpage.model.bean_class;
import com.example.ayou.jk_takeout.firstpage.model.bean_shop;
import com.example.ayou.jk_takeout.firstpage.model.class_shopBean;
import com.example.ayou.jk_takeout.firstpage.model.selectshopBean;
import com.example.ayou.jk_takeout.firstpage.shopcat.shopcartBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AYOU on 2017/4/24.
 */

public interface netConfig {
    String BASIC_URL = "http://wm.gou00.cn/";
    //图片拼接
    String IMG_URL = "http://wm.gou00.cn/api.php?r=/image/get&appcode=9373224c982cc891befc4788cf3548b9&token=18d279d1faaac72a89fe06af13fa7ecb&width=265&height=160&view=image&id=";

    //热门分类
    @GET("api.php?r=/catalog/hot&appcode=9373224c982cc891befc4788cf3548b9&token=18d279d1faaac72a89fe06af13fa7ecb")
    Call<bean_class> getClassData();

    //店铺列表 改变page
    @GET("api.php?r=/seller/all&appcode=9373224c982cc891befc4788cf3548b9&token=18d279d1faaac72a89fe06af13fa7ecb&sort=location&location=121.498262,31.384512")
    Call<bean_shop> getShopData(@Query("page") int page);

    //广告轮播
    @GET("api.php?r=/ad/d0&appcode=9373224c982cc891befc4788cf3548b9&token=18d279d1faaac72a89fe06af13fa7ecb&location=121.498262,31.384512")
    Call<bean_ad> getAdData();

    //分类里下拉菜单
    @GET("api.php?r=/catalog/mixed&appcode=9373224c982cc891befc4788cf3548b9&token=18d279d1faaac72a89fe06af13fa7ecb")
    Call<selectshopBean> getData();
    //分类里面店铺信息
    @GET("api.php?r=/seller/all&appcode=9373224c982cc891befc4788cf3548b9&token=18d279d1faaac72a89fe06af13fa7ecb&sort=location&location=121.498262,31.384512")
    Call<class_shopBean> getClassShopData(@Query("catalog") String catalog, @Query("page") int page);

    //首页店铺二级界面
    @GET("http://wm.gou00.cn/api.php?r=/product/info&appcode=9373224c982cc891befc4788cf3548b9&token=18d279d1faaac72a89fe06af13fa7ecb")
    Call<shopcartBean> getShopcartData(@Query("id") String id);


}
