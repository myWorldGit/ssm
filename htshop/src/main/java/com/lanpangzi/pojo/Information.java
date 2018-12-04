package com.lanpangzi.pojo;

import java.util.Date;

public class Information {
    private Integer iid;

    private String header;

    private String briefly;

    private String content;

    private String address;

    private String category;

    private String way;

    private Integer promulgator;

    private Integer receiver;

    private Integer state;

    private Date createtime;
    
    

    public Information(Integer receiver) {
		super();
		this.receiver = receiver;
	}

	public Information(Integer receiver, Date createtime) {
		super();
		this.receiver = receiver;
		this.createtime = createtime;
	}

	public Information() {
		super();
	}

	public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public String getBriefly() {
        return briefly;
    }

    public void setBriefly(String briefly) {
        this.briefly = briefly == null ? null : briefly.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public Integer getPromulgator() {
        return promulgator;
    }

    public void setPromulgator(Integer promulgator) {
        this.promulgator = promulgator;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getState() {
        return state;
    }


    public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Information [iid=" + iid + ", header=" + header + ", briefly=" + briefly + ", content=" + content
				+ ", address=" + address + ", category=" + category + ", way=" + way + ", promulgator=" + promulgator
				+ ", receiver=" + receiver + ", state=" + state + ", createtime=" + createtime + "]";
	}

	public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}