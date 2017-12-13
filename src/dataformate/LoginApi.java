package dataformate;

import java.io.Serializable;

public class LoginApi implements Serializable{
	public int code;
	public String message;
	public LoginData result;
	public class LoginData{
		public String accessToken;
		public String userId;
		public String account;
		public String sex=null;
		public String userName;
		public String nickName;
		public String cityName;
		public String iconUrl;
		public String hasDevice;
		public String alarmSwitch;
		public String voiceSwitch;
		public String shakeSwitch;
		public String pushImgSwitch;
		
	}

}
