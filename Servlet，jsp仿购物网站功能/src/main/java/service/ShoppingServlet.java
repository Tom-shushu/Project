package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ShoppingInfo;
import bean.UsersInfo;
import biz.ShoppingBiz;

@WebServlet("/gouwuche")
public class ShoppingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ShoppingBiz sbiz = new ShoppingBiz();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if ("find".equals(type)) {
			findshopping(request, response);
		} else if ("delete".equals(type)) {
			deleteGoods(request, response);
		} else if ("findgoodsbypage".equals(type)) {
			findGoodsBypage(request, response);
		}
	}

	// 删除
	@SuppressWarnings("unused")
	private void deleteGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int gid = Integer.parseInt(request.getParameter("goodsid"));
		int uid = Integer.parseInt(request.getParameter("userid"));
		int gnum = Integer.parseInt(request.getParameter("shopnum"));

		sbiz.deleteGoods(gid, uid);

		findshopping(request, response);
	}

	// 通过用户id查找用户添加的商品
	private void findshopping(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;

		HttpSession session = request.getSession();

		UsersInfo ui =(UsersInfo)session.getAttribute("USERSINFO");
		System.out.println(ui);
		
		if(ui == null) {
			//
			response.sendRedirect("index.jsp");
			
		}else {
			
		ShoppingBiz sbiz = new ShoppingBiz();
		List<ShoppingInfo> list = sbiz.findById(ui.getUserId());

		// 计算一下总价
		double sum = 0.0;
		for (ShoppingInfo s : list) {
			sum += s.getGs().getGoodsPrice() * s.getGoodsNum();
		}

		// 把所有商品放入容器中
		session.setAttribute("ALLSHOPPINGLIST", list);
		// 存放第一页的集合
		List<ShoppingInfo> newlist = new ArrayList<ShoppingInfo>();
		// 计算应该分的页数
		System.out.println("list.size" + list.size());

		int allPage = ((list.size() % 2) == 0) ? (list.size() / 2) : ((list.size() / 2) + 1);
		// 把第一页的数据存放在集合中
		if (list.size() < 2) {
			for (int i = 0; i < list.size(); i++) {
				newlist.add(list.get(i));
			}
		} else {
			for (int i = (page - 1) * 2; i < (page - 1) * 2 + 2; i++) {
				newlist.add(list.get(i));
			}
		}

		// 把这个集合放到容器中
		session.setAttribute("SHOPPINGLIST", newlist);

		response.sendRedirect("gouwuche.jsp?allpage=" + allPage + "&sum=" + sum);
		}

	}

	// 实现分页操作
	@SuppressWarnings("unchecked")
	public void findGoodsBypage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// 得到页码
		String p = request.getParameter("page");
		int page = Integer.parseInt(p);
		// 选取该页码上的所有商品
		List<ShoppingInfo> list = (List<ShoppingInfo>) session.getAttribute("ALLSHOPPINGLIST");

		// 计算一下总价
		double sum = 0.0;
		for (ShoppingInfo s : list) {
			sum += s.getGs().getGoodsPrice() * s.getGoodsNum();
		}

		List<ShoppingInfo> newlist = new ArrayList<ShoppingInfo>();
		// 计算应该分的页数
		int allPage = ((list.size() % 2) == 0) ? (list.size() / 2) : ((list.size() / 2) + 1);

		if (list.size() > 2 && list.size() % 2 != 0) {
			if (page == allPage) {
				for (int i = (page - 1) * 2; i < list.size(); i++) {
					newlist.add(list.get(i));
				}
			} else {
				for (int i = (page - 1) * 2; i < (page - 1) * 2 + 2; i++) {
					newlist.add(list.get(i));
				}
			}
		} else if (list.size() < 2) {
			for (int i = 0; i < list.size(); i++) {
				newlist.add(list.get(i));
			}
		} else {
			for (int i = (page - 1) * 2; i < (page - 1) * 2 + 2; i++) {
				newlist.add(list.get(i));
			}
		}

		// 把这个集合放到容器中
		session.setAttribute("SHOPPINGLIST", newlist);

		response.sendRedirect("gouwuche.jsp?allpage=" + allPage + "&sum=" + sum);
	}
}
