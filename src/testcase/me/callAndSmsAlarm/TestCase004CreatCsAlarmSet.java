package testcase.me.callAndSmsAlarm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.CreatCsAlarmSetApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase004CreatCsAlarmSet {
	TestCase004CreatCsAlarmSet() throws IOException, SQLException{
		this.testCreatCsAlarmSetSuccess();
	}
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> alarmsetInfo=new ArrayList<String>();
	@Test
	public void testCreatCsAlarmSetRepeat() throws IOException, SQLException{
		for(int i=0;i<paramD.creatCsAlarmSetReceiveNumber1().length;i++){
			CreatCsAlarmSetApi vGsonValue1=this.creatCsAlarmSet(paramD.creatCsAlarmSetReceiveNumberRepeat()[i], paramD.creatCsAlarmSetReceiveNumberRepeat()[i], paramD.creatCsAlarmSetCallStatus()[i], paramD.creatCsAlarmSetSmsStatus()[i]);
			Assert.assertEquals(vGsonValue1.code, 11024,"GetAlarmType code码错误");
			Assert.assertEquals(vGsonValue1.message, "	接收号码不能重复","CreatCsAlarmSet message错误");
			}
		
		}
	@Test
	public void testCreatCsAlarmSetSuccess() throws IOException, SQLException{
		for(int i=0;i<paramD.creatCsAlarmSetReceiveNumber1().length;i++){
			CreatCsAlarmSetApi vGsonValue1=this.creatCsAlarmSet(paramD.creatCsAlarmSetReceiveNumber1()[i], paramD.creatCsAlarmSetReceiveNumber2()[i], paramD.creatCsAlarmSetCallStatus()[i], paramD.creatCsAlarmSetSmsStatus()[i]);
			Assert.assertEquals(vGsonValue1.code, 0,"CreatCsAlarmSet code码错误");
			Assert.assertEquals(vGsonValue1.message, "新增成功","CreatCsAlarmSet message错误");
			alarmsetInfo=conn.connectMySqlM(sqlD.callAndSmsAlarmCreatCsAlarmSet()[0], sqlD.callAndSmsAlarmCreatCsAlarmSet()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
			Assert.assertEquals(alarmsetInfo.get(0), paramD.creatCsAlarmSetAlarmTypes(),"CreatCsAlarmSet AlarmTypes码错误");
			if(paramD.creatCsAlarmSetCallStatus()[i].equals("false")){
				Assert.assertEquals(alarmsetInfo.get(1), "0","CreatCsAlarmSet CallStatus 码错误");
			}else if(paramD.creatCsAlarmSetCallStatus()[i].equals("true")){
				Assert.assertEquals(alarmsetInfo.get(1), "1","CreatCsAlarmSet CallStatus 码错误");
			}
			if(paramD.creatCsAlarmSetSmsStatus()[i].equals("false")){
				Assert.assertEquals(alarmsetInfo.get(2), "0","CreatCsAlarmSet CallStatus 码错误");
			}else if(paramD.creatCsAlarmSetSmsStatus()[i].equals("true")){
				Assert.assertEquals(alarmsetInfo.get(2), "1","CreatCsAlarmSet CallStatus 码错误");
			}
			Assert.assertEquals(alarmsetInfo.get(3), paramD.creatCsAlarmSetReceiveNumber1()[i],"CreatCsAlarmSet ReceiveNumber1错误");
			Assert.assertEquals(alarmsetInfo.get(4), paramD.creatCsAlarmSetReceiveNumber2()[i],"CreatCsAlarmSet ReceiveNumber2错误");
			System.out.println(vGsonValue1.result);
			
			}
		
		}
	//public void testCreatCsAlarmSetSuccess(String a){
		
	//}
	
	private CreatCsAlarmSetApi creatCsAlarmSet(String receiveNumber1,String receiveNumber2,String callStatus,String smsStatus) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.creatCsAlarmSetSign(receiveNumber1, receiveNumber2, callStatus, smsStatus));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.creatCsAlarmSetMethod());
		paramsMap.put("alarmTypes", paramD.creatCsAlarmSetAlarmTypes());
		paramsMap.put("receiveNumber1", receiveNumber1);
		paramsMap.put("receiveNumber2", receiveNumber2);
		paramsMap.put("callStatus", callStatus);
		paramsMap.put("smsStatus",smsStatus);;
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		CreatCsAlarmSetApi vGsonValue1=vGson.fromJson(resp.body().string(), CreatCsAlarmSetApi.class);
		return vGsonValue1;
	}
	

}
