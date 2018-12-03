package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.GoodsInfo;
import bean.HistoryInfo;
import bean.StateInfo;
import bean.UsersInfo;
import biz.GoodsBiz;
import biz.UsersBiz;
import db.DBManager;
import util.DateUtil;

public class HistoryDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private GoodsBiz gbiz = new GoodsBiz();
	private UsersBiz ubiz= new UsersBiz();
	
	//��ȡhistory�����Ϣ
	public List<HistoryInfo> findAll(){
		List<HistoryInfo> list = new ArrayList<HistoryInfo>();
		conn = DBManager.getConnection();
		String sql = "select * from history";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				HistoryInfo hi = new HistoryInfo();
				
				hi.setHisShopId(rs.getInt(1));
				hi.setHisGoodsId(rs.getInt(2));
				hi.setHisGoodsNum(rs.getInt(3));
				hi.setHisTime( rs.getString(4));
				hi.setUserId(rs.getInt(5));
				hi.setHisGoodsPrice(rs.getDouble(6));
				list.add(hi);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBManager.closeConnection(conn, ps);
		}
		return list;
	}
	
	//ͨ���û���id�ҵ����й��������Ʒ
	public List<StateInfo> findByUserId(int userid){
		
		String sql = "select * from history where UserId=?";
		List<StateInfo> list1 = new ArrayList<StateInfo>();
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			while(rs.next()){
				HistoryInfo hi1 = new HistoryInfo();
				StateInfo si = new StateInfo();
				
				si.setHisShopId(rs.getInt(1));
				//ͨ�^goodsid ��ȫ����Ϣ
				GoodsInfo gi = gbiz.findGoodsById(rs.getInt(2));
				si.setGi(gi);
				si.setHisGoodsNum(rs.getInt(3));
				si.setHisTime( rs.getString(4));
				//ͨ�^�Ñ�id �����Ñ���������Ϣ
				
				UsersInfo ui = ubiz.findByUserId(userid);
				si.setUi(ui);

				si.setHisGoodsPrice(rs.getDouble(6));
				list1.add(si);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBManager.closeConnection(conn, ps);
		}
		
		return list1;
	}
	//���ͻ�������Ĳ�Ʒ�������ݿ�
public void saveHistory(HistoryInfo hi){
		
		
		String sql = "insert into history values(null,?,?,?,?,?)";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,hi.getHisGoodsId());
			ps.setInt(2,hi.getHisGoodsNum());
			ps.setString(3,DateUtil.getNowDate());
			System.out.println(DateUtil.getNowDate());
			ps.setInt(4,hi.getUserId());
			ps.setDouble(5, hi.getHisGoodsPrice());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBManager.closeConnection(conn, ps);
		}
	}

public List<HistoryInfo> dividByPage(int page){
	List<HistoryInfo> list = new ArrayList<HistoryInfo>();
	conn = DBManager.getConnection();
	
	//��һ���ʺ�:�ӵڼ�����ʼ���ڶ����ʺţ���ѯ��������
	String sql = "select * from history limit ?,?";
	
	try {
		ps = conn.prepareStatement(sql);
		ps.setInt(1, (page-1)*3);
		ps.setInt(2, 3);
		rs = ps.executeQuery();
	    
		while(rs.next()) {
			HistoryInfo hi1 = new HistoryInfo();
			hi1.setHisShopId(rs.getInt(1));
			hi1.setHisGoodsId(rs.getInt(2));
			hi1.setHisGoodsNum(rs.getInt(3));
			hi1.setHisTime( rs.getString(4));
			hi1.setUserId(rs.getInt(5));
			hi1.setHisGoodsPrice(rs.getDouble(6));
			list.add(hi1);
		}
	} catch (SQLException e) {
	System.out.println(e.getMessage());
	}finally {
		DBManager.closeConnection(conn, ps);
	}
	return list;
} 

	
}
