package com.cn.yijia.recyclerview.histroyobservernote;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author lxm
 * @version 2020/6/11-14:10
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class CustomEditText extends AppCompatEditText {
	private static final String TAG = CustomEditText.class.getSimpleName();
	private Paint mPaint;
	public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super( context, attrs, defStyleAttr );
	}
	public CustomEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor( Color.BLUE);
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		//      画底线
		canvas.drawLine(0,this.getHeight()-1,  this.getWidth()-1, this.getHeight()-1, mPaint);
	}


}
