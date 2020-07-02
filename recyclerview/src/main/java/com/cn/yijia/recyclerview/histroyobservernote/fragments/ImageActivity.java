package com.cn.yijia.recyclerview.histroyobservernote.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.cn.yijia.recyclerview.R;
import com.cn.yijia.recyclerview.histroyobservernote.BitmapUtil;
import com.cn.yijia.recyclerview.histroyobservernote.ImageBean;
import com.cn.yijia.recyclerview.histroyobservernote.ZoomImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author lxm
 * @version 2020/6/11-11:24
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class ImageActivity extends AppCompatActivity {
	private static final String TAG = ImageActivity.class.getSimpleName();
	private ZoomImageView mZoomImageView;
	byte[] imageBean;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			imageBean = extras.getByteArray( "imagebytes" );
		}
		setContentView( R.layout.layout_image_activity );
		mZoomImageView = findViewById(R.id.iv_zoom);
		Bitmap bitmap = BitmapUtil.Bytes2Bimap( imageBean );


		mZoomImageView.setImageBitmap( bitmap );


	}
}
