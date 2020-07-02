package com.cn.yijia.fortest;

import java.io.Serializable;

/**
 * @author lxm
 * @version 2020/5/15-15:30
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class Value implements Serializable {




	private  String values =      "\n\t\t检测显示任脉有失衡（或者有偏差）并伴症状。\n\t\t任脉与六阴经有联系，称为“阴脉之海”，具有调节全身诸阴经经气的作用。同时与督脉形成一个气血升降循环，所以养生建议与督脉一致：\n" +
                                "\t•\t按每天中的时辰安排好日常生活，尽量做到有规律有序。\n" +
                                "\t•\t根据一年中24节气安排饮食，食用时令蔬菜瓜果，尽量不食用反季节的蔬菜瓜果。\n" +
                                "\t•\t选择散步、太极拳等运动。注意保暖，保持心情越快，心态积极。\n" +
                                "\t•\t避免频繁的两性生活，特别是酒后的性行为。\n" +
                                "\t•\t日常注意穿袜，避免脚部、小腿肚、腹部及腰部着凉，不可久坐，夏天不要贪凉，特别是女孩不要穿露脐装，\n" +
                                "\t•\t用系统提示的任脉保健穴进行反复刺激，睡前按揉腹部，促进任脉气血运行。";
	public  String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}
}
