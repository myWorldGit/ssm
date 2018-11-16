package com.lanpangzi.pojo;

public class Address {
	private Integer aid;
	private String receName;
	private String phone;
	private String detailAddr;
	private String isDefault;
	private Integer uid;
	
	
	public Address() {
	}
	public Address(String receName, String phone, String detailAddr, String isDefault, Integer uid) {
		this.receName = receName;
		this.phone = phone;
		this.detailAddr = detailAddr;
		this.isDefault = isDefault;
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Address [aid=" + aid + ", receName=" + receName + ", phone=" + phone + ", detailAddr=" + detailAddr
				+ ", isDefault=" + isDefault + ", uid=" + uid + "]";
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getReceName() {
		return receName;
	}
	public void setReceName(String receName) {
		this.receName = receName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDetailAddr() {
		return detailAddr;
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
}
