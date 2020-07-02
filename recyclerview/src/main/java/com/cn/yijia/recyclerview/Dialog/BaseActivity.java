package com.cn.yijia.recyclerview.Dialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.cn.yijia.recyclerview.CTApp;
import com.cn.yijia.recyclerview.R;
import com.cn.yijia.recyclerview.chartdatabase.ChartData;
import com.cn.yijia.recyclerview.chartdatabase.DbController;
import com.cn.yijia.recyclerview.chartsavtivity.CultureTemperatureActivity;
import com.cn.yijia.recyclerview.chartutil.CustomValueFormatter;
import com.cn.yijia.recyclerview.chartutil.DateFormatter;
import com.cn.yijia.recyclerview.chartutil.MyMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.mumu.dialog.MMLoading;
import com.mumu.dialog.MMToast;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lxm
 * @version 2020/6/23-10:14
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class BaseActivity extends AppCompatActivity {

	private MMLoading mmLoading;
	private MMToast mmToast;
	protected DbController mDbController;
	protected ArrayList<Entry> entries = new ArrayList<>();
	protected ArrayList<String> timedate = new ArrayList<>();
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//		supportRequestWindowFeature( Window.FEATURE_NO_TITLE);
		getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
		if (mDbController == null){
			mDbController = ((CTApp)getApplication()).getDbController();
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	protected void showLoading() {
		if (mmLoading == null) {
			MMLoading.Builder builder = new MMLoading.Builder(this)
					.setMessage("加载中...")
					.setCancelable(false)
					.setCancelOutside(false);
			mmLoading = builder.create();
		}else {
			mmLoading.dismiss();
			MMLoading.Builder builder = new MMLoading.Builder(this)
					.setMessage("加载中...")
					.setCancelable(false)
					.setCancelOutside(false);
			mmLoading = builder.create();
		}
		mmLoading.show();
	}

	protected void showLoading(String msg) {
		if (mmLoading == null) {
			MMLoading.Builder builder = new MMLoading.Builder(this)
					.setMessage(msg)
					.setCancelable(false)
					.setCancelOutside(false);
			mmLoading = builder.create();
		}else {
			mmLoading.dismiss();
			MMLoading.Builder builder = new MMLoading.Builder(this)
					.setMessage(msg)
					.setCancelable(false)
					.setCancelOutside(false);
			mmLoading = builder.create();
		}
		mmLoading.show();
	}

	protected void hideLoading() {
		if (mmLoading != null && mmLoading.isShowing()) {
			mmLoading.dismiss();
		}
	}

	protected void showToastSuccess(String msg) {
		if (mmToast == null) {
			MMToast.Builder builder=new MMToast.Builder(this)
					.setMessage(msg)
					.setSuccess(true);
			mmToast=builder.create();
		}else {
			mmToast.cancel();
			MMToast.Builder builder=new MMToast.Builder(this)
					.setMessage(msg)
					.setSuccess(true);
			mmToast=builder.create();
		}
		mmToast.show();
	}

	protected void showToastFailure(String msg) {
		if (mmToast == null) {
			MMToast.Builder builder=new MMToast.Builder(this)
					.setMessage(msg)
					.setSuccess(false);
			mmToast=builder.create();
		}else {
			mmToast.cancel();
			MMToast.Builder builder=new MMToast.Builder(this)
					.setMessage(msg)
					.setSuccess(false);
			mmToast=builder.create();
		}
		mmToast.show();
	}
}
