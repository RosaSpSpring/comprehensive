package com.cn.yijia.recyclerview.chartdatabase;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author lxm
 * @version 2020/6/28-16:39
 * @des
 * @updateDes
 * @updateAuthor $
 */
@Entity(indexes = {@Index(value = "value,create_time",unique = true)})
public class ChartData {
	@Id(autoincrement = true)
	private Long id;
	private int machine_id;
	private int type;
	@NotNull
	private double value;
	private long create_time;
	@Generated(hash = 1347575873)
	public ChartData(Long id, int machine_id, int type, double value,
			long create_time) {
		this.id = id;
		this.machine_id = machine_id;
		this.type = type;
		this.value = value;
		this.create_time = create_time;
	}
	@Generated(hash = 1007808282)
	public ChartData() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getMachine_id() {
		return this.machine_id;
	}
	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}
	public int getType() {
		return this.type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getValue() {
		return this.value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public long getCreate_time() {
		return this.create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}


}
