package com.cn.yijia.recyclerview.chartsavtivity;

import android.graphics.Color;
import android.os.Bundle;

import com.cn.yijia.recyclerview.R;
import com.cn.yijia.recyclerview.chartutil.CustomValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lxm
 * @version 2020/6/22-17:19
 * @des 测试使用
 * @updateDes
 * @updateAuthor $
 */
public class DemoChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {
	private static final String TAG = DemoChartActivity.class.getSimpleName();
	private LineChart demoChart;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_demochart );
		demoChart = findViewById( R.id.demochart );

		ArrayList<Float> xfloats = new ArrayList<>();
		for (int i = 0; i < 1440; i++) {
			xfloats.add( (float) i );
		}
		ArrayList<Float> yfloats = new ArrayList<>();
		for (int i = 0; i < 1440; i++) {
			yfloats.add( (float) (i / 2) );
		}
		ArrayList<Entry> entries = new ArrayList<>();
		for (int i = 0; i < xfloats.size(); i++) {
			entries.add( new Entry( xfloats.get( i ), yfloats.get( i ) ) );
		}


		// enable touch gestures
		demoChart.setTouchEnabled( true );
		// enable scaling and dragging
		demoChart.setDragEnabled( true );
		demoChart.setScaleEnabled( false );
		demoChart.setScaleXEnabled( true );
		//		demoChart.setScaleX( 1.5f );
		demoChart.setScaleYEnabled( true );
		//		demoChart.setScaleY( 1.5f );
		demoChart.setPinchZoom( false );

		XAxis xAxis = demoChart.getXAxis();

		//	xAxis.setDrawLabels(  );
		xAxis.setPosition( XAxis.XAxisPosition.BOTTOM );
		xAxis.setLabelRotationAngle( 90 );
		//	xAxis.setValueFormatter( new CustomValueFormatter(  ) );
		xAxis.setAxisMaximum( entries.size() - 1 );

		YAxis leftAxis = demoChart.getAxisLeft();
		//		leftAxis.setAxisMinimum( 0f ); // this replaces setStartAtZero(true)
		//		leftAxis.setAxisMaximum( 3 );
		leftAxis.enableGridDashedLine( 10, 10, 0 );
		leftAxis.setAxisLineWidth( 1f );
		leftAxis.setAxisLineColor( Color.BLACK );
		//		leftAxis.setGranularityEnabled( true );
		//		leftAxis.setGranularity( .1f );

		// 培养箱温度 范围设置
		//		leftAxis.setAxisMinimum( 0 );
		//		leftAxis.setAxisMaximum( 300 );

		LineDataSet dEmo = new LineDataSet( entries, "DEMO" );
		dEmo.setMode( LineDataSet.Mode.CUBIC_BEZIER );
		dEmo.setColor( Color.BLUE );
		dEmo.setCircleColor( Color.BLUE );
		dEmo.setCircleHoleColor( Color.BLUE );
		LineData data = new LineData( dEmo );

		demoChart.setData( data );

				xAxis.setGranularityEnabled( true );
		// 一个label显示的个数
				xAxis.setGranularity( 5 );
		//	demoChart.setVisibleXRangeMinimum( 1 );
		//	demoChart.setMaxVisibleValueCount(  );

		demoChart.animateX( 1500 );
		demoChart.invalidate();
		// 一屏显示的个数
				demoChart.setVisibleXRange( 0, 12 );
		//		demoChart.setDragDecelerationFrictionCoef( 0.9f );

	}


	@Override
	public void onValueSelected(Entry e, Highlight h) {

	}

	@Override
	public void onNothingSelected() {

	}
}
