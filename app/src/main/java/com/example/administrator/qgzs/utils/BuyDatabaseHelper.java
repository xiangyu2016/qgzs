package com.example.administrator.qgzs.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.administrator.qgzs.bean.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class BuyDatabaseHelper extends SQLiteOpenHelper {

    /**
     * 数据库版本，需要升级数据库时只要加一即可
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "myGoods.db";

    /**
     * 构造方法
     * 每次创建DatabaseHelper对象时，若本应用无该数据库，则新建数据库并调用onCreate方法；
     * 若该数据库已创建则直接使用已存在的数据库且跳过onCreate方法
     * factory : 当打开的数据库执行查询语句的时候 会创建一个Cursor对象, 这时会调用Cursor工厂类 factory, 可以填写null默认值
     * @param context   上下文
     */
    public BuyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建数据库是时调用（只被调用一次）
     * @param db    数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建Goods表，属性：id（用户id，主键）、goodsID（商品编码）、price（期望价格）
        db.execSQL("CREATE TABLE GoodsBean (id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",goodsID VARCHAR(40)" +
                ",price VARCHAR(20)" +
                ",miaosha VARCHAR(20)" +
                ",danjia VARCHAR(20)" +
                ",isChecked VARCHAR(20)" +
                ",bingo VARCHAR(20)" +
                ",name VARCHAR(20)"+
                ")");
        db.execSQL("CREATE TABLE BuyGoodsBean (id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",goodsID VARCHAR(40)" +
                ",price VARCHAR(20)" +
                ",miaosha VARCHAR(20)" +
                ",danjia VARCHAR(20)" +
                ",isChecked VARCHAR(20)" +
                ",bingo VARCHAR(20)" +
                ",name VARCHAR(20)"+
                ")");
    }
    /**
     * 更新数据库时调用
     * @param db            数据库
     * @param oldVersion    旧版本号
     * @param newVersion    新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级BuyGoodsBean表，添加性别
        //db.execSQL("ALTER TABLE BuyGoodsBean ADD COLUMN gender VARCHAR(2)");
    }

    /**
     * 插入一条数据
     * @param bean  用户对象
     */
    public void insert(Goods bean) {
        //如果要对数据进行更改，就调用此方法得到用于操作数据库的实例,该方法以读和写方式打开数据库
        SQLiteDatabase database = getWritableDatabase();
        //向BuyGoodsBean表插入一条数据
        database.execSQL(
                "INSERT INTO BuyGoodsBean(goodsID, price, miaosha, danjia,isChecked, bingo,name) VALUES(?,?,?,?,?,?,?)",
                new Object[]{bean.getGoodsID(), bean.getPrice(), bean.getMiaosha(), bean.getDanjia(),
                        bean.getIsChecked(), bean.getBingo() ,bean.getName(),
                });
    }

    /**
     * 更新一条用户数据
     * @param bean  用户对象
     */
    public void updateAGoods(Goods bean) {
        SQLiteDatabase database = getWritableDatabase();
        //根据id更新一条数据
        database.execSQL(
                "UPDATE BuyGoodsBean SET goodsID=?, price=?,miaosha=?,danjia=?,isChecked=?,bingo=? ,name=? WHERE id=?",
                new Object[]{bean.getGoodsID(), bean.getPrice(), bean.getMiaosha(), bean.getDanjia(),
                        bean.getIsChecked(), bean.getBingo(),bean.getName(), bean.getId()});
    }

    /**
     * 根据id删除一条数据
     * @param id    用户id
     */
    public void deleteABuyGoodsBean(Integer id) {
        SQLiteDatabase database = getWritableDatabase();
        //根据id删除一条数据
        database.execSQL("DELETE FROM BuyGoodsBean WHERE id=?",
                new Object[]{id});
    }

    /**
     * 获取整个用户列表
     * @return
     */
    public List<Goods> readAllBuyGoodsBean() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM BuyGoodsBean", new String[]{});
        List<Goods> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Goods bean = new Goods();
            bean.setId(cursor.getString(cursor.getColumnIndex("id")));
            bean.setGoodsID(cursor.getString(cursor.getColumnIndex("goodsID")));
            bean.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            bean.setMiaosha(Integer.parseInt(cursor.getString(cursor.getColumnIndex("miaosha"))));
            bean.setDanjia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("danjia"))));
            bean.setIsChecked(Integer.parseInt(cursor.getString(cursor.getColumnIndex("isChecked"))));
            bean.setBingo(Integer.parseInt(cursor.getString(cursor.getColumnIndex("bingo"))));
            bean.setName(cursor.getString(cursor.getColumnIndex("name")));
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    /**
     * 读取一条数据
     * @param id    用户id
     * @return      用户对象
     */
    public Goods readABuyGoodsBean(Integer id) {

        //如果只对数据进行读取，建议使用此方法
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor  = database.rawQuery(
                "SELECT * FROM BuyGoodsBean WHERE id=?",
                new String[]{id.toString()});
        if (cursor.moveToFirst()) {
            //读取数据，并返回
            Goods bean = new Goods();
            bean.setId(cursor.getString(cursor.getColumnIndex("id")));
            bean.setGoodsID(cursor.getString(cursor.getColumnIndex("goodsID")));
            bean.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            bean.setMiaosha(Integer.parseInt(cursor.getString(cursor.getColumnIndex("miaosha"))));
            bean.setDanjia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("danjia"))));
            bean.setIsChecked(Integer.parseInt(cursor.getString(cursor.getColumnIndex("isChecked"))));
            bean.setBingo(Integer.parseInt(cursor.getString(cursor.getColumnIndex("bingo"))));
            bean.setName(cursor.getString(cursor.getColumnIndex("name")));
            cursor.close();
            return bean;
        } else {
            //未读出数据，返回空数据
            return null;
        }
    }
}
