package testcase.loginAuthentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.LoginApi;
import dataformate.RefreshTokenApi;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ParamData;
import sign.SignData;
import sign.SignUtils;

public class TestCase004RefrehToken {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	
	@Test
	public void testRefrehToken() throws IOException{
		RefreshTokenApi vGsonValue=this.testRefreshToken1(paramD.token(), signU.getTimeStamp(), signD.getRefershTokenSign(), paramD.appKey(), paramD.refreshTokenMethod());
		Assert.assertEquals(vGsonValue.code, 0,"刷新token:code码错误");
		Assert.assertEquals(vGsonValue.message, "操作成功","刷新token:message错误");
		Logger.getLogger(vGsonValue.message);
	}
	private RefreshTokenApi testRefreshToken1(String accessToken,String timestamp,String sign,String app_key,String method) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", accessToken);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("sign", sign);
		paramsMap.put("app_key", app_key);
		paramsMap.put("method", method);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson = new Gson();
		RefreshTokenApi vGsonValue1 = vGson.fromJson(resp.body().string(), RefreshTokenApi.class);
		return vGsonValue1;
	}

}
