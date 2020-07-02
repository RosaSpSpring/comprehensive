package com.cn.yijia.cala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.cn.yijia.cala.fragments.FinalIncome;
import com.cn.yijia.cala.fragments.FixedInvestment;
import com.cn.yijia.cala.fragments.Yield;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextInputLayout.OnEndIconChangedListener,TextInputLayout.OnEditTextAttachedListener{
	private static final String TAG = MainActivity.class.getSimpleName();

	private ViewPager mViewPager;
	private TabLayout mTabLayout;

	private List<Fragment> mFragments = new ArrayList<>();
	private List<String> mTitles = new ArrayList<>();

	private MaterialTextView startTime;
	private MaterialTextView endTime;
	private InputMethodManager imm;

	private TextInputLayout mCustom;
	private TextInputLayout mPassword;
	private TextInputLayout mClearText;
	private TextInputLayout mDropMenu;
	private AutoCompleteTextView mDropEdit;
	private TextInputLayout mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main_text2 );

		//		initView();
		//		initData();
		//		initListener();


		//		 imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		////		//隐藏软键盘 //
		////		imm.hideSoftInputFromWindow(et_edit.getWindowToken(), 0);
		////		//显示软键盘
		////		imm.showSoftInputFromInputMethod(tv.getWindowToken(), 0);
		////		//切换软键盘的显示与隐藏
		////		imm.toggleSoftInputFromWindow(tv.getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
		//		startTime = findViewById(R.id.editText9);
		//		endTime = findViewById(R.id.editText10);
		//		startTime.setHint( new SimpleDateFormat( "yyyy-MM-dd",Locale.CHINA ).format( new Date(  ) ) );
		//		endTime.setHint( new SimpleDateFormat( "yyyy-MM-dd",Locale.CHINA ).format( new Date(  ) ) );
		//		initDataPicker();


		mCustom = findViewById(R.id.filled_custom);
		mPassword = findViewById(R.id.filled_password);
		mClearText = findViewById(R.id.filled_clear);
		mDropMenu = findViewById(R.id.filled_dropdown_menu);
		mDropEdit = findViewById(R.id.drop_edit);
		mHelper = findViewById(R.id.filled_helper);

		String[] items = new String[]{"Material","Design","Components","Android"};

		ArrayAdapter arrayAdapter = new ArrayAdapter<String>( this, R.layout.list_item, items );
		mDropEdit.setAdapter( arrayAdapter );

		mCustom.setEndIconOnClickListener( this );
		mHelper.setEndIconOnClickListener( this );
		mDropMenu.setEndIconOnClickListener( this );
		mClearText.setEndIconOnClickListener( this );
		mPassword.setEndIconOnClickListener( this );


	}








	//// 响应末尾图标按下点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.filled_custom:
				// 响应末尾图标按下点击事件
				mCustom.setEndIconMode( TextInputLayout.END_ICON_PASSWORD_TOGGLE );
				mCustom.setEndIconDrawable(  getDrawable( R.drawable.password_drawable_selector ) );
				mCustom.setEndIconTintMode( PorterDuff.Mode.SRC_IN );
				try {
					mCustom.setEndIconTintList( ColorStateList.createFromXml( getResources(), getResources().getXml( R.xml.color_state_list),null ) );
				} catch (IOException | XmlPullParserException e) {
					e.printStackTrace();
				}
				break;
			case R.id.filled_password:

				break;
			case R.id.filled_dropdown_menu:

				break;
			case R.id.filled_clear:

				break;
			case R.id.filled_helper:

				break;

			default:
				break;
		}

	}

	@Override
	public void onEditTextAttached(@NonNull TextInputLayout textInputLayout) {
		// 如果附加了编辑文字后，应进行任何特定的更改，因此添加尾图时，设置 OnEditTextAttachedListener
		// eg: 明文图标的可见性行为取决于是否 EditText 存在输入，因此，设置 OnEditTextAttachedListener
		// 因此可以调用注入 editText.getText() 之类的东西。
		switch (textInputLayout.getId()) {
			case R.id.filled_custom:

				break;
			case R.id.filled_password:

				break;
			case R.id.filled_dropdown_menu:

				break;
			case R.id.filled_clear:

				break;
			case R.id.filled_helper:

				break;

			default:
				break;
		}

	}

	@Override
	public void onEndIconChanged(@NonNull TextInputLayout textInputLayout, int previousIcon) {
		//  如果在 endIconMode 得到时进行任何特定的更改，更改后，设置一个 OnEditTextAttachedListener
		//  eg: 如果设置了密码切换图标并且使用了不同的 EndIconMode 设置号以后，TextInputLayout必须确保编辑文本的
		//transformationMethod 仍为 PasswordTransFormationMethod 因此，使用 addOnEndIconChangedListener
		switch (textInputLayout.getId()) {
			case R.id.filled_custom:

				break;
			case R.id.filled_password:

				break;
			case R.id.filled_dropdown_menu:

				break;
			case R.id.filled_clear:

				break;
			case R.id.filled_helper:

				break;

			default:
				break;
		}
	}


	private void initDataPicker() {

		startTime.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//时间选择器
				initDateListener( startTime );
			}
		} );
		endTime.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				initDateListener( endTime );
			}
		} );


	}

	private void initDateListener(final MaterialTextView time) {
		//隐藏软键盘 //
		imm.hideSoftInputFromWindow( time.getWindowToken(), 0 );
		TimePickerView pvTime = new TimePickerBuilder( MainActivity.this, new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {// 选中事件回调
				time.setText( new SimpleDateFormat( "yyyy-MM-dd", Locale.CHINA ).format( date ) );
			}
		} ).build();
		// pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
		pvTime.show();
	}

	private void initListener() {
		mViewPager.setAdapter( new ManagerFragmentPagerAdapter( this.getSupportFragmentManager(), 1, this, mFragments, mTitles ) );
		mTabLayout.setupWithViewPager( mViewPager );
	}

	private void initData() {
		mFragments.add( new FinalIncome() );
		mFragments.add( new Yield() );
		mFragments.add( new FixedInvestment() );

		mTitles.add( getResources().getString( R.string.final_income ) );
		mTitles.add( getResources().getString( R.string.yield ) );
		mTitles.add( getResources().getString( R.string.fixed_investment ) );

	}

	private void initView() {
		mViewPager = findViewById( R.id.vp_show_detail );
		mTabLayout = findViewById( R.id.kinds_cala );
	}
}
