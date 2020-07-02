package com.cn.yijia.recyclerview.chartutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.yijia.recyclerview.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.text.DecimalFormat;
import java.util.logging.SimpleFormatter;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ViewConstructor")
public class MyMarkerView extends MarkerView {
	private static final String TAG = MyMarkerView.class.getSimpleName();

	private final TextView tvContent;
	private int Style;

	public MyMarkerView(Context context, int layoutResource, int style) {
		super( context, layoutResource );

		tvContent = findViewById( R.id.tvContent );
		Style = style;
	}


	// runs every time the MarkerView is redrawn, can be used to update the
	// content (user-interface)
	@Override
	public void refreshContent(Entry e, Highlight highlight) {
		System.out.println( "e.getY() = " + e.getY() );
		System.out.println( "e.getHeight() = " + highlight.getY() );
		StringBuilder stringBuilder = new StringBuilder();

		switch (Style) {
			//温度
			case 1:
				stringBuilder.delete( 0,stringBuilder.length() );
				if (e instanceof CandleEntry) {

					CandleEntry ce = (CandleEntry) e;


					tvContent.setText( stringBuilder.append(ce.getHigh() ).append( "℃" ).toString() );
				} else {
					tvContent.setText( stringBuilder.append( e.getY() ).append( "℃" ).toString() );
				}
				break;
			// ph值
			case 2:
				stringBuilder.delete( 0,stringBuilder.length() );
				if (e instanceof CandleEntry) {

					CandleEntry ce = (CandleEntry) e;

					tvContent.setText( stringBuilder.append( ce.getHigh() ) );
				} else {

					tvContent.setText( stringBuilder.append( e.getY() ) );
				}
				break;
			// doActivity
			case 3:
				stringBuilder.delete( 0,stringBuilder.length() );
				if (e instanceof CandleEntry) {

					CandleEntry ce = (CandleEntry) e;

					tvContent.setText( stringBuilder.append(  ce.getHigh() ).append( "%" ).toString()  );
				} else {

					tvContent.setText( stringBuilder.append( e.getY() ).append( "%" ).toString() );
				}
				break;
			// 细胞密度 百万/ml
			case 4:
				stringBuilder.delete( 0,stringBuilder.length() );
				if (e instanceof CandleEntry) {

					CandleEntry ce = (CandleEntry) e;

					tvContent.setText( stringBuilder.append(  ce.getHigh() ).append( "m/ml" ).toString()   );
				} else {

					tvContent.setText( stringBuilder.append(  e.getY() ).append( "m/ml" ).toString() );
				}
				break;
			default:
				break;
		}

		super.refreshContent( e, highlight );
	}


	@Override
	public MPPointF getOffset() {
		return new MPPointF( -(getWidth() / 2), -getHeight() );
	}
}
