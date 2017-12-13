package paramdata;

public class ComData {
	public String[] connectOperateLib(){
		String[] OperateLib={"tuqiang_query","tuqiang_query","jdbc:mysql://120.24.75.214:3306/tracker-web"};
		return OperateLib;
	}
	public String[] connectFrameLib(){
		String[] FrameLib={"tracker","tracker","jdbc:mysql://120.24.255.186:3306/tracker-api"};
		return FrameLib;
	}

}
