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
    
    private String photo;
    
    private String color;
    
    private List<Destail> details;
   

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Destail> getDestails() {
		return details;
	}

	public void setDestails(List<Destail> destails) {
		this.details = destails;
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
				+ ", photo=" + photo + ", color=" + color + ", details=" + details + "]";
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