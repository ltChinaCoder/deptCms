package cn.itcast.elec.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecUserService;

/**
 * Servlet implementation class CheckLogonName
 */
public class CheckLogonName extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  IElecUserService ies=(IElecUserService) ServiceProvider.getService(IElecUserService.service_name);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLogonName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		PrintWriter pw=response.getWriter();
		String s=request.getParameter("logonName");		
		String flag=ies.checkLogonName(s);
		pw.println(flag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
