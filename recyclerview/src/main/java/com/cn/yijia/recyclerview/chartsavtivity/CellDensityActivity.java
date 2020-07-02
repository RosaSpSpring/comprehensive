package com.cn.yijia.recyclerview.chartsavtivity;

import android.graphics.Color;
import android.graphics.Typeface;
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
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
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
 * @des 细胞密度
 * @updateDes 细胞密度
 * @updateAuthor $
 */
public class CellDensityActivity extends BaseActivity implements OnChartValueSelectedListener {
	private static final String TAG = CellDensityActivity.class.getSimpleName();
	private ScatterChart dotChart;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.layout_dot );
		setTitle( "细胞密度" );

		initChart();
		initData();

	}

	protected void initData()  {
		//  添加数据集 *********
		// 加载的 loading
		showLoading("正在加载数据中...");
		//___________________________________________________________________

		List<ChartData> list = mDbController.searchFilterDataByType( ChartTypes.DENISTY ).list();
		//		ArrayList<ChartData> chartData = new ArrayList<>();
		// 添加60秒去一个点
		/*for (int i = 0; i < list.size() ; i+=60) {
			chartData.add( list.get( i ) );
		}*/
		//
		ArrayList<ChartData> chartData = new ArrayList<>( list );
		for (int i = 0; i < chartData.size(); i++) {
			timedate.add( DateFormatter.getShortFormattedDateString( new Date( chartData.get( i ).getCreate_time() ) ) );
			entries.add( new Entry( (float) i, (float) ((int) (chartData.get( i ).getValue() * 100) / 100.00) ) );
		}





		MyMarkerView mv = new MyMarkerView( CellDensityActivity.this, R.layout.custom_marker_view,4 );
		// 怎么这两个可以互相 set
		mv.setChartView( dotChart ); // For bounds control
		dotChart.setMarker( mv ); // Set the marker to the chart
		// 绘制 x轴坐标中的数据

		Legend l = dotChart.getLegend();
		l.setVerticalAlignment( Legend.LegendVerticalAlignment.BOTTOM );
		l.setHorizontalAlignment( Legend.LegendHorizontalAlignment.LEFT );
		l.setOrientation( Legend.LegendOrientation.HORIZONTAL );
		l.setDirection( Legend.LegendDirection.LEFT_TO_RIGHT );

		l.setDrawInside( false );

		l.setXOffset( 5f );

		YAxis yl = dotChart.getAxisLeft();
		//					yl.resetAxisMinimum();
		//					yl.setAxisMinimum( 0 );


		dotChart.getAxisRight().setEnabled( false );

		XAxis xl = dotChart.getXAxis();
		xl.setDrawGridLines( false );
		xl.setPosition( XAxis.XAxisPosition.BOTTOM );
		xl.setLabelRotationAngle( 90 );
		CustomValueFormatter customValueFormatter = new CustomValueFormatter( timedate );
		xl.setValueFormatter( customValueFormatter );
		// 字体设置
		xl.setTypeface( Typeface.createFromAsset( getAssets(),"OpenSans-Italic.ttf" ) );


		dotChart.setDragEnabled( true );
		dotChart.setDragXEnabled( true );
		dotChart.setDragYEnabled( true );



		// create a dataset and give it a type
		ScatterDataSet set1 = new ScatterDataSet( entries, "细胞密度/n" );
		set1.setScatterShape( ScatterChart.ScatterShape.SQUARE );
		set1.setColor( Color.parseColor( "#9fedff" ) );

		set1.setScatterShapeSize( 18f );

		// create a data object with the data sets
		ScatterData data = new ScatterData( set1 );

		//		data.setValueTypeface(tfLight);
		dotChart.setData( data );
		hideLoading();
		//					dotChart.invalidate();

		xl.setDrawLabels( true );
		xl.setLabelCount( 10 );

		dotChart.setPinchZoom( true );
		dotChart.setVisibleXRange( 5, 10 );
		// modify the legend... 图例
		// don't forget to refresh the drawing
		dotChart.moveViewToX( timedate.size() - 1 );
		dotChart.animateX( 1500 );


	}

	protected void initChart() {
		dotChart = findViewById( R.id.dot_chart );
		dotChart.getDescription().setEnabled( true );
		Description description = new Description();
		description.setText( "时间/t" );
		dotChart.setDescription( description );

		dotChart.setOnChartValueSelectedListener( this );
		// chart 背景颜色设置
		dotChart.setGridBackgroundColor( Color.WHITE );

		dotChart.setDrawGridBackground( true );
		dotChart.setTouchEnabled( true );
		dotChart.setMaxHighlightDistance( 50f );

		// enable scaling and dragging
		dotChart.setDragEnabled( true );
		dotChart.setScaleEnabled( true );

		dotChart.setMaxVisibleValueCount( 200 );
		dotChart.setPinchZoom( true );
	}



	@Override
	public void onValueSelected(Entry e, Highlight h) {
//      Toast.makeText(this,"细胞密度 ：" + e.getY(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected() {

	}
}
