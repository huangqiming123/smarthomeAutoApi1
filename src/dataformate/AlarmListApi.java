package dataformate;

public class AlarmListApi {
	public int code;
	public String message;
	public alarmListData[] result;
	public class alarmListData{
		public String id;
		public String imei;
		public String deviceName;
		public String alarmType;
		public String alarmName;
		public String lat;
		public String lng;
		public String alarmTime;
		public String alarmTag;
		
	}

}
