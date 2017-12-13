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

import dataformate.PayListApi;
import dataformate.SystemListApi;
import model.ConnectMysql;
import okhttp3.Response;
import paramdata.ComData;
import paramdata.ParamData;
import paramdata.SqlData;
import sign.SignData;
import sign.SignUtils;
import okhttp.RequestManager;

public class TestCase003GetPayList {
	SignData signD=new SignData();
	RequestManager rem=new RequestManager();
	SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	ComData comD=new ComData();
	ConnectMysql conn=new ConnectMysql();
	SqlData sqlD=new SqlData();
	List<String> id10=new ArrayList<String>();
	List<String> tradeNo10=new ArrayList<String>();
	List<String> tradeType10=new ArrayList<String>();
	List<String> userId10=new ArrayList<String>();
	List<String> payMethod10=new ArrayList<String>();
	List<String> productDetail10=new ArrayList<String>();
	List<String> status10=new ArrayList<String>();
	List<String> amount10=new ArrayList<String>();
	List<String> payTime10=new ArrayList<String>();
	List<String> markNum10=new ArrayList<String>();
	
	List<String> id20=new ArrayList<String>();
	List<String> tradeNo20=new ArrayList<String>();
	List<String> tradeType20=new ArrayList<String>();
	List<String> userId20=new ArrayList<String>();
	List<String> payMethod20=new ArrayList<String>();
	List<String> productDetail20=new ArrayList<String>();
	List<String> status20=new ArrayList<String>();
	List<String> amount20=new ArrayList<String>();
	List<String> payTime20=new ArrayList<String>();
	List<String> markNum20=new ArrayList<String>();
	
	
	public void testSystemList0() throws JsonSyntaxException, IOException{
		PayListApi vGsonValue=this.payList(paramD.token(), signU.getTimeStamp(), signD.getPayListSign0(), paramD.appKey(), paramD.PayListMethod(), paramD.PayListPageNum()[0], paramD.PayListPageSize()[0]);
		Assert.assertEquals(vGsonValue.code, 0,"paylist:code码错误");
		//Assert.assertEquals(vGsonValue.message, 0,"paylist:code码错误");
		System.out.println(vGsonValue.message);
		//System.out.println(vGsonValue.result.id);
	}
	
	public void testSystemList10() throws JsonSyntaxException, IOException, SQLException{
		PayListApi vGsonValue=this.payList(paramD.token(), signU.getTimeStamp(), signD.getPayListSign10(), paramD.appKey(), paramD.PayListMethod(), paramD.PayListPageNum()[1], paramD.PayListPageSize()[1]);
		Assert.assertEquals(vGsonValue.code, 0,"paylist:code码错误");
		Assert.assertEquals(vGsonValue.message, "success","paylist:message错误");
		id10=conn.connectMySqlM(sqlD.payListDataGet10Id()[0], sqlD.payListDataGet10Id()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		tradeNo10=conn.connectMySqlM(sqlD.payListDataGet10TradeNo()[0], sqlD.payListDataGet10TradeNo()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		tradeType10=conn.connectMySqlM(sqlD.payListDataGet10TradeType()[0], sqlD.payListDataGet10TradeType()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		userId10=conn.connectMySqlM(sqlD.payListDataGet10userId()[0], sqlD.payListDataGet10userId()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		payMethod10=conn.connectMySqlM(sqlD.payListDataGet10payMethod()[0], sqlD.payListDataGet10payMethod()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		productDetail10=conn.connectMySqlM(sqlD.payListDataGet10productDetail()[0], sqlD.payListDataGet10productDetail()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		status10=conn.connectMySqlM(sqlD.payListDataGet10status()[0], sqlD.payListDataGet10status()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		amount10=conn.connectMySqlM(sqlD.payListDataGet10amount()[0], sqlD.payListDataGet10amount()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		payTime10=conn.connectMySqlM(sqlD.payListDataGet10payTime()[0], sqlD.payListDataGet10payTime()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		markNum10=conn.connectMySqlM(sqlD.payListDataGet10markNum()[0], sqlD.payListDataGet10markNum()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		for(int i=0;i<id10.size();i++){
			Assert.assertEquals(vGsonValue.result[i].id, id10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].tradeNo, tradeNo10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].tradeType, tradeType10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].userId, userId10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].payMethod, payMethod10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].productDetail, productDetail10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].status, status10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].amount, amount10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].payTime+".0", payTime10.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].markNum, markNum10.get(i),"paylist:id错误");
		}
		
	}
	
	@Test
	public void testSystemList20() throws JsonSyntaxException, IOException, SQLException{
		PayListApi vGsonValue=this.payList(paramD.token(), signU.getTimeStamp(), signD.getPayListSign20(), paramD.appKey(), paramD.PayListMethod(), paramD.PayListPageNum()[2], paramD.PayListPageSize()[2]);
		Assert.assertEquals(vGsonValue.code, 0,"paylist:code码错误");
		Assert.assertEquals(vGsonValue.message, "success","paylist:message错误");
		id20=conn.connectMySqlM(sqlD.payListDataGet20Id()[0], sqlD.payListDataGet20Id()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		tradeNo20=conn.connectMySqlM(sqlD.payListDataGet20TradeNo()[0], sqlD.payListDataGet20TradeNo()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		tradeType20=conn.connectMySqlM(sqlD.payListDataGet20TradeType()[0], sqlD.payListDataGet20TradeType()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		userId20=conn.connectMySqlM(sqlD.payListDataGet20userId()[0], sqlD.payListDataGet20userId()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		payMethod20=conn.connectMySqlM(sqlD.payListDataGet20payMethod()[0], sqlD.payListDataGet20payMethod()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		productDetail20=conn.connectMySqlM(sqlD.payListDataGet20productDetail()[0], sqlD.payListDataGet20productDetail()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		status20=conn.connectMySqlM(sqlD.payListDataGet20status()[0], sqlD.payListDataGet20status()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		amount20=conn.connectMySqlM(sqlD.payListDataGet20amount()[0], sqlD.payListDataGet20amount()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		payTime20=conn.connectMySqlM(sqlD.payListDataGet20payTime()[0], sqlD.payListDataGet20payTime()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		markNum20=conn.connectMySqlM(sqlD.payListDataGet20markNum()[0], sqlD.payListDataGet20markNum()[1], comD.connectOperateLib()[0],comD.connectOperateLib()[1],comD.connectOperateLib()[2]);
		for(int i=0;i<id20.size();i++){
			Assert.assertEquals(vGsonValue.result[i].id, id20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].tradeNo, tradeNo20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].tradeType, tradeType20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].userId, userId20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].payMethod, payMethod20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].productDetail, productDetail20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].status, status20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].amount, amount20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].payTime+".0", payTime20.get(i),"paylist:id错误");
			Assert.assertEquals(vGsonValue.result[i].markNum, markNum20.get(i),"paylist:id错误");
		}
		
	}
	
	private PayListApi payList(String accessToken,String timestamp,String sign,String app_key,String method,String pageNum,String pageSize) throws JsonSyntaxException, IOException{
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
		PayListApi vGsonValue1=vGson.fromJson(resp.body().string(), PayListApi.class);
		return vGsonValue1;
	}

}
