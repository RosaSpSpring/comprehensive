package com.cn.yijia.fortest;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lxm
 * @version 2020/5/21-16:43
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class MainActivity3 extends AppCompatActivity {
	private Button mButton;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main3 );
		mButton  = findViewById(R.id.button3);
		mButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity( new Intent( MainActivity3.this,MainActivity.class ) );
			}
		} );
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 是否触发按键为back键
		if (keyCode == KeyEvent.KEYCODE_BACK){
			onBackPressed();
			startActivity( new Intent( MainActivity3.this,MainActivity.class ) );
			return true;
		}else {
			return super.onKeyDown( keyCode, event );
		}

	}
}
