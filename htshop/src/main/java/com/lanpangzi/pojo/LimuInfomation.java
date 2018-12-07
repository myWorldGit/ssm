package com.lanpangzi.pojo;
/**
 * 立木信息保存在这里面   
 * @author 帅气的老胡
 *
 */
public class LimuInfomation {
	private Integer mid;
	private String taobao;
	private String mobile;
	private String credit;
	public LimuInfomation() {
		super();
	}
	public LimuInfomation(Integer mid, String taobao, String mobile, String credit) {
		super();
		this.mid = mid;
		this.taobao = taobao;
		this.mobile = mobile;
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "LimuInfomation [mid=" + mid + ", taobao=" + taobao + ", mobile=" + mobile + ", credit=" + credit + "]";
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getTaobao() {
		return taobao;
	}
	public void setTaobao(String taobao) {
		this.taobao = taobao;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	
}
