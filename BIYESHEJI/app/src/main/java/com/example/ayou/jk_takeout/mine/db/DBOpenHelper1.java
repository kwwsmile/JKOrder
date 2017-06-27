package com.example.ayou.jk_takeout.mine.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ayou.jk_takeout.mine.model.AccountAddress;

import java.util.ArrayList;

/**
 * Created by kongweiwei on 2017/5/24.
 */

public class DBOpenHelper1 extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public DBOpenHelper1(Context context) {
        super(context,"kww1",null,1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建收货地址具体信息的表格
        db.execSQL("create table if not exists AccountAddress(_id integer primary key autoincrement,name text,sex text,phoneNumber text,address text,addressDetail text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void add(String name,String sex,String phoneNumber,String address,String addressDetail){
        db.execSQL("insert into AccountAddress (name,sex,phoneNumber,address,addressDetail) values(?,?,?,?,?)",new Object[]{name,sex,phoneNumber,address,addressDetail});
    }

    //查询表2数据
    public ArrayList<AccountAddress> getAllData(){
        ArrayList<AccountAddress> list = new ArrayList<AccountAddress>();
        Cursor cursor = db.rawQuery("select * from AccountAddress", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String phone = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String addressDetail = cursor.getString(cursor.getColumnIndex("addressDetail"));
            list.add(new AccountAddress(name,sex,phone,address,addressDetail ));
        }
        return list;
    }

    //通过db中封装好的方法实现查询数据的操作
    public ArrayList<AccountAddress> getDataByMethod(){
        ArrayList<AccountAddress> list = new ArrayList<AccountAddress>();
        /**
         * 1. table 表名
         * 2. String[] 设置要查询的列名，查询所有列填null即可
         * 3. seletion    查询条件
         * 4. String[] selectionArgs  数组中的值用于替代条件中？的值
         * 5,6 合起来构成分组条件起始就是变相的where查询条件
         * 7. order by 排序    列名   asc或desc   asc升序   desc 降序
         */
        Cursor cursor = db.query("AccountAddress", null, null, null, null, null, "name desc");

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String phone = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String addressDetail = cursor.getString(cursor.getColumnIndex("addressDetail"));
            list.add(new AccountAddress(name,sex,phone,address,addressDetail ));
        }
        return list;
    }
}
