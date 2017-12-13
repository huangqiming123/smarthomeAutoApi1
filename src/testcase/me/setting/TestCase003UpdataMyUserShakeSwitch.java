package testcase.me.setting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.UpdataMyUserShakeSwitchApi;
import model.ConnectMysql;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;

public class TestCase003UpdataMyUserShakeSwitch {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> myConfig=new ArrayList<String>();
	@Test
	public void testUpdataMyUserSharkSwitch() throws IOException, SQLException{
		for(int i=0;i<paramD.UpdataMyUserShakeSwitchParam().length;i++){
		UpdataMyUserShakeSwitchApi vGsonValue1=this.updataMyUserShakeSwitch(paramD.UpdataMyUserShakeSwitchParam()[i]);
		Assert.assertEquals(vGsonValue1.code, 0,"UpdataMyUserSharkSwitch code码错误");
		Assert.assertEquals(vGsonValue1.message, "操作成功","UpdataMyUserSharkSwitch message错误");
		myConfig=conn.connectMySqlM(sqlD.settingMyUserConfig()[0], sqlD.settingMyUserConfig()[1], comD.connectOperateLib()[0], comD.connectOperateLib()[1], comD.connectOperateLib()[2]);
		Assert.assertEquals(myConfig.get(1), paramD.UpdataMyUserShakeSwitchParam()[i],"UpdataMyUserSharkSwitch设置错误");
		}
		
	}
	private UpdataMyUserShakeSwitchApi updataMyUserShakeSwitch(String shakeSwitch) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.updataMyUserSharkSwitchSign(shakeSwitch));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.UpdataMyUserShakeSwitchMethod());
		paramsMap.put("shakeSwitch", shakeSwitch);
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		UpdataMyUserShakeSwitchApi vGsonValue1=vGson.fromJson(resp.body().string(), UpdataMyUserShakeSwitchApi.class);
		return vGsonValue1;
	}

}
