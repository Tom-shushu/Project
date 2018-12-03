package Servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UsersInfo;
import biz.UsersBiz;
@WebServlet("/users")
public class UsersServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		//分流操作
		String type = req.getParameter("type");
	
		if(type ==null||"users".equals(type)) {
			
			jieshou(req,resp);
	} else if("update".equals(type)) {
		update(req,resp);
		
	} 
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type= req.getParameter("type");
		req.setCharacterEncoding("utf-8");
		String c= req.getParameter("c");
		
	if(type==null||"update".equals(type)) {
			
		updateuser(req,resp);
	} 	else if("login".equals(type)){
	
		loginUser(req, resp);
	} 
	
	
	}
	public void jieshou(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersBiz ubiz = new UsersBiz();
		UsersInfo ui1 = new UsersInfo();
		HttpSession session = req.getSession();
	
		UsersInfo ui = new UsersInfo();
		ui=(UsersInfo)(session.getAttribute("USERSINFO"));
		
		ui1 =ubiz.findByUserId(ui.getUserId());
		System.out.println(ui1.getUserName());
		System.out.println("------------");
		session.setAttribute("USERSINFO", ui1);
		
		resp.sendRedirect("self_info.jsp");
		
	}
	
	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersBiz ubiz = new UsersBiz();
	
		resp.sendRedirect("update.jsp");
	}
	
	public void updateuser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersBiz ubiz = new UsersBiz();

		Integer userid =Integer.parseInt( req.getParameter("userid"));
		System.out.println(userid);
		String name = req.getParameter("username");
		System.out.println(name);
		String password= req.getParameter("password");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");
		UsersInfo ui = new UsersInfo();
		ui.setUserId(userid);
		ui.setUserName(name);
		ui.setUserPassword(password);
		ui.setUserPhone(tel);
		ui.setUserAddress(address);
		ubiz.change(ui);
		returnuser(req, resp);
	}
	
	public void loginUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UsersBiz ubiz = new UsersBiz();
		
		HttpSession session = request.getSession();
		UsersInfo ui = new UsersInfo();
		
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println(name);
		System.out.println(pass);
		ui.setUserName(name);
		ui.setUserPassword(pass);
		ui=ubiz.checkLogin(ui);
		
		
		if(ui==null){
			session.setAttribute("ERROR", "你的账号或者密码错误!");
			response.sendRedirect("login.jsp");
		}else{
			session.setAttribute("USERSINFO", ui);
			response.sendRedirect("index.jsp");
		}
		
	}
	
	public void returnuser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UsersBiz ubiz = new UsersBiz();
		UsersInfo ui = new UsersInfo();
		ui=(UsersInfo)(session.getAttribute("USERSINFO"));
		ui=ubiz.findByUserId(ui.getUserId());
		session.setAttribute("USERSINFO", ui);
		resp.sendRedirect("self_info.jsp");
	}
}
