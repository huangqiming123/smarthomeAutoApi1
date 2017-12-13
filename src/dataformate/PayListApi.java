package dataformate;

public class PayListApi {
	public int code;
	public String message;
	public payListData[] result;
	public class payListData{
		public String id;
		public String tradeNo;
		public String productDetail;
		public String amount;
		public String payMethod;
		public String status;
		public String payTime;
		public String markNum;
		public String tradeType;
		public String remark;
		public String userId;
		
	}

}
