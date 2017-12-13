package testcase.loginAuthentication;

//import java.awt.List;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.LoginApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase003Login {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> accountInfo=new ArrayList<String>();
	List<String> getIsHasBind=new ArrayList<String>();
	ComData comD=new ComData();
/***
 * 
 * @throws IOException
 * @throws SQLException 
 
	@Test
	public  void testGetSmsFailWithIdenCode() throws IOException{
		GetSmsApi vGsonValue=this.testGetSms(paramD.getSmsMethod(), paramD.clientId(), signU.getTimeStamp(), signD.getSmsSignError(), paramD.appKey(), paramD.account(), paramD.captchaRight());
		Assert.assertEquals(vGsonValue.code, 11004,"idenCodeError:code码错误");
		Assert.assertEquals(vGsonValue.message, "验证码错误","idenCodeError:message错误");
		}
	
	@Test
	public  void testGetSmsFailWithInviCode() throws IOException{
		GetSmsApi vGsonValue=this.testGetSms(paramD.getSmsMethod(), paramD.clientId(), signU.getTimeStamp(), signD.getSmsSignFail(), paramD.appKey(), paramD.account(), paramD.captchaRight());
		Assert.assertEquals(vGsonValue.code, 1001,"idenCodeError:code码错误");
		Assert.assertEquals(vGsonValue.message, "缺少签名参数或签名无效","idenCodeError:message错误");
		}
		**/
	
	@Test
	public void testLoginSuccess() throws IOException, SQLException{
		LoginApi vGsonValue=this.testLogin(signU.getTimeStamp(), signD.getLoginSign(), paramD.appKey(), paramD.loginMethod(), paramD.account(), paramD.loginEdtionType(), paramD.loginSmsCaptcha(), paramD.clientId(), paramD.loginTimeZone(), paramD.loginLanguage());
		Assert.assertEquals(vGsonValue.code, 0,"登录成功:code码错误");
		Assert.assertEquals(vGsonValue.message, "操作成功","登录成功:message错误");
		Assert.assertEquals(vGsonValue.result.account, paramD.account(),"账号错误");
		accountInfo=conn.connectMySqlM(sqlD.loginDataGetAccountInfo()[0], sqlD.loginDataGetAccountInfo()[1],comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		Assert.assertEquals(vGsonValue.result.userId, accountInfo.get(0), "userid错误");
		Assert.assertEquals(vGsonValue.result.sex, accountInfo.get(2), "sex错误");
		Assert.assertEquals(vGsonValue.result.userName, accountInfo.get(3), "userName错误");
		Assert.assertEquals(vGsonValue.result.nickName, accountInfo.get(4), "nickName错误");
		Assert.assertEquals(vGsonValue.result.cityName, accountInfo.get(5), "cityName错误");
		getIsHasBind=conn.connectMySqlM(sqlD.loginDataGetIsHasBind()[0], sqlD.loginDataGetIsHasBind()[1],comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		int i;
		try{
			i=Integer.parseInt(getIsHasBind.get(0));
		}catch(NumberFormatException e){
			i=0;
		}
		if(i>0){
			Assert.assertTrue(vGsonValue.result.hasDevice.equals("1"), "返回存在绑定设备错误");
		}else{
			Assert.assertTrue(vGsonValue.result.hasDevice.equals("0"), "返回不存在绑定设备错误");
		}
		Assert.assertEquals(vGsonValue.result.cityName, accountInfo.get(5), "cityName错误");
		System.out.println("token:"+vGsonValue.result.accessToken);
		}
	
	/***
	 * 
	
	public String getToken() throws IOException{
		LoginApi vGsonValue=this.testLogin(signU.getTimeStamp(), signD.getLoginSign(), paramD.appKey(), paramD.loginMethod(), paramD.account(), paramD.loginEdtionType(), paramD.loginSmsCaptcha(), paramD.clientId(), paramD.loginTimeZone(), paramD.loginLanguage());
		return vGsonValue.result.accessToken;
	}**/
	
	
	private LoginApi testLogin(String timestamp,String sign,String app_key,String method,String account,String edtionType,String captcha,String clientId,String timeZone,String language ) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("sign", sign);
		paramsMap.put("app_key", app_key);
		paramsMap.put("method", method);
		paramsMap.put("account", account);
		paramsMap.put("edtionType", edtionType);
		paramsMap.put("captcha", captcha);
		paramsMap.put("clientId", clientId);
		paramsMap.put("timeZone", timeZone);
		paramsMap.put("language", language);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson = new Gson();
		LoginApi vGsonValue1 = vGson.fromJson(resp.body().string(), LoginApi.class);
		return vGsonValue1;
	}

	

}
