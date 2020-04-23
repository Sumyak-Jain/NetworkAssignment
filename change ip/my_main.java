package test_ip;

/**
 *
 * @author GAURAV
 */

import java.io.PrintWriter;
public class my_main {
	public static void main(String[] args) {
		String adapter_name="Wi-fi";//changing wi fi settings
     //give accordingly
		String ip_address="";
		String subnet_mask="";
		String default_gateway="";
		String dns_1="";
		String dns_2="";
		String[] command =
	    {
	        "cmd",
	    };
	    Process p;
		try {
			p = Runtime.getRuntime().exec(command);
		        new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
	                new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
	                PrintWriter stdin = new PrintWriter(p.getOutputStream());
	                stdin.println("netsh int ip set address "+adapter_name+" static "+ip_address+" "+subnet_mask+" "+default_gateway);
	                stdin.println("netsh int ip set dns "+adapter_name+" static "+dns_1+" primary");
	                stdin.println("netsh interface ip add dns "+adapter_name+" "+dns_2+" INDEX=2");
		                stdin.close();
	                p.waitFor();
	    	} catch (Exception e) {
	 		e.printStackTrace();
		}
	}	
}
