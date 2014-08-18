package app.utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ZDriverManagerDataSource extends DriverManagerDataSource{

	@Override
	public void setUrl(String url) {
		super.setUrl("jdbc:sqlite:"+AppPath.RootPath()+url);
	}
	
	

}
