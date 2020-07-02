package com.example.workcellsystem.histroyobservernote;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author lxm
 * @version 2020/6/11-9:37
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class ImageBean implements Parcelable {

	private String date;
	private Drawable mDrawable;

	public ImageBean(String date, Drawable drawable) {
		this.date = date;
		mDrawable = drawable;
	}

	protected ImageBean(Parcel in) {
		date = in.readString();
	}

	public static final Creator<ImageBean> CREATOR = new Creator<ImageBean>() {
		@Override
		public ImageBean createFromParcel(Parcel in) {
			return new ImageBean( in );
		}

		@Override
		public ImageBean[] newArray(int size) {
			return new ImageBean[size];
		}
	};

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Drawable getDrawable() {
		return mDrawable;
	}

	public void setDrawable(Drawable drawable) {
		mDrawable = drawable;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString( date );
	}
}
