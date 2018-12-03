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

	// ɾ��
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

	// ͨ���û�id�����û���ӵ���Ʒ
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

		// ����һ���ܼ�
		double sum = 0.0;
		for (ShoppingInfo s : list) {
			sum += s.getGs().getGoodsPrice() * s.getGoodsNum();
		}

		// ��������Ʒ����������
		session.setAttribute("ALLSHOPPINGLIST", list);
		// ��ŵ�һҳ�ļ���
		List<ShoppingInfo> newlist = new ArrayList<ShoppingInfo>();
		// ����Ӧ�÷ֵ�ҳ��
		System.out.println("list.size" + list.size());

		int allPage = ((list.size() % 2) == 0) ? (list.size() / 2) : ((list.size() / 2) + 1);
		// �ѵ�һҳ�����ݴ���ڼ�����
		if (list.size() < 2) {
			for (int i = 0; i < list.size(); i++) {
				newlist.add(list.get(i));
			}
		} else {
			for (int i = (page - 1) * 2; i < (page - 1) * 2 + 2; i++) {
				newlist.add(list.get(i));
			}
		}

		// ��������Ϸŵ�������
		session.setAttribute("SHOPPINGLIST", newlist);

		response.sendRedirect("gouwuche.jsp?allpage=" + allPage + "&sum=" + sum);
		}

	}

	// ʵ�ַ�ҳ����
	@SuppressWarnings("unchecked")
	public void findGoodsBypage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// �õ�ҳ��
		String p = request.getParameter("page");
		int page = Integer.parseInt(p);
		// ѡȡ��ҳ���ϵ�������Ʒ
		List<ShoppingInfo> list = (List<ShoppingInfo>) session.getAttribute("ALLSHOPPINGLIST");

		// ����һ���ܼ�
		double sum = 0.0;
		for (ShoppingInfo s : list) {
			sum += s.getGs().getGoodsPrice() * s.getGoodsNum();
		}

		List<ShoppingInfo> newlist = new ArrayList<ShoppingInfo>();
		// ����Ӧ�÷ֵ�ҳ��
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

		// ��������Ϸŵ�������
		session.setAttribute("SHOPPINGLIST", newlist);

		response.sendRedirect("gouwuche.jsp?allpage=" + allPage + "&sum=" + sum);
	}
}
