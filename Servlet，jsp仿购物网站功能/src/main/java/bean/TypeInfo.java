package bean;

import java.util.List;

public class TypeInfo {
	
	private Integer typeId;
	private String typeName;
	private Integer typeLevel;
	private String typeState;
	private List<TypeInfo> list;
	public void TypeIfo() {
		
	}
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeLevel() {
		return typeLevel;
	}
	public void setTypeLevel(Integer typeLevel) {
		this.typeLevel = typeLevel;
	}
	public String getTypeState() {
		return typeState;
	}
	public void setTypeState(String string) {
		this.typeState = string;
	}
	public List<TypeInfo> getList() {
		return list;
	}
	public void setList(List<TypeInfo> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "TypeInfo [typeId=" + typeId + ", typeName=" + typeName + ", typeLevel=" + typeLevel + ", typeState="
				+ typeState + ", list=" + list + "]";
	}
	
}
