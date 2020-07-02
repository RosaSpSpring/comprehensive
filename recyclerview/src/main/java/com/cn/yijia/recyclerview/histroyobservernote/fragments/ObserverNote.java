package com.cn.yijia.recyclerview.histroyobservernote.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cn.yijia.recyclerview.R;
import com.cn.yijia.recyclerview.histroyobservernote.BitmapUtil;
import com.cn.yijia.recyclerview.histroyobservernote.HistoryObserverNote;
import com.cn.yijia.recyclerview.histroyobservernote.ImageBean;
import com.cn.yijia.recyclerview.histroyobservernote.ObserNoteAdapter;
import com.cn.yijia.recyclerview.histroyobservernote.SpaceItemDecoration;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author lxm
 * @version 2020/6/10-17:26
 * @des 方案信息
 * @updateDes 方案信息
 * @updateAuthor $
 */
public class ObserverNote extends Fragment {
	private static final String TAG = ObserverNote.class.getSimpleName();
	private View rootView = null;
	private Context mContext;
	private RecyclerView mRecyclerView;
	private ObserNoteAdapter mObserNoteAdapter;

	public ObserverNote() {
	}

	// 图片数据 可以删
	private List<ImageBean> mImageBeanList = new ArrayList<>();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		mContext = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate( R.layout.layout_observer_note, container, false );
			mRecyclerView = rootView.findViewById( R.id.rv_observer_note );
		}
		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated( savedInstanceState );
		initData();
		initAdapter();
	}

	private void initData() {
		mImageBeanList.add( new ImageBean( "2020/05/07 11:10", getResources().getDrawable( R.drawable.drawable1 ) ) );
		mImageBeanList.add( new ImageBean( "2020/05/08 11:10", getResources().getDrawable( R.drawable.drawable2 ) ) );
		mImageBeanList.add( new ImageBean( "2020/05/09 11:10", getResources().getDrawable( R.drawable.drawable3 ) ) );
		mImageBeanList.add( new ImageBean( "2020/05/1 11:10", getResources().getDrawable( R.drawable.drawable4 ) ) );
		mImageBeanList.add( new ImageBean( "2020/05/2 11:10", getResources().getDrawable( R.drawable.drawable5 ) ) );
		mImageBeanList.add( new ImageBean( "2020/05/3 11:10", getResources().getDrawable( R.drawable.drawable6 ) ) );
		mImageBeanList.add( new ImageBean( "2020/05/4 11:10", getResources().getDrawable( R.drawable.drawable7 ) ) );
		mImageBeanList.add( new ImageBean( "2020/05/5 11:10", getResources().getDrawable( R.drawable.drawable8 ) ) );
		mImageBeanList.add( new ImageBean( "2020/05/6 11:10", getResources().getDrawable( R.drawable.drawable9 ) ) );
	}

	private void initAdapter() {
		mObserNoteAdapter = new ObserNoteAdapter( mContext, mImageBeanList );
		mRecyclerView.setAdapter( mObserNoteAdapter );
		mRecyclerView.addItemDecoration( new SpaceItemDecoration( 15 ) );
		mRecyclerView.setLayoutManager( new GridLayoutManager( mContext, 3 ) );
		mObserNoteAdapter.setOnItemClickListener( new ObserNoteAdapter.OnItemClickListener() {
			@Override
			public void onItemClickListener(View view, int position, ImageBean imageBean) {
				Toast.makeText( mContext, "点击第" + position + "张图片", Toast.LENGTH_SHORT ).show();
				if (imageBean != null) {
					Intent intent = new Intent();
					intent.setClass( mContext, ImageActivity.class );
					Bundle bundle = new Bundle();
					Bitmap bitmap = BitmapUtil.drawableToBitmap( imageBean.getDrawable() );
					byte[] bytes = BitmapUtil.Bitmap2Bytes( bitmap );
					bundle.putByteArray( "imagebytes",bytes );
					intent.putExtras( bundle );
					startActivity( intent );
				} else {
					Log.e( TAG, "imageBean is null" );
				}
			}
		} );
	}




}
