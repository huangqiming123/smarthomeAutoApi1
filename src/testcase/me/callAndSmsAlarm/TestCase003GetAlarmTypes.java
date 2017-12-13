package testcase.me.callAndSmsAlarm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.GetCsAlarmGetAlarmTypeApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase003GetAlarmTypes {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	@Test
	public void testGetAlarmType() throws IOException, SQLException{
		
		GetCsAlarmGetAlarmTypeApi vGsonValue1=this.getAlarmType();
		Assert.assertEquals(vGsonValue1.code, 0,"GetAlarmType code码错误");
		Assert.assertEquals(vGsonValue1.message, "success","GetAlarmType message错误");
		//myConfig=conn.connectMySqlM(sqlD.settingMyUserConfig()[0], sqlD.settingMyUserConfig()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		//Assert.assertEquals(vGsonValue1.result.voiceSwitch, myConfig.get(0),"MyUserConfig voiceSwitch错误");
		//Assert.assertEquals(vGsonValue1.result.shakeSwitch, myConfig.get(1),"MyUserConfig shakeSwitch错误");
		//Assert.assertEquals(vGsonValue1.result.pushImgSwitch, myConfig.get(2),"MyUserConfig pushImgSwitch错误");
		//Assert.assertEquals(vGsonValue1.result.pushVideoSwitch, myConfig.get(3),"MyUserConfig pushVideoSwitch错误");
		//Assert.assertEquals(vGsonValue1.result.alarmSwitch, myConfig.get(4),"MyUserConfig alarmSwitch错误");
		
		
	}
	private GetCsAlarmGetAlarmTypeApi getAlarmType() throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.getCsAlarmGetAlarmTypeSign());
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.getAlarmTypesMethod());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson=new Gson();
		GetCsAlarmGetAlarmTypeApi vGsonValue1=vGson.fromJson(resp.body().string(), GetCsAlarmGetAlarmTypeApi.class);
		return vGsonValue1;
	}



}
