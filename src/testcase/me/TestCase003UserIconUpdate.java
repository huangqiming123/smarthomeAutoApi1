package testcase.me;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.UserIconUpdateApi;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ParamData;
import sign.SignData;
import sign.SignUtils;

public class TestCase003UserIconUpdate {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	@Test
	public void testUserIconUpdate() throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.userIconUpdateSign());
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.userIconUpdateMethod());
		paramsMap.put("iconUrl", paramD.userIconUpdateIconUrl());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		UserIconUpdateApi vGsonValue1=vGson.fromJson(resp.body().string(), UserIconUpdateApi.class);
		Assert.assertEquals(vGsonValue1.code, 0,"修改头像code码错误");
		Assert.assertEquals(vGsonValue1.message, "操作成功","修改头像message错误");
		System.out.println(vGsonValue1.result);
	}

}
