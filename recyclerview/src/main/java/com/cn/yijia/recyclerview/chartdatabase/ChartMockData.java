package com.cn.yijia.recyclerview.chartdatabase;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author lxm
 * @version 2020/6/28-10:51
 * @des
 * @updateDes
 * @updateAuthor $
 */
@Entity(indexes = {@Index(value = "value,create_time,last_require_time",unique = true)})
public class ChartMockData {
	@Id(autoincrement = true)
	private Long id;

	private int machine_id;
	private int type;
	@NotNull
	private double value;
	private String create_time;
	private String last_require_time;
	@Generated(hash = 1114776155)
	public ChartMockData(Long id, int machine_id, int type, double value, String create_time,
			String last_require_time) {
		this.id = id;
		this.machine_id = machine_id;
		this.type = type;
		this.value = value;
		this.create_time = create_time;
		this.last_require_time = last_require_time;
	}
	@Generated(hash = 1849165111)
	public ChartMockData() {
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
	public String getCreate_time() {
		return this.create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_require_time() {
		return this.last_require_time;
	}
	public void setLast_require_time(String last_require_time) {
		this.last_require_time = last_require_time;
	}

	

}
