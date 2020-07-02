package com.cn.yijia.recyclerview.histroyobservernote;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author lxm
 * @version 2020/5/13-16:39
 * @des 设置Recycleview之间的item空隙
 * @updateDes 设置Recycleview之间的item空隙
 * @updateAuthor $
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
	private static final String TAG = SpaceItemDecoration.class.getSimpleName();
	private int space;
	public SpaceItemDecoration(int space) {
		this.space = space;
	}

	@Override
	public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
		outRect.left = space;
		outRect.right = space;
		outRect.bottom = space ;
		outRect.top = space ;
	}
}
