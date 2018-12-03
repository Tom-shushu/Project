package bean;

public class HistoryInfo {

	
/*	private Integer HisShopId;
	private Integer HisGoodsId;

	private Integer HisGoodsNum;
	private String HisTime;
	private Integer UserId;
	private Double HisGoodsPrice;*/
	
	public int  HisShopId;
	public int HisGoodsId;
	public int HisGoodsNum;
	public String HisTime;
	public int UserId;
	public Double HisGoodsPrice;
	
	
	public HistoryInfo() {
		
	}
	
	public int getHisShopId() {
		return HisShopId;
	}
	public void setHisShopId(int hisShopId) {
		HisShopId = hisShopId;
	}
	public int getHisGoodsId() {
		return HisGoodsId;
	}
	public void setHisGoodsId(int hisGoodsId) {
		HisGoodsId = hisGoodsId;
	}
	public int getHisGoodsNum() {
		return HisGoodsNum;
	}
	public void setHisGoodsNum(int hisGoodsNum) {
		HisGoodsNum = hisGoodsNum;
	}
	public String getHisTime() {
		return HisTime;
	}
	public void setHisTime(String hisTime) {
		HisTime = hisTime;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public Double getHisGoodsPrice() {
		return HisGoodsPrice;
	}
	public void setHisGoodsPrice(Double hisGoodsPrice) {
		HisGoodsPrice = hisGoodsPrice;
	}


	
	
}
