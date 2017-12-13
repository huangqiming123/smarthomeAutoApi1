package testcase.me;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.OauthTokenQiniuApi;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import sign.SignData;
import sign.SignUtils;

public class TestCase002OauthTokenQiniu {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	//ComData comD=new ComData();
	@Test
	public void testOauthTokenQiniu() throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.oauthTokenQiniuSign());
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.oauthTokenQiniuMethod());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson=new Gson();
		OauthTokenQiniuApi vGsonValue1=vGson.fromJson(resp.body().string(), OauthTokenQiniuApi.class);
		Assert.assertEquals(vGsonValue1.code, 0,"获取七牛云上传tokencode码错误");
		Assert.assertEquals(vGsonValue1.message, "获取七牛token成功","获取七牛云上传tokenmessage错误");
		System.out.println(vGsonValue1.result);
	}

}
