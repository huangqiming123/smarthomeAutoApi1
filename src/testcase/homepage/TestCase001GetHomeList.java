package testcase.homepage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dataformate.GetAlarmDetailNoPagingApi;
import dataformate.HomeListApi;
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

public class TestCase001GetHomeList {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	FrameRequestManager frem=new FrameRequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	FrameParamData fparamD=new FrameParamData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> devices=new ArrayList<String>();
	List<String> name=new ArrayList<String>();
	List<String> mcTypeUseScope=new ArrayList<String>();
	List<String> status=new ArrayList<String>();
	List<String> status1=new ArrayList<String>();
	List<String> status2=new ArrayList<String>();
	List<Date> expiration=new ArrayList<Date>();
	List<String> expirationFlag=new ArrayList<String>();
	List<String> shutDownStatus=new ArrayList<String>();
	List<String> sim=new ArrayList<String>();
	List<String> isLife=new ArrayList<String>();
	List<String> hbTime=new ArrayList<String>();
	List<String> others=new ArrayList<String>();
	List<String> imeis=new ArrayList<String>();
	List<String> payVoucher=new ArrayList<String>();
	ComData comD=new ComData();
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final String 停留告警="stayAlert";
	@Test
	public void testHomeList() throws JsonSyntaxException, IOException, SQLException{
		HomeListApi vGsonValue=this.homeList(paramD.token(), signU.getTimeStamp(), signD.getHomeListSign(), paramD.appKey(), paramD.homeListMethod());
		Assert.assertEquals(vGsonValue.code, 0,"获得首页数据:code码错误");
		Assert.assertEquals(vGsonValue.message, "success","获得首页数据:message错误");
		devices=conn.connectMySqlM(sqlD.homeListDataGetImei()[0], sqlD.homeListDataGetImei()[1],comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		Assert.assertEquals(vGsonValue.result.devices.length,devices.size(),"获得首页数据:imei数量错误");
		for(int i=0;i<devices.size();i++){
			Assert.assertTrue(devices.contains(vGsonValue.result.devices[i].imei), "获得首页数据：imei错误");
			name=conn.connectMySqlM(sqlD.homeListDataGetName(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetName(vGsonValue.result.devices[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
			Assert.assertEquals(vGsonValue.result.devices[i].deviceName, name.get(0),""+vGsonValue.result.devices[i].imei+":deviceName错误");
			mcTypeUseScope=conn.connectMySqlM(sqlD.homeListDataGetMcTypeUseScope(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetMcTypeUseScope(vGsonValue.result.devices[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
			Assert.assertEquals(vGsonValue.result.devices[i].mcTypeUseScope, mcTypeUseScope.get(0),""+vGsonValue.result.devices[i].imei+":mcTypeUseScope错误");
			Assert.assertEquals(vGsonValue.result.devices[i].equipType, mcTypeUseScope.get(1),""+vGsonValue.result.devices[i].imei+":equipType错误");
			status=conn.connectMySqlM(sqlD.homeListDataGetStatus(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetStatus(vGsonValue.result.devices[i].imei)[1], comD.connectFrameLib()[0],comD.connectFrameLib()[1],comD.connectFrameLib()[2]);
			status1=conn.connectMySqlM(sqlD.homeListDataGetStatus1(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetStatus1(vGsonValue.result.devices[i].imei)[1], comD.connectFrameLib()[0],comD.connectFrameLib()[1],comD.connectFrameLib()[2]);
			status2=conn.connectMySqlM(sqlD.homeListDataGetStatus2(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetStatus2(vGsonValue.result.devices[i].imei)[1], comD.connectFrameLib()[0],comD.connectFrameLib()[1],comD.connectFrameLib()[2]);
			others=conn.connectMySqlM(sqlD.homeListDataGetOthers(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetOthers(vGsonValue.result.devices[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
			if(status.size()==0){
				Assert.assertEquals(vGsonValue.result.devices[i].status, "0",""+vGsonValue.result.devices[i].status+":status错误");
			}
			else if(status.get(0).equals("1")||status.get(0)==null||status1.get(0).equals("0")||status2.get(0).equals("0")){
				Assert.assertEquals(vGsonValue.result.devices[i].status, "0",""+vGsonValue.result.devices[i].status+":status错误");
			}else{
					Assert.assertTrue(vGsonValue.result.devices[i].status.contains("1")||vGsonValue.result.devices[i].status.contains("2")||vGsonValue.result.devices[i].status.contains("3"),""+vGsonValue.result.devices[i].status+":status错误");
				}
			/**
			fmt.format(expiration.get(0))=conn.connectMySqlM(sqlD.homeListDataGetExpiration(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetExpiration(vGsonValue.result.devices[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]).get(0);
			if(expiration.size()==0){
				Assert.assertEquals(vGsonValue.result.devices[i].expiration,null);
			}else{
			Assert.assertEquals(fmt.format(vGsonValue.result.devices[i].expiration), fmt.format(expiration.get(0)),""+vGsonValue.result.devices[i].expiration+":expiration错误");
			}
			**/
			expirationFlag=conn.connectMySqlM(sqlD.homeListDataGetExpirationFlag(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetExpirationFlag(vGsonValue.result.devices[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
			if(expirationFlag.size()==0||expirationFlag.get(0)==null){
				Assert.assertEquals(vGsonValue.result.devices[i].expirationFlag, "0",""+vGsonValue.result.devices[i].imei+":expirationFlag错误");
			}else if(conn.connectMySqlM(sqlD.homeListDataGetExpirationFlag1(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetExpirationFlag1(vGsonValue.result.devices[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]).get(0).equals("1")){
				Assert.assertEquals(vGsonValue.result.devices[i].expirationFlag, "2",""+vGsonValue.result.devices[i].imei+":expirationFlag错误");
			}else{
				Assert.assertTrue(vGsonValue.result.devices[i].expirationFlag.contains("1"),""+vGsonValue.result.devices[i].imei+":expirationFlag错误");
			}
			shutDownStatus=conn.connectMySqlM(sqlD.homeListDataGetShutDownStatus(vGsonValue.result.devices[i].imei)[0], sqlD.homeListDataGetShutDownStatus(vGsonValue.result.devices[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
			if(shutDownStatus.get(0).equals("DISABLE")){
				Assert.assertEquals(vGsonValue.result.devices[i].shutDownStatus, "0",""+vGsonValue.result.devices[i].imei+":shutDownStatus错误");
			}else{
				Assert.assertEquals(vGsonValue.result.devices[i].shutDownStatus, "1",""+vGsonValue.result.devices[i].imei+":shutDownStatus错误");
			}
			Assert.assertEquals(vGsonValue.result.devices[i].sim, shutDownStatus.get(1),""+vGsonValue.result.devices[i].imei+":sim错误");
			Assert.assertEquals(vGsonValue.result.devices[i].isLife, shutDownStatus.get(2),""+vGsonValue.result.devices[i].imei+":isLife错误");
			hbTime=conn.connectMySqlM(sqlD.homeListDataGetHbTime(vGsonValue.result.devices[i].imei, vGsonValue.result.devices[i].hbTime)[0], sqlD.homeListDataGetHbTime(vGsonValue.result.devices[i].imei, vGsonValue.result.devices[i].hbTime)[1], comD.connectFrameLib()[0],comD.connectFrameLib()[1],comD.connectFrameLib()[2]);
			if(status1.size()==0){
				Assert.assertTrue(vGsonValue.result.devices[i].hbTime.equals("null"), ""+vGsonValue.result.devices[i].imei+":hbtime错误");
			}else if(vGsonValue.result.devices[i].hbTime.equals("null")||vGsonValue.result.devices[i].hbTime==null||vGsonValue.result.devices[i].hbTime.length()==0){
				Assert.assertTrue(status1.get(0).equals("0"), ""+vGsonValue.result.devices[i].imei+":hbtime错误");
			}else{
				Assert.assertTrue(hbTime.get(0).equals("1"),""+vGsonValue.result.devices[i].imei+":hbtime错误");
			}
			Assert.assertEquals(vGsonValue.result.devices[i].shakeTapeFlag, others.get(0),""+vGsonValue.result.devices[i].imei+":shakeTapeFlag错误");
			Assert.assertEquals(vGsonValue.result.devices[i].photoFlag, others.get(1),""+vGsonValue.result.devices[i].imei+":photoFlag错误");
			Assert.assertEquals(vGsonValue.result.devices[i].cameraFlag, others.get(2),""+vGsonValue.result.devices[i].imei+":cameraFlag错误");
			Assert.assertEquals(vGsonValue.result.devices[i].videoFlag, others.get(3),""+vGsonValue.result.devices[i].imei+":videoFlag错误");
			
	}
		GetAlarmDetailNoPagingApi vGsonValue2=this.getAlarmDetailNoPaging();
		if(vGsonValue2.data[0].status.equals("stayAlert")){
			vGsonValue2.data[0].status="停留告警 ";
		//Assert.assertEquals(vGsonValue.result.alarms.alarmName, vGsonValue2.data[0].status,"告警名错误");
		}else if(vGsonValue2.data[0].status.equals("ACC_OFF")){
			vGsonValue2.data[0].status="ACC关闭 ";
		}else if(vGsonValue2.data[0].status.equals("ACC_ON")){
			vGsonValue2.data[0].status="ACC开启 ";
		}else if(vGsonValue2.data[0].status.equals("VibrationAlert")){
			vGsonValue2.data[0].status="震动告警 ";
		}
		Assert.assertEquals(vGsonValue.result.alarms.alarmName, vGsonValue2.data[0].status,"告警名错误");
		Assert.assertEquals(vGsonValue.result.alarms.alarmTime, vGsonValue2.data[0].pushTime,"告警时间错误");
		payVoucher=conn.connectMySqlM(sqlD.homeListDataGetPayVoucher()[0], sqlD.homeListDataGetPayVoucher()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		Assert.assertEquals(vGsonValue.result.payVoucher.payTitle,payVoucher.get(0),"payTitle错误");
		Assert.assertEquals(vGsonValue.result.payVoucher.markNum,payVoucher.get(1),"markNum错误");
		Assert.assertEquals(vGsonValue.result.payVoucher.payTime+".0",payVoucher.get(2),"payTime错误");
	}
	
	private HomeListApi homeList(String accessToken,String timestamp,String sign,String app_key,String method) throws JsonSyntaxException, IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", accessToken);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("sign", sign);
		paramsMap.put("app_key", app_key);
		paramsMap.put("method", method);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson=new Gson();
		HomeListApi vGsonValue1=vGson.fromJson(resp.body().string(), HomeListApi.class);
		return vGsonValue1;
	}
	private GetAlarmDetailNoPagingApi getAlarmDetailNoPaging() throws SQLException, JsonSyntaxException, IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("startRow", fparamD.getAlarmDetailNoPagingStartRow());
		paramsMap.put("_method_", fparamD.getAlarmDetailNoPagingMethod());
		paramsMap.put("pageSize", fparamD.getAlarmDetailNoPagingPageSize());
		paramsMap.put("imeis", this.getAllImeisToString());
		Response fresp=frem.requestSyn(fparamD.getAlarmDetailNoPagingUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		GetAlarmDetailNoPagingApi vGsonValue2=vGson.fromJson(fresp.body().string(), GetAlarmDetailNoPagingApi.class);
		return vGsonValue2;
	}
	private String getAllImeisToString() throws SQLException{
		imeis=conn.connectMySqlM("FROM `sh_user_device_bind` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='18688888888');", "imei", comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		 StringBuilder sb = new StringBuilder();
		  for(int i=0;i<imeis.size();i++){
			  sb.append(imeis.get(i)).append(",");
		  }
		  String imei=sb.toString().substring(0, sb.toString().length()-1);
		  return imei;
	}
}


