package com.example.workcellsystem.histroyobservernote;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.example.workcellsystem.R;
import com.example.workcellsystem.histroyobservernote.fragments.CurveNote;
import com.example.workcellsystem.histroyobservernote.fragments.DetailInfo;
import com.example.workcellsystem.histroyobservernote.fragments.ObserverNote;
import com.example.workcellsystem.histroyobservernote.fragments.SchemeInfo;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @author lxm
 * @version 2020/6/10-16:39
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class HistoryObserverNote extends AppCompatActivity {
	private static final String TAG = HistoryObserverNote.class.getSimpleName();

	private ViewPager mViewPager;
	private TabLayout mTableLayout;

	private List<Fragment> mFragments = new ArrayList<>(  );
	private List<String> titles = new ArrayList<>(  );


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.layout_his_obe_note );

       initView();
       initData();
       initListener();

	}




	private void initListener() {
		mViewPager.setAdapter( new ManagerFragmentPagerAdapter(this.getSupportFragmentManager(),1,this,mFragments,titles) );
		mTableLayout.setupWithViewPager( mViewPager );

	}

	private void initData() {
		mFragments.add( new SchemeInfo() );
		mFragments.add( new DetailInfo() );
		mFragments.add( new ObserverNote() );
		mFragments.add( new CurveNote() );

		titles.add( "方案信息" );
		titles.add( "详细信息" );
		titles.add( "观察记录" );
		titles.add( "曲线记录" );
	}

	private void initView() {
		mViewPager = findViewById(R.id.vp_history_observer_note);
		mTableLayout = findViewById(R.id.tb_history_oberver_note);
	}


}
