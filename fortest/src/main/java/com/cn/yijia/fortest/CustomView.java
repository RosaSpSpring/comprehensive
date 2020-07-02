package com.cn.yijia.fortest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author lxm
 * @version 2020/5/28-14:41
 * @des 自定义view
 * @updateDes 自定义view
 * @updateAuthor $
 */
public class CustomView extends View {
	public CustomView(Context context) {
		super( context );
	}

	public CustomView(Context context, @Nullable AttributeSet attrs) {
		super( context, attrs );
	}

	public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super( context, attrs, defStyleAttr );
	}

	public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super( context, attrs, defStyleAttr, defStyleRes );
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 因为是自定义view所以父类帮不了我们处理尺寸所以就不用写 super.onMeasure(  ); 方法 完全自己计算然后保存


	}
}
