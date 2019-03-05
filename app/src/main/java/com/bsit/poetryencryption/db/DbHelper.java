package com.bsit.poetryencryption.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Description 数据库帮助类
 * @Author fpp
 * @Date 2019/3/5 0005 23:43
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "poetryencrypt";
    public static final String CREATE_POETRY_ENCRYPT = "create table " + DB_NAME + " ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "content text, "
            + "encryptResult text)";


    /**
     * 创建数据库
     * @param context
     * @param name  数据库名称
     * @param factory
     * @param version 数据库版本号
     */
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 创建数据库
     * @param context
     * @param name
     * @param factory
     * @param version
     * @param errorHandler
     */
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version
            , DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POETRY_ENCRYPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
