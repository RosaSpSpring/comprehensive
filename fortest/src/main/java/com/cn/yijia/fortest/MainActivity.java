package com.cn.yijia.fortest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.yijia.fortest.solarutil.AppUtils;
import com.cn.yijia.fortest.solarutil.SolarTermsUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private CheckBox mCheckBox1;
	private CheckBox mCheckBox2;
	private CheckBox mCheckBox3;
	private SquareImageView mImageView;
	private AppCompatImageView mAppCompatImageView;

	private Button mButton;
	private HandsCustomView mHandsCustomView;
	private FootsCustomView mFootsCustomView;
	private TextView mTextView;
	List<String> list_cb = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		initView();
		mHandsCustomView.setBackgroundColor( Color.GRAY);
		String s = initSolar();
		mImageView.setBackgroundColor( Color.RED);
		
		initListener();

	}

	private String initSolar() {

		// 获得当前日期节气
		Date date = new Date();
		String solarTerm = SolarTermsUtil.getSolarTerm( date );
		Log.e( TAG, "solarTerm" + solarTerm );
		long time = date.getTime();
		long oneday = 24 * 3600 * 1000;
		long addoneday = time + oneday;
		long subtractday = time - oneday;
		Log.e( TAG, "time" + time + "oneday" + oneday );
		ArrayList<Date> dates = new ArrayList<>();

		while (true) {
			Date date1 = new Date( addoneday );
			String solarTerm1 = SolarTermsUtil.getSolarTerm( date1 );

			if (solarTerm.equals( solarTerm1 )) {
				dates.add( date1 );
				addoneday += oneday;
			} else {
				break;
			}
		}
		while (true) {
			Log.e( TAG, "jinru" );
			Date date1 = new Date( subtractday );
			String solarTerm1 = SolarTermsUtil.getSolarTerm( date1 );

			if (solarTerm.equals( solarTerm1 )) {
				dates.add( date1 );
				subtractday -= oneday;
			} else {
				break;
			}

		}


		Collections.sort( dates );
		Date date1 = dates.get( 0 );
		Date date2 = dates.get( dates.size() - 1 );
		StringBuilder stringBuilder = new StringBuilder();
		String s1 = AppUtils.getShortMDFormattedDateString( date1 );
		String s2 = AppUtils.getShortMDFormattedDateString( date2 );


		StringBuilder formatdate = formatdate( stringBuilder, s1 );
		String solartoshow = formatdate.toString() + "-" ;
		StringBuilder formatdate1 = formatdate( stringBuilder, s2 );
		solartoshow = solartoshow + formatdate1.toString();
		return solartoshow;

	}

	private StringBuilder formatdate(StringBuilder stringBuilder, String s) {
		if (stringBuilder.length() != 0)
			stringBuilder.delete( 0, stringBuilder.length() );
		stringBuilder.append( String.format( Locale.CHINA, "%2d", Integer.parseInt( Objects.requireNonNull( s ).substring( 0, 2 ) ) ).trim() ).append( "月" );
		stringBuilder.append( String.format( Locale.CHINA, "%2d", Integer.parseInt( s.substring( 3, 5 ) ) ).trim() ).append( "日" );
		Log.e( TAG, "stringBu" + stringBuilder );
		return stringBuilder;
	}

	private void initView() {
		mCheckBox1 = findViewById( R.id.cb1 );
		mCheckBox2 = findViewById( R.id.cb2 );
		mCheckBox3 = findViewById( R.id.cb3 );
		mButton = findViewById( R.id.button );
		mHandsCustomView = findViewById( R.id.hand );
		mFootsCustomView = findViewById( R.id.foot );
		mTextView = findViewById( R.id.textView );
		mImageView = findViewById(R.id.squareiv);
		mAppCompatImageView = findViewById(R.id.sourceiv);
	}

	private void initListener() {

		ObjectAnimator curprogress = ObjectAnimator.ofInt( mHandsCustomView, "curprogress", 1 );
		initEA( curprogress );

		ObjectAnimator cuss = ObjectAnimator.ofInt( mFootsCustomView, "curprogress", 1 );
		initEA( cuss );

		mCheckBox1.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mCheckBox1.isChecked()) {
					String cb1_str = mCheckBox1.getText().toString().trim();
					list_cb.add( cb1_str );
				}
			}
		} );
		mCheckBox2.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mCheckBox2.isChecked()) {
					String cb2_str = mCheckBox2.getText().toString().trim();
					list_cb.add( cb2_str );
				}
			}
		} );
		mCheckBox3.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mCheckBox3.isChecked()) {
					String cb3_str = mCheckBox3.getText().toString().trim();
					list_cb.add( cb3_str );
				}
			}
		} );
		mButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity( new Intent( MainActivity.this, MainActivity2.class ) );
			}
		} );

	}

	// 实例化动画设置动画时间进度，动画线性移动
	private void initEA(ObjectAnimator curprogress) {
		IntEvaluator intEvaluator = new IntEvaluator();
		intEvaluator.evaluate( 1, 0, 1 );
		curprogress.setEvaluator( intEvaluator );
		curprogress.setDuration( 1000 );
		curprogress.setInterpolator( new LinearInterpolator() );
		curprogress.start();
	}


	private class MyEvaluator implements TypeEvaluator<Float> {

		@Override
		public Float evaluate(float fraction, Float startValue, Float endValue) {


			return null;
		}
	}
}
