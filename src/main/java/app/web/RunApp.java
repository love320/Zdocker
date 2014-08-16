package app.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.services.ContainersServer;
import app.services.ImageServer;
import app.services.PackageServer;

@Controller
@RequestMapping("/runapp")
public class RunApp {
	
	@Autowired
	private PackageServer packageServer;
	
	@Autowired
	private ImageServer imageServer;
	
	@Autowired
	private ContainersServer containersServer;
	
	@RequestMapping("/html/{page}")
	public String pageHome(Model mode,@PathVariable  String page,HttpServletRequest request){
		mode.addAllAttributes(request.getParameterMap());
		return "runapp/"+page;
	}
	
	//运行页面
	@RequestMapping("/{id}")
	public String pageHome(Model mode,@PathVariable  Integer id){
		//获取项目基础信息
		Map<String,Object> info = packageServer.get(id);
		mode.addAttribute("info", info);
		mode.addAttribute("image", imageServer.getAll((String)info.get("image")));//获取基于镜像信息
		
		//获取发布的镜像信息
		String build_image = (String)info.get("build_name");
		if(!StringUtils.isBlank(build_image)) mode.addAttribute("build_image", imageServer.get(build_image));
		
		//获取生成的容器信息
		if(!StringUtils.isBlank(build_image)) mode.addAttribute("containers", containersServer.getByImageName(build_image));
		return "runapp/manage";
	}
	
	@RequestMapping("/build/{id}")
	public @ResponseBody String build(Model mode,@PathVariable  Integer id,HttpServletRequest request){
		String name = request.getParameter("name");
		Map<String,Object> info = packageServer.get(id);
		imageServer.build((String)info.get("path"), name);
		//更新信息
		packageServer.build(id,name);
		return "ok";
	}

}
