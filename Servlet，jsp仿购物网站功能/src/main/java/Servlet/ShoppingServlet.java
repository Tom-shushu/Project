package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.HistoryInfo;
import bean.ShoppingInfo;
import bean.StateInfo;
import bean.UsersInfo;
import biz.HistoryBiz;
import biz.ShoppingBiz;


@WebServlet("/selfinfo")
public class ShoppingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int page1 =0;
	ShoppingBiz sbiz=new ShoppingBiz();
	HistoryBiz hbiz = new HistoryBiz();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if("find".equals(type)) {
			findshopping(request, response);
		}else if("delete".equals(type)) {
			deleteGoods(request,response);
		}
	}
	
	
	@SuppressWarnings("unused")
	private void deleteGoods(HttpServletRequest request, HttpServletResponse response)
			throws  ServletException,IOException{
		
		HttpSession session = request.getSession();
		
		int gid = Integer.parseInt(request.getParameter("goodsid"));
		int uid = Integer.parseInt(request.getParameter("userid"));
		
		sbiz.deleteGoods(gid,uid);	
		response.sendRedirect("index.jsp");
		
	}

	private void findshopping(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		UsersInfo user = (UsersInfo)session.getAttribute("USERSINFO");
		
		ShoppingBiz sbiz = new ShoppingBiz();
		List<StateInfo> list =hbiz.findByUserId(user.getUserId());
		
		
		
		
		//session.setAttribute("SHOPPINGLIST",list);
		System.out.println("pppppppppppppppp");
		System.out.println(list.size());
		System.out.println("pppppppppppppppp");	
		int num=((list.size()%3)==0)?(list.size()/3):((list.size()/3)+1);
		System.out.println("wowoow");
		System.out.println(num);
		System.out.println("wowoow");
		String page=request.getParameter("page");
		System.out.println(page);
		
		List<StateInfo>list1=new ArrayList<StateInfo>();
		if(page==null) {
			page1=1;
			
		}else {
			page1 = Integer.parseInt(request.getParameter("page"));
			
			if(page1<=0) {
				page1=1;
				
			}
			
			if(page1>=num) {
				page1=num;
			
			}
		
			
		}
		
		System.out.println("--------------");
		System.out.println(page1);
		System.out.println("cccc");
		if(num==page1) {
		for(int i=(page1-1)*3;i<list.size();i++) {
			
			System.out.println(i);
			list1.add(list.get(i));
		}
		
		} else if(num!=0&&list.size()>=2){
			for(int i=(page1-1)*3;i<=(page1*3-1);i++) {
				System.out.println(list.get(i));
			
				list1.add(list.get(i));
		}
		
		
		}
	
		session.setAttribute("SPLIST",list1);
		
		response.sendRedirect("dingdanzhongxin.jsp?page="+page1);
	}
	
	
	
	
}
