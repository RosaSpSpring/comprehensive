package com.cn.yijia.fortest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lxm
 * @version 2020/5/19-10:07
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class MainActivity2 extends AppCompatActivity {
	private static final String TAG = MainActivity2.class.getSimpleName();
	private Button mButton;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main2 );
		mButton = findViewById(R.id.button2);
		mButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity( new Intent( MainActivity2.this,MainActivity3.class ) );
			}
		} );
	}
}
