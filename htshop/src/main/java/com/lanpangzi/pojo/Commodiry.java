package com.lanpangzi.pojo;

import java.util.List;

public class Commodiry {
    private Integer cid;

    private String cname;

    private String description;

    private Integer ctype;
    
    private Integer counts;

    private Integer ishot;

    private Integer isrecommand;

    private Double price;
    //tid 对应一个类别的类  一对多
    //对应一个颜色的表List
    private List<Color> colors;

    public List<Color> getColors() {
		return colors;
	}
	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
	public String toString() {
		return "Commodiry [cid=" + cid + ", cname=" + cname + ", description=" + description + ", ctype=" + ctype
				+ ", counts=" + counts + ", ishot=" + ishot + ", isrecommand=" + isrecommand + ", price=" + price
				+ ", colors=" + colors + "]";
	}
	public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

  

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    

    public Integer getCtype() {
		return ctype;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    public Integer getIsrecommand() {
        return isrecommand;
    }

    public void setIsrecommand(Integer isrecommand) {
        this.isrecommand = isrecommand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

	public Commodiry() {
		super();
	}
    
}