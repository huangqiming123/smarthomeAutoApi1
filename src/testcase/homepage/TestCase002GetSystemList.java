package testcase.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dataformate.SystemListApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase002GetSystemList {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> systemList=new ArrayList<String>();
	@Test
	public void testSystemList() throws JsonSyntaxException, IOException{
		SystemListApi vGsonValue=this.systemList(paramD.token(), signU.getTimeStamp(), signD.getSystemListSign(), paramD.appKey(), paramD.systemListMethod(), paramD.systemListPageNum(), paramD.systemListPageSize());
		Assert.assertEquals(vGsonValue.code, 0,"systemlist:code码错误");
	}
	private SystemListApi systemList(String accessToken,String timestamp,String sign,String app_key,String method,String pageNum,String pageSize) throws JsonSyntaxException, IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", accessToken);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("sign", sign);
		paramsMap.put("app_key", app_key);
		paramsMap.put("method", method);
		paramsMap.put("pageNum", pageNum);
		paramsMap.put("pageSize", pageSize);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson=new Gson();
		SystemListApi vGsonValue1=vGson.fromJson(resp.body().string(), SystemListApi.class);
		return vGsonValue1;
	}

}
