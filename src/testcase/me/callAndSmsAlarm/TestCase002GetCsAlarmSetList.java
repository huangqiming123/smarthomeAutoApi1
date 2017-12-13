package testcase.me.callAndSmsAlarm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.GetCsAlarmSetListApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase002GetCsAlarmSetList {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> imeis=new ArrayList<String>();
	List<String> alarmTypes=new ArrayList<String>();
	List<String> ids=new ArrayList<String>();
	List<String> callStatus=new ArrayList<String>();
	List<String> smsStatus=new ArrayList<String>();
	List<String> receiveNumber1=new ArrayList<String>();
	List<String> receiveNumber2=new ArrayList<String>();
	List<String> Value_zh=new ArrayList<String>();
	List<String> alarmTypesList=new ArrayList<String>();
	List<String> imeisList=new ArrayList<String>();
	List<String> imeisCurrent=new ArrayList<String>();
	@Test
	public void testGetCsAlarmSetList() throws IOException, SQLException{
		GetCsAlarmSetListApi vGsonValue1=this.getCsAlarmSetList();
		Assert.assertEquals(vGsonValue1.code, 0,"getCsAlarmSetList code码错误");
		Assert.assertEquals(vGsonValue1.message, "success","getCsAlarmSetList message错误");
		ids=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetId()[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetId()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		alarmTypes=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetAlarmTypes()[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetAlarmTypes()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		callStatus=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetCallStatus()[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetCallStatus()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		smsStatus=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetSmsStatus()[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetSmsStatus()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		imeis=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetImeis()[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetImeis()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		receiveNumber1=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetReceiveNumber1()[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetReceiveNumber1()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		receiveNumber2=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetReceiveNumber2()[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetReceiveNumber2()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		//Value_zh=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetValue_zh(id)[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetValue_zh(id)[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(vGsonValue1.result.length, ids.size(),"getCsAlarmSetList 数量错误");
		for(int i=0;i<vGsonValue1.result.length;i++){
			Assert.assertEquals(vGsonValue1.result[i].vsAlarmId, ids.get(i),"getCsAlarmSetList vsAlarmId错误");
			Assert.assertEquals(vGsonValue1.result[i].alarmTypes, alarmTypes.get(i),"getCsAlarmSetList alarmTypes错误");
			if(vGsonValue1.result[i].callStatus.equals("false")){
				vGsonValue1.result[i].callStatus="0";
			}else if(vGsonValue1.result[i].callStatus.equals("true")){
				vGsonValue1.result[i].callStatus="1";
			}
			Assert.assertEquals(vGsonValue1.result[i].callStatus, callStatus.get(i),"getCsAlarmSetList callStatus错误");
			if(vGsonValue1.result[i].smsStatus.equals("false")){
				vGsonValue1.result[i].smsStatus="0";
			}else if(vGsonValue1.result[i].smsStatus.equals("true")){
				vGsonValue1.result[i].smsStatus="1";
			}
			Assert.assertEquals(vGsonValue1.result[i].smsStatus, smsStatus.get(i),"getCsAlarmSetList smsStatus错误");
			Assert.assertEquals(vGsonValue1.result[i].imeis, imeis.get(i),"getCsAlarmSetList imeis错误");
			Assert.assertEquals(vGsonValue1.result[i].receiveNumber1, receiveNumber1.get(i),"getCsAlarmSetList receiveNumber1错误");
			Assert.assertEquals(vGsonValue1.result[i].receiveNumber2, receiveNumber2.get(i),"getCsAlarmSetList receiveNumber2错误");
			for(int j=0;j<vGsonValue1.result[i].alarmList.length;j++){
				if(alarmTypes.get(i).contains(",")){
					String[] alarmType=alarmTypes.get(i).split(",");
					for(int x=0;x<alarmType.length;x++){
						alarmTypesList.add(alarmType[x]);
					}
					Assert.assertEquals(vGsonValue1.result[i].alarmList.length,alarmType.length,"getCsAlarmSetList alarmType数量错误");
					Assert.assertTrue(alarmTypesList.contains(vGsonValue1.result[i].alarmList[j].alarmType), "getCsAlarmSetList alarmType错误");
					
					if(vGsonValue1.result[i].alarmList[j].alarmName.equals("ACC开启 ")){
						//Assert.assertTrue(conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetAllValue_zh(this.getAllAlarmAlarmType(alarmType))[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetAllValue_zh(this.getAllAlarmAlarmType(alarmType))[1],comD.connectOperateLib()[0] , comD.connectOperateLib()[1], comD.connectOperateLib()[2]).contains(vGsonValue1.result[i].alarmList[j].alarmName), "getCsAlarmSetList alarmName错误");
						Assert.assertEquals(conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetValue_zh(alarmType[j])[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetValue_zh(alarmType[j])[1],comD.connectOperateLib()[0] , comD.connectOperateLib()[1], comD.connectOperateLib()[2]).get(j)+" ","ACC开启 ","getCsAlarmSetList alarmName错误");
						
					}else{
						Assert.assertTrue(conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetAllValue_zh(this.getAllAlarmAlarmType(alarmType))[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetAllValue_zh(this.getAllAlarmAlarmType(alarmType))[1],comD.connectOperateLib()[0] , comD.connectOperateLib()[1], comD.connectOperateLib()[2]).contains(vGsonValue1.result[i].alarmList[j].alarmName), "getCsAlarmSetList alarmName错误");
							//Assert.assertEquals(vGsonValue1.result[i].alarmList[j].alarmName,conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetValue_zh(alarmType[j])[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetValue_zh(alarmType[j])[1],comD.connectOperateLib()[0] , comD.connectOperateLib()[1], comD.connectOperateLib()[2]).get(0),"getCsAlarmSetList alarmName错误");
				}
			}else{
				Assert.assertEquals(vGsonValue1.result[i].alarmList[j].alarmName,conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetValue_zh(alarmTypes.get(0))[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetValue_zh(alarmTypes.get(0))[1],comD.connectOperateLib()[0] , comD.connectOperateLib()[1], comD.connectOperateLib()[2]).get(0),"getCsAlarmSetList alarmName错误");
			}
		
			}
			for(int y=0;y<vGsonValue1.result[i].deviceList.length;y++){
				if(imeis.get(i).contains(",")){
					
					String[] imei=imeis.get(i).split(",");
					for(int x=0;x<imei.length;x++){
						imeisList.add(imei[x]);
					}
					imeisCurrent=conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetAllImeiName(this.getAllImeiName(imei))[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetAllImeiName(this.getAllImeiName(imei))[1],comD.connectOperateLib()[0] , comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
					Assert.assertEquals(vGsonValue1.result[i].deviceList.length,imeisCurrent.size(),"getCsAlarmSetList imei数量错误");
					Assert.assertTrue(imeisList.contains(vGsonValue1.result[i].deviceList[y].imei), "getCsAlarmSetList imei错误");
					Assert.assertTrue(imeisCurrent.contains(vGsonValue1.result[i].deviceList[y].deviceName), "getCsAlarmSetList deviceName错误");
				}else{
					Assert.assertEquals(vGsonValue1.result[i].deviceList[y].imei,imeis.get(i),"getCsAlarmSetList imei错误");
					Assert.assertEquals(vGsonValue1.result[i].deviceList[y].deviceName,conn.connectMySqlM(sqlD.callAndSmsAlarmGetCsAlarmSetListGetImeiName(imeis.get(i))[0], sqlD.callAndSmsAlarmGetCsAlarmSetListGetImeiName(imeis.get(i))[1],comD.connectOperateLib()[0] , comD.connectOperateLib()[1], comD.connectOperateLib()[2]).get(0),"getCsAlarmSetList imeiName错误");
				}
			}
		}
}
	private GetCsAlarmSetListApi getCsAlarmSetList() throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.getCsAlarmSetListSign());
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.getCsAlarmSetListMethod());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson=new Gson();
		GetCsAlarmSetListApi vGsonValue1=vGson.fromJson(resp.body().string(), GetCsAlarmSetListApi.class);
		return vGsonValue1;
	}
	private String getAllAlarmAlarmType(String[] alarmType) throws SQLException{
		//imeis=conn.connectMySqlM("FROM `sh_user_device_bind` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='18688888888');", "imei", comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		 StringBuilder sb = new StringBuilder();
		  for(int i=0;i<alarmType.length;i++){
			  sb.append(alarmType[i]).append("','");
		  }
		  String alarmValue_zhToS=sb.toString().substring(0, sb.toString().length()-1);
		  return alarmValue_zhToS;
	}
	private String getAllImeiName(String[] imei) throws SQLException{
		//imeis=conn.connectMySqlM("FROM `sh_user_device_bind` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='18688888888');", "imei", comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		 StringBuilder sb = new StringBuilder();
		  for(int i=0;i<imei.length;i++){
			  sb.append(imei[i]).append("','");
		  }
		  String imeiNameToS=sb.toString().substring(0, sb.toString().length()-1);
		  return imeiNameToS;
	}
}
