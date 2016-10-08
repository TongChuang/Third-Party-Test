/**
 * 
 */
package webService.server;

import javax.xml.ws.Endpoint;

/**
 * @author zjn
 * @createTime 2016-9-13
 */
public class StartService {

//	public static void startWebservice() {
	public static void main(String a[]) {
		System.out.println("---------------- start webservice jax-ws ... ----------------");
		SIE_ServiceApi service = new SIE_ServiceApiImpl();
		Endpoint.publish("http://127.0.0.1:86/SIE_WebService", service);
		System.out.println("---------------- webservice URL：http://127.0.0.1:86/SIE_WebService ----------------");
		System.out.println("---------------- start webservice successful ----------------");
	}

}
