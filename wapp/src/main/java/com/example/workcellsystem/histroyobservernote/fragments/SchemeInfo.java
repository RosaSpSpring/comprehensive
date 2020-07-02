package com.example.workcellsystem.histroyobservernote.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.workcellsystem.R;

/**
 * @author lxm
 * @version 2020/6/10-17:26
 * @des 方案信息
 * @updateDes 方案信息
 * @updateAuthor $
 */
public class SchemeInfo extends Fragment {
	private static final String TAG = SchemeInfo.class.getSimpleName();
	private View rootView = null;
	private Context mContext;

	public SchemeInfo() {
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
			rootView = inflater.inflate( R.layout.layout_scheme_info,container,false );
		}
		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated( savedInstanceState );
	}
}
