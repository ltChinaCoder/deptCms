package cn.itcast.elec.util;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import sun.print.resources.serviceui_es;

public class MySessionContext {
	private  volatile static MySessionContext instance;
	private MySessionContext()
	{
		map=new ConcurrentHashMap<>();
	}
	private ConcurrentHashMap map;
	public static MySessionContext getSessionContext()
	{
		if(instance==null)
		{
			synchronized (MySessionContext.class) {
				if(instance==null)
					instance=new MySessionContext();
			}
		}
		return instance;
	}
	public void addSession(HttpSession session)
	{
		if(session!=null)
		{
			map.put(session.getId(), session);
		}
	}
	public void deleteSession(HttpSession session)
	{
		if(session!=null)
			map.remove(session);
	}
	public  HttpSession getSession(String sessionId)
	{
		if(sessionId!=null)
			return (HttpSession) map.get(sessionId);
		return null;
	}
}
