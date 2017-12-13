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
import dataformate.DeleteCsAlarmSetApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase007BindCsAlarmAndImeis {
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
	
	
	private BindCsAlarmAndImeisApi bindCsAlarmAndImeis(String imeis,String vsAlarmId) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.bindCsAlarmAndImeisSign(imeis,vsAlarmId));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.BindCsAlarmAndImeisMethod());
		paramsMap.put("imeis", imeis);
		paramsMap.put("vsAlarmId", vsAlarmId);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		BindCsAlarmAndImeisApi vGsonValue1=vGson.fromJson(resp.body().string(), BindCsAlarmAndImeisApi.class);
		return vGsonValue1;
	}
	private String getImeis() throws SQLException{
		List<String> imeis=new ArrayList<String>();
		imeis=conn.connectMySqlM(sqlD.callAndSmsAlarmBindCsAlarmAndImeis()[0], sqlD.callAndSmsAlarmBindCsAlarmAndImeis()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<imeis.size();i++){
			sb.append(imeis.get(i)).append(",");
		}
		String imeisToS=sb.toString().substring(0, sb.toString().length()-1);
		return imeisToS;
	}


}
