package com.cn.yijia.recyclerview.netdata;

import com.cn.yijia.recyclerview.netdata.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author lxm
 * @version 2020/6/17-15:44
 * @des 温度
 * @updateDes 温度
 * @updateAuthor $
 */
public interface NetData {
	@GET(Constants.ALLDATA)
	Call<ResponseBody> getAllData();
}
