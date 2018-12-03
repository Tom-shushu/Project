package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GoodsInfo;
import bean.ShoppingInfo;
import bean.UsersInfo;
import biz.GoodsBiz;
import util.DateUtil;

@SuppressWarnings("serial")
@WebServlet("/addShopping")
public class AddShoppingServlet extends HttpServlet {
	private GoodsBiz gbiz = new GoodsBiz();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	//将商品存储到shopping 表中
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取商品的id并把它查询到   然后包装  根据商品id 查询商品
		String gid = req.getParameter("goodsId");
		int goodsId = Integer.parseInt(gid);
		GoodsInfo gi = gbiz.findGoodsById(goodsId);
		
		HttpSession session = req.getSession();
		//获取user容器中的内容
		//获取user容器中的当前用户的对象
		String error =  "";
		int fl = 0;
		UsersInfo user = (UsersInfo)session.getAttribute("USERSINFO");
	
		String name = req.getParameter(user.getUserName());
		session.setAttribute("USERNAME", name);
		
		
		
		if(user == null) {// 说明没有任何的登陆  提示去 登陆
			session.setAttribute("ERROR1","未登录，请先登录!");
		    resp.sendRedirect("xiangqing.jsp");	
		}else {  //说明已经登录好了  可以使用ui			
			//先查询当前用户的购物车 
			List<ShoppingInfo> list = gbiz.findShoppingById(user.getUserId());
			////判断该物品在当前用户购物车中是否存在
			for (ShoppingInfo s : list) {
				//如果存在  那就给数量加1  并且 计算total
				
				if(s.getGoodsId() == goodsId) {
					//数量加1
					int num = s.getGoodsNum()+1;
				s.setGoodsNum(num);
				//求小计
				s.setGoodsTotal(num*gi.getGoodsPrice());
				fl = 1;
				//更新数据库信息 把s更新了
				gbiz.updateShopping(s);
				session.setAttribute("ERROR1", "成功加入购物车！");
				resp.sendRedirect("xiangqing.jsp");
				//error="成功加入购物车！";
				}
			}	
			//说明购物车不存在 添加到购物车
			if(fl == 0) {
				ShoppingInfo si = new ShoppingInfo();
				si.setGs(gi);
				si.setGoodsId(gi.getGoodsId());
				si.setGoodsNum(1);
				si.setGoodsTotal(si.getGoodsNum()*si.getGs().getGoodsPrice());
				si.setAddTime(DateUtil.getNowDate());
				si.setUserId(user.getUserId());
				si.setReceiverState(1);
				gbiz.addShooping(si);
				error="成功加入购物车！";
				session.setAttribute("ERROR1", "成功加入购物车！");
				resp.sendRedirect("xiangqing.jsp");
			}
			
		}
		
		
	}
	
	

}
