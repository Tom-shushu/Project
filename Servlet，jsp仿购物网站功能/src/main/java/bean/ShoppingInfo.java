package bean;

public class ShoppingInfo {

	private Integer shoppingId;
	private GoodsInfo gs;
	private Integer GoodsId ;
	private Integer goodsNum;
	private double goodsTotal;
	private String addTime;
	private Integer userId;
	private Integer receiverState;

	public ShoppingInfo() {

	}

	public Integer getShoppingId() {
		return shoppingId;
	}

	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}

	public GoodsInfo getGs() {
		return gs;
	}

	public void setGs(GoodsInfo gs) {
		this.gs = gs;
	}

	public Integer getGoodsId() {
		return GoodsId;
	}

	public void setGoodsId(Integer goodsId) {
		GoodsId = goodsId;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public double getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(double goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(Integer receiverState) {
		this.receiverState = receiverState;
	}

	@Override
	public String toString() {
		return "ShoppingInfo [shoppingId=" + shoppingId + ", gs=" + gs + ", GoodsId=" + GoodsId + ", goodsNum="
				+ goodsNum + ", goodsTotal=" + goodsTotal + ", addTime=" + addTime + ", userId=" + userId
				+ ", receiverState=" + receiverState + "]";
	}

	}
