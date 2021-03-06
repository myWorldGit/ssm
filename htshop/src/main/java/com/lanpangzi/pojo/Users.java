package com.lanpangzi.pojo;

import java.util.Date;


public class Users {
    private Integer uid;

    private String username;

    @Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", phone=" + phone + ", loginpassword=" + loginpassword
				+ ", palypassword=" + palypassword + ", qqToken=" + qqToken + ", wxToken=" + wxToken + ", photo="
				+ photo + ", inviter=" + inviter + ", state=" + state + ", createtime=" + createtime + "]";
	}

	private String phone;

    private String loginpassword;

    private String palypassword;
    
    private String qqToken;
    
    private String wxToken;

    private String photo;

    private String inviter;

    private String state;
   // @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

	public Integer getUid() {
		return uid;
	}

	public Users(Integer uid, String photo) {
		super();
		this.uid = uid;
		this.photo = photo;
	}

	public Users(Integer uid, String username, String loginpassword) {
		super();
		this.uid = uid;
		this.username = username;
		this.loginpassword = loginpassword;
	}

	public Users() {
		super();
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLoginpassword() {
		return loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public String getPalypassword() {
		return palypassword;
	}

	public void setPalypassword(String palypassword) {
		this.palypassword = palypassword;
	}

	public String getQqToken() {
		return qqToken;
	}

	public void setQqToken(String qqToken) {
		this.qqToken = qqToken;
	}

	public String getWxToken() {
		return wxToken;
	}

	public void setWxToken(String wxToken) {
		this.wxToken = wxToken;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}    
}