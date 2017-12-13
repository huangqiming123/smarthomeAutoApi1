package testcase.me.callAndSmsAlarm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.DeleteCsAlarmSetApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase006DeleteCsAlarmSet {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> vsAlarmIds=new ArrayList<String>();
	
	@Test
	public void testDeleteCsAlarmSet() throws IOException, SQLException{
		vsAlarmIds=conn.connectMySqlM(sqlD.callAndSmsAlarmCreatCsAlarmSet()[0], sqlD.callAndSmsAlarmCreatCsAlarmSet()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		for(int i=0;i<paramD.creatCsAlarmSetReceiveNumber1().length;i++){
			
			DeleteCsAlarmSetApi vGsonValue1=this.deleteCsAlarmSet(vsAlarmIds.get(5));
			Assert.assertEquals(vGsonValue1.code, 0,"UpdateCsAlarmSet code码错误");
			Assert.assertEquals(vGsonValue1.message, "删除成功","UpdateCsAlarmSet message错误");
			}
		
		}
	
	
	private DeleteCsAlarmSetApi deleteCsAlarmSet(String vsAlarmId) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.deleteCsAlarmSetSign(vsAlarmId));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.deleteCsAlarmSetMethod());
		paramsMap.put("vsAlarmId", vsAlarmId);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		DeleteCsAlarmSetApi vGsonValue1=vGson.fromJson(resp.body().string(), DeleteCsAlarmSetApi.class);
		return vGsonValue1;
	}

}
