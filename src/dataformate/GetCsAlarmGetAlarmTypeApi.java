package dataformate;

public class GetCsAlarmGetAlarmTypeApi {
	public int code;
	public String message;
	public getAlarmTypeData[] result;
	public class getAlarmTypeData{
		public String alarmType;
		public String alarmName;
	}

}
