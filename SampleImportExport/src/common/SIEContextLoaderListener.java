package common;

import javax.servlet.ServletContextEvent;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SIEContextLoaderListener extends ContextLoaderListener {

	public SIEContextLoaderListener() {
	}

	public void contextInitialized(ServletContextEvent ctxEvent) {
		super.contextInitialized(ctxEvent);
		javax.servlet.ServletContext context = ctxEvent.getServletContext();
		SIEContext.setServletContext(context);
		org.springframework.context.ApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(context);
		SIEContext.setSpringContext(springContext);
		SIEBeanFactory.initModules();
	}

	public void contextDestroyed(ServletContextEvent ctxEvent) {
		SIEBeanFactory.shutDownModules();
		super.contextDestroyed(ctxEvent);
	}
}