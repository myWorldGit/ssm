package com.lanpangzi.pojo;

import java.util.Date;

public class Orders {
    private Integer oid;

    private Integer ordernumber;

    private Integer status;

    private String name;

    private String phone;

    private String address;
    /**
     * 对应的商品信息   颜色也在里面
     */
    private Commodiry commodiry;
    
    /**
     * 分期利率
     */
    private Bystages bystages;
    private Date beginbystages;
    private String express;

    private String company;

    private String refund;
    /**
     * 用户id 用户外键
     */
    private Integer uid;


    private Date ordertime;


	public Integer getOid() {
		return oid;
	}


	public void setOid(Integer oid) {
		this.oid = oid;
	}


	public Integer getOrdernumber() {
		return ordernumber;
	}


	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}


	public Date getBeginbystages() {
		return beginbystages;
	}


	public void setBeginbystages(Date beginbystages) {
		this.beginbystages = beginbystages;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Commodiry getCommodiry() {
		return commodiry;
	}


	public void setCommodiry(Commodiry commodiry) {
		this.commodiry = commodiry;
	}


	public Bystages getBystages() {
		return bystages;
	}


	public void setBystages(Bystages bystages) {
		this.bystages = bystages;
	}


	public String getExpress() {
		return express;
	}


	public void setExpress(String express) {
		this.express = express;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getRefund() {
		return refund;
	}


	public void setRefund(String refund) {
		this.refund = refund;
	}


	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public Date getOrdertime() {
		return ordertime;
	}


	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}


	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", ordernumber=" + ordernumber + ", status=" + status + ", name=" + name
				+ ", phone=" + phone + ", address=" + address + ", commodiry=" + commodiry + ", bystages=" + bystages
				+ ", beginbystages=" + beginbystages + ", express=" + express + ", company=" + company + ", refund="
				+ refund + ", uid=" + uid + ", ordertime=" + ordertime + "]";
	}

   



}