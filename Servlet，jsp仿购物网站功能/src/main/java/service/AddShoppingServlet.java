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
	
	//����Ʒ�洢��shopping ����
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ��Ʒ��id��������ѯ��   Ȼ���װ  ������Ʒid ��ѯ��Ʒ
		String gid = req.getParameter("goodsId");
		int goodsId = Integer.parseInt(gid);
		GoodsInfo gi = gbiz.findGoodsById(goodsId);
		
		HttpSession session = req.getSession();
		//��ȡuser�����е�����
		//��ȡuser�����еĵ�ǰ�û��Ķ���
		String error =  "";
		int fl = 0;
		UsersInfo user = (UsersInfo)session.getAttribute("USERSINFO");
	
		String name = req.getParameter(user.getUserName());
		session.setAttribute("USERNAME", name);
		
		
		
		if(user == null) {// ˵��û���κεĵ�½  ��ʾȥ ��½
			session.setAttribute("ERROR1","δ��¼�����ȵ�¼!");
		    resp.sendRedirect("xiangqing.jsp");	
		}else {  //˵���Ѿ���¼����  ����ʹ��ui			
			//�Ȳ�ѯ��ǰ�û��Ĺ��ﳵ 
			List<ShoppingInfo> list = gbiz.findShoppingById(user.getUserId());
			////�жϸ���Ʒ�ڵ�ǰ�û����ﳵ���Ƿ����
			for (ShoppingInfo s : list) {
				//�������  �Ǿ͸�������1  ���� ����total
				
				if(s.getGoodsId() == goodsId) {
					//������1
					int num = s.getGoodsNum()+1;
				s.setGoodsNum(num);
				//��С��
				s.setGoodsTotal(num*gi.getGoodsPrice());
				fl = 1;
				//�������ݿ���Ϣ ��s������
				gbiz.updateShopping(s);
				session.setAttribute("ERROR1", "�ɹ����빺�ﳵ��");
				resp.sendRedirect("xiangqing.jsp");
				//error="�ɹ����빺�ﳵ��";
				}
			}	
			//˵�����ﳵ������ ��ӵ����ﳵ
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
				error="�ɹ����빺�ﳵ��";
				session.setAttribute("ERROR1", "�ɹ����빺�ﳵ��");
				resp.sendRedirect("xiangqing.jsp");
			}
			
		}
		
		
	}
	
	

}
