package dataformate;

public class GetCsAlarmSetListApi {
	public int code;
	public String message;
	public GetCsAlarmSetListData[] result;
	public class GetCsAlarmSetListData{
		public String vsAlarmId;
		public String alarmTypes;
		public String imeis;
		public alarmListData[] alarmList;
		public class alarmListData{
			public String alarmName;
			public String alarmType;
		}
		public deviceListData[] deviceList;
		public class deviceListData{
			public String imei;
			public String deviceName;
		}
		public String callStatus;
		public String smsStatus;
		public String receiveNumber1;
		public String receiveNumber2;
			
		
	}

}
