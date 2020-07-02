package com.cn.yijia.recyclerview.histroyobservernote.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.yijia.recyclerview.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author lxm
 * @version 2020/6/10-17:26
 * @des 方案信息
 * @updateDes 方案信息
 * @updateAuthor $
 */
public class CurveNote extends Fragment {
	private static final String TAG = CurveNote.class.getSimpleName();
	private View rootView = null;
	private Context mContext;

	public CurveNote() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		mContext = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (rootView == null){
			rootView = inflater.inflate( R.layout.layout_curve_note,container,false );
		}
		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated( savedInstanceState );
	}
}
