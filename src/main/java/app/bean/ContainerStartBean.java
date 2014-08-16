package app.bean;

import java.util.HashMap;
import java.util.Map;

public class ContainerStartBean {
	 public String[] Binds;
	 public String[]  Links ;
	 public Map LxcConf ;
	 public Map PortBindings = new HashMap<String, Object>() ; //":{ "22/tcp": [{ "HostPort": "11022" }] },
     public boolean PublishAllPorts = false ;
     public boolean Privileged = false;
     public String[] Dns ; // = ["8.8.8.8"],
     public String[] VolumesFrom; //": ["parent", "other:ro"]
     
     public void addPort(String port,String hostPort){
    	 HostPort[] hps = new HostPort[1];
    	 hps[0] = new HostPort(hostPort);
    	 PortBindings.put(port,hps);
     }
     
     public void addPortTCP(Integer port,Integer hostPort){
    	 HostPort[] hps = new HostPort[1];
    	 hps[0] = new HostPort(hostPort+"");
    	 PortBindings.put(port+"/tcp",  hps);
     }
     
}
