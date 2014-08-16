package app.bean;

public class ContainersBean {
         public String Hostname = "";
         public String User = "";
         public Integer Memory = 0;
         public Integer MemorySwap = 0;
         public boolean AttachStdin = false;
         public boolean AttachStdout = true;
         public boolean AttachStderr =  true;
         public String PortSpecs = null ;
         public boolean Tty = false ;
         public boolean OpenStdin = false ;
         public boolean StdinOnce  = false ;
         public String Env = null ;
         public String Cmd = null ;
         public String Image = "ubuntu:12.04" ;
         public String Volumes = null;
         public String WorkingDir = "";
         public boolean DisableNetwork = false;
         public String ExposedPorts = null;//"{\"22/tcp\": {}}";
}
