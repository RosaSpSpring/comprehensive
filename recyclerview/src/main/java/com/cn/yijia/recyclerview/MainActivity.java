package com.cn.yijia.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.yijia.recyclerview.chartdatabase.ChartData;
import com.cn.yijia.recyclerview.chartdatabase.DbController;
import com.cn.yijia.recyclerview.chartsavtivity.CellDensityActivity;
import com.cn.yijia.recyclerview.chartsavtivity.CultureTemperatureActivity;
import com.cn.yijia.recyclerview.chartsavtivity.DemoChartActivity;
import com.cn.yijia.recyclerview.chartsavtivity.DoActivity;
import com.cn.yijia.recyclerview.chartsavtivity.PHActivity;
import com.cn.yijia.recyclerview.chartutil.DateFormatter;
import com.cn.yijia.recyclerview.netdata.Constants;
import com.cn.yijia.recyclerview.netdata.DataBean;
import com.cn.yijia.recyclerview.netdata.GetDataUtil;
import com.cn.yijia.recyclerview.netdata.NetData;
import com.cn.yijia.recyclerview.netdata.NetDataBean;
import com.google.gson.Gson;

import org.greenrobot.greendao.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "壹";
	private EditText mTextView;
	private Button mButton;
	private RecyclerView mRecyclerView;
	private RVAdapter mRVAdapter;
	private EditText mCustomTV;
	private Button mWendu;
	private Button mpH;
	private Button mDO;
	private Button mDemo;
	NetDataBean netDataBean;
	DbController dbController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		Log.e( TAG, "onCreate" );


		initview();
		initListener();
		initAdapter();
		// 预请求数据
		if (dbController == null) {
			dbController = ((CTApp) getApplication()).getDbController();
		}
		netData();

		if (netDataBean != null) {
			Log.e( TAG, "1111" );
		} else {
			Log.e( TAG, "2222" );
		}
	}

	private void netData() {
		Retrofit build = new Retrofit.Builder().baseUrl( Constants.BASEURL ).addConverterFactory( GsonConverterFactory.create() ).build();
		Call<ResponseBody> call = build.create( NetData.class ).getAllData();
		call.enqueue( new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					String json = response.body().string();
					Log.e( TAG, "3333" );
					netDataBean = new Gson().fromJson( json, NetDataBean.class );
					Log.e( TAG, "netDatBena" + netDataBean.getData().size() );

					ArrayList<DataBean> jsondata = netDataBean.getData();

					new Thread( new Runnable() {
						@Override
						public void run() {
							synchronized (this) {
								for (int i = 0; i < jsondata.size(); i++) {
									ChartData chartData = new ChartData();
									chartData.setCreate_time( DateFormatter.getShortFormattedStringToDate( jsondata.get( i ).getCreate_time() ).getTime() );
									chartData.setMachine_id( jsondata.get( i ).getMachine_id() );
									chartData.setValue( jsondata.get( i ).getValue() );
									chartData.setType( jsondata.get( i ).getType() );
									dbController.insertOrReplace( chartData );
								}
							}
						}
					} ).start();

					new Thread( new Runnable() {
						@Override
						public void run() {
							// 打印筛选数据
							synchronized (this) {
								List<ChartData> chartData = dbController.searchFilterData( 1, 1592929000, 1592934000 ).list();
								if (chartData == null) {
									Log.e( TAG, "is null" );
								} else {
									Log.e( TAG, "chatdata.size" + chartData.size() );
								}
								List<ChartData> chartData1 = dbController.searchFilterDataByType( 4 ).list();
								Log.e( TAG, "chart1.size" + chartData1.size() );
								List<ChartData> chartData2 = dbController.searchFilterDataByDate( 1592899000, 1592928000 ).list();
								Log.e( TAG, "chart2" + chartData2.size() );
							}
						}
					} ).start();

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


	private void initAdapter() {
		mRVAdapter = new RVAdapter( MainActivity.this );
		mRecyclerView.setAdapter( mRVAdapter );
	}

	private void initListener() {
		mButton.setText( "细胞密度" );
		mButton.setOnClickListener( v -> startActivity( new Intent( MainActivity.this, CellDensityActivity.class ) ) );
		mWendu.setOnClickListener( v -> startActivity( new Intent( MainActivity.this, CultureTemperatureActivity.class ) ) );
		mpH.setOnClickListener( v -> startActivity( new Intent( MainActivity.this, PHActivity.class ) ) );
		mDO.setOnClickListener( v -> startActivity( new Intent( MainActivity.this, DoActivity.class ) ) );
		mDemo.setOnClickListener( v -> startActivity( new Intent( MainActivity.this, DemoChartActivity.class ) ) );

	}

	private void initview() {
		mButton = findViewById( R.id.btn );
		//		mTextView = findViewById(R.id.tv);
		mRecyclerView = findViewById( R.id.rv );
		mCustomTV = findViewById( R.id.custom_tv );
		mWendu = findViewById( R.id.btn1 );
		mpH = findViewById( R.id.btn2 );
		mDO = findViewById( R.id.btn3 );
		mDemo = findViewById( R.id.btn_demo );
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.e( TAG, "onRestart" );
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e( TAG, "onResume" );

	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e( TAG, "onPause" );
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		Log.e( TAG, "onPostResume" );
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.e( TAG, "onStart" );
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e( TAG, "onStop" );
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e( TAG, "onDestroy" );
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState( outState );
		Log.e( TAG, "onSaveInstanceState" );
	}


}
