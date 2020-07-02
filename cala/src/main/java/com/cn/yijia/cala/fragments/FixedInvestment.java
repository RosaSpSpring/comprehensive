package com.cn.yijia.cala.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.yijia.cala.R;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

/**
 * @author lxm
 * @version 2020/6/29-16:46
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class FixedInvestment extends Fragment {
	private static final String TAG = FixedInvestment.class.getSimpleName();
	private Context mContext;
	private View rootView;
	private AppCompatEditText investmentYear;
	private AppCompatEditText yield;
	private AppCompatEditText storeMonth;
	private AppCompatEditText finalIncome;
	private AppCompatButton mBtnClear;
	private AppCompatButton mBtnCalc;


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		mContext = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (rootView == null)
			rootView = inflater.inflate( R.layout.fiexd_investment, container, false );
		return rootView;
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated( savedInstanceState );
        initView();
		mBtnCalc.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				initView();
				Integer invests = Integer.valueOf( investmentYear.getText().toString().trim() );
				Float yields = Float.valueOf( yield.getText().toString().trim() )/100;
				Long stores = Long.valueOf( storeMonth.getText().toString().trim() );

				if (!invests.equals( "" )&&!yields.equals( "" )&&!stores.equals( "" )){
					// 使用 BigDecimal 计算浮点精度的数据 double
					double fixedInvestmentIncom = getFixedInvestmentIncom( invests, yields, stores );
					finalIncome.setText( String.valueOf( fixedInvestmentIncom ) );

				}

			}
		} );
		mBtnClear.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				investmentYear.setText( "" );
				yield.setText( "" );
				storeMonth.setText( "" );
				finalIncome.setText( "" );
			}
		} );


	}

	// 计算收益的最终结果方法
	public double getFixedInvestmentIncom(int invest,float yield,long storemoney ){
		//定投最后期末的利本合计公式是:
		//M=a(1+x)[-1+(1+x)^n]/x;
		//其中: M代表预期收益, a代表每期定投金额, x代表收益率, n代表定投期数。
		//封装函数如下:
		//# 定投计算公式,如果用需要输入参数
		//def dingtou(a,x,n):
		//    """定投收益的计算公式为：M=a(1+x)[-1+(1+x)^n]/x;
		//    其中
		//    M代表预期收益,
		//    a代表每期定投金额,
		//    x代表收益率,
		//    n代表定投期数。
		//    假设用户每月定投金额为300元,一年也就是3600元,年收益率为15%,
		//    定投期限为35年,则可以计算出收益为:
		//    3600(1+15%)[-1+(1+15%)^35]/15%=3648044元

		//    a=float(a)
		//    x = float(x) / 100
		//    n = int(n)
		//    M = a*(1 + x)*(-1 + (1 + x) ** n) / x
		//    return  round(M,2)

		return 12*storemoney*(1 + yield)*(-1 + Math.pow( (1 + yield),invest ) )/yield;

	}


	private void initView() {
		investmentYear = rootView.findViewById( R.id.year_investment );
		yield = rootView.findViewById( R.id.yield );
		storeMonth = rootView.findViewById( R.id.store_month);
		finalIncome = rootView.findViewById( R.id.final_income );
		mBtnClear = rootView.findViewById( R.id.btn_clear );
		mBtnCalc = rootView.findViewById( R.id.btn_calc );
	}
}
