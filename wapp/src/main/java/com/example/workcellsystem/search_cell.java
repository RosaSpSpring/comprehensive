package com.example.workcellsystem;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class search_cell extends Fragment {
	private static final String TAG = search_cell.class.getSimpleName();
	private View rootView;
	private Context mContext;
	private MaterialTextView startTime;
	private MaterialTextView endTime;
	private InputMethodManager imm;


	public search_cell() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		mContext = this.getActivity();
		imm = (InputMethodManager) getActivity().getSystemService( Context.INPUT_METHOD_SERVICE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if (rootView == null && inflater != null) {
			rootView = inflater.inflate( R.layout.search_cell, container, false );
		}else {
			Log.e(TAG, "inflater is null" );
		}
		startTime = rootView.findViewById( R.id.editText9 );
		endTime = rootView.findViewById( R.id.editText10 );

		startTime.setHint( new SimpleDateFormat( "yyyy-MM-dd", Locale.CHINA ).format( new Date() ) );
		endTime.setHint( new SimpleDateFormat( "yyyy-MM-dd", Locale.CHINA ).format( new Date() ) );

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated( savedInstanceState );

		initDataPicker();
	}

	private void initDataPicker() {

		startTime.setOnClickListener( v -> {
			//时间选择器
			initDateListener( startTime );
		} );
		endTime.setOnClickListener( v -> initDateListener( endTime ) );


	}

	private void initDateListener(final MaterialTextView time) {
		//隐藏软键盘 //
		imm.hideSoftInputFromWindow( time.getWindowToken(), 0 );
		TimePickerView pvTime = new TimePickerBuilder( mContext, (date, v) -> {// 选中事件回调
			time.setText( new SimpleDateFormat( "yyyy-MM-dd", Locale.CHINA ).format( date ) );
		} ).build();
		// pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），
		// 此项可以在弹出选择器的时候重新设置当前时间，
		// 避免在初始化之后由于时间已经设定，
		// 导致选中时间与当前时间不匹配的问题。
		pvTime.show();
	}

}
