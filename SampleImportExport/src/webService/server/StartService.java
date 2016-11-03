/**
 * 
 */
package webService.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.ws.Endpoint;

/**
 * @author zjn
 * @createTime 2016-9-13
 */
public class StartService {

//	public static void startWebservice() {
	public static void main(String a[]) {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			ip = "127.0.0.1";
		}
		System.out.println("---------------- start webservice jax-ws ... ----------------");
		SIE_ServiceApi service = new SIE_ServiceApiImpl();
		Endpoint.publish("http://"+ip+":86/SIE_WebService", service);
		System.out.println("---------------- webservice URL：http://"+ip+":86/SIE_WebService ----------------");
		System.out.println("---------------- start webservice successful ----------------");
	}

}
