package com.cn.yijia.recyclerview.histroyobservernote;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.yijia.recyclerview.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author lxm
 * @version 2020/6/11-9:40
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class ObserNoteAdapter extends RecyclerView.Adapter<ObserNoteAdapter.ViewHolder> {
	private static final String TAG = ObserNoteAdapter.class.getSimpleName();
	private Context mContext;
	private List<ImageBean> mImageBeans;

	public ObserNoteAdapter(Context context, List<ImageBean> imageBeans) {
		mContext = context;
		mImageBeans = imageBeans;
	}

	@NonNull
	@Override
	public ObserNoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = View.inflate( mContext, R.layout.layout_observer_note_item,null );
		return new ViewHolder( itemView );
	}

	@Override
	public void onBindViewHolder(@NonNull ObserNoteAdapter.ViewHolder holder, final int position) {
		final ImageBean imageBean = mImageBeans.get( position );
		holder.mTextView.setText( imageBean.getDate() );
		holder.mImageView.setBackground( imageBean.getDrawable() );
		holder.itemView.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnItemClickListener != null){
					mOnItemClickListener.onItemClickListener( v,  position ,mImageBeans.get( position ));
				}
			}
		} );
	}

	@Override
	public int getItemCount() {
		return mImageBeans.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		private ImageView mImageView;
		private TextView mTextView;


		ViewHolder(@NonNull View itemView) {
			super( itemView );
			mTextView = itemView.findViewById( R.id.tv_obs_not_item );
			mImageView = itemView.findViewById( R.id.iv_obs_not_item );

		}
	}
	public interface OnItemClickListener{
		void onItemClickListener(View view,int position ,ImageBean imageBean);
	}
	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}
}
