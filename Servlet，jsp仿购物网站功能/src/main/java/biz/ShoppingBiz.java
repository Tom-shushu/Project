package biz;

import java.util.List;

import bean.GoodsInfo;
import bean.ShoppingInfo;
import dao.ShoppingDAO;

public class ShoppingBiz {
	private ShoppingDAO sdao = new ShoppingDAO();

	public void deleteGoods(int shoppingId, int userId) {
		sdao.deleteGoods(shoppingId, userId);
	}

	public List<ShoppingInfo> findById(int userid) {
		return sdao.findById(userid);
	}

	public GoodsInfo findByGoodsId(int goodsId) {

		return sdao.findByGoodsId(goodsId);
	}

	public void deleteShoppingBySid(Integer shoppingId) {
		// TODO Auto-generated method stub
		sdao.deleteShoppingBySid(shoppingId);
	}

}
