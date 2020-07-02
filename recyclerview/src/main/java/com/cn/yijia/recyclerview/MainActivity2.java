package com.cn.yijia.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cn.yijia.recyclerview.netdata.Constants;
import com.cn.yijia.recyclerview.netdata.DataBean;
import com.cn.yijia.recyclerview.netdata.GetDataUtil;
import com.cn.yijia.recyclerview.netdata.NetData;
import com.cn.yijia.recyclerview.netdata.NetDataBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lxm
 * @version 2020/6/10-14:53
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class MainActivity2 extends AppCompatActivity {
	private static final String TAG = "贰";
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		Log.e( TAG, "onCreate" );
		/*String json = "{\n" + "        \"statusCode\": 200,\n" + "        \"msg\": \"success\",\n" + "        \"data\": [\n" + "            {\n" + "                \"id\": 0,\n" + "                \"machine_id\": 111,\n" + "                \"type\": 1,\n" + "                \"value\": 20.85910787001516,\n" + "                \"create_time\": 1592376595\n" + "            },\n" + "            {\n" + "                \"id\": 0,\n" + "                \"machine_id\": 111,\n" + "                \"type\": 1,\n" + "                \"value\": 32.77003161098965,\n" + "                \"create_time\": 1592376645\n" + "            },\n" + "            {\n" + "                \"id\": 0,\n" + "                \"machine_id\": 111,\n" + "                \"type\": 1,\n" + "                \"value\": 28.906634837719263,\n" + "                \"create_time\": 1592376655\n" + "            },\n" + "            {\n" + "                \"id\": 0,\n" + "                \"machine_id\": 111,\n" + "                \"type\": 1,\n" + "                \"value\": 21.1663159363299,\n" + "                \"create_time\": 1592376665\n" + "            },\n" + "            {\n" + "                \"id\": 0,\n" + "                \"machine_id\": 111,\n" + "                \"type\": 1,\n" + "                \"value\": 38.85106952361688,\n" + "                \"create_time\": 1592376675\n" + "            },\n" + "            {\n" + "                \"id\": 0,\n" + "                \"machine_id\": 111,\n" + "                \"type\": 1,\n" + "                \"value\": 35.76800009938249,\n" + "                \"create_time\": 1592376685\n" + "            },\n" + "            \n" + "            {\n" + "                \"id\": 0,\n" + "                \"machine_id\": 111,\n" + "                \"type\": 1,\n" + "                \"value\": 35.09072113832668,\n" + "                \"create_time\": 1592377065\n" + "            }\n" + "    \t]\n" + "    }\n";
		NetDataBean myJsonData = new Gson().fromJson( json, NetDataBean.class );

		if (myJsonData == null) {
			Log.e( TAG, "jiexicuowu" );
		} else {
			Log.e( TAG, "ok" );
			Log.e(TAG, "---------------" + myJsonData.getMsg() + myJsonData.getStatusCode());
			ArrayList<DataBean> dataBeans = myJsonData.getData();
			if (dataBeans != null) {
				Log.e(TAG, "myJsonData.getDataBeans().size()" + myJsonData.getData().size() );
				for (int i = 0; i < dataBeans.size(); i++) {
					Log.e(TAG, "dataBeans.get( i ).getValue()" + dataBeans.get( i ).getValue());
				}

			} else {
			    Log.e(TAG, "dataBeans is null" );
			}
		}*/
//		myData();
		Date date = new Date( 1633376655 );
		Log.e(TAG, "date" + date.toString());
		Log.e(TAG, "date2" + new Date(  ).getTime());


		mButton = findViewById( R.id.btn );
		mButton.setText( "2" );
		mButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity( new Intent( MainActivity2.this, MainActivity3.class ) );
				finish();
			}
		} );
	}

	private void myData() {
		Retrofit build = new Retrofit.Builder().baseUrl( Constants.BASEURL ).addConverterFactory( GsonConverterFactory.create() ).build();
		Call<ResponseBody> call = build.create( NetData.class ).getAllData();
		call.enqueue( new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

				try {
					Gson gson = new Gson();
					String json = null;
					if (response.body() != null) {
						json = response.body().string();
						Log.e( TAG, "json" + json );
					}

					NetDataBean myJsonData = gson.fromJson( json, NetDataBean.class );
//					dataBeanList.addAll( Objects.requireNonNull( myJsonData ).getData() );
					if (myJsonData == null){
						Log.e(TAG, "出错" );
					}else {
						if (myJsonData.getData().size() == 0){
							Log.e(TAG, "shi 0" );
						}else {
							Log.e(TAG, "bushi 0" );
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				Log.e( TAG, "请求ok" );

			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e( TAG, "请求出错" + t.getMessage() );
			}
		} );
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
