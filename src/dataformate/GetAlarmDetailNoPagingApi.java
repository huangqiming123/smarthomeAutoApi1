package dataformate;

public class GetAlarmDetailNoPagingApi {
	public int code;
	public int dataTotalRows;
	public String msg;
	public Data[] data;
	public class Data{
		public String addr;
		public String alarmType;
		public String createTime;
		public String id;
		public String imei;
		public String lat;
		public String lng;
		public String pushTime;
		public String readStatus;
		public String speed;
		public String status;
		public String userId;
		
	}

}
