package testcase.homepage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dataformate.DeleteAlarmApi;
import dataformate.GetAlarmDetailNoPagingApi;
import model.ConnectMysql;
import okhttp.FrameRequestManager;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.FrameParamData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase005DeleteAlarm {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	FrameRequestManager frem=new FrameRequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	FrameParamData fparamD=new FrameParamData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	ComData comD=new ComData();
	List<String> ids=new ArrayList<String>();
	@Test
	public void testDeleteAlarm() throws JsonSyntaxException, IOException, SQLException, InterruptedException{
		GetAlarmDetailNoPagingApi vGsonValue2=this.getAlarmDetailNoPaging(fparamD.getAlarmDetailNoPagingStartRow(),fparamD.getAlarmDetailNoPagingMethod(),paramD.AlarmListPageSize()[0]);
		//String id=;
		DeleteAlarmApi vGsonValue1=this.deleteAlarm(paramD.token(), signU.getTimeStamp(), signD.deleteAlarmSign(vGsonValue2.data[0].id), paramD.appKey(), paramD.DeleteAlarmMethod(), vGsonValue2.data[0].id);
		System.out.println(vGsonValue1.message);
		Thread.sleep(2000);
		GetAlarmDetailNoPagingApi vGsonValue3=this.getAlarmDetailNoPaging(fparamD.getAlarmDetailNoPagingStartRow(),fparamD.getAlarmDetailNoPagingMethod(),paramD.AlarmListPageSize()[0]);
		for(int i=0;i<vGsonValue3.data.length;i++){
		ids.add(vGsonValue3.data[i].id);
		}
		Assert.assertFalse(ids.contains(vGsonValue2.data[0].id), "告警数据错误");
	}
	private DeleteAlarmApi deleteAlarm(String accessToken,String timestamp,String sign,String app_key,String method,String alarmIds) throws JsonSyntaxException, IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", accessToken);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("sign", sign);
		paramsMap.put("app_key", app_key);
		paramsMap.put("method", method);
		paramsMap.put("alarmIds", alarmIds);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		DeleteAlarmApi vGsonValue1=vGson.fromJson(resp.body().string(), DeleteAlarmApi.class);
		return vGsonValue1;
	}
	
	private GetAlarmDetailNoPagingApi getAlarmDetailNoPaging(String startRow,String method,String pageSize) throws SQLException, JsonSyntaxException, IOException{
		HashMap<String, String> paramsMap = new HashMap<>();    
		paramsMap.put("startRow", startRow);
		paramsMap.put("_method_", method);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("userIds", this.getUserId());
		Response fresp=frem.requestSyn(fparamD.getAlarmDetailNoPagingUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		GetAlarmDetailNoPagingApi vGsonValue2=vGson.fromJson(fresp.body().string(), GetAlarmDetailNoPagingApi.class);
		return vGsonValue2;
	}
	private String getUserId() throws SQLException{
		String userId=conn.connectMySqlM("FROM `sh_user_device_bind` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"');", "userId", comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]).get(0);
		return userId;
		
	}

}
