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

//�̳�filter�ӿ�
public class LogonFilter implements Filter {
	private static HashMap<String, ElecUser> map = new HashMap<>();
	private List<String> list = new ArrayList<String>();

	// ����ÿ���������ĳ�ʼ�� ��������Ҫ���岻��Ҫ���ص�do��jsp ��Ҫ���ǵ�½������Ҫ�õ���
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
		 * �ٶ����web application ����Ϊnews,�������������������·����
		 * http://localhost:8080/news/main/list.jsp ��ִ���������д�����ӡ�����½���� 1��
		 * System.out.println(request.getContextPath()); //�ɷ���վ��ĸ�·����Ҳ������Ŀ������
		 * ��ӡ�����/news 2��System.out.println(request.getServletPath());
		 * ��ӡ�����/main/list.jsp 3�� System.out.println(request.getRequestURI());
		 * ��ӡ�����/news/main/list.jsp 4��
		 * System.out.println(request.getRealPath("/")); ��ӡ�����F:\Tomcat
		 * 6.0\webapps\news\test
		 */
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
