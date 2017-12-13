package paramdata;

public class SqlData {
	ParamData paramD=new ParamData();
	public String[] loginDataGetAccountInfo(){
		String[] loginData={"FROM `sh_user_info` AS u LEFT JOIN sh_user_device_bind AS d ON u.userId=d.userId WHERE u.account='"+paramD.account()+"';","u.userId,u.account,u.sex,u.userName,u.nickName,u.cityName,u.iconUrl,COUNT(d.imei),u.alarmSwitch"};
		return loginData;
	}
	public String[] loginDataGetIsHasBind(){
		String[] getIsHasBind={"FROM `sh_user_device_bind` AS b WHERE b.userId=(SELECT u.userId FROM sh_user_info AS u WHERE u.account='"+paramD.account()+"') AND b.enabledFlag='1';","COUNT(b.imei)"};
		return getIsHasBind;
	}
	public String[] homeListDataGetImei(){
		String[] GetImei={"FROM `sh_user_device_bind` AS b WHERE b.userId=(SELECT u.userId from sh_user_info AS u WHERE u.account='"+paramD.account()+"') AND b.enabledFlag='1';","b.imei"};
		return GetImei;
	}
	public String[] homeListDataGetName(String imei){
		String[] getName={"FROM sh_user_device_bind AS b WHERE b.imei='"+imei+"' AND b.userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') AND b.enabledFlag='1';","b.`name`"};
		return getName;
	}
	public String[] homeListDataGetMcTypeUseScope(String imei){
		String[] getMcTypeUseScope={"FROM t_sys_dictionary WHERE `key`=(SELECT mcType FROM t_dev_device_info WHERE imei='"+imei+"');","ext2,equipType"};
		return getMcTypeUseScope;
	}
	public String[] homeListDataGetStatus(String imei){
		String[] getStatus={"FROM `device_login` WHERE imei='"+imei+"';","lastLoginTime<lastLogoutTime"};
		return getStatus;
	}
	public String[] homeListDataGetStatus1(String imei){
		String[] getStatus1={"FROM `device_login` WHERE imei='"+imei+"';","COUNT(lastLoginTime)"};
		return getStatus1;
	}
	public String[] homeListDataGetStatus2(String imei){
		String[] getStatus2={"FROM `device_login` WHERE imei='"+imei+"';","COUNT(lastLogoutTime)"};
		return getStatus2;
	}
	public String[] homeListDataGetExpiration(String imei){
		String[] getExpiration={"FROM `equipment_mostly` WHERE imei='"+imei+"';","expiration"};
		return getExpiration;
	}
	public String[] homeListDataGetExpirationFlag(String imei){
		String[] getExpirationFlag={"FROM `equipment_mostly` WHERE imei='"+imei+"';","expiration"};
		return getExpirationFlag;
	}
	public String[] homeListDataGetExpirationFlag1(String imei){
		String[] getExpirationFlag1={"FROM `equipment_mostly` WHERE imei='"+imei+"';","expiration>NOW()"};
		return getExpirationFlag1;
	}
	public String[] homeListDataGetShutDownStatus(String imei){
		String[] getExpirationShutDownStatus={"FROM `equipment_mostly` WHERE imei='"+imei+"';","status,sim,isLife "};
		return getExpirationShutDownStatus;
	}
	public String[] homeListDataGetHbTime(String imei,String hbtime){
		String[] getHbTime={"FROM device_login WHERE imei='"+imei+"';","TO_SECONDS(lastLogoutTime)-TO_SECONDS('"+hbtime+"')<=601"};
		return getHbTime;
	}
	public String[] homeListDataGetOthers(String imei){
		String[] getOthers={"FROM `t_sys_dictionary` WHERE `key`=(SELECT mcType FROM t_dev_device_info WHERE imei='"+imei+"');","shakeTapeFlag,photoFlag,cameraFlag,videoFlag"};
		return getOthers;
	}
	public String[] homeListDataGetImeis(){
		String[] getOthers={"FROM `sh_user_device_bind` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"');","imei"};
		return getOthers;
	}
	public String[] homeListDataGetPayVoucher(){
		String[] getPayVoucher={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY createTime DESC LIMIT 1;","productDetail,markNum,payTime"};
		return getPayVoucher;
	}
	public String[] payListDataGet10Id(){
		String[] get10Id={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","id"};
		return get10Id;
	}
	public String[] payListDataGet10TradeNo(){
		String[] get10TradeNo={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","tradeNo"};
		return get10TradeNo;
	}
	public String[] payListDataGet10TradeType(){
		String[] get10TradeType={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","tradeType"};
		return get10TradeType;
	}
	public String[] payListDataGet10userId(){
		String[] get10UserId={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","userId"};
		return get10UserId;
	}
	public String[] payListDataGet10payMethod(){
		String[] get10payMethod={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","payMethod"};
		return get10payMethod;
	}
	public String[] payListDataGet10productDetail(){
		String[] get10productDetail={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","productDetail"};
		return get10productDetail;
	}
	public String[] payListDataGet10status(){
		String[] get10status={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","status"};
		return get10status;
	}
	public String[] payListDataGet10amount(){
		String[] get10amount={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","amount"};
		return get10amount;
	}
	public String[] payListDataGet10payTime(){
		String[] get10payTime={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 10;","payTime"};
		return get10payTime;
	}
	public String[] payListDataGet10markNum(){
		String[] get10markNum={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","markNum"};
		return get10markNum;
	}
	
	public String[] payListDataGet20Id(){
		String[] get10Id={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","id"};
		return get10Id;
	}
	public String[] payListDataGet20TradeNo(){
		String[] get10TradeNo={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","tradeNo"};
		return get10TradeNo;
	}
	public String[] payListDataGet20TradeType(){
		String[] get10TradeType={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","tradeType"};
		return get10TradeType;
	}
	public String[] payListDataGet20userId(){
		String[] get10UserId={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","userId"};
		return get10UserId;
	}
	public String[] payListDataGet20payMethod(){
		String[] get10payMethod={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","payMethod"};
		return get10payMethod;
	}
	public String[] payListDataGet20productDetail(){
		String[] get10productDetail={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","productDetail"};
		return get10productDetail;
	}
	public String[] payListDataGet20status(){
		String[] get10status={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","status"};
		return get10status;
	}
	public String[] payListDataGet20amount(){
		String[] get10amount={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","amount"};
		return get10amount;
	}
	public String[] payListDataGet20payTime(){
		String[] get10payTime={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","payTime"};
		return get10payTime;
	}
	public String[] payListDataGet20markNum(){
		String[] get10markNum={"FROM `sh_pay_token` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') ORDER BY payTime DESC LIMIT 20,20;","markNum"};
		return get10markNum;
	}
	public String[] alarmListDataGetDeviceName(String imei){
		String[] getDeviceName={"FROM `sh_user_device_bind` WHERE imei='"+imei+"' AND userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"');","name"};
		return getDeviceName;
	}
	public String[] meGetUserInfo(){
		String[] GetUserInfo={"FROM `sh_user_info` AS u  WHERE u.account='"+paramD.account()+"';","u.account,u.sex,u.userName,u.nickName,u.cityName,u.iconUrl"};
		return GetUserInfo;
	}
	public String[] meGetUserInfoUsername(){
		String[] GetUserInfoUsername={"FROM `sh_user_info` AS u  WHERE u.account='"+paramD.account()+"';","u.userName"};
		return GetUserInfoUsername;
	}
	public String[] meGetUserInfoNickname(){
		String[] GetUserInfoNikname={"FROM `sh_user_info` AS u  WHERE u.account='"+paramD.account()+"';","u.nickName"};
		return GetUserInfoNikname;
	}
	public String[] meGetUserInfoCityname(){
		String[] GetUserInfoCityname={"FROM `sh_user_info` AS u  WHERE u.account='"+paramD.account()+"';","u.cityName"};
		return GetUserInfoCityname;
	}
	public String[] meGetUserInfoSex(){
		String[] GetUserInfoSex={"FROM `sh_user_info` AS u  WHERE u.account='"+paramD.account()+"';","u.sex"};
		return GetUserInfoSex;
	}
	public String[] settingMyUserConfig(){
		String[] GetMyUserConfig={"FROM `sh_user_info` AS u  WHERE u.account='"+paramD.account()+"';","u.voiceSwitch,u.shakeSwitch,u.pushImgSwitch,u.pushVideoSwitch,u.alarmSwitch"};
		return GetMyUserConfig;
	}
	public String[] callAndSmsAlarmGetCsResidueDegree(){
		String[] GetCsResidueDegree={"FROM `sh_recharge_card_stock` WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"');","callNumber,smsNumber"};
		return GetCsResidueDegree;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetId(){
		String[] GetCsAlarmSetListGetId={"FROM `sh_vs_alarmset` AS v  WHERE v.userId=(SELECT userId FROM sh_user_info   WHERE account='"+paramD.account()+"');","v.id"};
		return GetCsAlarmSetListGetId;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetAlarmTypes(){
		String[] GetCsAlarmSetListGetAlarmTypes={"FROM `sh_vs_alarmset` AS v  WHERE v.userId=(SELECT userId FROM sh_user_info   WHERE account='"+paramD.account()+"');","v.alarmTypes"};
		return GetCsAlarmSetListGetAlarmTypes;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetImeis(){
		String[] GetCsAlarmSetListGetImeis={"FROM `sh_vs_alarmset` AS v  WHERE v.userId=(SELECT userId FROM sh_user_info   WHERE account='"+paramD.account()+"');","v.imeis"};
		return GetCsAlarmSetListGetImeis;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetCallStatus(){
		String[] GetCsAlarmSetListGetCallStatus={"FROM `sh_vs_alarmset` AS v  WHERE v.userId=(SELECT userId FROM sh_user_info   WHERE account='"+paramD.account()+"');","v.callStatus"};
		return GetCsAlarmSetListGetCallStatus;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetSmsStatus(){
		String[] GetCsAlarmSetListGetSmsStatus={"FROM `sh_vs_alarmset` AS v  WHERE v.userId=(SELECT userId FROM sh_user_info   WHERE account='"+paramD.account()+"');","v.smsStatus"};
		return GetCsAlarmSetListGetSmsStatus;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetReceiveNumber1(){
		String[] GetCsAlarmSetListGetReceiveNumber1={"FROM `sh_vs_alarmset` AS v  WHERE v.userId=(SELECT userId FROM sh_user_info   WHERE account='"+paramD.account()+"');","v.receiveNumber1"};
		return GetCsAlarmSetListGetReceiveNumber1;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetReceiveNumber2(){
		String[] GetCsAlarmSetListGetReceiveNumber2={"FROM `sh_vs_alarmset` AS v  WHERE v.userId=(SELECT userId FROM sh_user_info   WHERE account='"+paramD.account()+"');","v.receiveNumber2"};
		return GetCsAlarmSetListGetReceiveNumber2;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetDeviceName(String imei){
		String[] GetCsAlarmSetListGetDevicename={"FROM sh_user_device_bind WHERE imei='"+imei+"';","name"};
		return GetCsAlarmSetListGetDevicename;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetValue_zh(String id){
		String[] GetCsAlarmSetListGetValue_zh={"FROM alarm_type_configure WHERE id="+"'"+id+"'","DISTINCT value_zh"};
		return GetCsAlarmSetListGetValue_zh;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetAllValue_zh(String id){
		String[] GetCsAlarmSetListGetValue_zh={"FROM alarm_type_configure WHERE id in "+"("+"'"+id+"''"+")","DISTINCT value_zh"};
		return GetCsAlarmSetListGetValue_zh;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetImeiName(String imei){
		String[] GetCsAlarmSetListGetImeiName={"FROM sh_user_device_bind WHERE imei ="+"'"+imei+"' AND userId=(SELECT userId from sh_user_info WHERE account='"+paramD.account()+"')","name"};
		return GetCsAlarmSetListGetImeiName;
	}
	public String[] callAndSmsAlarmGetCsAlarmSetListGetAllImeiName(String imei){
		String[] GetCsAlarmSetListGetAllImei={"FROM sh_user_device_bind WHERE imei IN "+"("+"'"+imei+"''"+") AND userId=(SELECT userId from sh_user_info WHERE account='"+paramD.account()+"')","name"};
		return GetCsAlarmSetListGetAllImei;
	}
	public String[] callAndSmsAlarmCreatCsAlarmSet(){
		String[] creatCsAlarmSet={"FROM `sh_vs_alarmset` WHERE userId=(SELECT userId from sh_user_info WHERE account='"+paramD.account()+"') ORDER BY creationDate DESC LIMIT 1;","alarmTypes,callStatus,smsStatus,receiveNumber1,receiveNumber2,id"};
		return creatCsAlarmSet;
	}
	public String[] callAndSmsAlarmBindCsAlarmAndImeis(){
		String[] bindCsAlarmAndImeis={"FROM sh_user_device_bind WHERE userId=(SELECT userId FROM sh_user_info WHERE account='"+paramD.account()+"') LIMIT 2;","imei"};
		return bindCsAlarmAndImeis;
	}
	public String[] callAndSmsAlarmBindCsAlarmAndImeisGetImeis(String id){
		String[] bindCsAlarmAndImeisGetImeis={"FROM sh_vs_alarmset WHERE id='"+id+"';","imeis"};
		return bindCsAlarmAndImeisGetImeis;
	}

}
