package com.example.workcellsystem;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.workcellsystem.histroyobservernote.CustomEditText;
import com.google.android.material.textview.MaterialTextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SearchViewActivity extends AppCompatActivity {
private static final String TAG = SearchViewActivity.class.getSimpleName();
    private MaterialTextView startTime;
    private MaterialTextView endTime;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_cell);

        imm = (InputMethodManager)this.getSystemService( Context.INPUT_METHOD_SERVICE);
        initView();
        initDataPicker();


        Button resetButton = findViewById(R.id.button6);
        Button searchButton = findViewById(R.id.button7);
        CustomEditText editText = findViewById(R.id.editText5);
        CustomEditText editText2 = findViewById(R.id.editText6);
        CustomEditText editText3 = findViewById(R.id.editText7);


        resetButton.setOnClickListener(v -> {
            editText.getText().clear();
            editText2.getText().clear();
            editText3.getText().clear();
        });

        searchButton.setOnClickListener(v -> {
            if(editText.getText()!=null && editText2.getText()!=null && editText3.getText()!=null) {
//                startActivity();


            } else {
                Toast.makeText(getApplicationContext(), "文本框不能为空", Toast.LENGTH_LONG).show();
            }
        });



        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    /*
     * 日期选择器
     */
    private void initDataPicker() {

        startTime.setOnClickListener( v -> {
            //时间选择器
            initDateListener( startTime );
        } );
        endTime.setOnClickListener( v -> initDateListener( endTime ) );


    }

    private void initDateListener(final MaterialTextView time) {
        //隐藏软键盘 //
        imm.hideSoftInputFromWindow( time.getWindowToken(), 0 );
        // 选中事件回调
        TimePickerView pvTime = new TimePickerBuilder( SearchViewActivity.this, (date, v) -> {
            time.setText( new SimpleDateFormat( "yyyy-MM-dd", Locale.CHINA ).format( date ) );
        } )
                .setCancelText( getResources().getString( R.string.pickerview_cancel ) ) // 取消
                .setCancelColor( getResources().getColor( R.color.cancel,null ) )// 取消色 与下面按钮色对应
                .setSubmitText( getResources().getString( R.string.pickerview_submit ) ) // 确定
//                .setSubmitColor( getResources().getColor( R.color.submit ,null) )   // 确定色，与下面按钮色对应
//                .setTitleBgColor( getResources().getColor( R.color.color_theme,null ) ) // 标题 背景色 主题色
//                .setTitleText( getResources().getString( R.string.date_pickup ) ) // 标题 label 日期选择
                .isDialog( true ) // 弹出方式
                .build();
        // pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况）
        // 此项可以在弹出选择器的时候重新设置当前时间，
        // 避免在初始化之后由于时间已经设定，
        // 导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void initView() {
        startTime = findViewById( R.id.editText9 );
        endTime = findViewById( R.id.editText10 );

        startTime.setHint( new SimpleDateFormat( "yyyy-MM-dd", Locale.CHINA ).format( new Date() ) );
        endTime.setHint( new SimpleDateFormat( "yyyy-MM-dd", Locale.CHINA ).format( new Date() ) );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
