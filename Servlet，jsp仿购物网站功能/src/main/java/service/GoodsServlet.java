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
import bean.GoodsInfo;
import biz.GoodsBiz;

@WebServlet("/goods")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodsBiz gbiz = new GoodsBiz();


	@Override

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("8888");
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if (null == type || "findallgoodsbyid".equals(type)) {
			findAllGoodsById(request, response);
		} else if ("xiangqing".equals(type)) {
			xiangQing(request, response);
		} else if ("findallgoodsbypage".equals(type)) {
			findAllGoodsByPage(request, response);
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//关于详情页面的处理
	public void xiangQing(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 根据商品id查询商品完整信息
		String goodsId = request.getParameter("id");
		int id = Integer.parseInt(goodsId);
		System.out.println(id);
		GoodsInfo gi = gbiz.findGoodsById(id);
		HttpSession session = request.getSession();
		System.out.println(gi);
		session.removeAttribute("ERROR1");
		session.setAttribute("GOODSINFO", gi);
		response.sendRedirect("xiangqing.jsp");
	}
	//通过typeId查询 所有的商品信息  然后进行首页的分页处理
	public void findAllGoodsById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int page = 1;
		String typeId = request.getParameter("id");
		int id = Integer.parseInt(typeId);
		String typename = request.getParameter("typename");
		String typeNAME = request.getParameter("Typename");
		// 在商品表中查询 typeId = id 的商品
		List<GoodsInfo> glist = gbiz.findAllGoodsByTypeId(id);
		int allPage = (glist.size() % 5 == 0 ? glist.size() / 5 : glist.size() / 5 + 1);
		List<GoodsInfo> list = new ArrayList<GoodsInfo>();
		for (int i = (page - 1) * 5; i < (page - 1) * 5 + 5; i++) {
			list.add(glist.get(i));
		}
		for (GoodsInfo goodsInfo : list) {
			System.out.println(goodsInfo);
		}
		HttpSession session = request.getSession();
		session.setAttribute("ALLGOODSBYTYPEID", list);
		session.setAttribute("AllGOODS", glist);
		session.setAttribute("TYPENAME", typename);
		session.setAttribute("TYPEname", typeNAME);
		response.sendRedirect("goodsLists.jsp?allpage=" + allPage);
	}

	//第一页之后  从集合中获取到所需要分页的商品
	public void findAllGoodsByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		int page = 1;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List<GoodsInfo> glist = (List<GoodsInfo>) session.getAttribute("AllGOODS");
		// 查看glist的长度
		int allPage = (glist.size() % 5 == 0 ? glist.size() / 5 : glist.size() / 5 + 1);
		List<GoodsInfo> list = new ArrayList<GoodsInfo>();
		for (int i = (page - 1) * 5; i < (page - 1) * 5 + 5; i++) {
			list.add(glist.get(i));
		}
		session.setAttribute("ALLGOODSBYTYPEID", list);
		response.sendRedirect("goodsLists.jsp?allpage=" + allPage);
	}
}
