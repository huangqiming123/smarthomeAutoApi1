package testcase.me.callAndSmsAlarm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.GetCsResidueDegreeApi;
import dataformate.UpdataMyUserPushVideoSwitchApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase001GetCsResidueDegree {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> getCsResidueDegree=new ArrayList<String>();
	@Test
	public void testGetCsResidueDegree() throws IOException, SQLException{
		
		GetCsResidueDegreeApi vGsonValue1=this.getCsResidueDegree();
		Assert.assertEquals(vGsonValue1.code, 0,"getCsResidueDegree code码错误");
		Assert.assertEquals(vGsonValue1.message, "查询成功","getCsResidueDegree message错误");
		getCsResidueDegree=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsResidueDegree()[0], sqlD.callAndSmsAlarmGetCsResidueDegree()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(vGsonValue1.result.callNumber, getCsResidueDegree.get(0),"getCsResidueDegree call数量错误");
		Assert.assertEquals(vGsonValue1.result.smsNumber, getCsResidueDegree.get(1),"getCsResidueDegree sms数量错误");
			
		
	}
	private GetCsResidueDegreeApi getCsResidueDegree() throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.getCsResidueDegreeSign());
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.getCsResidueDegreeMethod());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson=new Gson();
		GetCsResidueDegreeApi vGsonValue1=vGson.fromJson(resp.body().string(), GetCsResidueDegreeApi.class);
		return vGsonValue1;
	}

}
