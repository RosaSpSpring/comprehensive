package com.cn.yijia.recyclerview.chartsavtivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.cn.yijia.recyclerview.Dialog.BaseActivity;
import com.cn.yijia.recyclerview.R;
import com.cn.yijia.recyclerview.chartdatabase.ChartData;
import com.cn.yijia.recyclerview.chartutil.CustomValueFormatter;
import com.cn.yijia.recyclerview.chartutil.DateFormatter;
import com.cn.yijia.recyclerview.chartutil.MyMarkerView;
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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author lxm
 * @version 2020/6/12-9:57
 * @des 培养温度
 * @updateDes 培养温度
 * @updateAuthor $
 */
public class CultureTemperatureActivity extends BaseActivity implements OnChartValueSelectedListener {
	private static final String TAG = CultureTemperatureActivity.class.getSimpleName();
	private LineChart chart;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.layout_culture_temperature );
		setTitle( "温度折线图" );

		initChart();
		initMYdata();

	}


	protected void initChart() {
		chart = findViewById( R.id.chart_tem );
		chart.setNoDataText( "加载中..." );
		chart.setOnChartValueSelectedListener( CultureTemperatureActivity.this );
		chart.setBackgroundColor( Color.WHITE );
		chart.setGridBackgroundColor( Color.WHITE );
		chart.setAutoScaleMinMaxEnabled( true );
		// 画方格背景
		chart.setDrawGridBackground( true );

		// no description text
		chart.getDescription().setEnabled( true );
		Description description = new Description();
		description.setTextAlign( Paint.Align.CENTER );
		description.setText( "时间/t" );
		chart.setDescription( description );

		// enable touch gestures
		chart.setTouchEnabled( true );
		// enable scaling and dragging
		chart.setDragEnabled( true );
		chart.setScaleEnabled( true );
		chart.setScaleXEnabled( true );
		chart.setScaleYEnabled( true );
		// 用于指示是否启用y轴自动缩放的标志。
		// 如果启用，则每当视口更改时，y轴就会自动调整为当前x轴范围的最小和最大 y 值。
		// 对于显示财务数据的图表而言，这尤其有趣。默认值：false
		//	chart.setAutoScaleMinMaxEnabled( true );
		// 惯性滑动
		chart.setDragDecelerationEnabled( true );
		// 滑动摩擦(0 - 1) 0 立即停止
		chart.setDragDecelerationFrictionCoef( 0.98f );

		// to use for it
		MyMarkerView mv = new MyMarkerView( CultureTemperatureActivity.this, R.layout.custom_marker_view, 1 );
		mv.setChartView( chart ); // For bounds control
		chart.setMarker( mv ); // Set the marker to the chart


	}


	protected void initMYdata() {

		// 加载的 loading
		showLoading( "正在加载数据中..." );


		List<ChartData> list = mDbController.searchFilterData( 1, 1592462000, 1592961000 ).list();
		/*for (int i = 0; i < list.size() ; i+=60) {
			chartData.add( list.get( i ) );


		}*/
		ArrayList<ChartData> chartData = new ArrayList<>( list );
		for (int i = 0; i < chartData.size(); i++) {
			timedate.add( DateFormatter.getShortFormattedDateString( new Date( chartData.get( i ).getCreate_time() ) ) );
			entries.add( new Entry( (float) i, (float) ((int) (chartData.get( i ).getValue() * 100) / 100.00) ) );
		}
		//___________________________________________________________________

		XAxis xl = chart.getXAxis();
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
		xl.setLabelRotationAngle( 0 );
		xl.setAvoidFirstLastClipping( false );


		YAxis leftAxis = chart.getAxisLeft();
		// 左边的 Y 轴倒转
		leftAxis.setInverted( false );
		leftAxis.setAxisMinimum( 0f ); // this replaces setStartAtZero(true)
		leftAxis.setAxisMaximum( 3 );
		leftAxis.enableGridDashedLine( 10, 10, 0 );
		leftAxis.setAxisLineWidth( 1f );
		leftAxis.setAxisLineColor( Color.BLACK );
		leftAxis.setGranularityEnabled( true );
		leftAxis.setGranularity( .1f );

		// 培养箱温度 量程设置
		leftAxis.setAxisMinimum( 0 );
		leftAxis.setAxisMaximum( 45 );

		YAxis rightAxis = chart.getAxisRight();
		// 右边的 不设置
		rightAxis.setEnabled( false );

		//___________________________________________________________________

		Collections.sort( entries, new EntryXComparator() );

		LineDataSet set = new LineDataSet( entries, "温度折线图/℃" );
		set.setLineWidth( 4f );
		set.setCircleRadius( 4f );
		//					set.setColor( Color.BLUE );
		//					set.setCircleColor( Color.BLUE );
		//实心颜色
		//					set.setCircleHoleColor( Color.BLUE );
		set.setDrawValues( false );
		set.setDrawFilled( false );
		//					set.setMode( LineDataSet.Mode.LINEAR );
		//					set.setCircleHoleRadius( 2f );


		LineData data = new LineData( set );
		chart.setData( data );

		set.notifyDataSetChanged();

		Legend l = chart.getLegend();
		l.setForm( Legend.LegendForm.LINE );


		//					xl.setDrawLabels( true );
		//					xl.setLabelCount( 10 );

		chart.setPinchZoom( true );
		chart.setVisibleXRange( 10, 20 );

		xl.setGranularityEnabled( true );
		xl.setGranularity( 10 );


		chart.moveViewToX( timedate.size() - 1 );
		chart.animateX( 1500 );
		hideLoading();

		//  添加数据集 ************
		/*Retrofit build = new Retrofit.Builder().baseUrl( Constants.BASEURL ).addConverterFactory( GsonConverterFactory.create() ).build();
		Call<ResponseBody> call = build.create( NetData.class ).getAllData();
		call.enqueue( new Callback<ResponseBody>() {
			@RequiresApi(api = Build.VERSION_CODES.N)
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

				try {
					Gson gson = new Gson();
					String json = null;
					if (response.body() != null) {
						json = response.body().string();
						Log.e( TAG, "json" + json );
					}
					ArrayList<DataBean> dataBeanList = new ArrayList<>();
					NetDataBean myJsonData = gson.fromJson( json, NetDataBean.class );
					ArrayList<DataBean> jsondata = myJsonData.getData();




					if (dataBeanList.size() == 0) {
						for (int i = 0; i < jsondata.size(); i += 60) {
							dataBeanList.add( jsondata.get( i ) );
						}
						Log.e( TAG, "dataJson.size" + myJsonData.getData().size() );
					} else {
						dataBeanList.clear();
						ArrayList<DataBean> data = myJsonData.getData();

						for (int i = 0; i < data.size(); i += 60) {
							dataBeanList.add( data.get( i ) );
						}

						Log.e( TAG, "datebeanlist.size" + dataBeanList.size() );
					}

					ArrayList<DataBean> temperature = GetDataUtil.getInstance().getFilterData( dataBeanList, 1 );


					for (int i = 0; i < temperature.size(); i++) {
						String create_time = temperature.get( i ).getCreate_time();
						Date shortFormattedStringToDate = DateFormatter.getShortFormattedStringToDate( create_time );

						long time = 0;
						if (shortFormattedStringToDate != null) {
							time = shortFormattedStringToDate.getTime();
							longs.add( (float) time );
							timedate.add( DateFormatter.getShortFormattedDateString( shortFormattedStringToDate ) );
						}

						entries.add( new Entry( (float) i, (float) ((int) (temperature.get( i ).getValue() * 100) / 100.00) ) );

						Log.e( TAG, "temperature.get( i ).getCreate_time()" + (float) (time) + "temperature.get( i ).getValue()" + (float) ((int) (temperature.get( i ).getValue() * 100) / 100.00) );
					}

					//___________________________________________________________________

					XAxis xl = chart.getXAxis();
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
					xl.setLabelRotationAngle( 0 );
					xl.setAvoidFirstLastClipping( false );


					YAxis leftAxis = chart.getAxisLeft();
					// 左边的 Y 轴倒转
					leftAxis.setInverted( false );
					leftAxis.setAxisMinimum( 0f ); // this replaces setStartAtZero(true)
					leftAxis.setAxisMaximum( 3 );
					leftAxis.enableGridDashedLine( 10, 10, 0 );
					leftAxis.setAxisLineWidth( 1f );
					leftAxis.setAxisLineColor( Color.BLACK );
					leftAxis.setGranularityEnabled( true );
					leftAxis.setGranularity( .1f );

					// 培养箱温度 量程设置
					leftAxis.setAxisMinimum( 0 );
					leftAxis.setAxisMaximum( 45 );

					YAxis rightAxis = chart.getAxisRight();
					// 右边的 不设置
					rightAxis.setEnabled( false );

					//___________________________________________________________________

					Collections.sort( entries, new EntryXComparator() );

					LineDataSet set = new LineDataSet( entries, "温度折线图/℃" );
					set.setLineWidth( 4f );
					set.setCircleRadius( 4f );
					//					set.setColor( Color.BLUE );
					//					set.setCircleColor( Color.BLUE );
					//实心颜色
					//					set.setCircleHoleColor( Color.BLUE );
					set.setDrawValues( false );
					set.setDrawFilled( false );
					//					set.setMode( LineDataSet.Mode.LINEAR );
					//					set.setCircleHoleRadius( 2f );


					LineData data = new LineData( set );
					chart.setData( data );

					set.notifyDataSetChanged();

					Legend l = chart.getLegend();
					l.setForm( Legend.LegendForm.LINE );


					//					xl.setDrawLabels( true );
					//					xl.setLabelCount( 10 );

					chart.setPinchZoom( true );
					chart.setVisibleXRange( 10, 20 );

					xl.setGranularityEnabled( true );
					xl.setGranularity( 10 );


					chart.moveViewToX( timedate.size() - 1 );
					chart.animateX( 1500 );
					hideLoading();

				} catch (IOException e) {
					e.printStackTrace();
				}

				Log.e( TAG, "解析结束" );

			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e( TAG, "请求出错" + t.getMessage() );
			}
		} );
*/

	}

	// chart listener
	@Override
	public void onValueSelected(Entry e, Highlight h) {
		//		Toast.makeText( this, "温度：" + e.getY() + "℃", Toast.LENGTH_SHORT ).show();
	}

	// chart listener
	@Override
	public void onNothingSelected() {

	}
}
