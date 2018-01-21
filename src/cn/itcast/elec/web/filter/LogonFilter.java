package cn.itcast.elec.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.elec.domain.ElecUser;

//继承filter接口
public class LogonFilter implements Filter {
	private static HashMap<String, ElecUser> map = new HashMap<>();
	private List<String> list = new ArrayList<String>();

	// 这是每个拦截器的初始化 在这里面要定义不需要拦截的do和jsp 主要就是登陆界面需要用到的
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		list.add("/index.jsp");
		list.add("/image.jsp");
		 list.add("/system/elecMenuAction_home.do");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpServletResponse response1 = (HttpServletResponse) response;
		String path = request1.getServletPath();
		if (list != null && list.contains(path)) {
			chain.doFilter(request1, response1);
			return;
		}
//		String requestedSessionId = request1.getRequestedSessionId();
//		HttpSession session = MySessionContext.getSessionContext().getSession(requestedSessionId);
//		if (session != null) {
//			chain.doFilter(request1, response1);
//			return;
//		}
		ElecUser eu = (ElecUser) request1.getSession().getAttribute("user");
		if (eu != null) {

			chain.doFilter(request1, response1);
			return;
		}

		response1.sendRedirect(request1.getContextPath() + "/");
		/*
		 * 假定你的web application 名称为news,你在浏览器中输入请求路径：
		 * http://localhost:8080/news/main/list.jsp 则执行下面向行代码后打印出如下结果： 1、
		 * System.out.println(request.getContextPath()); //可返回站点的根路径。也就是项目的名字
		 * 打印结果：/news 2、System.out.println(request.getServletPath());
		 * 打印结果：/main/list.jsp 3、 System.out.println(request.getRequestURI());
		 * 打印结果：/news/main/list.jsp 4、
		 * System.out.println(request.getRealPath("/")); 打印结果：F:\Tomcat
		 * 6.0\webapps\news\test
		 */
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
