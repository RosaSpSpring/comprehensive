package com.cn.yijia.recyclerview.netdata;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author lxm
 * @version 2020/6/17-15:56
 * @des
 * @updateDes
 * @updateAuthor $
 */
/*
public class DataBean implements Parcelable {
	private int id;
	private int machine_id;
	private int type;
	private double value;
	private long create_time;


	protected DataBean(Parcel in) {
		id = in.readInt();
		machine_id = in.readInt();
		type = in.readInt();
		value = in.readDouble();
		create_time = in.readLong();
	}

	public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
		@Override
		public DataBean createFromParcel(Parcel in) {
			return new DataBean( in );
		}

		@Override
		public DataBean[] newArray(int size) {
			return new DataBean[size];
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt( id );
		dest.writeInt( machine_id );
		dest.writeInt( type );
		dest.writeDouble( value );
		dest.writeLong( create_time );
	}
}
*/


/**
 * @author lxm
 * @version 2020/6/17-15:56
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class DataBean implements Parcelable {
	private int id;
	private int machine_id;
	private int type;
	private double value;
	private String create_time;


	protected DataBean(Parcel in) {
		id = in.readInt();
		machine_id = in.readInt();
		type = in.readInt();
		value = in.readDouble();
		create_time = in.readString();
	}

	public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
		@Override
		public DataBean createFromParcel(Parcel in) {
			return new DataBean( in );
		}

		@Override
		public DataBean[] newArray(int size) {
			return new DataBean[size];
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt( id );
		dest.writeInt( machine_id );
		dest.writeInt( type );
		dest.writeDouble( value );
		dest.writeString( create_time );
	}
}

