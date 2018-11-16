package com.lanpangzi.mapper.business2;

import org.springframework.web.bind.annotation.RequestParam;

import com.lanpangzi.pojo.Borrow;

/**
 * 		银行卡认证    状态查询  修改等操作
 * @author 帅气的老胡
 *
 */
public interface BorrowAuthenticationMapper {
	//用户id对用表中的id是否存在过
	public Boolean IsAuthentication(@RequestParam("uid")Integer bid);
	/**
	 * 认证资料查询
	 * @param bid
	 * @return
	 */
	public Borrow findBankAuthentication(@RequestParam("uid")Integer bid);
	public Borrow findAliPayAuthentication(@RequestParam("uid")Integer bid);
	public Borrow findPersonalAuthentication(@RequestParam("uid")Integer bid);
	/**
	 * 认证状态查询
	 * @param bid
	 * @return
	 */
	public String findBankActiveState(@RequestParam("uid")Integer bid);
	public String findAliPayActiveState(@RequestParam("uid")Integer bid);
	public String findPersonalActiveState(@RequestParam("uid")Integer bid);
	/**
	 * 认证修改  
	 */
	public Boolean updateBankAuthentication(@RequestParam("borrow") Borrow borrow);
	public Boolean updateAliPayAuthentication(@RequestParam("borrow") Borrow borrow);
	public Boolean updatePersonalAuthentication(@RequestParam("borrow") Borrow borrow);
	
	/**
	 * 认证插入  
	 */
	public Boolean insertBankAuthentication(@RequestParam("borrow") Borrow borrow);
	public Boolean insertAliPayAuthentication(@RequestParam("borrow") Borrow borrow);
	public Boolean insertPersonalAuthentication(@RequestParam("borrow") Borrow borrow);
	
	
}
