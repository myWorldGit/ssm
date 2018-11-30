package com.lanpangzi.pojo;

public class Limu {
	private Integer lid;
	private String alitoken;
	private String facetoken;
	private String phonetoken;
	private String banktoken;
	private Integer ishaspay;
	private Double money;
	private Users user;
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Limu(Integer lid, Integer ishaspay) {
		super();
		this.lid = lid;
		this.ishaspay = ishaspay;
	}
	
	public Limu(Integer lid, Integer ishaspay, Double money) {
		super();
		this.lid = lid;
		this.ishaspay = ishaspay;
		this.money = money;
	}
	public Limu(Integer lid) {
		this.lid = lid;
	}
	public Limu() {
		super();
	}
	public Limu(Integer lid, String alitoken, String facetoken, String phonetoken, String banktoken, Integer ishaspay) {
		super();
		this.lid = lid;
		this.alitoken = alitoken;
		this.facetoken = facetoken;
		this.phonetoken = phonetoken;
		this.banktoken = banktoken;
		this.ishaspay = ishaspay;
	}
	@Override
	public String toString() {
		return "Limu [lid=" + lid + ", alitoken=" + alitoken + ", facetoken=" + facetoken + ", phonetoken=" + phonetoken
				+ ", banktoken=" + banktoken + ", ishaspay=" + ishaspay + "]";
	}
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public String getAlitoken() {
		return alitoken;
	}
	public void setAlitoken(String alitoken) {
		this.alitoken = alitoken;
	}
	public String getFacetoken() {
		return facetoken;
	}
	public void setFacetoken(String facetoken) {
		this.facetoken = facetoken;
	}
	public String getPhonetoken() {
		return phonetoken;
	}
	public void setPhonetoken(String phonetoken) {
		this.phonetoken = phonetoken;
	}
	public String getBanktoken() {
		return banktoken;
	}
	public void setBanktoken(String banktoken) {
		this.banktoken = banktoken;
	}
	public Integer getIshaspay() {
		return ishaspay;
	}
	public void setIshaspay(Integer ishaspay) {
		this.ishaspay = ishaspay;
	}
	
	
	
}
