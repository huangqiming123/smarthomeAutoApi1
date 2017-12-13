package dataformate;

import java.util.List;

public class HomeListApi {
	public int code;
	public String message;
	public HomeListDate result;
	public class HomeListDate{
		public SystemMsgData systemMsg;
		public class SystemMsgData{
			public String markNum;
			public String content;
			public String createTime;
		}
		public PayVoucherData payVoucher;
		public class PayVoucherData{
			public String payTitle;
			public String payTime;
			public String markNum;
		}
		public AlarmsData  alarms;
		public class AlarmsData{
			public String alarmName;
			public String alarmTime;
			public String markNum;
		}
		public DevicesData[] devices;
		public class DevicesData{
			public String imei;
			public String deviceName;
			public String mcTypeUseScope;
			public String equipType;
			public String expiration;
			public String status;
			public String hbTime;
			public String expirationFlag;
			public String shutDownStatus;
			public String shakeTapeFlag;
			public String photoFlag;
			public String cameraFlag;
			public String sim;
			public String videoFlag;
			public String tripFlag;
			public String icon;
			public String isLife;
			public String functionType;
			public String uuid;
			
		}
	}

}
