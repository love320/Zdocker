package app.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.bean.ContainerStartBean;
import app.bean.ContainersBean;
import app.http.HTTP;
import app.utils.AppPath;
import app.utils.JsonUtils;

@Controller
@RequestMapping("/containers")
public class Containers {

	@Autowired
	private HTTP http;
	
	@RequestMapping("/list")
	public String list(Model mode){
		String path = "/containers/json?all=1";
		String json = http.get(AppPath.dockerPath+path );
		mode.addAttribute("list", JsonUtils.objectFromJson(json, List.class));
		return "containers/list";
	}
	
	@RequestMapping("/create")
	public @ResponseBody String create(Model mode,HttpServletRequest request){
		String image = request.getParameter("image");
		String name = request.getParameter("name");
		String path = "/containers/create?name="+name;
		ContainersBean cb = new ContainersBean();
		cb.Image = image;
		//cb.Hostname = image.split(":")[0]+new Date()
		String json = http.postBody(AppPath.dockerPath+path, JsonUtils.jsonFromObject(cb));
		
		return "ok";
	}
	
	@RequestMapping("/{id}/stop")
	public ModelAndView stop(Model mode,@PathVariable  String id){
		String path = "/containers/"+id+"/stop";
		String json = http.post(AppPath.dockerPath+path );
		return new ModelAndView("redirect:/containers/list"); 
	}
	
	@RequestMapping("/{id}/startpage")
	public String startPage(Model mode,@PathVariable  String id){
		String path = "/containers/"+id+"/json";
		String json = http.get(AppPath.dockerPath+path );
		mode.addAttribute("info", JsonUtils.objectFromJson(json, Map.class));
		mode.addAttribute("title", id);
		mode.addAttribute("id", id);
		mode.addAttribute("rurl","containers/list");
		return "containers/startPage";
	}
	
	@RequestMapping("/{id}/startpagerun/{iid}")
	public String startPageRun(Model mode,@PathVariable  String id,@PathVariable  String iid){
		String path = "/containers/"+id+"/json";
		String json = http.get(AppPath.dockerPath+path );
		mode.addAttribute("info", JsonUtils.objectFromJson(json, Map.class));
		mode.addAttribute("title", id);
		mode.addAttribute("id", id);
		mode.addAttribute("rurl","runapp/"+iid);
		return "containers/startPage";
	}
	
	@RequestMapping("/{id}/start/{outport}/{inport}")
	public @ResponseBody String start(Model mode,@PathVariable  String id,@PathVariable Integer outport,@PathVariable Integer inport){
		String path = "/containers/"+id+"/start";
		ContainerStartBean csb = new ContainerStartBean();
		csb.addPortTCP(inport,outport);
		String json = http.postBody(AppPath.dockerPath+path, JsonUtils.jsonFromObject(csb));
		return "ok";
	}
	
	@RequestMapping("/{id}/restart")
	public ModelAndView restart(Model mode,@PathVariable  String id){
		String path = "/containers/"+id+"/restart";
		String json = http.post(AppPath.dockerPath+path );
		return new ModelAndView("redirect:/containers/list");
	}
	
	@RequestMapping("/{id}/kill")
	public ModelAndView kill(Model mode,@PathVariable  String id){
		String path = "/containers/"+id+"/kill";
		String json = http.post(AppPath.dockerPath+path );
		return new ModelAndView("redirect:/containers/list"); 
	}
	
	@RequestMapping("/{id}/pause")
	public ModelAndView pause(Model mode,@PathVariable  String id){
		String path = "/containers/"+id+"/pause";
		String json = http.post(AppPath.dockerPath+path );
		return new ModelAndView("redirect:/containers/list"); 
	}
	
	@RequestMapping("/{id}/unpause")
	public ModelAndView unpause(Model mode,@PathVariable  String id){
		String path = "/containers/"+id+"/unpause";
		String json = http.post(AppPath.dockerPath+path );
		return new ModelAndView("redirect:/containers/list"); 
	}
	
	@RequestMapping("/{id}/delete")
	public ModelAndView delete(Model mode,@PathVariable  String id){
		String path = "/containers/"+id;
		String json = http.delete(AppPath.dockerPath+path );
		return new ModelAndView("redirect:/containers/list"); 
	}
	
	@RequestMapping("/{id}/copy")
	public ModelAndView copy(Model mode,@PathVariable  String id){
		String path = "/containers/"+id+"/copy";
		String json = http.post(AppPath.dockerPath+path );
		return new ModelAndView("redirect:/containers/list"); 
	}
	
	
	
}
