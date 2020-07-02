package com.cn.yijia.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author lxm
 * @version 2020/6/10-15:44
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{
	private Context mContext;
	private List<String> names = new ArrayList<>(  );
	public RVAdapter(Context context) {
		context = mContext;
	}

	@NonNull
	@Override
	public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = View.inflate( mContext, R.layout.rv_layout, null );
		return new ViewHolder( itemView );
	}

	@Override
	public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
		holder.mTextView.setText( names.get( position ) );
	}

	@Override
	public int getItemCount() {
		return names.size();
	}

	// 添加数据
	public void addData(List<String> nas){
		if (names.size() == 0){
			names.addAll( nas );
		}else {
			names.clear();
			names.addAll( nas );
		}
	}

	public interface  OnItemClickListener{
		void onItemClickListener(View view ,String name);
	}
	private OnItemClickListener mOnItemClickListener = null;

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}

	class ViewHolder extends RecyclerView.ViewHolder{
		private TextView mTextView;
		public ViewHolder(@NonNull View itemView) {
			super( itemView );
			mTextView = itemView.findViewById( R.id.tv_item );
		}
	}
}
