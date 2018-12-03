package bean;

public class StateInfo {
	
	
	private  int  HisShopId;
	private GoodsInfo gi;
	private int HisGoodsNum;
	private String HisTime;
	private UsersInfo ui;
	private Double HisGoodsPrice;
	

	public StateInfo() {
	
	}


	public int getHisShopId() {
		return HisShopId;
	}


	public void setHisShopId(int hisShopId) {
		HisShopId = hisShopId;
	}


	public GoodsInfo getGi() {
		return gi;
	}


	public void setGi(GoodsInfo gi) {
		this.gi = gi;
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


	public UsersInfo getUi() {
		return ui;
	}


	public void setUi(UsersInfo ui) {
		this.ui = ui;
	}


	public Double getHisGoodsPrice() {
		return HisGoodsPrice;
	}


	public void setHisGoodsPrice(Double hisGoodsPrice) {
		HisGoodsPrice = hisGoodsPrice;
	}


	@Override
	public String toString() {
		return "StateInfo [HisShopId=" + HisShopId + ", gi=" + gi + ", HisGoodsNum=" + HisGoodsNum + ", HisTime="
				+ HisTime + ", ui=" + ui + ", HisGoodsPrice=" + HisGoodsPrice + "]";
	}
	
	
	
	
	
}
