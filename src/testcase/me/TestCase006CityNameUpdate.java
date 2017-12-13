package testcase.me;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.UserCityNameUpdateApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase006CityNameUpdate {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> userCityName=new ArrayList<String>();
	@Test
	public void testUserCityNameUpdate() throws IOException, SQLException{
		for(int i=0;i<paramD.CitynameUpdateCityname().length;i++){
		UserCityNameUpdateApi vGsonValue1=this.userCityNameUpdate(paramD.CitynameUpdateCityname()[i]);
		Assert.assertEquals(vGsonValue1.code, 0,"修改cityname code码错误");
		Assert.assertEquals(vGsonValue1.message, "操作成功","修改cityname message错误");
		String param=paramD.CitynameUpdateCityname()[i];
		//System.out.println(vGsonValue1.result);
		userCityName=conn.connectMySqlM(sqlD.meGetUserInfoCityname()[0], sqlD.meGetUserInfoCityname()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(param, userCityName.get(0),"修改userCityName失败");
	}
	}
	private UserCityNameUpdateApi userCityNameUpdate(String cityName) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.userCitynameUpdateSign(cityName));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.UserCitynameUpdateMethod());
		paramsMap.put("cityName", cityName);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		UserCityNameUpdateApi vGsonValue1=vGson.fromJson(resp.body().string(), UserCityNameUpdateApi.class);
		return vGsonValue1;
	}

}
