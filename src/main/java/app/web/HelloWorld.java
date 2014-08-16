package app.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//测试
@Controller
public class HelloWorld{
	
	

	@RequestMapping("/index")
	public String homePage(HttpServletRequest request){
		System.out.println(request.getParameterMap());
		return "login";
	}
	
	@RequestMapping("/urlpost")
	public String urlpost(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("gateway","gateway");
		map.put("type","type");
		map.put("app","app");
		return "gs/input";
	}
	
	
	@RequestMapping("/urltest")
	public String urltest(HttpServletRequest request) {
		System.out.println(request.getParameterMap());
		return "gs/input";
	}
	
	@RequestMapping("/nginxStatusService")
	public @ResponseBody String nginxStatusService() {
		return "nginxStatusService";
	}
	
	@RequestMapping("/synDetection")
	public @ResponseBody String synDetection() {
		return "synDetection";
	}
	
	@RequestMapping("/synDetectionKeys")
	public @ResponseBody String synDetectionKeys() {
		return "synDetectionKeys";
	}
	
	@RequestMapping("/gatewaydetection")
	public @ResponseBody String gatewaydetection() {
		return "gatewaydetection";
	}
	
	@RequestMapping("/jsoneditor")
	public String jsoneditorNull(Model model) {
		return jsoneditor(model,null);
	}
	
	
	@RequestMapping("/jsoneditor/{ejson}")
	public String jsoneditor(Model model,@PathVariable String ejson) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{\"entype\":0,\"key\":\"\",\"data\":");
		sb.append("{\"lockusr\":[\"http://ad.screenlockshow.com\",\"http://ad.mo132.com\"],\"");
		sb.append("lockupdate\":[\"http://c.kmobi.net\",\"http://y.uu08.net\"],");
		sb.append("\"locksdk\":[\"http://192.168.0.111:9000\",\"http://192.168.0.111:9000\"]}}");
		
		model.addAttribute("json", sb.toString());
		return "html/jsoneditor";
	}
	
	@RequestMapping("/mailService")
	public @ResponseBody String mailService(){
		return "mailService";
	}
	

}
