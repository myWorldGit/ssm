package com.lanpangzi.pojo;

import java.util.List;

public class Classify {
    private Integer tid;
    private String tname;
    private List<Commodiry> commodirys;
    public Integer getTid() {
        return tid;
    }
    
    @Override
	public String toString() {
		return "Classify [tid=" + tid + ", tname=" + tname + ", commodirys=" + commodirys + "]";
	}

	public List<Commodiry> getCommodirys() {
		return commodirys;
	}

	public void setCommodirys(List<Commodiry> commodirys) {
		this.commodirys = commodirys;
	}

	public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }
}