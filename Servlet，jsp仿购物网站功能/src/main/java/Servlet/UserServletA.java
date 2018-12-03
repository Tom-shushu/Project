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

@SuppressWarnings("serial")
@WebServlet("/regist_submit")
public class UserServletA extends HttpServlet{

	private UsersBiz ub = new UsersBiz();
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		addUser(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		UsersInfo ui = new UsersInfo();
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String tel = request.getParameter("tel");
		String yzm = request.getParameter("yzm");
		String error="�û�������Ϊ��!";
		int f1=0,f2=0,f3=0,f4 = 0,f5 = 0;
		System.out.println(name);
		System.out.println(password);
		System.out.println(repassword);
		System.out.println(tel);
		System.out.println(yzm);
		
		
		//���ȼ���û�������ȷ��
		if(name==null) {
			f1 = 0;
			error="�û�������Ϊ��!";
		}else{
			//�����ݿ��в�ѯ�Ƿ��û����Ѿ�����
			if(ub.findUserByName(name) == null) {
				f1=1;
			}else {
				f1=0;
				error="�û����Ѵ��ڣ�";
			}
		}
		if(password==null) {
			f2 = 0;
			error="���볤��Ϊ8-16λ";
		}else{
			if(password.length()<8||password.length()>16) {
				f2=0;
				error="���볤��Ϊ8-16λ";
			}else {
				f2=1;
				error="���볤��δ��8-16λ��";
			}
			
		}if(repassword==null) {
			f3 = 0;
			
		}else{
			if(password.equals(repassword)) {
				f3=1;
			}else {
				error="�����������벻һ��!";
				f3=0;
			}
		} if(tel==null){
			f4 = 0;
			
		}else{
			String pattern = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$";
			if(tel.matches(pattern)) {
				f4 = 1;
			}else {
				error="�ֻ��Ų�ƥ��!";
			}	
		} if(yzm==null){
			f5 = 0;
			
		}else{
			if(session.getAttribute("YANZHENGMA").toString().equalsIgnoreCase(yzm)) {
				f5=1;
			}else {
				f5=0;
				error="��֤�벻��ȷ!";
			}
		}
		
		
		if(f1==1&&f2==1&&f3==1&&f4==1&&f5==1) {
			ui.setUserName(name);
			ui.setUserPhone(tel);
			ui.setUserPassword(password);
			ub.addUser(ui);
			response.sendRedirect("index.jsp");
			
		}else {
			System.out.print("  f1  "+f1);
			System.out.print("  f2  "+f2);
			System.out.print("  f3  "+f3);
			System.out.print("  f4  "+f4);
			System.out.print("  f5  "+f5);
			System.out.println(error);
			session.setAttribute("ERROR", error);
			response.sendRedirect("registera.jsp");
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
		
		ui = ub.checkLogin1(ui);
		if(ui==null){
			session.setAttribute("ERROR", "����˺Ż����������!");
			response.sendRedirect("index.jsp");
		}else{
			session.setAttribute("USERSINFO", ui);
			response.sendRedirect("index.jsp");
		}
		
	}
}
