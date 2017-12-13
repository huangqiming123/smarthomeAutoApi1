package sign;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import paramdata.ParamData;
import sign.SignUtils;
import testcase.loginAuthentication.TestCase003Login;

public class SignData {
	static SignUtils signU=new SignUtils();
	ParamData paramD=new ParamData();
	Map<String, String> SmsParams = new HashMap<>();
	//TestCase003Login login=new TestCase003Login();
	//得到获取图形验证码sign	 
	public String getKaptchaSign() throws IOException{	
		Map<String, String> KaptchaParams = new HashMap<>();
		KaptchaParams.put("app_key", paramD.appKey());
		KaptchaParams.put("method", paramD.getKaptchaMethod());
		KaptchaParams.put("timestamp", signU.getTimeStamp());
		KaptchaParams.put("clientId", paramD.clientId());
		String KaptchaSign=signU.signTopRequest(KaptchaParams);
		return KaptchaSign;
	}
	//得到获取短信验证码sign错误
	public String getSmsSignError() throws IOException{
		String SmsSignError=this.getSmsSign(paramD.appKey(), paramD.getSmsMethod(), signU.getTimeStamp(), paramD.clientId(), paramD.account(), paramD.captchaError());
		return SmsSignError;
	}
	//得到获取短信验证码sign失败
	public String getSmsSignFail() throws IOException{
		String SmsSignFail=this.getSmsSign(paramD.appKey(), paramD.getSmsMethod(), signU.getTimeStamp(), paramD.clientId(), paramD.account(), paramD.captchaFail());
		return SmsSignFail;
	}
	//得到获取短信验证码sign成功
	public String getSmsSignSuccess() throws IOException{
		String SmsSignRight=this.getSmsSign(paramD.appKey(), paramD.getSmsMethod(), signU.getTimeStamp(), paramD.clientId(), paramD.account(), paramD.captchaRight());
		return SmsSignRight;
	}
	private String getSmsSign(String app_key,String method,String timestamp,String clientId,String account,String captcha) throws IOException{
		Map<String, String> SmsParams = new HashMap<>();
		SmsParams.put("app_key", app_key);
		SmsParams.put("method", method);
		SmsParams.put("timestamp", timestamp);
		SmsParams.put("clientId", clientId);
		SmsParams.put("account", account);
		SmsParams.put("captcha", captcha);
		String SmsSign=signU.signTopRequest(SmsParams);
		return SmsSign;
	}
	//得到获取登录接口sign
	public String getLoginSign() throws IOException{
		Map<String, String> LoginParams = new HashMap<>();
		LoginParams.put("app_key", paramD.appKey());
		LoginParams.put("method", paramD.loginMethod());
		LoginParams.put("timestamp", signU.getTimeStamp());
		LoginParams.put("clientId", paramD.clientId());
		LoginParams.put("account", paramD.account());
		LoginParams.put("edtionType", paramD.loginEdtionType());
		LoginParams.put("captcha", paramD.loginSmsCaptcha());
		LoginParams.put("timeZone", paramD.loginTimeZone());
		LoginParams.put("language", paramD.loginLanguage());
		String LoginSign=signU.signTopRequest(LoginParams);
		return LoginSign;
	}
	//得到刷新tokensign
	public String getRefershTokenSign() throws IOException{
		Map<String, String> RefershTokenParams = new HashMap<>();
		RefershTokenParams.put("accessToken", paramD.token());
		RefershTokenParams.put("timestamp", signU.getTimeStamp());
		RefershTokenParams.put("app_key", paramD.appKey());
		RefershTokenParams.put("method", paramD.refreshTokenMethod());
		String LoginSign=signU.signTopRequest(RefershTokenParams);
		return LoginSign;
	}
	public String getHomeListSign() throws IOException{
		Map<String, String> HomeListParams = new HashMap<>();
		HomeListParams.put("accessToken", paramD.token());
		HomeListParams.put("timestamp", signU.getTimeStamp());
		HomeListParams.put("app_key", paramD.appKey());
		HomeListParams.put("method", paramD.homeListMethod());
		String homeListSign=signU.signTopRequest(HomeListParams);
		return homeListSign;
	}
	public String getSystemListSign() throws IOException{
		Map<String, String> SystemListParams = new HashMap<>();
		SystemListParams.put("accessToken", paramD.token());
		SystemListParams.put("timestamp", signU.getTimeStamp());
		SystemListParams.put("app_key", paramD.appKey());
		SystemListParams.put("method", paramD.homeListMethod());
		SystemListParams.put("pageNum", paramD.homeListMethod());
		SystemListParams.put("pageSize", paramD.homeListMethod());
		String systemListSign=signU.signTopRequest(SystemListParams);
		return systemListSign;
	}
	public String getPayListSign0() throws IOException{
		String payListSign0=this.getPayListSign(paramD.PayListPageNum()[0], paramD.PayListPageSize()[0]);
		return payListSign0;
	}
	public String getPayListSign10() throws IOException{
		String payListSign10=this.getPayListSign(paramD.PayListPageNum()[1], paramD.PayListPageSize()[1]);
		return payListSign10;
	}
	public String getPayListSign20() throws IOException{
		String payListSign20=this.getPayListSign(paramD.PayListPageNum()[2], paramD.PayListPageSize()[2]);
		return payListSign20;
	}
	private String getPayListSign(String pageNum,String pageSize) throws IOException{
		Map<String, String> PayListParams = new HashMap<>();
		PayListParams.put("accessToken", paramD.token());
		PayListParams.put("timestamp", signU.getTimeStamp());
		PayListParams.put("app_key", paramD.appKey());
		PayListParams.put("method", paramD.PayListMethod());
		PayListParams.put("pageNum", pageNum);
		PayListParams.put("pageSize", pageSize);
		String payListSign=signU.signTopRequest(PayListParams);
		return payListSign;
	}
	public String getAlarmListSign10() throws IOException{
		String alarmListSign10=this.getAlarmListSign(paramD.AlarmListPageNum()[0], paramD.AlarmListPageSize()[0]);
		return alarmListSign10;
	}
	public String getAlarmListSign20() throws IOException{
		String AlarmListSign20=this.getAlarmListSign(paramD.AlarmListPageNum()[1], paramD.AlarmListPageSize()[1]);
		return AlarmListSign20;
	}
	private String getAlarmListSign(String pageNum,String pageSize) throws IOException{
		Map<String, String> AlarmListParams = new HashMap<>();
		AlarmListParams.put("accessToken", paramD.token());
		AlarmListParams.put("timestamp", signU.getTimeStamp());
		AlarmListParams.put("app_key", paramD.appKey());
		AlarmListParams.put("method", paramD.AlarmListMethod());
		AlarmListParams.put("pageNum", pageNum);
		AlarmListParams.put("pageSize", pageSize);
		String alarmListSign=signU.signTopRequest(AlarmListParams);
		return alarmListSign;
	}
	public String deleteAlarmSign(String alarmIds) throws IOException{
		Map<String, String> DeleteAlarmParams = new HashMap<>();
		DeleteAlarmParams.put("accessToken", paramD.token());
		DeleteAlarmParams.put("timestamp", signU.getTimeStamp());
		DeleteAlarmParams.put("app_key", paramD.appKey());
		DeleteAlarmParams.put("method", paramD.DeleteAlarmMethod());
		DeleteAlarmParams.put("alarmIds", alarmIds);
		String deleteAlarmSign=signU.signTopRequest(DeleteAlarmParams);
		return deleteAlarmSign;
	}
	public String delAllAlarmSign() throws IOException{
		Map<String, String> DelAllAlarmParams = new HashMap<>();
		DelAllAlarmParams.put("accessToken", paramD.token());
		DelAllAlarmParams.put("timestamp", signU.getTimeStamp());
		DelAllAlarmParams.put("app_key", paramD.appKey());
		DelAllAlarmParams.put("method", paramD.DelAllAlarmMethod());
		String delAllAlarmSign=signU.signTopRequest(DelAllAlarmParams);
		return delAllAlarmSign;
	}
	public String getMyUserSign() throws IOException{
		Map<String, String> GetMyUserParams = new HashMap<>();
		GetMyUserParams.put("accessToken", paramD.token());
		GetMyUserParams.put("timestamp", signU.getTimeStamp());
		GetMyUserParams.put("app_key", paramD.appKey());
		GetMyUserParams.put("method", paramD.GetMyUserMethod());
		String getMyUserSign=signU.signTopRequest(GetMyUserParams);
		return getMyUserSign;
	}
	public String oauthTokenQiniuSign() throws IOException{
		Map<String, String> GetMyUserParams = new HashMap<>();
		GetMyUserParams.put("accessToken", paramD.token());
		GetMyUserParams.put("timestamp", signU.getTimeStamp());
		GetMyUserParams.put("app_key", paramD.appKey());
		GetMyUserParams.put("method", paramD.oauthTokenQiniuMethod());
		String getMyUserSign=signU.signTopRequest(GetMyUserParams);
		return getMyUserSign;
	}
	public String userIconUpdateSign() throws IOException{
		Map<String, String> UserIconUpdateParams = new HashMap<>();
		UserIconUpdateParams.put("accessToken", paramD.token());
		UserIconUpdateParams.put("timestamp", signU.getTimeStamp());
		UserIconUpdateParams.put("app_key", paramD.appKey());
		UserIconUpdateParams.put("method", paramD.userIconUpdateMethod());
		UserIconUpdateParams.put("iconUrl", paramD.userIconUpdateIconUrl());
		String userIconUpdateSign=signU.signTopRequest(UserIconUpdateParams);
		return userIconUpdateSign;
	}
	public String UsernameUpdateSign(String username) throws IOException{
		Map<String, String> UsernameUpdateParams = new HashMap<>();
		UsernameUpdateParams.put("accessToken", paramD.token());
		UsernameUpdateParams.put("timestamp", signU.getTimeStamp());
		UsernameUpdateParams.put("app_key", paramD.appKey());
		UsernameUpdateParams.put("method", paramD.UsernameUpdateMethod());
		UsernameUpdateParams.put("userName", username);
		String usernameUpdateSign=signU.signTopRequest(UsernameUpdateParams);
		return usernameUpdateSign;
	}
	public String userNicknameUpdateSign(String nickName) throws IOException{
		Map<String, String> UserNicknameParams = new HashMap<>();
		UserNicknameParams.put("accessToken", paramD.token());
		UserNicknameParams.put("timestamp", signU.getTimeStamp());
		UserNicknameParams.put("app_key", paramD.appKey());
		UserNicknameParams.put("method", paramD.UserNicknameUpdateMethod());
		UserNicknameParams.put("nickName", nickName);
		String usernameUpdateSign=signU.signTopRequest(UserNicknameParams);
		return usernameUpdateSign;
	}
	public String userCitynameUpdateSign(String cityName) throws IOException{
		Map<String, String> UserCitynameParams = new HashMap<>();
		UserCitynameParams.put("accessToken", paramD.token());
		UserCitynameParams.put("timestamp", signU.getTimeStamp());
		UserCitynameParams.put("app_key", paramD.appKey());
		UserCitynameParams.put("method", paramD.UserCitynameUpdateMethod());
		UserCitynameParams.put("cityName", cityName);
		String citynameUpdateSign=signU.signTopRequest(UserCitynameParams);
		return citynameUpdateSign;
	}
	public String userSexUpdateSign(String sex) throws IOException{
		Map<String, String> UserSexParams = new HashMap<>();
		UserSexParams.put("accessToken", paramD.token());
		UserSexParams.put("timestamp", signU.getTimeStamp());
		UserSexParams.put("app_key", paramD.appKey());
		UserSexParams.put("method", paramD.UserSexUpdateMethod());
		UserSexParams.put("sex", sex);
		String sexUpdateSign=signU.signTopRequest(UserSexParams);
		return sexUpdateSign;
	}
	public String userQaFeedbackSign(String qaType) throws IOException{
		Map<String, String> userQaFeedbackParams = new HashMap<>();
		userQaFeedbackParams.put("accessToken", paramD.token());
		userQaFeedbackParams.put("timestamp", signU.getTimeStamp());
		userQaFeedbackParams.put("app_key", paramD.appKey());
		userQaFeedbackParams.put("method", paramD.UserQaFeedbackMethod());
		userQaFeedbackParams.put("qaType", qaType);
		userQaFeedbackParams.put("context", paramD.UserQaContext());
		userQaFeedbackParams.put("contactPerson", paramD.UserQaContactPerson());
		userQaFeedbackParams.put("contactWay", paramD.UserQacontactWay());
		String userQaFeedbackSign=signU.signTopRequest(userQaFeedbackParams);
		return userQaFeedbackSign;
	}
	public String myUserConfigSign() throws IOException{
		Map<String, String> myUserConfigParams = new HashMap<>();
		myUserConfigParams.put("accessToken", paramD.token());
		myUserConfigParams.put("timestamp", signU.getTimeStamp());
		myUserConfigParams.put("app_key", paramD.appKey());
		myUserConfigParams.put("method", paramD.myUserConfigMethod());
		String myUserConfigSign=signU.signTopRequest(myUserConfigParams);
		return myUserConfigSign;
	}
	public String UpdataMyUserVoiceSwitchSign(String voiceSwitch) throws IOException{
		Map<String, String> updataMyUserVoiceSwitchParams = new HashMap<>();
		updataMyUserVoiceSwitchParams.put("accessToken", paramD.token());
		updataMyUserVoiceSwitchParams.put("timestamp", signU.getTimeStamp());
		updataMyUserVoiceSwitchParams.put("app_key", paramD.appKey());
		updataMyUserVoiceSwitchParams.put("method", paramD.UpdataMyUserVoiceSwitchMethod());
		updataMyUserVoiceSwitchParams.put("voiceSwitch", voiceSwitch);
		String updataMyUserVoiceSwitchSign=signU.signTopRequest(updataMyUserVoiceSwitchParams);
		return updataMyUserVoiceSwitchSign;
	}
	public String updataMyUserSharkSwitchSign(String shakeSwitch) throws IOException{
		Map<String, String> updataMyUserSharkSwitchParams = new HashMap<>();
		updataMyUserSharkSwitchParams.put("accessToken", paramD.token());
		updataMyUserSharkSwitchParams.put("timestamp", signU.getTimeStamp());
		updataMyUserSharkSwitchParams.put("app_key", paramD.appKey());
		updataMyUserSharkSwitchParams.put("method", paramD.UpdataMyUserShakeSwitchMethod());
		updataMyUserSharkSwitchParams.put("shakeSwitch", shakeSwitch);
		String updataMyUserVoiceSwitchSign=signU.signTopRequest(updataMyUserSharkSwitchParams);
		return updataMyUserVoiceSwitchSign;
	}
	public String updataMyUserAlarmSwitchSign(String alarmSwitch) throws IOException{
		Map<String, String> updataMyUserAlarmSwitchSwitchParams = new HashMap<>();
		updataMyUserAlarmSwitchSwitchParams.put("accessToken", paramD.token());
		updataMyUserAlarmSwitchSwitchParams.put("timestamp", signU.getTimeStamp());
		updataMyUserAlarmSwitchSwitchParams.put("app_key", paramD.appKey());
		updataMyUserAlarmSwitchSwitchParams.put("method", paramD.updataMyUserAlarmSwitchMethod());
		updataMyUserAlarmSwitchSwitchParams.put("alarmSwitch", alarmSwitch);
		String updataMyUserAlarmSwitchSwitchSign=signU.signTopRequest(updataMyUserAlarmSwitchSwitchParams);
		return updataMyUserAlarmSwitchSwitchSign;
	}
	public String updataMyUserPushImgSwitchSign(String pushImgSwitch) throws IOException{
		Map<String, String> updataMyUserPushImgSwitchParams = new HashMap<>();
		updataMyUserPushImgSwitchParams.put("accessToken", paramD.token());
		updataMyUserPushImgSwitchParams.put("timestamp", signU.getTimeStamp());
		updataMyUserPushImgSwitchParams.put("app_key", paramD.appKey());
		updataMyUserPushImgSwitchParams.put("method", paramD.updataMyUserPushImgSwitchMethod());
		updataMyUserPushImgSwitchParams.put("pushImgSwitch", pushImgSwitch);
		String updataMyUserPushImgSwitchSign=signU.signTopRequest(updataMyUserPushImgSwitchParams);
		return updataMyUserPushImgSwitchSign;
	}
	public String updataMyUserPushVideoSwitchSign(String pushVideoSwitch) throws IOException{
		Map<String, String> updataMyUserPushVideoSwitchParams = new HashMap<>();
		updataMyUserPushVideoSwitchParams.put("accessToken", paramD.token());
		updataMyUserPushVideoSwitchParams.put("timestamp", signU.getTimeStamp());
		updataMyUserPushVideoSwitchParams.put("app_key", paramD.appKey());
		updataMyUserPushVideoSwitchParams.put("method", paramD.updataMyUserPushVideoSwitchMethod());
		updataMyUserPushVideoSwitchParams.put("pushVideoSwitch", pushVideoSwitch);
		String updataMyUserPushVideoSwitchSign=signU.signTopRequest(updataMyUserPushVideoSwitchParams);
		return updataMyUserPushVideoSwitchSign;
	}
	public String getCsResidueDegreeSign() throws IOException{
		Map<String, String> getCsResidueDegreeParams = new HashMap<>();
		getCsResidueDegreeParams.put("accessToken", paramD.token());
		getCsResidueDegreeParams.put("timestamp", signU.getTimeStamp());
		getCsResidueDegreeParams.put("app_key", paramD.appKey());
		getCsResidueDegreeParams.put("method", paramD.getCsResidueDegreeMethod());
		String getCsResidueDegreeSign=signU.signTopRequest(getCsResidueDegreeParams);
		return getCsResidueDegreeSign;
	}
	public String getCsAlarmSetListSign() throws IOException{
		Map<String, String> getCsAlarmSetListParams = new HashMap<>();
		getCsAlarmSetListParams.put("accessToken", paramD.token());
		getCsAlarmSetListParams.put("timestamp", signU.getTimeStamp());
		getCsAlarmSetListParams.put("app_key", paramD.appKey());
		getCsAlarmSetListParams.put("method", paramD.getCsAlarmSetListMethod());
		String getCsAlarmSetListSign=signU.signTopRequest(getCsAlarmSetListParams);
		return getCsAlarmSetListSign;
	}
	public String getCsAlarmGetAlarmTypeSign() throws IOException{
		Map<String, String> getAlarmTypesParams = new HashMap<>();
		getAlarmTypesParams.put("accessToken", paramD.token());
		getAlarmTypesParams.put("timestamp", signU.getTimeStamp());
		getAlarmTypesParams.put("app_key", paramD.appKey());
		getAlarmTypesParams.put("method", paramD.getAlarmTypesMethod());
		String getAlarmTypesSign=signU.signTopRequest(getAlarmTypesParams);
		return getAlarmTypesSign;
	}
	public String creatCsAlarmSetSign(String receiveNumber1,String receiveNumber2,String callStatus,String smsStatus) throws IOException{
		Map<String, String> creatCsAlarmSetParams = new HashMap<>();
		creatCsAlarmSetParams.put("accessToken", paramD.token());
		creatCsAlarmSetParams.put("timestamp", signU.getTimeStamp());
		creatCsAlarmSetParams.put("app_key", paramD.appKey());
		creatCsAlarmSetParams.put("method", paramD.creatCsAlarmSetMethod());
		creatCsAlarmSetParams.put("alarmTypes", paramD.creatCsAlarmSetAlarmTypes());
		creatCsAlarmSetParams.put("receiveNumber1", receiveNumber1);
		creatCsAlarmSetParams.put("receiveNumber2", receiveNumber2);
		creatCsAlarmSetParams.put("callStatus", callStatus);
		creatCsAlarmSetParams.put("smsStatus",smsStatus);
		String creatCsAlarmSetSign=signU.signTopRequest(creatCsAlarmSetParams);
		return creatCsAlarmSetSign;
	}
	public String updateCsAlarmSetSign(String vsAlarmId,String receiveNumber1,String receiveNumber2,String callStatus,String smsStatus) throws IOException{
		Map<String, String> updateCsAlarmSetParams = new HashMap<>();
		updateCsAlarmSetParams.put("accessToken", paramD.token());
		updateCsAlarmSetParams.put("timestamp", signU.getTimeStamp());
		updateCsAlarmSetParams.put("app_key", paramD.appKey());
		updateCsAlarmSetParams.put("method", paramD.updateCsAlarmSetMethod());
		updateCsAlarmSetParams.put("vsAlarmId", vsAlarmId);
		updateCsAlarmSetParams.put("alarmTypes", paramD.creatCsAlarmSetAlarmTypes());
		updateCsAlarmSetParams.put("receiveNumber1", receiveNumber1);
		updateCsAlarmSetParams.put("receiveNumber2", receiveNumber2);
		updateCsAlarmSetParams.put("callStatus", callStatus);
		updateCsAlarmSetParams.put("smsStatus",smsStatus);
		String updateCsAlarmSetSign=signU.signTopRequest(updateCsAlarmSetParams);
		return updateCsAlarmSetSign;
	}
	public String deleteCsAlarmSetSign(String vsAlarmId) throws IOException{
		Map<String, String> deleteCsAlarmSetParams = new HashMap<>();
		deleteCsAlarmSetParams.put("accessToken", paramD.token());
		deleteCsAlarmSetParams.put("timestamp", signU.getTimeStamp());
		deleteCsAlarmSetParams.put("app_key", paramD.appKey());
		deleteCsAlarmSetParams.put("method", paramD.deleteCsAlarmSetMethod());
		deleteCsAlarmSetParams.put("vsAlarmId", vsAlarmId);
		String deleteCsAlarmSetSign=signU.signTopRequest(deleteCsAlarmSetParams);
		return deleteCsAlarmSetSign;
	}
	public String bindCsAlarmAndImeisSign(String imeis,String vsAlarmId) throws IOException{
		Map<String, String> bindCsAlarmAndImeisParams = new HashMap<>();
		bindCsAlarmAndImeisParams.put("accessToken", paramD.token());
		bindCsAlarmAndImeisParams.put("timestamp", signU.getTimeStamp());
		bindCsAlarmAndImeisParams.put("app_key", paramD.appKey());
		bindCsAlarmAndImeisParams.put("method", paramD.BindCsAlarmAndImeisMethod());
		bindCsAlarmAndImeisParams.put("imeis", imeis);
		bindCsAlarmAndImeisParams.put("vsAlarmId", vsAlarmId);
		String bindCsAlarmAndImeisSign=signU.signTopRequest(bindCsAlarmAndImeisParams);
		return bindCsAlarmAndImeisSign;
	}
	public String getMyPayListNoParamSign() throws IOException{
		Map<String, String> getMyPayListParams = new HashMap<>();
		getMyPayListParams.put("accessToken", paramD.token());
		getMyPayListParams.put("timestamp", signU.getTimeStamp());
		getMyPayListParams.put("app_key", paramD.appKey());
		getMyPayListParams.put("method", paramD.getMyPayListMethod());
		getMyPayListParams.put("pageNum", paramD.getMyPayListPageNumber()[0]);
		getMyPayListParams.put("pageSize", paramD.getMyPayListPageSize());
		String getMyPayListSign=signU.signTopRequest(getMyPayListParams);
		return getMyPayListSign;
	}
	public String getMyPayListSign(String pageNum) throws IOException{
		Map<String, String> getMyPayListParams = new HashMap<>();
		getMyPayListParams.put("accessToken", paramD.token());
		getMyPayListParams.put("timestamp", signU.getTimeStamp());
		getMyPayListParams.put("app_key", paramD.appKey());
		getMyPayListParams.put("method", paramD.getMyPayListMethod());
		getMyPayListParams.put("phone", paramD.getMyPayListPhone());
		getMyPayListParams.put("alarmPushType", paramD.getMyPayListAlarmPushType());
		getMyPayListParams.put("alarmType", paramD.getMyPayListAlarmType());
		getMyPayListParams.put("startTime", paramD.getMyPayListStartTime());
		getMyPayListParams.put("endTime", paramD.getMyPayListEndTime());
		getMyPayListParams.put("pageNum", pageNum);
		getMyPayListParams.put("pageSize", paramD.getMyPayListPageSize());
		String getMyPayListSign=signU.signTopRequest(getMyPayListParams);
		return getMyPayListSign;
	}
	public static void main(String[] args) throws IOException{
		SignData signD=new SignData();
		System.out.println(signD.getLoginSign());
		System.out.println(signU.getTimeStamp());
	}
}
	

