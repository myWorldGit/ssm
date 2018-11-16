package com.lanpangzi.pojo;

public class Color {
	private Integer colorid;
	private String image;
	private String color;
	public Integer getColorid() {
		return colorid;
	}
	public void setColorid(Integer colorid) {
		this.colorid = colorid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Color [colorid=" + colorid + ", image=" + image + ", color=" + color + "]";
	}

}
