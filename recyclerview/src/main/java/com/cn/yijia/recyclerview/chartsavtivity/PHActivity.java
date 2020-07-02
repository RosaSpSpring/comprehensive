package com.cn.yijia.recyclerview.chartsavtivity;

import android.database.DatabaseUtils;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.cn.yijia.recyclerview.CTApp;
import com.cn.yijia.recyclerview.Dialog.BaseActivity;
import com.cn.yijia.recyclerview.R;
import com.cn.yijia.recyclerview.chartdatabase.ChartData;
import com.cn.yijia.recyclerview.chartdatabase.DbController;
import com.cn.yijia.recyclerview.chartutil.CustomValueFormatter;
import com.cn.yijia.recyclerview.chartutil.DateFormatter;
import com.cn.yijia.recyclerview.chartutil.MyMarkerView;
import com.cn.yijia.recyclerview.netdata.ChartTypes;
import com.cn.yijia.recyclerview.netdata.Constants;
import com.cn.yijia.recyclerview.netdata.DataBean;
import com.cn.yijia.recyclerview.netdata.GetDataUtil;
import com.cn.yijia.recyclerview.netdata.NetData;
import com.cn.yijia.recyclerview.netdata.NetDataBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lxm
 * @version 2020/6/12-9:57
 * @des pH
 * @updateDes PH
 * @updateAuthor $
 */
public class PHActivity extends BaseActivity implements OnChartValueSelectedListener {
	private static final String TAG = PHActivity.class.getSimpleName();
	private LineChart phChart;



	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.layout_ph );
		setTitle( "pH折线图" );

		initChart();
		initData();

	}

	private void initData()  {
		//  添加数据集 ************
		// 加载的 loading
		showLoading("正在加载数据中...");

		List<ChartData> list = mDbController.searchFilterDataByDate(1592462000,1592588000 ).list();
		ArrayList<ChartData> filterData = GetDataUtil.getInstance().getFilterData( new ArrayList<ChartData>( list ), ChartTypes.PH );
		ArrayList<ChartData> chartData = new ArrayList<>(filterData);
		// 添加 60 秒一个点
		/*for (int i = 0; i < list.size() ; i+=60) {
			chartData.add( list.get( i ) );
		}*/

		//
		for (int i = 0; i < chartData.size(); i++) {
			timedate.add( DateFormatter.getShortFormattedDateString( new Date( chartData.get( i ).getCreate_time() ) ) );
			entries.add( new Entry( (float) i, (float) ((int) (chartData.get( i ).getValue() * 100) / 100.00) ) );
		}


		//___________________________________________________________________

		XAxis xl = phChart.getXAxis();
		xl.setPosition( XAxis.XAxisPosition.BOTTOM );
		// x 轴的颜色 和 线宽度
		xl.setAxisLineWidth( 1.5f );
		xl.setAxisLineColor( Color.BLACK );
		// 设置网格线 虚线， 线长 10f 间隔 10f 起点位 0
		xl.enableGridDashedLine( 10, 10, 0 );

		xl.setCenterAxisLabels( false );
		xl.setDrawGridLines( true );

		CustomValueFormatter customValueFormatter = new CustomValueFormatter( timedate );
		xl.setValueFormatter( customValueFormatter );
		xl.setLabelRotationAngle( 90 );
		xl.setAvoidFirstLastClipping( false );

		YAxis leftAxis = phChart.getAxisLeft();
		// 左边的 Y 轴倒转
		leftAxis.setInverted( false );
		leftAxis.setAxisMinimum( 0f ); // this replaces setStartAtZero(true)
		leftAxis.setAxisMaximum( 3 );
		leftAxis.enableGridDashedLine( 10, 10, 0 );
		leftAxis.setAxisLineWidth( 1f );
		leftAxis.setAxisLineColor( Color.BLACK );
		leftAxis.setGranularityEnabled( true );
		leftAxis.setGranularity( .1f );

		// pH值 量程设置
		leftAxis.setAxisMinimum( 0 );
		leftAxis.setAxisMaximum( 45 );

		YAxis rightAxis = phChart.getAxisRight();
		// 右边的 不设置
		rightAxis.setEnabled( false );

		//___________________________________________________________________

		Collections.sort( entries, new EntryXComparator() );

		LineDataSet set = new LineDataSet( entries, "pH折线图" );
		set.setLineWidth( 4f );
		set.setCircleRadius( 4f );
		set.setDrawValues( false );
		set.setDrawFilled( false );

		LineData data = new LineData( set );
		phChart.setData( data );
		hideLoading();
		set.notifyDataSetChanged();

		Legend l = phChart.getLegend();
		l.setForm( Legend.LegendForm.LINE );


		xl.setDrawLabels( true );
		xl.setLabelCount( 10 );

		phChart.setPinchZoom( true );
		phChart.setVisibleXRange( 5, 10 );
		// modify the legend... 图例
		// don't forget to refresh the drawing
		phChart.moveViewToX( timedate.size() - 1 );
		phChart.animateX( 1500 );

	}




	protected void initChart() {
		phChart = findViewById( R.id.ph_chart );
		phChart.setNoDataText( "没有数据可以加载" );
		phChart.setOnChartValueSelectedListener( this );
		phChart.setBackgroundColor( Color.WHITE );
		phChart.setGridBackgroundColor( Color.WHITE );
		phChart.setAutoScaleMinMaxEnabled( true );
		// 画方格背景
		phChart.setDrawGridBackground( true );


		// 设置边界宽黑但是没有作用
		phChart.setBorderWidth( 4f );
		phChart.setBorderColor( Color.BLACK );


		// no description text
		phChart.getDescription().setEnabled( true );
		Description description = new Description();
		description.setTextAlign( Paint.Align.CENTER );
		description.setText( "时间/t" );
		phChart.setDescription( description );

		// enable touch gestures
		phChart.setTouchEnabled( true );
		// enable scaling and dragging
		phChart.setDragEnabled( true );
		phChart.setScaleEnabled( true );

		// if disabled,scaling can be done on x- and y-axis separately
		phChart.setPinchZoom( false );


		// set an alternative background color
		// chart.setBackgroundColor(Color.GRAY);
		// create a custom MarkerView (extend MarkerView) and specify the layout
		// to use for it
		MyMarkerView mv = new MyMarkerView( this, R.layout.custom_marker_view,2 );
		// 怎么这两个可以互相 set
		mv.setChartView( phChart ); // For bounds control
		phChart.setMarker( mv ); // Set the marker to the chart
	}



	// chart listener
	@Override
	public void onValueSelected(Entry e, Highlight h) {
//		Toast.makeText( this, "pH值 ：" + e.getY() , Toast.LENGTH_SHORT ).show();
	}

	// chart listener
	@Override
	public void onNothingSelected() {

	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}
}
