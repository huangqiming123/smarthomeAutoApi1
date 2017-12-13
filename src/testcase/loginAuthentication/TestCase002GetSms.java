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

public class TestCase002GetSms {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();

	@Test
	public  void testGetSmsFailWithIdenCode() throws IOException{
		GetSmsApi vGsonValue=this.testGetSms(paramD.getSmsMethod(), paramD.clientId(), signU.getTimeStamp(), signD.getSmsSignError(), paramD.appKey(), paramD.account(), paramD.captchaRight());
		Assert.assertEquals(vGsonValue.code, 1001,"idenCodeError:code码错误");
		Assert.assertEquals(vGsonValue.message, "缺少签名参数或签名无效","idenCodeError:message错误");
		}
	
	@Test
	public  void testGetSmsFailWithInviCode() throws IOException{
		GetSmsApi vGsonValue=this.testGetSms(paramD.getSmsMethod(), paramD.clientId(), signU.getTimeStamp(), signD.getSmsSignFail(), paramD.appKey(), paramD.account(), paramD.captchaRight());
		Assert.assertEquals(vGsonValue.code, 1001,"idenCodeError:code码错误");
		Assert.assertEquals(vGsonValue.message, "缺少签名参数或签名无效","idenCodeError:message错误");
		}
	
	@Test
	public  void testGetSmsSuccess() throws IOException{
		GetSmsApi vGsonValue=this.testGetSms(paramD.getSmsMethod(), paramD.clientId(), signU.getTimeStamp(), signD.getSmsSignSuccess(), paramD.appKey(), paramD.account(), paramD.captchaRight());
		Assert.assertEquals(vGsonValue.code, 0,"获取验证码success:code码错误");
		Assert.assertEquals(vGsonValue.message, "短信发送成功","获取验证码success:message错误");
		}
	private GetSmsApi testGetSms(String method,String clientId,String timestamp,String sign,String app_key,String account,String captcha) throws IOException{
		
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("method", method);
		paramsMap.put("clientId", clientId);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("sign", sign);
		paramsMap.put("app_key", app_key);
		paramsMap.put("account", account);
		paramsMap.put("captcha", captcha);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		//System.out.println(resp.body().string());
		Gson vGson = new Gson();
		GetSmsApi vGsonValue = vGson.fromJson(resp.body().string(), GetSmsApi.class);
		return vGsonValue;
	}

}
