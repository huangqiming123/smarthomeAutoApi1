package testcase.me;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.UserNickNameUpdateApi;
import dataformate.UsernameUpdateApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase005NicknameUpdate {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> userNickName=new ArrayList<String>();
	@Test
	public void testUserNickNameUpdate() throws IOException, SQLException{
		for(int i=0;i<paramD.UsernameUpdateUsername().length;i++){
		UserNickNameUpdateApi vGsonValue1=this.userNickNameUpdate(paramD.UsernameUpdateUsername()[i]);
		Assert.assertEquals(vGsonValue1.code, 0,"修改nickname code码错误");
		Assert.assertEquals(vGsonValue1.message, "操作成功","修改nickname message错误");
		String param=paramD.UsernameUpdateUsername()[i];
		//System.out.println(vGsonValue1.result);
		userNickName=conn.connectMySqlM(sqlD.meGetUserInfoNickname()[0], sqlD.meGetUserInfoNickname()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(param, userNickName.get(0),"修改userNickName失败");
	}
	}
	private UserNickNameUpdateApi userNickNameUpdate(String nickName) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.userNicknameUpdateSign(nickName));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.UserNicknameUpdateMethod());
		paramsMap.put("nickName", nickName);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		UserNickNameUpdateApi vGsonValue1=vGson.fromJson(resp.body().string(), UserNickNameUpdateApi.class);
		return vGsonValue1;
	}


}
