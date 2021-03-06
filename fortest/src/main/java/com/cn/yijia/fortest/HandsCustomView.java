package com.cn.yijia.fortest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 画好躯干， 每种颜色和点和图片结尾，其中动画的气泡和数字再添加上去
 * 按顺序画还是每一个都好好的画。
 * 按顺序 先画出第一个
 */
public class HandsCustomView extends View {
	public static final String TAG = HandsCustomView.class.getSimpleName();

	//线段的宽度
	private float linewidth = 0;
	//端点的点的半径
	private float pointradius = 0;

	//内边距大约20
	private float innerPadding = 0.0f;
	//起始位置
	private float startX = 0;
	//高度位置 view 高度的一般
	private float startY = 0;

	//当前进度
	private int curprogress = 0;
	//画笔 线段画笔
	Paint lineGrayPaint;
	Paint lineRedPaint;
	Paint lineBluePaint;
	Paint lineGreenPaint;
	Paint lineYellowPaint;

	// 画笔 点的画笔 和气泡的画笔
	Paint pointRedPaint;
	Paint pointBluePaint;
	Paint pointGreenPaint;
	Paint pointYellowPaint;
	Paint pointCirclePaint;

	// 下面数字的画笔
	Paint numPaint;
	// 文本的画笔
	Paint textPaint;
	//气泡数字画笔
	Paint bubbleNumPaint;


	Paint bitmapPaint;

	RectF rectF;
	Rect rect;
	//圆画笔
	Paint circlePaint;

	//圆的半径radius
	private int radius = 0;
	// 圆点的半径
	private float pointCircleRadius = 0;


	// 测手 竖着画 画笔
	private Paint testHandPaint;

	// 测手文本换行对象定义
	StaticLayout.Builder staticLayout ;

	Bitmap bitmap;
	String[] text;
	String[] text1;
	int[] color;

	Path path;
	Matrix matrix;
	private float numSize = 0;
	private float textSize = 0;


	/**
	 * 计算整个控件的高度
	 */
	// 上下的padding

	//气球高度
	// 线的宽度
	// 数字的高度
	// 文字的高度


	public int getCurprogress() {
		return curprogress;
	}

	public void setCurprogress(int curprogress) {
		this.curprogress = curprogress;
		invalidate();
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public HandsCustomView(Context context) {
		super( context );
	}

	public HandsCustomView(Context context, @Nullable AttributeSet attrs) {
		super( context, attrs );
		initData( context );
	}

	public HandsCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super( context, attrs, defStyleAttr );
	}



	{
		bitmap = BitmapFactory.decodeResource( getResources(), R.drawable.test_hand );
		text = new String[]{"(加油)", "(勉强达标)", "(达标)", "(优秀)"};
		text1 = new String[]{"0", "4", "6", "8"};
		color = new int[]{Color.parseColor( "#e51c23" ), Color.parseColor( "#ff9800" ), Color.parseColor( "#0092cc" ), Color.parseColor( "#259b24" )};
	}

	private void initData(Context context) {
		linewidth = dp2px( context, 2 );
		pointradius = dp2px( context, 6 );
		radius = dp2px( context, 5 );
		pointCircleRadius = dp2px( context, 7 );
		innerPadding = dp2px( context, 10 );
		numSize = dp2px( context, 12 );
		textSize = dp2px( context, 8 );


		lineGrayPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		lineGrayPaint.setStyle( Paint.Style.FILL );
		lineGrayPaint.setColor( Color.parseColor( "#aeaeae" ) );
		lineGrayPaint.setStrokeWidth( 6 );

		lineRedPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		lineRedPaint.setStyle( Paint.Style.FILL );
		lineRedPaint.setColor( Color.parseColor( "#e51c23" ) );
		lineRedPaint.setStrokeWidth( linewidth );

		lineYellowPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		lineYellowPaint.setStyle( Paint.Style.FILL );
		lineYellowPaint.setColor( Color.parseColor( "#ff9800" ) );
		lineYellowPaint.setStrokeWidth( linewidth );

		lineBluePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		lineBluePaint.setStyle( Paint.Style.FILL );
		lineBluePaint.setColor( Color.parseColor( "#0092cc" ) );
		lineBluePaint.setStrokeWidth( linewidth );

		lineGreenPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		lineGreenPaint.setStyle( Paint.Style.FILL );
		lineGreenPaint.setColor( Color.parseColor( "#259b24" ) );
		lineGreenPaint.setStrokeWidth( linewidth );


		pointRedPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		pointRedPaint.setColor( Color.parseColor( "#e51c23" ) );
		pointRedPaint.setStyle( Paint.Style.FILL );
		pointRedPaint.setStrokeWidth( pointradius );
		pointRedPaint.setStrokeCap( Paint.Cap.ROUND );

		pointYellowPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		pointYellowPaint.setColor( Color.parseColor( "#ff9800" ) );
		pointYellowPaint.setStyle( Paint.Style.FILL );
		pointYellowPaint.setStrokeWidth( pointradius );
		pointYellowPaint.setStrokeCap( Paint.Cap.ROUND );

		pointBluePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		pointBluePaint.setColor( Color.parseColor( "#0092cc" ) );
		pointBluePaint.setTextSize( textSize );
		pointBluePaint.setTextAlign( Paint.Align.CENTER );
		pointBluePaint.setStyle( Paint.Style.FILL );
		pointBluePaint.setStrokeWidth( pointradius );
		pointBluePaint.setStrokeCap( Paint.Cap.ROUND );

		pointGreenPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		pointGreenPaint.setColor( Color.parseColor( "#259b24" ) );
		pointGreenPaint.setTextSize( textSize );
		pointGreenPaint.setTextAlign( Paint.Align.CENTER );
		pointGreenPaint.setStyle( Paint.Style.FILL );
		pointGreenPaint.setStrokeWidth( pointradius );
		pointGreenPaint.setStrokeCap( Paint.Cap.ROUND );

		pointCirclePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		pointCirclePaint.setColor( Color.parseColor( "#259b24" ) );
		pointCirclePaint.setStyle( Paint.Style.FILL );
		pointCirclePaint.setStrokeWidth( pointCircleRadius );
		pointCirclePaint.setStrokeCap( Paint.Cap.ROUND );

		circlePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		circlePaint.setColor( Color.parseColor( "#259b24" ) );
		circlePaint.setStyle( Paint.Style.STROKE );
		circlePaint.setStrokeWidth( 3 );

		// 测手 竖着画 画笔
		testHandPaint = new Paint( TextPaint.ANTI_ALIAS_FLAG );
		testHandPaint.setColor( color[2] );
		testHandPaint.setTextSize( 25 );
		testHandPaint.setTextAlign( Paint.Align.RIGHT );
		testHandPaint.setTypeface( Typeface.DEFAULT );


		// 空间里面的数字
		numPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		numPaint.setTextSize( numSize );
		numPaint.setTextAlign( Paint.Align.CENTER );

		// 画文本文字
		textPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		textPaint.setTextSize( textSize );
		textPaint.setTextAlign( Paint.Align.CENTER );

		// 当在画气泡里面的数字
		bubbleNumPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		bubbleNumPaint.setTextSize( textSize );
		bubbleNumPaint.setTextAlign( Paint.Align.CENTER );
		bubbleNumPaint.setStyle( Paint.Style.FILL );


		bitmapPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		//		bitmapPaint.setStyle( Paint.Style.FILL_AND_STROKE );
		//		bitmapPaint.setColor( Color.parseColor( "#b5b5b5" ) );
		//		bitmapPaint.setStrokeWidth( 4 );

		matrix = new Matrix();
		path = new Path();


	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onDraw(final Canvas canvas) {
		super.onDraw( canvas );

		drawRectF();
		//		rect = new Rect( (int) innerPadding, (int) innerPadding, (int) (getMeasuredWidth() - 2 * innerPadding)  / 5, getMeasuredHeight() - (int) innerPadding );
//		getStaticLayout();
		float w = 0;
		if (curprogress >= 8){
			w = (getMeasuredWidth() - startX - innerPadding) / 8.5f * 8 + startX;
		}else {
			w = (getMeasuredWidth() - startX - innerPadding) / 8.5f * curprogress + startX;
		}
		//		float nw = (getMeasuredWidth() - 2 * innerPadding) * 3 / 10;
		float h = getMeasuredHeight();
		float yellow = (getMeasuredWidth() - startX - innerPadding) / 8.5f * 4f + startX;
		float red = (getMeasuredWidth() - startX - innerPadding) / 8.5f * 0f + startX;
		float blue = (getMeasuredWidth() - startX - innerPadding) / 8.5f * 6f + startX;
		float green = (getMeasuredWidth() - startX - innerPadding) / 8.5f * 8f + startX;
		float circle = (getMeasuredWidth() - startX - innerPadding) / 8.5f * 8.5f + startX;

		//画灰线段
		canvas.save();
		canvas.drawLine( startX, startY, getMeasuredWidth() - innerPadding, startY, lineGrayPaint );
		canvas.restore();


		if (curprogress >= 0 && curprogress < 4) {
			//画第一段 红线段
			canvas.save();
			canvas.drawLine( startX, startY, w, startY, lineRedPaint );
			canvas.restore();
			//画红色气泡
			canvas.save();
			path.reset();
			path.arcTo( w - h / 8, h / 16, w + h / 8, 5 * h / 16, -180, 180,false );
			path.quadTo( w + h /8 ,5 * h / 16,w,startY - linewidth / 2 );
			path.quadTo( w - h /8,5 * h / 16,w - h / 8,3 * h / 16 );
			path.close();
			canvas.drawPath( path, lineRedPaint );
			canvas.restore();


			// 画红色气泡里面的数字

			canvas.save();
			bubbleNumPaint.reset();
			bubbleNumPaint.setTextSize( dp2px( getContext(), 10 ) );
			bubbleNumPaint.setTextAlign( Paint.Align.CENTER );
			bubbleNumPaint.setStyle( Paint.Style.FILL );
			bubbleNumPaint.setColor( Color.parseColor( "#ffffff" ) );
			float width = bubbleNumPaint.measureText( String.valueOf( curprogress ) );
			float v = bubbleNumPaint.getFontMetrics().descent - bubbleNumPaint.getFontMetrics().ascent;
			float leading = bubbleNumPaint.getFontMetrics().leading;
			canvas.drawText( String.valueOf( curprogress ), w, 5 * h / 16 + leading, bubbleNumPaint );
			canvas.restore();

		} else if (curprogress < 6) {

			//画第一段 红线段
			canvas.save();
			canvas.drawLine( startX, startY, w, startY, lineRedPaint );
			canvas.restore();

			//画第二段 黄线段
			canvas.save();
			canvas.drawLine( yellow, startY, w, startY, lineYellowPaint );
			canvas.restore();
			//画黄色气泡
			canvas.save();
			path.reset();
			path.arcTo( w - h / 8, h / 16, w + h / 8, 5 * h / 16, -180, 180,false );
			path.quadTo( w + h /8 ,5 * h / 16,w,startY - linewidth / 2 );
			path.quadTo( w - h /8,5 * h / 16,w - h / 8,3 * h / 16 );
			path.close();
			canvas.drawPath( path, lineYellowPaint );
			canvas.restore();
			// 黄色气泡里面的数字
			canvas.save();
			bubbleNumPaint.reset();
			bubbleNumPaint.setTextSize( dp2px( getContext(), 10 ) );
			bubbleNumPaint.setTextAlign( Paint.Align.CENTER );
			bubbleNumPaint.setStyle( Paint.Style.FILL );
			bubbleNumPaint.setColor( Color.parseColor( "#ffffff" ) );
			float width = bubbleNumPaint.measureText( String.valueOf( curprogress ) );
			float v = bubbleNumPaint.getFontMetrics().descent - bubbleNumPaint.getFontMetrics().ascent;
			float abs = Math.abs( bubbleNumPaint.getFontMetrics().descent - bubbleNumPaint.getFontMetrics().bottom );
			//这里面的高度不起作用 有很大的疑问
			canvas.drawText( String.valueOf( curprogress ), w, 5 * h / 16, bubbleNumPaint );
			canvas.restore();

		} else if (curprogress < 8) {

			//画第一段 红线段
			canvas.save();
			canvas.drawLine( startX, startY, yellow, startY, lineRedPaint );
			canvas.restore();
			//画第二段 黄线段
			canvas.save();
			canvas.drawLine( yellow, startY, blue, startY, lineYellowPaint );
			canvas.restore();
			//画第三段 蓝线段
			canvas.save();
			canvas.drawLine( blue, startY, w, startY, lineBluePaint );
			canvas.restore();
			//画蓝色气泡
			canvas.save();
			path.reset();
			path.arcTo( w - h / 8, h / 16, w + h / 8, 5 * h / 16, -180, 180,false );
			path.quadTo( w + h /8 ,5 * h / 16,w,startY - linewidth / 2 );
			path.quadTo( w - h /8,5 * h / 16,w - h / 8,3 * h / 16 );
			path.close();
			canvas.drawPath( path, pointBluePaint );
			canvas.restore();
			// 画蓝色气泡里面的数字
			canvas.save();
			bubbleNumPaint.reset();
			bubbleNumPaint.setTextSize( dp2px( getContext(), 10 ) );
			bubbleNumPaint.setTextAlign( Paint.Align.CENTER );
			bubbleNumPaint.setStyle( Paint.Style.FILL );
			bubbleNumPaint.setColor( Color.parseColor( "#ffffff" ) );
			float width = bubbleNumPaint.measureText( String.valueOf( curprogress ) );
			float v = bubbleNumPaint.getFontMetrics().descent - bubbleNumPaint.getFontMetrics().ascent;
			float leading = bubbleNumPaint.getFontMetrics().leading;
			canvas.drawText( String.valueOf( curprogress ), w, 5 * h / 16 + leading, bubbleNumPaint );
			canvas.restore();
		} else  {

			//画第一段 红线段
			canvas.save();
			canvas.drawLine( startX, startY, yellow, startY, lineRedPaint );
			canvas.restore();

			//画第二段 黄线段
			canvas.save();
			canvas.drawLine( yellow, startY, blue, startY, lineYellowPaint );
			canvas.restore();

			//画第三段 蓝线段
			canvas.save();
			canvas.drawLine( blue, startY, green, startY, lineBluePaint );
			canvas.restore();

			//画第四段 绿线段
			canvas.save();
			canvas.drawLine( green, startY, circle, startY, lineGreenPaint );
			canvas.restore();

			//画绿色气泡
			canvas.save();
			path.reset();
			path.arcTo( w - h / 8, h / 16, w + h / 8, 5 * h / 16, -180, 180,false );
			path.quadTo( w + h /8 ,5 * h / 16,w,startY - linewidth / 2 );
			path.quadTo( w - h /8,5 * h / 16,w - h / 8,3 * h / 16 );
			path.close();


			canvas.drawPath( path, lineGreenPaint );
			//
			//			bubbleNumPaint.reset();
			//			bubbleNumPaint.setColor( Color.parseColor( "#ffffff" ));
			//			bubbleNumPaint.setTextSize( dp2px( getContext(),10 ));
			//			bubbleNumPaint.setTextAlign( Paint.Align.CENTER );
			//			bubbleNumPaint.setStyle( Paint.Style.FILL );
			//
			//			canvas.drawText( "4",w ,3*h/16,bubbleNumPaint);

			canvas.restore();

			// 画绿色里面的数字
			canvas.save();
			bubbleNumPaint.reset();
			bubbleNumPaint.setTextSize( dp2px( getContext(), 10 ) );
			bubbleNumPaint.setTextAlign( Paint.Align.CENTER );
			bubbleNumPaint.setStyle( Paint.Style.FILL );
			bubbleNumPaint.setColor( Color.parseColor( "#ffffff" ) );
			float width = bubbleNumPaint.measureText( String.valueOf( 8 ) );
			float v = bubbleNumPaint.getFontMetrics().descent - bubbleNumPaint.getFontMetrics().ascent;
			float leading = bubbleNumPaint.getFontMetrics().leading;
			canvas.drawText( "8", w, 5 * h / 16 + leading, bubbleNumPaint );

			canvas.restore();

		}

		//画红点
		canvas.save();
		canvas.drawPoint( startX, startY, pointRedPaint );
		canvas.restore();
		//画黄点
		canvas.save();
		canvas.drawPoint( yellow, startY, pointYellowPaint );
		canvas.restore();

		//画蓝点
		canvas.save();
		canvas.drawPoint( blue, startY, pointBluePaint );
		canvas.restore();
		//画绿点
		canvas.save();
		canvas.drawPoint( green, startY, pointGreenPaint );
		canvas.restore();
		// 画绿点加外圆
		canvas.save();
		canvas.drawPoint( circle, startY, pointCirclePaint );
		canvas.restore();
		// 画绿外圆
		canvas.save();
		canvas.drawCircle( circle, startY, radius, circlePaint );
		canvas.restore();

		// 画数字//画文字
		float v1 = textPaint.getFontMetrics().descent - textPaint.getFontMetrics().ascent;
		float v = numPaint.getFontMetrics().descent - numPaint.getFontMetrics().ascent;
		float leading = numPaint.getFontMetrics().leading;

		canvas.save();
		numPaint.setColor( color[0] );
		canvas.drawText( text1[0], startX, startY + v - 2 * leading, numPaint );
		textPaint.setColor( color[0] );
		canvas.drawText( text[0], startX, startY + v + v1 - 2 * leading, textPaint );
		canvas.restore();

		canvas.save();
		numPaint.setColor( color[1] );
		canvas.drawText( text1[1], yellow, startY + v - 2 * leading, numPaint );
		textPaint.setColor( color[1] );
		canvas.drawText( text[1], yellow, startY + v + v1 - 2 * leading, textPaint );
		canvas.restore();

		canvas.save();
		numPaint.setColor( color[2] );
		canvas.drawText( text1[2], blue, startY + v - 2 * leading, numPaint );
		textPaint.setColor( color[2] );
		canvas.drawText( text[2], blue, startY + v + v1 - 2 * leading, textPaint );
		canvas.restore();

		canvas.save();
		numPaint.setColor( color[3] );
		canvas.drawText( text1[3], green, startY + v - 2 * leading, numPaint );
		textPaint.setColor( color[3] );
		canvas.drawText( text[3], green, startY + v + v1 - 2 * leading, textPaint );
		canvas.restore();


		//画图片
		canvas.save();
		//		matrix.reset();
		//		//		float hr = getMeasuredHeight() * 8/bitmap.getHeight()/10;
		//		//		float wr = (float) getMeasuredWidth() * 3/bitmap.getWidth()/10;
		//		matrix.preScale( 0.5f, 0.5f, innerPadding, (float) getMeasuredHeight() / 2 - innerPadding/2 );
		//		canvas.concat( matrix );
		//		canvas.drawBitmap( bitmap, 0, 0, bitmapPaint );
		canvas.drawBitmap( bitmap, null, rectF, bitmapPaint );
		canvas.restore();

		// 画出测手
		canvas.save();

//		canvas.drawText( "测",startX - innerPadding - textSize,startY,testHandPaint );
//		canvas.drawText( "手",startX - innerPadding - textSize,startY + textSize ,testHandPaint );
		canvas.restore();


	}

	/**
	 * @return 返回指定的文字高度
	 */
	public float getFontHeight(Paint paint) {
		Paint.FontMetrics fm = paint.getFontMetrics();
		//文字基准线的下部距离-文字基准线的上部距离 = 文字高度
		return fm.bottom - fm.top;
	}

	// 画出 bitmap 的控件
	private void drawRectF() {
		rectF = new RectF( innerPadding, 3 * innerPadding / 2, startX - innerPadding, getMeasuredHeight() - 3 * innerPadding / 2 );
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int width = getWidthMySize( widthMeasureSpec );
		int height = getHeightMySize( heightMeasureSpec );


		startX = 2 * innerPadding + (float) bitmap.getWidth() / 2;
		startY = height / 2;
		setMeasuredDimension( width , height );

	}

	private int getHeightMySize(int heightMeasureSpec) {

          // 达标字数的高度
		float fontHeight = getFontHeight( numPaint );
		// 达标文字高度
		float fontHeight1 = getFontHeight( textPaint );
		// 线段的宽度

		// 气球的高度
		float lengt = startY - linewidth/2 - (float) getMeasuredHeight() / 16;
		// 总的高度
		float he = fontHeight + fontHeight1 + lengt + dp2px( getContext() ,12 ) + linewidth;
/*
		int mySize = 0;
		int mode = MeasureSpec.getMode( heightMeasureSpec );
		int size = MeasureSpec.getSize( heightMeasureSpec );
		//注意处理一下Padding，把Padding值也赋给想要设置的子View的大小
		int paddingTop = getPaddingTop();
		int paddingBottom = getPaddingBottom();

		switch (mode) {
			//未确定模式下返回尽可能容纳View的尺寸
			case MeasureSpec.UNSPECIFIED://如果没有指定大小，就设置为默认大小
				mySize = paddingTop + paddingBottom + dp2px( getContext(),55 );
				break;
			case MeasureSpec.AT_MOST://如果测量模式是最大取值为size
				//我们将大小取最大值,你也可以取其他值
				//AT_MOST下返回父View限制值和View尽可能大尺寸之间的最小值
				mySize = paddingBottom + paddingTop + dp2px( getContext(), 55 );
				break;
			case MeasureSpec.EXACTLY://如果是固定的大小，那就不要去改变它 //精确模式下返回具体值
				mySize = size + paddingTop + paddingBottom;
				break;
			default:
				break;
		}*/


		return (int) he;
	}


	private int getWidthMySize(int widthmeasureSpec) {
		int mySize = 0;
		//取得父View或ViewGroup分配的默认为子View的大小和模式
		int mode = MeasureSpec.getMode( widthmeasureSpec );
		int size = MeasureSpec.getSize( widthmeasureSpec );
		//注意处理一下Padding，把Padding值也赋给想要设置的子View的大小
		int paddingLeft = getPaddingLeft();
		int paddingRight = getPaddingRight();

		switch (mode) {
			//未确定模式下返回尽可能容纳View的尺寸
			case MeasureSpec.UNSPECIFIED://如果没有指定大小，就设置为默认大小
				mySize = paddingLeft + paddingRight + dp2px( getContext(),340 );

				break;

			case MeasureSpec.AT_MOST://如果测量模式是最大取值为size
				//我们将大小取最大值,你也可以取其他值
				//AT_MOST下返回父View限制值和View尽可能大尺寸之间的最小值
				mySize = paddingLeft + paddingRight +dp2px( getContext(),340 );

				break;
			case MeasureSpec.EXACTLY://如果是固定的大小，那就不要去改变它 //精确模式下返回具体值
				mySize = size + paddingLeft + paddingRight;
				break;
			default:
				break;
		}


		return mySize;
	}


	public void setProgress(int curprogress) {
		this.curprogress = curprogress;
		postInvalidate();
	}

	private int dp2px(Context context, int value) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (density * value + 0.5f);
	}

	private int sp2px(Context context, int value) {
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (value / scaledDensity + 0.5f);
	}


}
