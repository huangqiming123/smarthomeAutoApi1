package dataformate;

public class MyUserConfigApi {
	public int code;
	public String message;
	public myUserConfigData result;
	public class myUserConfigData{
		public String voiceSwitch;
		public String shakeSwitch;
		public String alarmSwitch;
		public String pushImgSwitch;
		public String pushVideoSwitch;
	}

}
