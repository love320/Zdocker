package app.web;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.bean.FileMeta;
import app.http.HTTP;
import app.utils.AppPath;
import app.utils.FileMeteCache;
import app.utils.JsonUtils;

@Controller
@RequestMapping("/images")
public class Images {

	@Autowired
	private HTTP http;
	
	@RequestMapping("/list")
	public String list(Model mode){
		String path = "/images/json?all=0";
		String json = http.get(AppPath.dockerPath+path );
		List list =  JsonUtils.objectFromJson(json, List.class);
		mode.addAttribute("list",list);
		return "images/list";
	}
	
	@RequestMapping("/name/json")
	public String info(Model mode,HttpServletRequest request){
		String title = request.getParameter("name");
		String path = "/images/"+title+"/json";
		String json = http.get(AppPath.dockerPath+path );
		mode.addAttribute("info", JsonUtils.objectFromJson(json, Map.class));
		mode.addAttribute("title", title);
		return "images/info";
	}
	
	@RequestMapping("/addc")
	public String addContainers(Model mode,HttpServletRequest request){
		String title = request.getParameter("name");
		String rurl = request.getParameter("rurl");
		String path = "/images/"+title+"/json";
		String json = http.get(AppPath.dockerPath+path );
		mode.addAttribute("info", JsonUtils.objectFromJson(json, Map.class));
		mode.addAttribute("title", title);
		mode.addAttribute("rurl", rurl);
		return "images/addc";
	}
	
	@RequestMapping("/build/{id}")
	public  @ResponseBody String buildImage(Model mode,@PathVariable  Integer id){
		String path = "/build";
		FileMeta getFile = FileMeteCache.files.get(id);//List 使用序号获取文件
		String json = http.postBuild(AppPath.dockerPath+path ,getFile.getBytes());
		System.out.println(json);
		return json;
	}
	
	@RequestMapping("/{name}/delete")
	public @ResponseBody String delete(Model mode,@PathVariable  String name){
		String path = "/images/"+name;
		String json = http.delete(AppPath.dockerPath+path );
		return "ok";
	}
	
}
