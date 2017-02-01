package com.xiaodong.takeout.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xiaodong.takeout.MyApplication;
import com.xiaodong.takeout.model.dao.bean.AddressBean;
import com.xiaodong.takeout.model.dao.bean.UserBean;

import java.sql.SQLException;

/**
 * Created by Think on 2017/1/27.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper{
    private static final String DBNAME = "takeout.db";
    private static final int DBVERSION  = 1;

    public static DBHelper getInstance() {
        if (instance == null){
            synchronized (DBHelper.class){
                if (instance == null){
                    instance = new DBHelper(MyApplication.getContext());
                }
            }
        }
        return instance;
    }

    private static DBHelper instance;

    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserBean.class);
            TableUtils.createTable(connectionSource, AddressBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
