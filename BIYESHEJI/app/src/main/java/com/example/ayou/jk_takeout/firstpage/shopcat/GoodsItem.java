package com.example.ayou.jk_takeout.firstpage.shopcat;

import com.example.ayou.jk_takeout.firstpage.config.netConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodsItem {

    public int id;
    public int typeId;
    public int rating;
    public String name;
    public String typeName;
    public double price;
    public int count;
    public String img;

    private static ArrayList<GoodsItem> goodsList;
    private static ArrayList<GoodsItem> typeList;



    private static String mIndex;



    public GoodsItem(){}

    public GoodsItem(int id, double price, String name, int typeId, String typeName, String img) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        this.img = img;
        rating = new Random().nextInt(5) + 1;
    }


    public static void initData(String index) {



        goodsList = new ArrayList<>();

        typeList = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(netConfig.BASIC_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        netConfig netConfig = retrofit.create(netConfig.class);
        Call<shopcartBean> call = netConfig.getShopcartData(index);
        call.enqueue(new Callback<shopcartBean>() {
            @Override
            public void onResponse(Call<shopcartBean> call, Response<shopcartBean> response) {
                shopcartBean bean = response.body();
                if (bean != null) {
                    List<shopcartBean.DataBean.EntityBean> entityList = bean.getData().getEntity();
                    GoodsItem item = null;
                    for (int i = 0; i < entityList.size(); i++) {
                        List<shopcartBean.DataBean.EntityBean.AttrsBean> attrsList = entityList.get(i).getAttrs();
                        for (int j = 0; j < attrsList.size(); j++) {
                            item = new GoodsItem(attrsList.get(j).getId(), attrsList.get(j).getPrice_moves(), attrsList.get(j).getName(), entityList.get(i).getId(), entityList.get(i).getName(), attrsList.get(j).getImg());

                            goodsList.add(item);
                        }
                        typeList.add(item);
                    }

                }



            }

            @Override
            public void onFailure(Call<shopcartBean> call, Throwable t) {

            }
        });

    }



    public static ArrayList<GoodsItem> getGoodsList(String index) throws InterruptedException {
        if (goodsList == null) {
            initData(index);
        }
        Thread.sleep(300);
        return goodsList;
    }
    public static ArrayList<GoodsItem> getTypeList(String index) throws InterruptedException {

        if (typeList == null) {
            initData(index);
        }
        Thread.sleep(600);
        return typeList;
    }




}
