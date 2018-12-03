package bean;

public class UserAddressInfo {
private Integer addressId;
private String 	recevierName;
private String	recevierPhone;
private String 	recevieAddress;
private Integer userId;
private String	recevierState;



public UserAddressInfo() {
	// TODO Auto-generated constructor stub
}

public Integer getAddressId() {
	return addressId;
}
public void setAddressId(Integer addressId) {
	this.addressId = addressId;
}
public String getRecevierName() {
	return recevierName;
}
public void setRecevierName(String recevierName) {
	this.recevierName = recevierName;
}
public String getRecevierPhone() {
	return recevierPhone;
}
public void setRecevierPhone(String recevierPhone) {
	this.recevierPhone = recevierPhone;
}
public String getRecevieAddress() {
	return recevieAddress;
}
public void setRecevieAddress(String recevieAddress) {
	this.recevieAddress = recevieAddress;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public String getRecevierState() {
	return recevierState;
}
public void setRecevierState(String recevierState) {
	this.recevierState = recevierState;
}


@Override
public String toString() {
	return "UserAddressInfo [addressId=" + addressId + ", recevierName=" + recevierName + ", recevierPhone="
			+ recevierPhone + ", recevieAddress=" + recevieAddress + ", userId=" + userId + ", recevierState="
			+ recevierState + "]";
}
	
	
}
