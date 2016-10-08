package common;

import security.SecurityApi;	
import javax.servlet.http.HttpSessionEvent;
import org.springframework.web.util.HttpSessionMutexListener;

public class SIESessionListener extends HttpSessionMutexListener {

	public SIESessionListener() {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		javax.servlet.http.HttpSession session = event.getSession();
		SecurityApi securityApi = SIEBeanFactory.getSecurityApi();
		securityApi.removeOfflineUser(session);
		super.sessionDestroyed(event);
	}
}