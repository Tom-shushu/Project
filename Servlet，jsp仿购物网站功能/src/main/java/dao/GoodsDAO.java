package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bean.GoodsInfo;
import bean.ShoppingInfo;
import bean.TypeInfo;
import db.DBManager;
import util.DateUtil;

public class GoodsDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	//通过高一级别的id  去查询  下一级别所属的所有分类
	public List<TypeInfo> findTypeNameById(int typeLevel){
		ResultSet rs = null;
		 List<TypeInfo> list = new ArrayList<>();
		 
		 String sql = "select *from typer where typer_level = ?";
		 
		 conn = DBManager.getConnection();
		 
		 try {
			ps = conn.prepareStatement(sql);
			 
			 ps.setInt(1, typeLevel);
			 
			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 
				 TypeInfo tif = new TypeInfo();
				 
				 tif.setTypeId(rs.getInt(1));
				 
				 tif.setTypeName(rs.getString(2));
				 
				 tif.setTypeLevel(typeLevel);
				 
				 tif.setTypeState(rs.getString(4));
				 
				 //查询当前level所对应的的所有  type
				 
				 List<TypeInfo> tlist = findTypeById(rs.getInt(1)); 
				 
				 tif.setList(tlist);
				 
				 list.add(tif);
				 
			 }
			 ps.close();
			 conn.close();
			 rs.close();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBManager.closeConnection(conn, ps);
		}
		return list;
		
	}
	
	
	
	public List<TypeInfo> findTypeById(int typeId){
		 ResultSet rs1 = null;
	 List<TypeInfo> list = new ArrayList<>();
		 
		 String sql = "select *from typer where typer_level = ?";
		 
		 conn = DBManager.getConnection();
		 
		 try {
			ps = conn.prepareStatement(sql);
			 
			 ps.setInt(1, typeId);
			 
			 rs1 = ps.executeQuery();
			 
			 while(rs1.next()) {
				 
				 TypeInfo tif = new TypeInfo();
				 
				 tif.setTypeId(rs1.getInt(1));
				 
				 tif.setTypeName(rs1.getString(2));
				 
				 tif.setTypeLevel(typeId);
				 
				 tif.setTypeState(rs1.getString(4));
				 
				 list.add(tif);
				 
			 }
			 ps.close();
			 conn.close();
			 rs1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
		return list;
	}
	
	
	//查找所有typer 信息
	
	
	public List<TypeInfo> findALLTypeByTypeId(int typeId){
		ResultSet rs = null;
		List<TypeInfo>list = new ArrayList<TypeInfo>();
		String sql = "select * from typer where typer_id = ?";
		conn = DBManager.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,typeId);
			rs = ps.executeQuery();
			while(rs.next()) {
				TypeInfo ti = new TypeInfo();
				ti.setTypeId(rs.getInt(1));
				ti.setTypeName(rs.getString(2));
				ti.setTypeLevel(rs.getInt(3));
				ti.setTypeState(rs.getString(4));
				list.add(ti);
}
		ps.close();
			 conn.close();
			 rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
		return list;
	
	}
	
	
	
	
	
	
	// 通过商品id去查询商品的所有信息
	
	public  GoodsInfo findGoodsById(int goodsId){
		ResultSet rs = null;
		GoodsInfo gi = new GoodsInfo();
		String sql = "select * from goods where goods_id = ?";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, goodsId);
			rs = ps.executeQuery();
			if(rs.next()){
				gi.setGoodsId(goodsId);
				gi.setGoodsImage(rs.getString(2));
				gi.setGoodsName(rs.getString(3));
				gi.setGoodsPrice(rs.getDouble(4));
				gi.setGoodsIntro(rs.getString(5));
				gi.setGoodsStock(rs.getInt(6));
				gi.setTyperId(rs.getInt(7));
				gi.setGoodsState(rs.getString(8));
			}
			 ps.close();
			 conn.close();
			 rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return gi;
	}
	//通过 typeid查询到  下的所有商品
	public List<GoodsInfo> findAllGoodsByTypeId(int typeId){
		ResultSet rs = null;
		List<GoodsInfo> list = new ArrayList<GoodsInfo>();
		String sql = "select * from goods where typer_id = ?";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,typeId);
			rs = ps.executeQuery();
			while(rs.next()){
				GoodsInfo gi = new GoodsInfo();

				gi.setGoodsId(rs.getInt(1));
				gi.setGoodsImage(rs.getString(2));
				gi.setGoodsName(rs.getString(3));
				gi.setGoodsPrice(rs.getDouble(4));
				gi.setGoodsIntro(rs.getString(5));
				gi.setGoodsStock(rs.getInt(6));
				gi.setTyperId(typeId);
				gi.setGoodsState(rs.getString(8));
				list.add(gi);
			}
			 ps.close();
			 conn.close();
			 rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	
	public List<GoodsInfo> findPage(int page){
		ResultSet rs = null;
		List<GoodsInfo> list = new ArrayList<GoodsInfo>();
		String sql = "select * from goods limit ?,?";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*5);
			ps.setInt(2,5);
			rs = ps.executeQuery();
			while(rs.next()){
				GoodsInfo gi = new GoodsInfo();
				gi.setGoodsId(rs.getInt(1));
				gi.setGoodsImage(rs.getString(2));
				gi.setGoodsName(rs.getString(3));
				gi.setGoodsPrice(rs.getDouble(4));
				gi.setGoodsIntro(rs.getString(5));
				gi.setGoodsStock(rs.getInt(6));
				gi.setTyperId(rs.getInt(7));
				gi.setGoodsState(rs.getString(8));
				list.add(gi);
			}
			 ps.close();
			 conn.close();
			 rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	

	
//搜索	
	public List<GoodsInfo> find(String wd){
		ResultSet rs = null;
		List<GoodsInfo> list = new ArrayList<GoodsInfo>();
		String sql = "select * from goods where goods_id like ?";
		
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, wd);
			rs = ps.executeQuery();
			while(rs.next()){
				GoodsInfo gi = new GoodsInfo();
				gi.setGoodsImage(rs.getString(2));
				gi.setGoodsName(rs.getString(3));
				gi.setGoodsPrice(rs.getDouble(4));
				gi.setGoodsIntro(rs.getString(5));
				gi.setGoodsStock(rs.getInt(6));
				gi.setTyperId(rs.getInt(7));
				gi.setGoodsState(rs.getString(8));
				list.add(gi);
			}
			 ps.close();
			 conn.close();
			 rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	
	
	
	

	
	public void addShooping(ShoppingInfo si){
		String sql = "insert into shopping values(NULL,?,?,?,?,?,default)";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, si.getGoodsId());
			ps.setInt(2, si.getGoodsNum());
			ps.setDouble(3, si.getGoodsTotal());
			ps.setString(4, si.getAddTime());
			ps.setInt(5, si.getUserId());
			ps.executeUpdate();
			 ps.close();
			 conn.close();

		} 

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	

	
	
	//通过id查询shopping
	public List<ShoppingInfo> findShoppingById(int userId) {
		ResultSet  rs = null;
		List<ShoppingInfo> list = new ArrayList<>();
		String sql = "select * from shopping where user_id = ?";
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				ShoppingInfo si = new ShoppingInfo();
				// 通过goodsId查询 goods的整条信息
				
				si.setShoppingId(rs.getInt(1));	
				GoodsInfo goods = findGoodsById(rs.getInt(2));
				si.setGs(goods);
				si.setGoodsId(goods.getGoodsId());
			
				si.setGoodsNum(rs.getInt(3));
			
				si.setGoodsTotal(rs.getDouble(4));	
			
				si.setAddTime(rs.getString(5));	
				
				si.setUserId(userId);			
			
				si.setReceiverState(1);
				
				list.add(si);			
			}
			 ps.close();
			 conn.close();
			 rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}



	public void updateShopping(ShoppingInfo s) {
		System.out.println(s.getGoodsNum());
		System.out.println(s.getGoodsTotal());
		//更新s的信息   更新数量 更新总价 更新时间
		String sql =" UPDATE shopping SET goods_num =  "+s.getGoodsNum()+" , "
				+ "goods_total= "+s.getGoodsTotal()+","
						+ "add_time= '"+DateUtil.getNowDate()+"' WHERE shopping_id = ? ";
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getShoppingId());
			int i = ps.executeUpdate();
			 ps.close();
			 conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<GoodsInfo> findAll(){
		ResultSet rs = null;
				List<GoodsInfo> list = new ArrayList<GoodsInfo>();
				conn = DBManager.getConnection();
				String sql = "select * from goods";
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						GoodsInfo gi = new GoodsInfo();
						
						gi.setGoodsId(rs.getInt(1));
						gi.setGoodsImage(rs.getString(2));
						gi.setGoodsName(rs.getString(3));
						gi.setGoodsPrice((double) rs.getInt(4));
						gi.setGoodsIntro(rs.getString(5));
						list.add(gi);
						
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}finally {
					DBManager.closeConnection(conn, ps);
				}
				return list;
			}
			
			public List<GoodsInfo> findById(int typeId){
				ResultSet rs = null;
				String sql = "select * from goods where typer_level=?";
				List<GoodsInfo> list = new ArrayList<GoodsInfo>();
				conn = DBManager.getConnection();
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, typeId);
					rs = ps.executeQuery();
					if(rs.next()){
						GoodsInfo gi = new GoodsInfo();
						gi.setGoodsId(rs.getInt(1));
						gi.setGoodsImage(rs.getString(2));
						gi.setGoodsName(rs.getString(3));
						gi.setGoodsPrice((double) rs.getInt(4));
						gi.setGoodsIntro(rs.getString(5));
						list.add(gi);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}finally {
					DBManager.closeConnection(conn, ps);
				}
				
				return list;
			}
	
	
	
}
