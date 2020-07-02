package com.cn.yijia.recyclerview.netdata;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.cn.yijia.recyclerview.chartdatabase.ChartData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Handler;

import androidx.annotation.RequiresApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author lxm
 * @version 2020/6/17-15:54
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class GetDataUtil {
	private static final String TAG = GetDataUtil.class.getSimpleName();


	// 单例
	private static volatile GetDataUtil instance;

	public static GetDataUtil getInstance() {
		if (instance == null) {
			synchronized (GetDataUtil.class) {
				if (instance == null) {
					instance = new GetDataUtil();
					return instance;
				}
			}
		}
		return instance;

	}

	public ArrayList<ChartData> getFilterData(ArrayList<ChartData> dataAll, int type) {

		ArrayList<ChartData> tem = new ArrayList<>();

		if (dataAll == null) {
			Log.e( TAG, "dataAll is null" );

		} else if (dataAll.size() <= 0) {
			Log.e( TAG, "dataAll is 0" );
		} else {
			for (int i = 0; i < dataAll.size(); i++) {
				ChartData chartData = dataAll.get( i );
				switch (type) {
					case 1:
						tem.add( chartData );
						break;
					case 2:
						tem.add( chartData );
						break;
					case 3:
						tem.add( chartData );
						break;
					case 4:
						tem.add( chartData );
						break;

					default:
						break;
				}
			}
			Log.e( TAG, "tem.size" + tem.size() );
		}
		return tem;


	}

	private static NetDataBean netDataBean;

	public void getAllData() {

		Retrofit build = new Retrofit.Builder().baseUrl( Constants.BASEURL ).addConverterFactory( GsonConverterFactory.create() ).build();
		Call<ResponseBody> call = build.create( NetData.class ).getAllData();
		call.enqueue( new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					String json = response.body().string();
					Log.e(TAG, "3333"  );
					netDataBean = new Gson().fromJson( json, NetDataBean.class );
					Log.e(TAG, "netDatBena" + netDataBean.getData().size());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e( TAG, "请求出错" + t.getMessage() );
			}
		} );

	}

	public NetDataBean getNetDataBean() {
		return netDataBean;
	}


	//	public ArrayList<DataBean> getFilterData(int type) {
	//		getAllData();
	//		while (true){
	//			if (netDataBean != null){
	//				break;
	//			}
	//		}
	//		ArrayList<DataBean> dataAll = netDataBean.getData();
	//		ArrayList<DataBean> tem = new ArrayList<>();
	//		if (dataAll == null) {
	//			Log.e( TAG, "dataAll is null" );
	//
	//		} else if (dataAll.size() <= 0) {
	//			Log.e( TAG, "dataAll is 0" );
	//		} else {
	//			for (int i = 0; i < dataAll.size(); i++) {
	//				DataBean dataBean = dataAll.get( i );
	//				switch (type) {
	//					case 1:
	//						tem.add( dataBean );
	//						break;
	//					case 2:
	//						tem.add( dataBean );
	//						break;
	//					case 3:
	//						tem.add( dataBean );
	//						break;
	//					case 4:
	//						tem.add( dataBean );
	//						break;
	//
	//					default:
	//						break;
	//				}
	//			}
	//			Log.e( TAG, "tem.size" + tem.size() );
	//		}
	//		return tem;
	//
	//
	//	}


/*

	public static ArrayList<DataBean> getTemperature(ArrayList<DataBean> dataAll,int Type) {
		ArrayList<DataBean> tem = new ArrayList<>();
		if (dataAll == null) {
			Log.e( TAG, "dataAll is null" );
		} else if (dataAll.size() <= 0) {
			Log.e( TAG, "dataAll is 0" );
		} else {
			for (int i = 0; i < dataAll.size(); i++) {
				DataBean dataBean = dataAll.get( i );
				if (dataBean.getType() == Type) {
					// 温度
					tem.add( dataBean );
				}
			}
			Log.e( TAG, "tem.size" + tem.size() );
		}
		return tem;


	}

	// ph
	public static ArrayList<DataBean> getPh() {
		ArrayList<DataBean> dataAll = getDataAll();
		ArrayList<DataBean> ph = new ArrayList<>();
		if (dataAll == null) {
			Log.e( TAG, "dataAll is null" );
		} else if (dataAll.size() <= 0) {
			Log.e( TAG, "dataAll is 0" );
		} else {
			for (int i = 0; i < dataAll.size(); i++) {
				DataBean dataBean = dataAll.get( i );
				if (dataBean.getType() == 2) {
					// ph
					ph.add( dataBean );
				}
			}
			Log.e( TAG, "ph.size" + ph.size() );
		}
		return ph;


	}

	// Do
	public static ArrayList<DataBean> getDo() {
		ArrayList<DataBean> dataAll = getDataAll();
		ArrayList<DataBean> Do = new ArrayList<>();
		if (dataAll == null) {
			Log.e( TAG, "dataAll is null" );
		} else if (dataAll.size() <= 0) {
			Log.e( TAG, "dataAll is 0" );
		} else {
			for (int i = 0; i < dataAll.size(); i++) {
				DataBean dataBean = dataAll.get( i );
				if (dataBean.getType() == 3) {
					// Do
					Do.add( dataBean );
				}
			}
			Log.e( TAG, "Do.size" + Do.size() );
		}
		return Do;
	}

	// Denisty
	public static ArrayList<DataBean> getDenisty() {
		ArrayList<DataBean> dataAll = getDataAll();
		ArrayList<DataBean> denisty = new ArrayList<>();
		if (dataAll == null) {
			Log.e( TAG, "dataAll is null" );
		} else if (dataAll.size() <= 0) {
			Log.e( TAG, "dataAll is 0" );
		} else {
			for (int i = 0; i < dataAll.size(); i++) {
				DataBean dataBean = dataAll.get( i );
				if (dataBean.getType() == 4) {
					// denisty
					denisty.add( dataBean );
				}
			}
			Log.e( TAG, "denisty.size" + denisty.size() );
		}
		return denisty;
	}
*/


}
