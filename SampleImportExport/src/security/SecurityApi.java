package security;

import java.util.List;	

import common.StartAPI;			

import javax.servlet.http.HttpSession;

public interface SecurityApi
	extends StartAPI {
	
	public abstract int checkUser(String userName, String password);
	public abstract void removeOfflineUser(HttpSession session);
	
}