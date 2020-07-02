package com.example.ble;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.Cache;

/**
 * @author lxm
 * @version 2020/6/3-10:13
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class ShotScreen extends AppCompatActivity {
	private static final String TAG = ShotScreen.class.getSimpleName();
	private ImageView mImageView1;


	Bitmap mScrollScreenShot;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.layout_single_test_hand_report );
		mImageView1 = findViewById(R.id.test_hand_hist);

		//  长截图
		View view = this.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap screenShotBm = view.getDrawingCache();
		view.destroyDrawingCache();//注意,这里需要释放缓存
//        mImageView.setImageBitmap( screenShotBm );
		mImageView1.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ShotScreen.this,"miv", Toast.LENGTH_SHORT).show();
//				mImageView1.setImageBitmap( screenShotBm );
//				mImageView1.setBackground( getResources().getDrawable( R.drawable.arrow_left_black_24dp ) );
				Bitmap bitmap = sheareScroll();
				mImageView1.setImageBitmap( bitmap );
			}
		} );


	}
	public Bitmap sheareScroll() {
		ViewGroup rootView = findViewById(android.R.id.content);
		View inflate = LayoutInflater.from(ShotScreen.this).inflate(R.layout.layout_single_test_hand_report, null);
		rootView.addView(inflate, 0);
		final ScrollView scrollView = inflate.findViewById(R.id.scrollView);
		inflate.post(new Runnable() {
			@Override
			public void run() {
				mScrollScreenShot = getScrollScreenShot(scrollView);

			}
		});

		return mScrollScreenShot;
	}
	public Bitmap getScrollScreenShot(ScrollView view) {
		if (null != view) {
			int height = 0;
			//正确获取ScrollView
			for (int i = 0; i < view.getChildCount(); i++) {
				height += view.getChildAt(i).getHeight();
			}
			Log.e(TAG, "child个数" + view.getChildCount());
			if (height > 0) {
				//创建保存缓存的bitmap
				Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), height, Bitmap.Config.RGB_565);
				//可以简单的把Canvas理解为一个画板 而bitmap就是块画布
				Canvas canvas = new Canvas(bitmap);
				//把view的内容都画到指定的画板Canvas上
				view.draw(canvas);
				return bitmap;
			}
		}

		return null;
	}





	/**
	 * 截取scrollview的屏幕
	 **/
	public static Bitmap getScrollViewBitmap(ScrollView scrollView) {
		int h = 0;
		Bitmap bitmap;
		for (int i = 0; i < scrollView.getChildCount(); i++) {
			h += scrollView.getChildAt(i).getHeight();
		}
		// 创建对应大小的bitmap
		bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
				Bitmap.Config.RGB_565);
		final Canvas canvas = new Canvas(bitmap);
		scrollView.draw(canvas);
		return bitmap;
	}

	/**
	 * 压缩图片
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image, int quality) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		image.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		int options = 100;
		// 循环判断如果压缩后图片是否大于100kb,大于继续压缩
		//        while (baos.toByteArray().length / 1024 > 30) {
		// 重置baos
		baos.reset();
		// 这里压缩options%，把压缩后的数据存放到baos中
		image.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		// 每次都减少10
		//            options -= 10;
		//        }
		// 把压缩后的数据baos存放到ByteArrayInputStream中
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		// 把ByteArrayInputStream数据生成图片
		return BitmapFactory.decodeStream(isBm, null, null);
	}

	//----点分享朋友弹出PopupWindow
	PopupWindow window;
	ImageView iv_wx;

	/*private void showPopupWindow() {
		View contentView = LayoutInflater.from(ShotScreen.this).inflate(R.layout.popupdow_share, null, false);
		window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		window.setOutsideTouchable(true);
		window.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				backgroundAlpha(ShotScreen.this, 1.0f);
			}
		});
		backgroundAlpha(ShotScreen.this, 0.5f);//0.0-1.0
		iv_wx = contentView.findViewById(R.id.iv_image1);
		iv_fiend = contentView.findViewById(R.id.iv_image2);
		im_delt = contentView.findViewById(R.id.im_delt);
		rl_delt = contentView.findViewById(R.id.rl_delt);

		window.showAtLocation(LayoutInflater.from(ShotScreen.this).inflate(R.layout.activity_group_bulk, null), Gravity.BOTTOM, 0, 0);

		iv_wx.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				wechatShare(0);
				window.dismiss();
				backgroundAlpha(ShotScreen.this, 1f);
			}
		});
		iv_fiend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				wechatShare(1);
				window.dismiss();
				backgroundAlpha(ShotScreen.this, 1f);
			}
		});
		rl_delt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				window.dismiss();
				backgroundAlpha(ShotScreen.this, 1f);
			}
		});
	}*/

	/*private void wechatShare(int flag) {
		String baseUrl = Cache.exist(CacheKey.BASE_URL) ? Cache.get(CacheKey.BASE_URL).toString() : URL.BASE_URL;
		// 分享图片
		Bitmap mBitmap = getScrollViewBitmap(scrollView);
		//        Bitmap mBitmap = CodeUtils.createImage(data.getCodeUrl(), 200, 200, BitmapFactory.decodeResource(getResources(), R.drawable.logo));
		//初始化WXImageObject和WXMediaMessage对象
		WXImageObject imageObject = new WXImageObject(compressImage(mBitmap, 80));
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = imageObject;
		//        mBitmap = getScrollViewBitmap(scrollView);
		msg.setThumbImage(Bitmap.createScaledBitmap(mBitmap, 200, 200, true));

		mBitmap.recycle();
		//构造一个Req
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
		MyApp.api.sendReq(req);

	}*/
}
