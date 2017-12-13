package okhttp;

import java.net.URLEncoder;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FrameRequestManager {
	private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    //private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    //private static final String TAG = RequestManager.class.getSimpleName();
    private static final String BASE_URL = "http://120.76.26.4:9080";//请求接口根地址
    //private static volatile RequestManager mInstance;//单利引用
    public static final int TYPE_GET = 0;//get请求
    public static final int TYPE_POST_JSON = 1;//post请求参数为json
    public static final int TYPE_POST_FORM = 2;//post请求参数为表单
    //private OkHttpClient mOkHttpClient;//okHttpClient 实例
    //private Handler okHttpHandler;//全局处理子线程和M主线程通信
    OkHttpClient mOkHttpClient=new OkHttpClient();
    //Handler okHttpHandler=new Handler();

  

    
    /**
     *  okHttp同步请求统一入口
     * @param actionUrl  接口地址
     * @param requestType 请求类型
     * @param paramsMap   请求参数
     */
    public Response requestSyn(String actionUrl, int requestType, HashMap<String, String> paramsMap) {
        switch (requestType) {
            case TYPE_GET:
               return  requestGetBySyn(actionUrl, paramsMap);
                //break;
            case TYPE_POST_JSON:
                return requestPostBySyn(actionUrl, paramsMap);
                //break;
            case TYPE_POST_FORM:
                return requestPostBySynWithForm(actionUrl, paramsMap);
                //break;
        }
		return null;
    }
    /**
     * okHttp get同步请求
     * @param actionUrl  接口地址
     * @param paramsMap   请求参数
     */
    private Response requestGetBySyn(String actionUrl, HashMap<String, String> paramsMap) {
    	
        StringBuilder tempParams = new StringBuilder();
        try {
            //处理参数
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                //对参数进行URLEncoder
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s/%s?%s", BASE_URL, actionUrl, tempParams.toString());
            System.out.println(requestUrl);
            //创建一个请求
            Request request = addHeaders().url(requestUrl).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            final Response response = call.execute();
            //System.out.println(response.code());
            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
        	return null;
        }
    }
    
    /**
     * okHttp post同步请求
     * @param actionUrl  接口地址
     * @param paramsMap   请求参数
     */
    private Response requestPostBySyn(String actionUrl, HashMap<String, String> paramsMap) {
        try {
            //处理参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s/%s", BASE_URL, actionUrl);
            //生成参数
            String params = tempParams.toString();
            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            //创建一个请求
            final Request request = addHeaders().url(requestUrl).post(body).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            Response response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
                //获取返回数据 可以是String，bytes ,byteStream
                return response;
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
		return null;
    }
    
    /**
     * okHttp post同步请求表单提交
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private Response  requestPostBySynWithForm(String actionUrl, HashMap<String, String> paramsMap){
    	try{
    		FormBody.Builder builder=new FormBody.Builder();
    		for(String key:paramsMap.keySet()){
    			builder.add(key, paramsMap.get(key));
    		}
    		RequestBody formBody=builder.build();
    		String requestUrl=String.format("%s/%s", BASE_URL,actionUrl);
    		System.out.println(requestUrl);
    		Request request=addHeaders().url(requestUrl).post(formBody).build();
    		final Call call=mOkHttpClient.newCall(request);
    		final Response response=call.execute();
    		if(response.isSuccessful()){
    			return response;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		}
		return null;
    	}
    
 
    /**
     * 统一为请求添加头信息
     * @return
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder()
                .addHeader("Content-Type", "application/x-www-form-urlencode");
                
        return builder;
    }

}
