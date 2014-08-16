package app.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import app.bean.FileMeta;
import app.http.HTTP;
import app.utils.AppPath;
import app.utils.FileMeteCache;
import app.utils.JsonUtils;

@Service
public class ImageServer {
	
	@Autowired
	private HTTP http;
	
	public List list(){
		String path = "/images/json";
		String json = http.get(AppPath.dockerPath+path );
		List list =  JsonUtils.objectFromJson(json, List.class);
		return list;
	}

	public Map get(String Id) {
		String path = "/images/"+Id+"/json";
		String json = http.get(AppPath.dockerPath+path );
		if(json != null) return  JsonUtils.objectFromJson(json, Map.class);
		return null;
	}
	
	public Map getAll(String Id) {
		String path = "/images/"+Id+"/json";
		String json = http.get(AppPath.dockerPath+path );
		Map map =  JsonUtils.objectFromJson(json, Map.class);
		
		Map imageMap = getByName(Id,null);
		if(imageMap != null) map.putAll(imageMap);
		return map;
	}
	
	public Map getByName(String Id,String name){
		List<Map<String,Object>> list = list();
		for(Map sing : list){
			if(Id.equals(sing.get("Id"))){
				return sing;
			}
		}
		return null;
	}
	
	public Map build(String path,String name){
		byte[] bytes;
		try {
			String json = http.postBuild(AppPath.dockerPath+"/build?t="+name ,FileCopyUtils.copyToByteArray(new File(AppPath.RootPath()+"../../"+path)));
			Map map =  JsonUtils.objectFromJson(json, Map.class);
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
