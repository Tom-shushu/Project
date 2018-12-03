package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.GoodsInfo;
import bean.ShoppingInfo;
import db.DBManager;

public class ShoppingDAO {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private ResultSet rs1 = null;

	// 删除
	public void deleteGoods(int goodsId, int userId) {
				
		String sql = "DELETE FROM shopping WHERE goods_id=? AND user_id=?";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, goodsId);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBManager.closeConnection(conn, ps);
		}
	}

	// 通过id查询
	public List<ShoppingInfo> findById(int userId) {
		List<ShoppingInfo> list = new ArrayList<>();
		String sql = "select * from shopping where user_id = ?";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				ShoppingInfo si = new ShoppingInfo();
				// 通过goodsId查询 goods的整条信息
				GoodsInfo goods = findByGoodsId(rs.getInt(2));
				si.setShoppingId(rs.getInt(1));
				si.setGs(goods);
				si.setGoodsNum(rs.getInt(3));
				si.setGoodsTotal(rs.getInt(4));
				si.setAddTime(rs.getString(5));
				si.setUserId(userId);
				si.setReceiverState(7);
				list.add(si);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBManager.closeConnection(conn, ps);
		}
		return list;
	}

	// 通过商品的id查询
	public GoodsInfo findByGoodsId(int goodsId) {
		GoodsInfo goods = new GoodsInfo();
		String sql = "select * from goods where goods_id = ?";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, goodsId);
			rs1 = ps.executeQuery();
			if (rs1.next()) {

				goods.setGoodsId(goodsId);
				goods.setGoodsImage(rs1.getString(2));
				goods.setGoodsName(rs1.getString(3));
				goods.setGoodsPrice(rs1.getDouble(4));
				goods.setGoodsIntro(rs1.getString(5));
				goods.setGoodsStock(rs1.getInt(6));
				goods.setTyperId(rs1.getInt(7));
				goods.setGoodsState(rs1.getString(8));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBManager.closeConnection(conn, ps);
		}
		return goods;
	}

	public void deleteShoppingBySid(Integer s) {
		String sql = "DELETE FROM shopping WHERE shopping_id = ?";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBManager.closeConnection(conn, ps);
		}
		
	}
	
	
	
}
