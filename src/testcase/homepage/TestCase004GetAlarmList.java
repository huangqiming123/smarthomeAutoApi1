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

import dataformate.AlarmListApi;
import dataformate.GetAlarmDetailNoPagingApi;
import dataformate.HomeListApi;
import model.ConnectMysql;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.FrameParamData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;
import okhttp.FrameRequestManager;
import okhttp.RequestManager;

public class TestCase004GetAlarmList {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	FrameRequestManager frem=new FrameRequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	FrameParamData fparamD=new FrameParamData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	ComData comD=new ComData();
	List<String> deviceNames=new ArrayList<String>();
	@Test
	public void testAlarmList10() throws JsonSyntaxException, IOException, SQLException{
		AlarmListApi vGsonValue1=this.alarmList(paramD.token(), signU.getTimeStamp(), signD.getAlarmListSign10(), paramD.appKey(), paramD.AlarmListMethod(),paramD.AlarmListPageNum()[0], paramD.AlarmListPageSize()[0]);
	    Assert.assertEquals(vGsonValue1.code, 0,"告警列表：code码错误");
	    Assert.assertEquals(vGsonValue1.message, "success","告警列表:message错误");
	    GetAlarmDetailNoPagingApi vGsonValue2=this.getAlarmDetailNoPaging(fparamD.getAlarmDetailNoPagingStartRow(),fparamD.getAlarmDetailNoPagingMethod(),paramD.AlarmListPageSize()[0]);
	    Assert.assertEquals(vGsonValue2.code, 0,"架构告警接口：code码错误");
	    Assert.assertEquals(vGsonValue1.result.length, vGsonValue2.data.length,"告警列表和接口数据数量不一致");
	    for(int i=0;i<vGsonValue1.result.length;i++){
	    	Assert.assertEquals(vGsonValue1.result[i].id, vGsonValue2.data[i].id,"告警列表和接口数据id不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].imei, vGsonValue2.data[i].imei,"告警列表和接口数据imei不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].alarmType, vGsonValue2.data[i].alarmType,"告警列表和接口数据alarmType不一致");
	    	if(vGsonValue2.data[i].status.equals("stayAlert")){
				vGsonValue2.data[i].status="停留告警";
			//Assert.assertEquals(vGsonValue.result.alarms.alarmName, vGsonValue2.data[0].status,"告警名错误");
			}else if(vGsonValue2.data[i].status.equals("ACC_OFF")){
				vGsonValue2.data[i].status="ACC关闭 ";
			}else if(vGsonValue2.data[i].status.equals("ACC_ON")){
				vGsonValue2.data[i].status="ACC开启 ";
			}else if(vGsonValue2.data[i].status.equals("VibrationAlert")){
				vGsonValue2.data[i].status="震动告警";
			}else if(vGsonValue2.data[i].status.equals("offline")){
				vGsonValue2.data[i].status="离线告警";
			}
	    	Assert.assertEquals(vGsonValue1.result[i].alarmName, vGsonValue2.data[i].status,"告警列表和接口数据告警名不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].lat, vGsonValue2.data[i].lat,"告警列表和接口数据纬度不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].lng, vGsonValue2.data[i].lng,"告警列表和接口数据经度不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].alarmTime, vGsonValue2.data[i].pushTime,"告警列表和接口数据告警时间不一致");
	    	deviceNames=conn.connectMySqlM(sqlD.alarmListDataGetDeviceName(vGsonValue1.result[i].imei)[0], sqlD.alarmListDataGetDeviceName(vGsonValue1.result[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
	    	Assert.assertEquals(vGsonValue1.result[i].deviceName,deviceNames.get(0),"告警列表和接口数据设备名称不一致");
	    }
	}
	@Test
	public void testAlarmList20() throws JsonSyntaxException, IOException, SQLException{
		AlarmListApi vGsonValue1=this.alarmList(paramD.token(), signU.getTimeStamp(), signD.getAlarmListSign20(), paramD.appKey(), paramD.AlarmListMethod(),paramD.AlarmListPageNum()[1], paramD.AlarmListPageSize()[1]);
	    Assert.assertEquals(vGsonValue1.code, 0,"告警列表：code码错误");
	    Assert.assertEquals(vGsonValue1.message, "success","告警列表:message错误");
	    GetAlarmDetailNoPagingApi vGsonValue2=this.getAlarmDetailNoPaging(fparamD.getAlarmDetailNoPagingStartRow20(),fparamD.getAlarmDetailNoPagingMethod(),paramD.AlarmListPageSize()[1]);
	    Assert.assertEquals(vGsonValue2.code, 0,"架构告警接口：code码错误");
	    Assert.assertEquals(vGsonValue1.result.length, vGsonValue2.data.length,"告警列表和接口数据数量不一致");
	    for(int i=0;i<vGsonValue1.result.length;i++){
	    	Assert.assertEquals(vGsonValue1.result[i].id, vGsonValue2.data[i].id,"告警列表和接口数据id不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].imei, vGsonValue2.data[i].imei,"告警列表和接口数据imei不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].alarmType, vGsonValue2.data[i].alarmType,"告警列表和接口数据alarmType不一致");
	    	if(vGsonValue2.data[i].status.equals("stayAlert")){
				vGsonValue2.data[i].status="停留告警";
			//Assert.assertEquals(vGsonValue.result.alarms.alarmName, vGsonValue2.data[0].status,"告警名错误");
			}else if(vGsonValue2.data[i].status.equals("ACC_OFF")){
				vGsonValue2.data[i].status="ACC关闭 ";
			}else if(vGsonValue2.data[i].status.equals("ACC_ON")){
				vGsonValue2.data[i].status="ACC开启 ";
			}else if(vGsonValue2.data[i].status.equals("VibrationAlert")){
				vGsonValue2.data[i].status="震动告警";
			}else if(vGsonValue2.data[i].status.equals("offline")){
				vGsonValue2.data[i].status="离线告警";
			}
	    	Assert.assertEquals(vGsonValue1.result[i].alarmName, vGsonValue2.data[i].status,"告警列表和接口数据告警名不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].lat, vGsonValue2.data[i].lat,"告警列表和接口数据纬度不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].lng, vGsonValue2.data[i].lng,"告警列表和接口数据经度不一致");
	    	Assert.assertEquals(vGsonValue1.result[i].alarmTime, vGsonValue2.data[i].pushTime,"告警列表和接口数据告警时间不一致");
	    	deviceNames=conn.connectMySqlM(sqlD.alarmListDataGetDeviceName(vGsonValue1.result[i].imei)[0], sqlD.alarmListDataGetDeviceName(vGsonValue1.result[i].imei)[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
	    	Assert.assertEquals(vGsonValue1.result[i].deviceName,deviceNames.get(0),"告警列表和接口数据设备名称不一致");
	    }
	}
	private AlarmListApi alarmList(String accessToken,String timestamp,String sign,String app_key,String method,String pageNum,String pageSize) throws JsonSyntaxException, IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", accessToken);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("sign", sign);
		paramsMap.put("app_key", app_key);
		paramsMap.put("method", method);
		paramsMap.put("pageNum", pageNum);
		paramsMap.put("pageSize", pageSize);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 0, paramsMap);
		Gson vGson=new Gson();
		AlarmListApi vGsonValue1=vGson.fromJson(resp.body().string(), AlarmListApi.class);
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
