package com.lanpangzi.pojo;

public class Destail {
	private Integer did;
	private String image;
	private Integer width;
	private Integer height;
	
	
	
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Destail [did=" + did + ", image=" + image + ", width=" + width + ", height=" + height + "]";
	}
}
