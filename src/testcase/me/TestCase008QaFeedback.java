package testcase.me;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import dataformate.QaFeedbackApi;
import dataformate.UserSexUpdateApi;
import okhttp.RequestManager;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import sign.SignData;
import sign.SignUtils;

public class TestCase008QaFeedback {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	@Test
	public void testUserSexUpdate() throws IOException, SQLException{
		for(int i=0;i<paramD.UserQaType().length;i++){
		QaFeedbackApi vGsonValue1=this.QaFeedback(paramD.UserQaType()[i]);
		Assert.assertEquals(vGsonValue1.code, 0,"修改QaFeedback code码错误");
		Assert.assertEquals(vGsonValue1.message, "操作成功","修改QaFeedback message错误");
		
		
	}
	}
	private QaFeedbackApi QaFeedback(String qaType) throws IOException{
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("accessToken", paramD.token());
		paramsMap.put("timestamp", signU.getTimeStamp());
		paramsMap.put("sign", signD.userQaFeedbackSign(qaType));
		paramsMap.put("app_key", paramD.appKey());
		paramsMap.put("method", paramD.UserQaFeedbackMethod());
		paramsMap.put("qaType", qaType);
		paramsMap.put("context", paramD.UserQaContext());
		paramsMap.put("contactPerson", paramD.UserQaContactPerson());
		paramsMap.put("contactWay", paramD.UserQacontactWay());
		Response resp=rem.requestSyn(paramD.getKatchaUrl(), 2, paramsMap);
		Gson vGson=new Gson();
		QaFeedbackApi vGsonValue1=vGson.fromJson(resp.body().string(), QaFeedbackApi.class);
		return vGsonValue1;
	}

}
