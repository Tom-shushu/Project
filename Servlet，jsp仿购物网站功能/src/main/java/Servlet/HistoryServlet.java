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

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private	HistoryBiz hb = new HistoryBiz(); 
	private ShoppingBiz sbiz = new ShoppingBiz();
	

	boolean b = true;
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		//分流操作
		String type = req.getParameter("type");
		/*if(type ==null||"findall".equals(type)) {
			
			findall(req,resp);
			
		}else */if("save".equals(type)) {
			save(req,resp);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*List<HistoryInfo> list = hb.findAll();
		HttpSession session = req.getSession();
		session.setAttribute("HISTORYLIST", list);
		resp.sendRedirect("dingdanzhongxin.jsp");
	*/
	}
	

	public void findall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		List<StateInfo> list = new ArrayList<StateInfo>();
		List<StateInfo> list1 = new ArrayList<StateInfo>();
		HttpSession session = req.getSession();

		UsersInfo ui = new UsersInfo();
		ui=(UsersInfo)(session.getAttribute("USERSINFO"));
		
		list1=hb.findByUserId(ui.getUserId());
		
		for(StateInfo hi:list1) {
			System.out.println("shgj");
		}
	int page,num;
	num = ((list1.size()%3)==0)?(int)(list1.size()/3):(int)(list1.size()/3+1);
	System.out.println(num);
		 String p = req.getParameter("page");
		
	if(p==null) {
		 page = 1;
		
	} else {
		 page = Integer.parseInt(p);
			if(page>=num) {
				page=num;
			}
			if(page<=0) {
			page=1;
			}
	}
	if(page==num&&num!=1) {
		for(int i=(page-1)*3;i<list1.size();i++) {
			list.add(list1.get(i));
			
		}
		
	} else if(list.size()>2)  {
	for(int i=(page-1)*3;i<=(3*page)-1;i++) {
	list.add(list1.get(i));
	}
	
	}
	
	 for(StateInfo hi:list) {
		 System.out.println(hi);
	 }
		session.setAttribute("PAGELIST", list);
	
	
		resp.sendRedirect("dingdanzhongxin.jsp?page="+page);
	}
	
	public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<ShoppingInfo> list = (List<ShoppingInfo>)(session.getAttribute("ALLSHOPPINGLIST"));
		for(ShoppingInfo si: list ) {
			HistoryBiz  hb = new HistoryBiz();
			HistoryInfo hi = new HistoryInfo();
			hi.setHisGoodsId(si.getShoppingId());
			hi.setHisGoodsNum(si.getGoodsNum());
			hi.setUserId(si.getUserId());
			hi.setHisGoodsPrice(si.getGoodsTotal()*1.0);
			hb.saveUser(hi);
		}
		//结算完成之后  进行清空该用户的购物车
		for (ShoppingInfo s : list) {
			sbiz.deleteShoppingBySid(s.getShoppingId());
		}
		resp.sendRedirect("gouwuche?type=find");
	}



}
