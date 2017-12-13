package testcase.me;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dataformate.GetMyUserApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;



public class TestCase001GetMyUser {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> userInfo=new ArrayList<String>();
	@Test
	public void testGetMyUser() throws JsonSyntaxException, IOException, SQLException{
		GetMyUserApi vGsonValue=this.getMyUser(paramD.token(), signU.getTimeStamp(), signD.getMyUserSign(), paramD.appKey(), paramD.GetMyUserMethod());
		Assert.assertEquals(vGsonValue.code, 0,"得到用户信息code码错误");
		Assert.assertEquals(vGsonValue.message, "操作成功","得到用户信息message错误");
		userInfo=conn.connectMySqlM(sqlD.meGetUserInfo()[0], sqlD.meGetUserInfo()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(vGsonValue.result.account, userInfo.get(0),"得到用户信息account错误");
		Assert.assertEquals(vGsonValue.result.sex, userInfo.get(1),"得到用户信息sex错误");
		Assert.assertEquals(vGsonValue.result.userName, userInfo.get(2),"得到用户信息userName错误");
		Assert.assertEquals(vGsonValue.result.nickName, userInfo.get(3),"得到用户信息nickName错误");
		Assert.assertEquals(vGsonValue.result.cityName, userInfo.get(4),"得到用户信息cityName错误");
		Assert.assertEquals(vGsonValue.result.iconUrl, userInfo.get(5),"得到用户信息cityName错误");
	}
	private GetMyUserApi getMyUser(String accessToken,String timestamp,String sign,String app_key,String method) throws JsonSyntaxException, IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", accessToken);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("sign", sign);
		paramsMap.put("app_key", app_key);
		paramsMap.put("method", method);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson=new Gson();
		GetMyUserApi vGsonValue1=vGson.fromJson(resp.body().string(), GetMyUserApi.class);
		return vGsonValue1;
	}

}
