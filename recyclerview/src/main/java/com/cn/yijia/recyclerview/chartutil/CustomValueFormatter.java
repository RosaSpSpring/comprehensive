package com.cn.yijia.recyclerview.chartutil;

import android.util.Log;

import com.cn.yijia.recyclerview.netdata.DataBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * @author lxm
 * @version 2020/6/12-13:36
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class CustomValueFormatter extends ValueFormatter {
	private static final String TAG = CustomValueFormatter.class.getSimpleName();
	private ArrayList<String> mDateTime = new ArrayList<>(  );

	public CustomValueFormatter(ArrayList<String> datetime) {
		mDateTime = datetime;
	}



	@Override
	public String getAxisLabel(float value, AxisBase axis) {


		return mDateTime.get( (int) (value % mDateTime.size()) );
	}



}

