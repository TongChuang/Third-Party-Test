package security;

import common.SIEBeanFactory;			
import common.util.CommonUtil;
import dataaccess.DataAccessApi;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class OnlineUserMgr {

	private Logger logger = null;
	private static Map sessionUserMap = new HashMap();
	private static Map userNameSessionMap = new HashMap();
	private static OnlineUserMgr ins = new OnlineUserMgr();

	private OnlineUserMgr() {
		logger = null;
		logger = CommonUtil.getLogger();
	}

	public static OnlineUserMgr getInstance() {
		return ins;
	}


	public List getAllOnlineUsers() {
		List onlineUsers = new Vector();
		onlineUsers.addAll(sessionUserMap.values());
		return onlineUsers;
	}

	private void removeOldLogin(String userName) {
		try {
			if (userNameSessionMap.containsKey(userName)) {
				HttpSession oldsession = (HttpSession) userNameSessionMap
						.get(userName);
				sessionUserMap.remove(oldsession);
				userNameSessionMap.remove(userName);

				oldsession.invalidate();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}


	private void removeOfflineUser2(HttpSession session, String userName) {
		session.removeAttribute(userName);
		session.invalidate();
	}

	private boolean isIllegalParam(HttpSession session, String userName) {
		return session == null || userName == null
				|| userName.trim().length() == 0;
	}

}