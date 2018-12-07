package htshop;

import org.junit.Test;

import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.WX.APPParamsUtils;
import com.lanpangzi.utils.limu.LAJILimuCommonUtils;

public class Test022 {
	@Test  
	public void test() {
		System.out.println(
				LAJILimuCommonUtils.getSign(
				LAJILimuCommonUtils.getPararms("c1fdcaf6f55f4e5da990cce6a084275f","taobao")));
	}
}
