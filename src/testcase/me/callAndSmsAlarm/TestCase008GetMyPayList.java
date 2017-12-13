package testcase.me.callAndSmsAlarm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.BindCsAlarmAndImeisApi;
import dataformate.GetMyPayListApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase008GetMyPayList {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> vsAlarmIds=new ArrayList<String>();
	List<String> imeisTS=new ArrayList<String>();
	
	@Test
	public void testBindCsAlarmAndImeis() throws IOException, SQLException{
		vsAlarmIds=conn.connectMySqlM(sqlD.callAndSmsAlarmCreatCsAlarmSet()[0], sqlD.callAndSmsAlarmCreatCsAlarmSet()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		BindCsAlarmAndImeisApi vGsonValue1=this.bindCsAlarmAndImeis(this.getImeis(), vsAlarmIds.get(5));
		Assert.assertEquals(vGsonValue1.code, 0,"BindCsAlarmAndImeis:code码错误");
		Assert.assertEquals(vGsonValue1.message, "绑定成功","BindCsAlarmAndImeis:message错误");
		imeisTS=conn.connectMySqlM(sqlD.callAndSmsAlarmBindCsAlarmAndImeisGetImeis(vsAlarmIds.get(5))[0], sqlD.callAndSmsAlarmBindCsAlarmAndImeisGetImeis(vsAlarmIds.get(5))[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(imeisTS.get(0),this.getImeis(),"BindCsAlarmAndImeis:imeis错误");
	}
	
	
	private GetMyPayListApi getMyPayList(String pageNum) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.getMyPayListSign(pageNum));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.getMyPayListMethod());
		paramsMap.put("phone", paramD.getMyPayListPhone());
		paramsMap.put("alarmPushType", paramD.getMyPayListAlarmPushType());
		paramsMap.put("alarmType", paramD.getMyPayListAlarmType());
		paramsMap.put("startTime", paramD.getMyPayListStartTime());
		paramsMap.put("endTime", paramD.getMyPayListEndTime());
		paramsMap.put("pageNum", pageNum);
		paramsMap.put("pageSize", paramD.getMyPayListPageSize());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		GetMyPayListApi vGsonValue1=vGson.fromJson(resp.body().string(), GetMyPayListApi.class);
		return vGsonValue1;
	}
	private GetMyPayListApi getMyPayListNoParam(String pageNum) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.getMyPayListSign(pageNum));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.getMyPayListMethod());
		paramsMap.put("phone", paramD.getMyPayListPhone());
		paramsMap.put("alarmPushType", paramD.getMyPayListAlarmPushType());
		paramsMap.put("alarmType", paramD.getMyPayListAlarmType());
		paramsMap.put("startTime", paramD.getMyPayListStartTime());
		paramsMap.put("endTime", paramD.getMyPayListEndTime());
		paramsMap.put("pageNum", pageNum);
		paramsMap.put("pageSize", paramD.getMyPayListPageSize());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		//GetMyPayListNoParamApi vGsonValue1=vGson.fromJson(resp.body().string(), GetMyPayListNoParamApi.class);
		//return vGsonValue1;
	}

}
