package com.cn.yijia.recyclerview;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cn.yijia.recyclerview.chartdatabase.DaoMaster;
import com.cn.yijia.recyclerview.chartdatabase.DaoSession;
import com.cn.yijia.recyclerview.chartdatabase.DbController;

import org.greenrobot.greendao.database.Database;

/**
 * @author lxm
 * @version 2020/6/28-10:39
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class CTApp extends Application {
	private static final String TAG = CTApp.class.getSimpleName();
//	private DaoSession mDaoSession ;
	private DbController mDbController;

	@Override
	public void onCreate() {
		super.onCreate();
		if (mDbController == null){
			mDbController = new DbController( getApplicationContext() );
		}
//		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper( this, "chart-db" );
//		Database db = helper.getWritableDb();
//
//		mDaoSession = new DaoMaster( db ).newSession();
	}
//	public DaoSession getDaoSession(){
//		if (mDaoSession != null){
//			return mDaoSession;
//		}else {
//			Log.e(TAG, "mDaoSession is null" );
//			return null;
//		}
//	}
	public DbController getDbController(){
		return mDbController;
	}



}
