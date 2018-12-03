package biz;

import java.util.List;

import bean.UsersInfo;
import dao.UsersDAO;

public class UsersBiz {
	private UsersDAO udao = new UsersDAO();
	
	public UsersInfo findByUserId(int userid){
		return udao.findByUserId(userid);
	}

	public void change(UsersInfo ui) {
		udao.change(ui);
	}
	
	public UsersInfo checkLogin1(UsersInfo ui) {
		UsersInfo ui1 = new UsersInfo();
		ui1= udao.checkLogin(ui);
		return ui1;
	}
	
	public UsersInfo checkLogin(UsersInfo ui) {
		return udao.checkLogin(ui);
	}
	
	public void addUser(UsersInfo ui) {
		udao.addUser(ui);
	}
	public  UsersInfo findUserByName(String userName){
		return udao.findUserByName(userName);
		
	}
	
}
