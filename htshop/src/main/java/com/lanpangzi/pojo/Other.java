package com.lanpangzi.pojo;

import java.util.Date;

public class Other {
    private Integer otherid;

    private String value;

    private String types;

    private Date lasttime;

    public Integer getOtherid() {
        return otherid;
    }

    public void setOtherid(Integer otherid) {
        this.otherid = otherid;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
		this.value = value;
	}

	public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

	@Override
	public String toString() {
		return "Other [otherid=" + otherid + ", value=" + value + ", types=" + types + ", lasttime=" + lasttime + "]";
	}
    
}