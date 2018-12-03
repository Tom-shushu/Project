package biz;

import java.util.List;

import bean.GoodsInfo;
import bean.ShoppingInfo;
import bean.TypeInfo;
import dao.GoodsDAO;

public class GoodsBiz {
	
	GoodsDAO gdao = new GoodsDAO();
	
	public List<TypeInfo> findTypeNameById(int typeLevel){
		
		return gdao.findTypeNameById(typeLevel);
	}
	public GoodsInfo findGoodsById(int goodsId){
		return gdao.findGoodsById(goodsId);
	}
	
	


	public List<GoodsInfo> findPage(int page) {
		// TODO Auto-generated method stub
		return gdao.findPage(page);
	}
	
	public List<GoodsInfo> findAllGoodsByTypeId(int typeId){
		return gdao.findAllGoodsByTypeId(typeId);
	}
	
	
	
	public List<GoodsInfo> find(String wd){
		List<GoodsInfo> list = gdao.find(wd);
		for (GoodsInfo ui : list) {
			String name = ui.getGoodsName();
			name = name.replace(wd, "<label style='color:red'>"+wd+"</label>");
			ui.setGoodsName(name);		
		}
		return list;
	}

	public void addShooping(ShoppingInfo si){
		gdao.addShooping(si);
	}
	public List<ShoppingInfo> findShoppingById(int userId) {
		return gdao.findShoppingById(userId);
	}
	public void updateShopping(ShoppingInfo s) {
		// TODO Auto-generated method stub
		gdao.updateShopping(s);
		
	}
	
}
