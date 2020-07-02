package com.example.workcellsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.head_page_fragment );
		getSupportActionBar().setTitle( "全自动全封闭工程化细胞培养系统" );
		//        getSupportActionBar().setDisplayUseLogoEnabled(true);
		//        getActionBar().setDisplayShowHomeEnabled(true);
		//        getActionBar().setHomeButtonEnabled(true);
		//        getActionBar().setDisplayHomeAsUpEnabled(true);


		getSupportActionBar().setIcon( R.mipmap.user );
		getSupportActionBar().setDisplayUseLogoEnabled( true );
		getSupportActionBar().setDisplayShowHomeEnabled( true );
		View actionbarLayout = getLayoutInflater().inflate( R.layout.content_actionbar_layout, null );
		getSupportActionBar().setDisplayShowCustomEnabled( true );
		getSupportActionBar().setCustomView( actionbarLayout );
		getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );

		//        getSupportActionBar().

		//        setIcon();
		//        getActionBar().setIcon(R.mipmap.user);
		Button button = findViewById( R.id.button );
		Button button2 = findViewById( R.id.button2 );
		Button button3 = findViewById( R.id.button4 );
		button.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity( new Intent( getApplicationContext(), SearchActivity.class ) );
			}
		} );

		button2.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		} );

		button3.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		} );


	}


}
