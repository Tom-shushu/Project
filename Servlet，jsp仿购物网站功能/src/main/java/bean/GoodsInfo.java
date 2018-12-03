package bean;

public class GoodsInfo {

	private  Integer	goodsId; 
	private String  	goodsImage;
	private String	goodsName;
	private Double	goodsPrice ;
	private String goodsIntro ;
	private  Integer	goodsStock ;
	private  Integer	typerId ;
	private String	goodsState ;	
	
	
	
	public GoodsInfo() {
		// TODO Auto-generated constructor stub
	}



	public Integer getGoodsId() {
		return goodsId;
	}



	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}



	public String getGoodsImage() {
		return goodsImage;
	}



	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}



	public String getGoodsName() {
		return goodsName;
	}



	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}



	public Double getGoodsPrice() {
		return goodsPrice;
	}



	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}



	public String getGoodsIntro() {
		return goodsIntro;
	}



	public void setGoodsIntro(String goodsIntro) {
		this.goodsIntro = goodsIntro;
	}



	public Integer getGoodsStock() {
		return goodsStock;
	}



	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}



	public Integer getTyperId() {
		return typerId;
	}



	public void setTyperId(Integer typerId) {
		this.typerId = typerId;
	}



	public String getGoodsState() {
		return goodsState;
	}



	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}



	@Override
	public String toString() {
		return "GoodsInfo [goodsId=" + goodsId + ", goodsImage=" + goodsImage + ", goodsName=" + goodsName
				+ ", goodsPrice=" + goodsPrice + ", goodsIntro=" + goodsIntro + ", goodsStock=" + goodsStock
				+ ", typerId=" + typerId + ", goodsState=" + goodsState + "]";
	}
	
	
		
}
