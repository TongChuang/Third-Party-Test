package common;

import common.xmlmodel.SystemConfigTable;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;

public class SIEContext {

	private static ServletContext servletCtx = null;
	private static ApplicationContext springContext = null;
	private static SystemConfigTable systemConfigTable = null;

	public SIEContext() {
	}


	public static void setSpringContext(ApplicationContext context) {
		springContext = context;
	}

	public static ApplicationContext getSpringContext() {
		return springContext;
	}

	public static ServletContext getServletContext() {
		return servletCtx;
	}

	public static void setServletContext(ServletContext ctx) {
		servletCtx = ctx;
	}

	public static SystemConfigTable getSystemConfigTable() {
		return systemConfigTable;
	}

	public static void setSystemConfigTable(SystemConfigTable asystemConfigTable) {
		systemConfigTable = asystemConfigTable;
	}


}