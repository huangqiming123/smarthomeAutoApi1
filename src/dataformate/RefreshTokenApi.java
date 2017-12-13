package dataformate;

import java.io.Serializable;


public class RefreshTokenApi implements Serializable{
	public int code;
	public String message;
	public TokenData result;
	public class TokenData{
		public String accessToken;
	}

}
