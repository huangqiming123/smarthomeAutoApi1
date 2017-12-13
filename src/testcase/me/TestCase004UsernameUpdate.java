package testcase.me;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.UsernameUpdateApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase004UsernameUpdate {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> userName=new ArrayList<String>();
	@Test
	public void testUsernameUpdate() throws IOException, SQLException{
		for(int i=0;i<paramD.UsernameUpdateUsername().length;i++){
		UsernameUpdateApi vGsonValue1=this.usernameUpdata(paramD.UsernameUpdateUsername()[i]);
		Assert.assertEquals(vGsonValue1.code, 0,"修改头像code码错误");
		Assert.assertEquals(vGsonValue1.message, "操作成功","修改username message错误");
		//System.out.println(vGsonValue1.result);
		userName=conn.connectMySqlM(sqlD.meGetUserInfoUsername()[0], sqlD.meGetUserInfoUsername()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(paramD.UsernameUpdateUsername()[i], userName.get(0),"修改username失败");
	}
	}
	private UsernameUpdateApi usernameUpdata(String username) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.UsernameUpdateSign(username));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.UsernameUpdateMethod());
		paramsMap.put("userName", username);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		UsernameUpdateApi vGsonValue1=vGson.fromJson(resp.body().string(), UsernameUpdateApi.class);
		return vGsonValue1;
	}


}
