package app.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Component;

@Component
public class ClientByte implements HTTP{
	
	HttpClient httpClient = new HttpClient();
	
	@Override
	public String get(String url) {
		return get(url,new HashMap<String, String>());
	}

	@Override
	public String get(String url, Map<String, String> map) {
		StringBuffer p = new StringBuffer();
		boolean first = true;
		for (Map.Entry<String, String> pair : map.entrySet()) {
			if (first){
				first = false;
			}else{
				p.append('&');
			}
			p.append(pair.getKey());
			p.append('=');
			p.append(pair.getValue());
		}

		//byte b[] = p.toString().getBytes();// 把字符串转换为二进制数据
		//RequestEntity requestEntity = new ByteArrayRequestEntity(b);

		HttpMethodBase getMethod = new GetMethod(url);
		String responseMsg = executeMethod(getMethod);
		return responseMsg;
	}

	@Override
	public String get(String ip, Integer port, Map<String, Object> map) {
		return null;
	}

	@Override
	public String post(String url) {
		return post(url,new HashMap<String, String>());
	}

	
	/**
	 * 发送post请求,客户端采用二进制流发送,服务端采用二进制流介绍
	 * 
	 * @param json
	 *            入参的json格式的报文
	 * @param url
	 *            http服务器的地址
	 * @return 返回响应信息
	 */
	@Override
	public String post(String url, Map<String, String> map) {
		StringBuffer p = new StringBuffer();
		boolean first = true;
		for (Map.Entry<String, String> pair : map.entrySet()) {
			if (first){
				first = false;
			}else{
				p.append('&');
			}
			p.append(pair.getKey());
			p.append('=');
			p.append(pair.getValue());
		}

		byte b[] = p.toString().getBytes();// 把字符串转换为二进制数据
		RequestEntity requestEntity = new ByteArrayRequestEntity(b);

		EntityEnclosingMethod postMethod = new PostMethod();
		postMethod.setRequestEntity(requestEntity);// 设置数据
		postMethod.setPath(url);// 设置服务的url
		postMethod.setRequestHeader("Content-Type", "text/html;charset=GBK");// 设置请求头编码

		String responseMsg = executeMethod(postMethod);
		
		return responseMsg;
	}
	
	@Override
	public String postBuild(String url,byte b[]) {
		RequestEntity requestEntity = new ByteArrayRequestEntity(b);

		EntityEnclosingMethod postMethod = new PostMethod();
		postMethod.setRequestEntity(requestEntity);// 设置数据
		postMethod.setPath(url);// 设置服务的url
		postMethod.setRequestHeader("Content-Type", "application/tar");// 设置请求头编码
		String responseMsg = executeMethod(postMethod);
		
		return responseMsg;
	}

	@Override
	public String post(String ip, Integer port, Map map) {
		return post("http://" + ip + ":" + port, map);// 二进制处理参数
	}
	
	
	private synchronized String executeMethod(HttpMethodBase httpmethod){
		
		// 设置连接超时
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5 * 1000);
		// 设置读取超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(20 * 1000);
				
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(httpmethod);// 发送请求
			
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("HTTP服务异常" + statusCode);
				return null;
			}
			StringBuffer sb = new StringBuffer();
			InputStream is = httpmethod.getResponseBodyAsStream();// 获取返回值
			int kk = -1;
			byte[] tt = new byte[1024];
			while((kk=is.read(tt))!=-1){
				String txt = new String(tt);
				if(kk != 1024) txt = txt.substring(0, kk);
				sb.append(txt);
			}
			return sb.toString();
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (java.net.ConnectException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpmethod.releaseConnection();// 释放连接
		}
		
		return null;
	}

	@Override
	public String postBody(String url, String body) {
		String responseMsg = null;
		try {
			RequestEntity requestEntity = new StringRequestEntity(body,"text/json", null);
			EntityEnclosingMethod postMethod = new PostMethod();
			postMethod.setRequestEntity(requestEntity);// 设置数据
			postMethod.setPath(url);// 设置服务的url
			postMethod.setRequestHeader("Content-Type", "application/json");// 设置请求头编码

			responseMsg = executeMethod(postMethod);
			
			return responseMsg;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String delete(String url) {
		 //创建GET方法的实例
        DeleteMethod deleteMethod = new DeleteMethod(url);
        //使用系统提供的默认的恢复策略
        deleteMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        String responseMsg = executeMethod(deleteMethod);
		
		return responseMsg;
	}

}
