package app.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.services.ImageServer;
import app.services.PackageServer;

@Controller
@RequestMapping("/packages")
public class Packages {
	
	@Autowired
	private PackageServer packageServer;
	
	@Autowired
	private ImageServer imageServer;
	
	@RequestMapping("/list")
	public String list(Model mode){
		mode.addAttribute("list", packageServer.list());
		mode.addAttribute("images",imageServer.list());
		return "packages/list";
	}
	
	@RequestMapping("/input")
	public String input(Model mode){
		mode.addAttribute("title", "添加项目文件");
		mode.addAttribute("images",imageServer.list());
		return "packages/input";
	}
	
	@RequestMapping("/input/{id}")
	public String input(Model mode,@PathVariable  Integer id){
		mode.addAttribute("title", "编辑项目文件");
		mode.addAttribute("info", packageServer.get(id));
		mode.addAttribute("images",imageServer.list());
		return "packages/input";
	}
	
	@RequestMapping("/save")
	public ModelAndView save(Model mode,HttpServletRequest request){
		String idstr = request.getParameter("id");
		Integer id = StringUtils.isBlank(idstr)? null:Integer.parseInt(idstr);
		String name = request.getParameter("name");
		String path = request.getParameter("path");
		String image = request.getParameter("image");
		String note = request.getParameter("note");
		packageServer.save(id,name, path,image,note);
		return new ModelAndView("redirect:/packages/list");
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(Model mode,@PathVariable  Integer id){
		if(id != null && id > 0) packageServer.delete(id);
		return new ModelAndView("redirect:/packages/list");
	}
	
}
