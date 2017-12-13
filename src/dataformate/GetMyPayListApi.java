package dataformate;

public class GetMyPayListApi {
	public int code;
	public String message;
	public GetMyPayListData result;
		public class GetMyPayListData{
			public String id;
			public String imei;
			public String receiveNumber;
			public String alarmType;
			public String alarmName;
			public String alarmPushType;
			public String amount;
			public String createTime;
			public String phone;
		}
	

}
