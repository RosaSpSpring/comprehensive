package com.cn.yijia.fortest;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author lxm
 * @version 2020/5/28-14:09
 * @des 自定义正方形imageview
 * @updateDes 自定义正方形imageview
 * @updateAuthor $
 */
public class SquareImageView extends AppCompatImageView {


	public SquareImageView(Context context) {
		super( context );
	}

	public SquareImageView(Context context, AttributeSet attrs) {
		super( context, attrs );
	}

	public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super( context, attrs, defStyleAttr );
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
		//获取原来的宽高
		int w = MeasureSpec.getSize( widthMeasureSpec );
		int h = getMeasuredHeight();

		// 将其设置成正方形
		if (w > h){
			w = h;
		}else {
			h = w;
		}
		setMeasuredDimension( w,h );
	}
}
