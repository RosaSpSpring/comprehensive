package com.cn.yijia.recyclerview.netdata;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxm
 * @version 2020/6/17-17:05
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class NetDataBean  implements Parcelable{
	private int statusCode;
	private String msg;
	private ArrayList<DataBean> data;
	private long timestamp;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<DataBean> getData() {
		return data;
	}

	public void setData(ArrayList<DataBean> data) {
		this.data = data;
	}

	public static Creator<NetDataBean> getCREATOR() {
		return CREATOR;
	}

	protected NetDataBean(Parcel in) {
		statusCode = in.readInt();
		msg = in.readString();
		data = in.createTypedArrayList( DataBean.CREATOR );
	}

	public static final Creator<NetDataBean> CREATOR = new Creator<NetDataBean>() {
		@Override
		public NetDataBean createFromParcel(Parcel in) {
			return new NetDataBean( in );
		}

		@Override
		public NetDataBean[] newArray(int size) {
			return new NetDataBean[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt( statusCode );
		dest.writeString( msg );
		dest.writeTypedList( data );
	}
}
