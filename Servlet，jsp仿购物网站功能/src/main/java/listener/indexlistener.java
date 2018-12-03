package listener;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import bean.GoodsInfo;
import bean.ShoppingInfo;
import bean.TypeInfo;
import biz.GoodsBiz;
import util.DateUtil;

public class indexlistener implements HttpSessionListener{
	GoodsBiz gbiz = new GoodsBiz();
	//���������һ������ʱ�����Ự
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		List<TypeInfo> list0 = gbiz.findTypeNameById(0);
		List<TypeInfo> list1=  gbiz.findTypeNameById(1);
		List<TypeInfo> list2=  gbiz.findTypeNameById(2);
		List<GoodsInfo> list3 = gbiz.findPage(1);
			
		
		session.setAttribute("SYSP", list3);
		session.setAttribute( "LIST0",list0);
		session.setAttribute("LIST1",list1);
		session.setAttribute("LIST2",list2);
		
		
	}
	
	//��������ر�ʱ���ٻỰ(���ӳ���)
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(DateUtil.getNowDate()+"�Ự��������!");
	}
}
