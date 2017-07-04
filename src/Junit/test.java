package Junit;

import javax.servlet.ServletContext;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.struts2.ServletActionContext;

public class test {
	ServletContext servletContext=ServletActionContext.getServletContext();
	String realPath=servletContext.getContextPath();
	//System.out.println(reasalPath);
}
