package com.cn.yijia.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cn.yijia.recyclerview.histroyobservernote.HistoryObserverNote;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lxm
 * @version 2020/6/10-14:53
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class MainActivity3 extends AppCompatActivity {
	private static final String TAG = "叁";
	private Button mButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		Log.e(TAG, "onCreate" );
		mButton =findViewById(R.id.btn);
		mButton.setText( "3" );
		mButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				startActivity( new Intent( MainActivity3.this, HistoryObserverNote.class ) );
				MainActivity3.this.finish();

			}
		} );
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.e(TAG, "onRestart" );
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e(TAG, "onResume" );
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e(TAG, "onPause" );
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		Log.e(TAG, "onPostResume" );
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.e(TAG, "onStart" );
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e(TAG, "onStop" );
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy" );
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState( outState );
		Log.e(TAG, "onSaveInstanceState" );
	}
}
