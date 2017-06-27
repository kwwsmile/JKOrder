package com.example.ayou.jk_takeout.mine.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ayou.jk_takeout.mine.model.User;

import java.util.ArrayList;

/**
 * Created by kongweiwei on 2017/4/25.
 */
//存储用户信息的本地数据库
public class DBOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
//     /**
//      * * 构造方法
//	  * @param context  上下文环境，此处的作用：通过此环境获取到程序包名，可以确定数据库文件的存储位置
//	  * 默认位置： data/data/程序包名/databases文件内
//      * @param name  设置数据库文件的名称
//	  * @param factory  传参时填null即可
//	  * @param version 设置数据库的版本号
//	  */
//    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    //通常会将上方的构造方法，直接修改为以下样式
    public DBOpenHelper(Context context) {
        super(context, "kww", null, 1);
        // TODO Auto-generated constructor stub
        db = getReadableDatabase();
    }

    /**
     * 当第一次创建数据库文件时运行此方法，通常情况下会在此方法中进行创建表的操作
     * 参数：数据库对象，可以通过此对象调用方法实现创建表，或者针对表中数据进行增删改查操作
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("oye", "============onCreate");
        /**
         * 通过执行创建表的sql语句实现创建表的需求
         * execSQL方法用于执行方法参数中设置的sql语句
         *
         * 创建表的sql语句的格式为：
         * create table if not exists 表名 （列名1    列类型     primary key autoincrement，   列名2  列类型。。。。 ）
         */
        db.execSQL("create table if not exists user (_id integer  primary key autoincrement,name  text,  password  text)");

    }
        /*
         * 当new MyHelper对象时，如果传递的版本号发生改变，就会运行此方法
         * 注意：版本号改变时只能上调，不能下调
         */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }




    //增加数据
    public void add (String name,String password) {
        /**
         * 执行添加数据的sql语句
         * insert into 表名 （列名1，列名2.。。） values (值1，值2.。。。)
         * ? 用于代表占位符，稍后可以有参数二指定的数据中的内容替代？的内容
         */
        db.execSQL("insert into user (name,password) values(?,?)",new Object[]{name,password});
    }

    //删除数据
    public void delete (String name,String password) {
        /**
         * 执行删除的sql语句
         * delete from 表名      删除表中所有的数据
         * delete from 表名  where  条件表达式   ，  按指定条件删除内容
         */
        db.execSQL("delete from user where name = and password = "+name+password );
    }

    //修改数据
    public void update (String password) {
        /**
         * 执行修改的sql语句
         * update 表名   set 列1 = 值1，列2 = 值2.。。。       修改表中所有的数据
         *  update 表名   set 列1 = 值1，列2 = 值2.。。。 where  条件表达式      修改符合条件表达式的内容
         */
        db.execSQL("update user set password = ? ", new Object[]{password});
    }

    //查询所有数据
    public ArrayList<User> getAllData(){
        ArrayList<User> list = new ArrayList<User>();
        /**
         * 执行查询的sql语句
         * select * from 表名
         * 通过rawQuery方法执行sql语句，原因为：通过该方法的返回值可以取出查询到的结果
         * 返回值：Cursor对象，游标对象
         */
        Cursor cursor = db.rawQuery("select * from user", null);
        //moveToNext：让Cursor向下挪动一行，并在挪动后将该行的所有数据存储到Cursor对象中
        //一旦返回值为false，则代表没有下一行了，即所有数据读取完毕
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name, password));
        }
        return list;
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////
    //通过db中封装好的方法实现增加数据的操作
    public void addByMethod(String name,String password){
        /**
         * 1. String table  用于设置表名
         * 3. ContentValues对象， 用于设置要添加的数据
         * 特点：专门用于封装数据库数据的类，可以存储多组键值对，键必须是表的表名，值为想要添加的数据
         * 2. 随便写一个表中的列名即可
         * 参数二作用: 当参数二和三都为null时，在执行添加方法时程序会崩溃
         * 如果参数二不为null，参数三为null是，执行添加操作时不会崩溃，只会给表的列中添加值：NULL
         */
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("password", password);
		/*
		 * 当insert方法的返回值为-1时，代表添加失败
		 * 当返回值不为-1时，该数字代表的是新添加的数据在表中对应的id的值
		 */
        long id = db.insert("user", "_id", values);
        //Log.i("oye", "insert  id  "+id);
    }
    //通过db中封装好的方法实现删除数据的操作
    public void deleteByMethod(int id){
		/*
		 * String table  设置表名
		 * String whereClause  删除条件
		 * whereArgs  删除条件中如果有？的占位符时，通过此参数替代？的值
		 * 返回值： 根据指定的删除条件成功删除了几条数据
		 */
        int num = db.delete("user", "_id="+id, null);
       // Log.i("oye", "delete num  "+num);
    }
    //通过db中封装好的方法实现更改数据的操作
    public void updateByMethod (int id,String name) {
        /**
         * table  表名
         * values  ContentValues 用于设置修改后的数据
         * whereClause  修改的条件
         * whereArgs  用于替换条件中的？的值
         */
        ContentValues values = new ContentValues();
        values.put("name", name);
        //根据指定条件，修改的条数
        int num = db.update("user", values, "_id = ?", new String[]{id+""});
    }
    //通过db中封装好的方法实现查询数据的操作
    public ArrayList<User> getDataByMethod(){
        ArrayList<User> list = new ArrayList<User>();
        /**
         * 1. table 表名
         * 2. String[] 设置要查询的列名，查询所有列填null即可
         * 3. seletion    查询条件
         * 4. String[] selectionArgs  数组中的值用于替代条件中？的值
         * 5,6 合起来构成分组条件起始就是变相的where查询条件
         * 7. order by 排序    列名   asc或desc   asc升序   desc 降序
         */
        Cursor cursor = db.query("user", null, null, null, null, null, "name desc");

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name, password));
        }
        return list;
    }
}
