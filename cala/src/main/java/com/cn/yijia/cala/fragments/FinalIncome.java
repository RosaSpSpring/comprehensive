package com.cn.yijia.cala.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.yijia.cala.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author lxm
 * @version 2020/6/29-16:45
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class FinalIncome extends Fragment {
	private static final String TAG = FinalIncome.class.getSimpleName();
	private Context mContext;
	private View rootView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		mContext = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate( R.layout.final_income, container, false );
		}
		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated( savedInstanceState );
	}

}
