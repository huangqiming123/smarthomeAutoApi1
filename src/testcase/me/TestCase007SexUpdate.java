package testcase.me;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.UserSexUpdateApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase007SexUpdate {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> userSex=new ArrayList<String>();
	@Test
	public void testUserSexUpdate() throws IOException, SQLException{
		for(int i=0;i<paramD.UserUpdateSex().length;i++){
		UserSexUpdateApi vGsonValue1=this.userSexUpdate(paramD.UserUpdateSex()[i]);
		Assert.assertEquals(vGsonValue1.code, 0,"修改userSex code码错误");
		Assert.assertEquals(vGsonValue1.message, "操作成功","修改userSex message错误");
		String param=paramD.UserUpdateSex()[i];
		//System.out.println(vGsonValue1.result);
		userSex=conn.connectMySqlM(sqlD.meGetUserInfoSex()[0], sqlD.meGetUserInfoSex()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(param, userSex.get(0),"修改userSex失败");
	}
	}
	private UserSexUpdateApi userSexUpdate(String sex) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.userSexUpdateSign(sex));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.UserSexUpdateMethod());
		paramsMap.put("sex", sex);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		UserSexUpdateApi vGsonValue1=vGson.fromJson(resp.body().string(), UserSexUpdateApi.class);
		return vGsonValue1;
	}

}
