package dataformate;

import java.io.Serializable;

public class GetSmsApi implements Serializable {
	public SmsData result;
	public String message;
	public int code;
	public class SmsData{
		public String account;
	}

}
