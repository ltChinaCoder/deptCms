package cn.itcast.elec.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import freemarker.template.utility.StringUtil;

public class CheckUtil {

	public static boolean checkNumber(HttpServletRequest request) {
		 String checkNumber=(String) request.getSession().getAttribute("CHECK_NUMBER_KEY");
		 if(StringUtils.isBlank(checkNumber))
			 return false;
		 String writeNumber=request.getParameter("checkNumber");
		 if(StringUtils.isBlank(writeNumber))
			 return false;
		 //equalsIgnoreCase忽略大小写
		 return checkNumber.equalsIgnoreCase(writeNumber);
	}

	public static void checkRemberMe(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String name=request.getParameter("name");
		String passWord=request.getParameter("password");
		String nameCode=URLEncoder.encode(name, "utf-8");
		Cookie c=new  Cookie("name", nameCode);	
		Cookie c1=new  Cookie("password", passWord);
		String s=request.getParameter("rememberMe");
		//设置路径
		c.setPath(request.getContextPath()+"/");
		c1.setPath(request.getContextPath()+"/");
		if(s!=null && "yes".equals(s))
		{
			//设置时效
			c.setMaxAge(60*60*24*7);
			c1.setMaxAge(60*60*24*7);
		}
		else
		{
			c.setMaxAge(0);
			c1.setMaxAge(0);
		}
		response.addCookie(c);
		response.addCookie(c1);
	}

}
