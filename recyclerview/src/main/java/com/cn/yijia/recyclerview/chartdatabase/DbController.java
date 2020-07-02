package com.cn.yijia.recyclerview.chartdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cn.yijia.recyclerview.CTApp;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author lxm
 * @version 2020/6/29-9:07
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class DbController {
	/**
	 * helper
	 */
	private DaoMaster.DevOpenHelper mHelper;
	private SQLiteDatabase db;
	private DaoMaster mDaoMaster;
	private DaoSession mDaoSession;
	private Context context;

	private ChartDataDao mChartDataDao;
	private static DbController mDbController;

	/**
	 * 获取单例
	 */
	public static DbController getInstance(Context context) {
		if (mDbController == null) {
			synchronized (DbController.class) {
				if (mDbController == null) {
					mDbController = new DbController( context );
				}
			}
		}
		return mDbController;
	}

	/**
	 * 初始化
	 */
	public DbController(Context context) {
		this.context = context;
		mHelper = new DaoMaster.DevOpenHelper( context, "chart-db" );
		mDaoMaster = new DaoMaster( getWritableDatabase() );
		mDaoSession = mDaoMaster.newSession();
		mChartDataDao = mDaoSession.getChartDataDao();
	}

	/**
	 * 获取可写数据库
	 */
	private SQLiteDatabase getWritableDatabase() {
		if (mHelper == null) {
			mHelper = new DaoMaster.DevOpenHelper( context, "chart-db", null );
		}
		return mHelper.getWritableDatabase();
	}

	/**
	 * 获取可读数据库
	 */

	private SQLiteDatabase getReadableDatabase() {
		if (mHelper == null) {
			mHelper = new DaoMaster.DevOpenHelper( context, "chart-db", null );
		}
		return mHelper.getReadableDatabase();
	}

	/**
	 * 会自动判断插入还是替换
	 */
	public void insertOrReplace(ChartData chartData) {
		mChartDataDao.insertOrReplace( chartData );
	}

	/**
	 * 插入一条记录，表里面要没有与之相同的记录
	 */
	public long insert(ChartData chartData) {
		return mChartDataDao.insert( chartData );
	}

	/**
	 * 更新数据???
	 */
	public void update(ChartData chartData) {
		// ge >= ;le<=; gt >; lt <;
		QueryBuilder<ChartData> where = mChartDataDao.queryBuilder().where( ChartDataDao.Properties.Create_time.ge( chartData.getCreate_time() ) );
		List<ChartData> list = where.list();
	}

	/**
	 * 条件查询
	 */
	public List<ChartData> searchByWhere(String wherecluse) {
		List<ChartData> list = mChartDataDao.queryBuilder().where( ChartDataDao.Properties.Create_time.ge( wherecluse ) ).build().list();
		return list;

	}

	/**
	 * 查询所有数据
	 */
	public List<ChartData> searchAll() {
		List<ChartData> list = mChartDataDao.queryBuilder().orderAsc( ChartDataDao.Properties.Create_time ).list();
		return list;
	}

	/**
	 * 查询获取最近的一个数据获取时间戳
	 */
	public long searchLastChartData() {
		long create_time = mChartDataDao.queryBuilder().orderDesc( ChartDataDao.Properties.Create_time ).uniqueOrThrow().getCreate_time();
		return create_time;
	}

	/**
	 * 删除数据
	 */
	public void delete(String wherecluse) {
		mChartDataDao.queryBuilder().where( ChartDataDao.Properties.Create_time.eq( wherecluse ) ).buildDelete().executeDeleteWithoutDetachingEntities();
	}

	/**
	 * 通过 type 和时间区间查找
	 */
	public Query<ChartData> searchFilterData(int type, long start_time, long end_time) {

		Query<ChartData> build = mChartDataDao.queryBuilder().where( ChartDataDao.Properties.Type.eq( type ), ChartDataDao.Properties.Create_time.ge( start_time ), ChartDataDao.Properties.Create_time.le( end_time ) ).orderAsc( ChartDataDao.Properties.Create_time ).build();
		return build;

	}

	/**
	 * 只通过 type 搜索数据
	 */
	public Query<ChartData> searchFilterDataByType(int type) {
		Query<ChartData> build = mChartDataDao.queryBuilder().where( ChartDataDao.Properties.Type.eq( type ) ).orderAsc( ChartDataDao.Properties.Create_time ).build();
		return build;
	}
	/**
	 * 只通过时间区间搜索数据
	 */
	public Query<ChartData> searchFilterDataByDate(long start_time,long end_time){
		Query<ChartData> build = mChartDataDao.queryBuilder().where( ChartDataDao.Properties.Create_time.ge( start_time ), ChartDataDao.Properties.Create_time.le( end_time ) ).orderAsc( ChartDataDao.Properties.Create_time ).build();
		return build;
	}

}
