package com.lanpangzi.mapper.business2;

import java.util.List;

import com.lanpangzi.pojo.Color;

public interface ColorMapper2 {
	public List<Color> findAllColors(Integer cid);
	
	public Color findColorById(Integer cid);
}
