package app.http;

import java.util.Map;

public interface HTTP {
	
	public String get(String url);
	public String get(String url, Map<String, String> map);
	public String get(String ip, Integer port, Map<String, Object> map);
	
	
	public String post(String url);
	public String post(String url, Map<String, String> map);
	public String post(String ip, Integer port, Map<String, Object> map);
	
	public String postBody(String url,String body);
	public String postBuild(String url,byte b[]) ;

	public String delete(String url);
}
