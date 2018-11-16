package com.lanpangzi.pojo;

public class Bystages {
	/**
	 * 主键id
	 */
	private Integer sid;
	/**
	 * 分期次数
	 */
	private Integer countmonth;
	/**
	 * 分期利率
	 */
	private Integer rate;
	
	
	
	@Override
	public String toString() {
		return "Bystages [sid=" + sid + ", countmonth=" + countmonth + ", rate=" + rate + "]";
	}
	public Bystages(Integer countmonth, Integer rate) {
		super();
		this.countmonth = countmonth;
		this.rate = rate;
	}
	public Bystages() {
		super();
	}
	public Bystages(Integer sid, Integer countmonth, Integer rate) {
		super();
		this.sid = sid;
		this.countmonth = countmonth;
		this.rate = rate;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
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
	
	
	
}
