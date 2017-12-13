package testcase.loginAuthentication;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.GetSmsApi;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ParamData;
import sign.SignData;
import sign.SignUtils;





public class TestCase001GetKaptche {

	@Test
	public void testGetKaptcha() throws IOException{
		SignData signD=new SignData();
		RequestManager rem=new RequestManager();
		SignUtils signU=new SignUtils();
		ParamData paramD=new ParamData();
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("method", paramD.getKaptchaMethod());
		paramsMap.put("clientId", paramD.clientId());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.getKaptchaSign());
		paramsMap.put("app_key", paramD.appKey());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Assert.assertEquals(resp.code(), 200, "获取图形码code码错误");
		
	}


}
