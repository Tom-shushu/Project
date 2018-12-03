package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.GoodsInfo;
import bean.TypeInfo;
import db.DBManager;

import bean.GoodsInfo;

import db.DBManager;

public class TyperDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
public List<TypeInfo> findByLevelId(int typeId){
		
		String sql = "select * from typer where typer_level=?";
		List<TypeInfo> list = new ArrayList<TypeInfo>();
		List<GoodsInfo> list1 = new ArrayList<GoodsInfo>(); 
		GoodsDAO gidao = new GoodsDAO();
		
		conn = DBManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, typeId);
			rs = ps.executeQuery();
			if(rs.next()){
				
				TypeInfo ti = new TypeInfo();
				ti.setTypeId(rs.getInt(1));
				ti.setTypeName(rs.getString(2));
				ti.setTypeLevel(rs.getInt(3));
				ti.setTypeState("1");
				list1=gidao.findById(rs.getInt(1));
				//ti.setList(list1);
				list.add(ti);
			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBManager.closeConnection(conn, ps);
			}
		
		return list;
}
}