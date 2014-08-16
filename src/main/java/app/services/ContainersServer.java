package app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.http.HTTP;
import app.utils.AppPath;
import app.utils.JsonUtils;


@Service
public class ContainersServer {
	
	@Autowired
	private HTTP http;
	
	public List getByImageName(String name){
		String path = "/containers/json?all=1";
		String json = http.get(AppPath.dockerPath+path );
		List<Map<String,Object>> list =  JsonUtils.objectFromJson(json, List.class);
		
		List<Map> listName = new ArrayList<Map>();
		if(list != null && list.size() > 0 ){
			for(Map sing : list){
				if(StringUtils.indexOf((String)sing.get("Image"),name)>=0){
					listName.add(sing);
				}
			}
		}
		return listName;
	}

}
