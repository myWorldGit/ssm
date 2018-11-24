package htshop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lanpangzi.mapper.business.BystagesMapper;
import com.lanpangzi.mapper.business.CommodiryAdminMapper;
import com.lanpangzi.mapper.business2.ClassifyMapper2;
import com.lanpangzi.mapper.business2.Commodiry2Mapper;
import com.lanpangzi.mapper.business2.OtherInfomationMapper;
import com.lanpangzi.pojo.Bystages;
import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;
import com.lanpangzi.pojo.Other;
import com.lanpangzi.utils.TokenUtil;

public class Test02 {
	@Test 
/*	public void getToken() {
		System.out.println(TokenUtil.createToken(3));
	}*/
	public void testSQL() {
		
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream("mybatis-test.xml");
			//根据相关的mybatis配置文件， 创建连接SQLSessionFactory连接对象
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
			//创建出SQLSession对象
			SqlSession sqlSeesion = factory.openSession();
			CommodiryAdminMapper mapper = 
						sqlSeesion.getMapper(CommodiryAdminMapper.class);
			Destail d= new Destail("122221",5225,5225,new Commodiry(57));
			d.setDid(78);
			Boolean flag =mapper.updateDetailById(d);
			
			System.out.println(flag);
			sqlSeesion.commit();
		} catch (IOException e) {
			e.printStackTrace(); 
		} 
	}

}

