package app.http;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import app.utils.JsonUtils;


public class HttpUtil{
	
	private static HTTP ngxHttp = new ClientByte();

	public static String get(String url) {
		return ngxHttp.get(url);
	}

	public static String get(String url, Map<String, String> map) {
		return ngxHttp.get(url, map);
	}

	public static String get(String ip, Integer port, Map map) {
		return ngxHttp.get(ip, port, map);
	}

	public static String post(String url) {
		return ngxHttp.post(url);
	}

	public static String post(String url, Map<String, String> map) {
		return ngxHttp.post(url, map);
	}

	public static String post(String ip, Integer port, Map map) {
		return ngxHttp.post(ip, port, map);
	}
	

}
