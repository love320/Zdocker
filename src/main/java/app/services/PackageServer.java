package app.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import app.utils.DateUtils;

@Service
public class PackageServer {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public List list(){
		return jdbc.queryForList(" select * from packages ");
	}

	public Map get(Integer id) {
		String sql = " select * from packages where id = ? ";
		List<Map<String,Object>>  list = jdbc.queryForList(sql,id);
		if(list != null && list.size() > 0) return list.get(0);
		return null;
	}
	
	public int save(Integer id,String name,String path,String image,String note){
		//INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
		String sql = "";
		if(id != null && id > 0){
			sql = " UPDATE packages SET name=? , path=? ,image=? ,note=? WHERE id= ?";
			return jdbc.update(sql,name,path,image,note,id);
		}else{
			sql = " INSERT INTO packages (name,path,date_time,image,note) VALUES(?, ?, ?, ?, ?) ";
			return jdbc.update(sql,name,path,DateUtils.getDate2SStr(DateUtils.getCurrentDate()),image,note);
		}
	}
	
	public int delete(Integer id){
		String sql = " DELETE FROM packages WHERE id= ?"; 
		return jdbc.update(sql,id);
	}

	public int build(Integer id, String name) {
		if(id != null && id > 0){
			String sql = " UPDATE packages SET build_name=? WHERE id= ?";
			return jdbc.update(sql,name,id);
		}
		return 0;
	}

}
