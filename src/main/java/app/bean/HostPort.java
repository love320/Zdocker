package app.bean;

public class HostPort {
	public String HostPort;
	public String HostIp = "0.0.0.0";
	
	public HostPort(String hostPort){
		this.HostPort = hostPort;
	}
	
	public HostPort(String hostPort,String hostIp){
		this.HostPort = hostPort;
		this.HostIp = hostIp;
	}
}
