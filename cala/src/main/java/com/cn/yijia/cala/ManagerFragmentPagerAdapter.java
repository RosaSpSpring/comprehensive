package com.cn.yijia.cala;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author lxm
 * @version 2020/6/29-16:48
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class ManagerFragmentPagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = ManagerFragmentPagerAdapter.class.getSimpleName();
	private Context mContext;
	private List<Fragment> mFragments;
	private List<String> mTitles;

	ManagerFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context, List<Fragment> fragments, List<String> titles) {
		super( fm, behavior );
		mContext = context;
		mFragments = fragments;
		mTitles = titles;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		return mFragments.get( position );
	}


	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		super.destroyItem( container, position, object );
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles.get( position );
	}
}
