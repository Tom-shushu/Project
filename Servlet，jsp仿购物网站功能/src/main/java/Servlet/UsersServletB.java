package Servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthStyle;

import bean.UsersInfo;
import biz.UsersBiz;

@WebServlet("/user")
public class UsersServletB extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private UsersBiz ub=new UsersBiz();
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		String checkcode=request.getParameter("checkCode");
		if("login".equals(type)){
			loginUser(request, response);
		}else if("${YANZHENGMA}".equals(checkcode)) {
			loginUser(request, response);
		}
	}

	public void loginUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		UsersInfo ui = new UsersInfo();
		
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		
		ui.setUserName(name);
		ui.setUserPassword(pass);
		
		ui = ub.checkLogin(ui);
		if(ui==null){
			session.setAttribute("ERROR","ÄúµÄÕËºÅ»òÃÜÂë´íÎó!");
			response.sendRedirect("login.jsp");
		}else{
			String piccode=(String) request.getSession().getAttribute("YANZHENGMA");
	         String checkCode=request.getParameter("checkCode");
	         checkCode=checkCode.toUpperCase();
	         piccode=piccode.toUpperCase();
	         response.setContentType("text/html;charset=gbk");
	         PrintWriter out=response.getWriter();
	         if(checkCode.equals(piccode))
	         {
	            session.setAttribute("USERSINFO", ui);
	 			response.sendRedirect("index.jsp");
	         }
	         else
	         {
	        	 session.setAttribute("ERROR","ÄúµÄÑéÖ¤Âë´íÎó!");
	             response.sendRedirect("login.jsp");
	             
	         }

			
		}
		
	}

}
