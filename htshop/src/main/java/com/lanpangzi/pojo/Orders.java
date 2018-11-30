package com.lanpangzi.pojo;

import java.util.Date;

public class Orders {
	//订单id
    private Integer oid;
    //订单号
    private String ordernumber;
    //订单时间
    private Date ordertime;
    //订单状态
    private Integer cstate;   
    //用户id
    private Integer uid;    
    
    //商品id
    private Integer cid;   
    //商品名字
    private String cname;
    //商品型号
    private String ctype;   
    //商品图片
    private String photo;
    //商品价格
    private String price;    
    //商品颜色
    private String color;
    
    //分期月份
    private Integer countmonth;
    //分期率利
    private Integer rate;
    //分期开始时间
    private Date beginbystages;
    
    //收货人姓名
    private String rname;
    //收获人手机
    private String phone;    
    //收获人地址
    private String address;
    
    //快递编号
    private String express;
    //快递公司
    private String company;    
    //优惠卷id
    private Integer vid;
    
    
    
    
	public Integer getOid() {
		return oid;
	}




	public void setOid(Integer oid) {
		this.oid = oid;
	}




	public String getOrdernumber() {
		return ordernumber;
	}




	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}




	public Date getOrdertime() {
		return ordertime;
	}




	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}




	public Integer getCstate() {
		return cstate;
	}




	public void setCstate(Integer cstate) {
		this.cstate = cstate;
	}




	public Integer getUid() {
		return uid;
	}




	public void setUid(Integer uid) {
		this.uid = uid;
	}




	public Integer getCid() {
		return cid;
	}




	public void setCid(Integer cid) {
		this.cid = cid;
	}




	public String getCname() {
		return cname;
	}




	public void setCname(String cname) {
		this.cname = cname;
	}




	public String getCtype() {
		return ctype;
	}




	public void setCtype(String ctype) {
		this.ctype = ctype;
	}




	public String getPhoto() {
		return photo;
	}




	public void setPhoto(String photo) {
		this.photo = photo;
	}




	public String getPrice() {
		return price;
	}




	public void setPrice(String price) {
		this.price = price;
	}




	public String getColor() {
		return color;
	}




	public void setColor(String color) {
		this.color = color;
	}




	public Integer getCountmonth() {
		return countmonth;
	}




	public void setCountmonth(Integer countmonth) {
		this.countmonth = countmonth;
	}




	public Integer getRate() {
		return rate;
	}




	public void setRate(Integer rate) {
		this.rate = rate;
	}




	public Date getBeginbystages() {
		return beginbystages;
	}




	public void setBeginbystages(Date beginbystages) {
		this.beginbystages = beginbystages;
	}




	public String getRname() {
		return rname;
	}




	public void setRname(String rname) {
		this.rname = rname;
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




	public Integer getVid() {
		return vid;
	}




	public void setVid(Integer vid) {
		this.vid = vid;
	}




	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", ordernumber=" + ordernumber + ", ordertime=" + ordertime + ", cstate=" + cstate
				+ ", uid=" + uid + ", cid=" + cid + ", cname=" + cname + ", ctype=" + ctype + ", photo=" + photo
				+ ", price=" + price + ", color=" + color + ", countmonth=" + countmonth + ", rate=" + rate
				+ ", beginbystages=" + beginbystages + ", rname=" + rname + ", phone=" + phone + ", address=" + address
				+ ", express=" + express + ", company=" + company + ", vid=" + vid + "]";
	}


}