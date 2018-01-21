package cn.itcast.elec.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionContextListener implements  HttpSessionListener{
	private   MySessionContext myc=MySessionContext.getSessionContext();
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		myc.addSession(se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		myc.deleteSession(se.getSession());
	}

}
