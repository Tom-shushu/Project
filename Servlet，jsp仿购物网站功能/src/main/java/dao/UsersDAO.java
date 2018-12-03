package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.HistoryInfo;
import bean.UsersInfo;

import db.DBManager;

public class UsersDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
public UsersInfo findByUserId(int userid){
		
		String sql = "select * from users where user_id=?";
		List<UsersInfo> list = new ArrayList<UsersInfo>();
		conn = DBManager.getConnection();
		UsersInfo ui = new UsersInfo();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
		
			if(rs.next()){
		
			ui.setUserId(rs.getInt(1));
			ui.setUserImage(rs.getString(2));
			ui.setUserName(rs.getString(3));
			ui.setUserPassword(rs.getString(4));
			ui.setUserSex(rs.getString(5));
			ui.setUserPhone(rs.getString(6));
			ui.setUserEmail(rs.getString(7));
			ui.setUserAddress(rs.getString(8));
			ui.setUserState(rs.getString(9));
			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
		DBManager.closeConnection(conn, ps);
		}
		
		return ui;
		
}

public void change(UsersInfo ui) {
	conn = DBManager.getConnection();
	String sql = "update users set user_name=?,user_password=?,user_phone=?,user_address=? where user_id=?";
	try {
		ps=conn.prepareStatement(sql);
		ps.setString(1, ui.getUserName());
		ps.setString(2, ui.getUserPassword());
		ps.setString(3, ui.getUserPhone());
		ps.setString(4, ui.getUserAddress());
		ps.setInt(5, ui.getUserId());
		ps.executeUpdate();
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}finally {
		DBManager.closeConnection(conn, ps);
		}
	
}

public UsersInfo checkLogin(UsersInfo ui) {
	
	String sql = "select * from users where user_name=? and user_password=?";
	conn = DBManager.getConnection();
	
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, ui.getUserName());
		ps.setString(2, ui.getUserPassword());
		rs = ps.executeQuery();
		if (rs.next()) {
			ui.setUserId(rs.getInt(1));
			ui.setUserImage(rs.getString(2));
			ui.setUserSex(rs.getString(5));
			ui.setUserPhone(rs.getString(6));
			ui.setUserEmail(rs.getString(7));
			ui.setUserAddress(rs.getString(8));
			ui.setUserState(rs.getString(9));
			
		} else {
			ui = null;
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}finally {
		DBManager.closeConnection(conn, ps);
		}
	
	return ui;
}

public UsersInfo checkLogin1(UsersInfo ui) {
	String sql = "select * from users where user_name=? and user_pass=?";
	conn = DBManager.getConnection();
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, ui.getUserName());
		ps.setString(2, ui.getUserPassword());
		rs = ps.executeQuery();
		if (rs.next()) {
			ui.setUserId(rs.getInt(1));
			ui.setUserImage(rs.getString(2));
			ui.setUserSex(rs.getString(5));
			ui.setUserPhone(rs.getString(6));
			ui.setUserEmail(rs.getString(7));
			ui.setUserState(rs.getString(8));
			
		} else {
			ui = null;
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}finally {
		DBManager.closeConnection(conn, ps);
		}
	return ui;
}
/**
 * 实现了用户注册效果,表中有默认的值的列,在Java都传递的是null
 * 
 * @param ui
 */
public void addUser(UsersInfo ui) {
	String sql = "INSERT INTO users VALUES(NULL,DEFAULT,?,?,'男',?,'1@qq.com','西安',DEFAULT)";
	System.out.println( ui.getUserName());
	System.out.println(  ui.getUserPassword());
	System.out.println( ui.getUserPhone());
	conn = DBManager.getConnection();
	try {
	
		ps = conn.prepareStatement(sql);
		ps.setString(1, ui.getUserName());
		ps.setString(2, ui.getUserPassword());
		ps.setString(3, ui.getUserPhone());
		ps.executeUpdate();
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}finally {
		DBManager.closeConnection(conn, ps);
		}
}
public  UsersInfo findUserByName(String userName){
	System.out.println("username"+userName);
	UsersInfo ui = new UsersInfo();
	String sql = "select * from users where user_name like ?";
	conn = DBManager.getConnection();
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, userName);
		rs = ps.executeQuery();
		if (rs.next()) {
			System.out.println("进入循环");
			ui.setUserId(rs.getInt(1));
			ui.setUserImage(rs.getString(2));
			ui.setUserName(userName);
			ui.setUserSex(rs.getString(5));
			ui.setUserPhone(rs.getString(6));
			ui.setUserEmail(rs.getString(7));
			ui.setUserState(rs.getString(8));
			
		} else {
			ui = null;
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}finally {
		DBManager.closeConnection(conn, ps);
		}
	return ui;
}


}