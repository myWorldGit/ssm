package htshop;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lanpangzi.mapper.business.BystagesMapper;
import com.lanpangzi.mapper.business.CommodiryAdminMapper;
import com.lanpangzi.mapper.business.LimuMapper;
import com.lanpangzi.mapper.business.OrderMapper;
import com.lanpangzi.mapper.business2.BorrowAuthenticationMapper;
import com.lanpangzi.mapper.business2.ClassifyMapper2;
import com.lanpangzi.mapper.business2.Commodiry2Mapper;
import com.lanpangzi.mapper.business2.OtherInfomationMapper;
import com.lanpangzi.pojo.Bystages;
import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;
import com.lanpangzi.pojo.Limu;
import com.lanpangzi.pojo.Orders;
import com.lanpangzi.pojo.Other;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.common.CommonUtils;
import com.lanpangzi.utils.common.LAJILimuCommonUtils;
import com.lanpangzi.utils.common.LimuInfomationUtils;

public class Test02 {
	@Test 
	public void sign() {
		List<BasicNameValuePair> list =new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("version",LimuInfomationUtils.version));
		list.add(new BasicNameValuePair("apiKey", LimuInfomationUtils.api_key));
		list.add(new BasicNameValuePair("method", "api.common.getStatus"));
		list.add(new BasicNameValuePair("bizType", "education"));
		list.add(new BasicNameValuePair("token", "f582289f7e03431da7e49135ca0d0d89"));
		list.add(new BasicNameValuePair("sign", LAJILimuCommonUtils.getSign(list)));
	}
	public void testSQL() {
		//System.out.println(TokenUtil.createToken(100));
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream("mybatis-test.xml");
			//根据相关的mybatis配置文件， 创建连接SQLSessionFactory连接对象
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
			//创建出SQLSession对象
			SqlSession sqlSeesion = factory.openSession();
			LimuMapper mapper = 
						sqlSeesion.getMapper(LimuMapper.class);
			Limu l = new  Limu();
			l.setLid(3);
			l.setAlitoken("alitoken@@s222");
			l.setBanktoken("bak");
			Boolean flag =mapper.isHasLimuInfo(22);
			
			System.out.println(flag);
			sqlSeesion.commit();
		} catch (IOException e) {
			e.printStackTrace(); 
		} 
	}

}

