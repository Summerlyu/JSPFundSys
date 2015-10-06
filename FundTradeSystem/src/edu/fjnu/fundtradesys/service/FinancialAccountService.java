package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.bo.FinancialAccountBO;
import edu.fjnu.fundtradesys.domain.AccountTransInfo;
import edu.fjnu.fundtradesys.domain.FinancialAccount;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.utils.Page;

public interface FinancialAccountService {
	/**
	 * 
	 * @Title: createFinancialAccount
	 * @Description: 开立资金账户
	 * @param @param financialaccount
	 * @return void
	 * @throws
	 */
	void createFinancialAccount(FinancialAccount financialaccount,
			Operator operator);

	/**
	 * 
	 * @Title: loadPagedFinancialAccounts
	 * @Description: 分页查询资金账户信息
	 * @param @param page
	 * @param @param financialaccountBO
	 * @param @return
	 * @return Page
	 * @throws
	 */
	Page loadPagedFinancialAccounts(Page page,
			FinancialAccountBO financialaccountBO);

	/**
	 * 
	 * @Title: addFund
	 * @Description: 追加账户资金
	 * @param @param f
	 * @param @param money
	 * @param @param o
	 * @return void
	 * @throws
	 */
	void addFund(FinancialAccount f, float money, Operator o);

	/**
	 * 
	 * @Title: getFund
	 * @Description: 取出账户资金
	 * @param @param f
	 * @param @param money
	 * @param @param o
	 * @return void
	 * @throws
	 */
	void getFund(FinancialAccount f, float money, Operator o);

	/**
	 * 
	 * @Title: getAccountByNo
	 * @Description: 获取业务对象
	 * @param @param accountNo
	 * @param @return
	 * @return FundAccountBO
	 * @throws
	 */
	FinancialAccountBO getAccountByNo(Integer accountNo);

	/**
	 * 
	 * @Title: updateFinancialAccountBO
	 * @Description: 修改业务对象信息
	 * @param @param financialaccountBo
	 * @return void
	 * @throws
	 */
	void updateFinancialAccountBO(FinancialAccountBO financialaccountBo);

	/**
	 * 
	 * @Title: getFinancialAccountByNo
	 * @Description: 根据账号获得资金账户
	 * @param @param accountNo
	 * @param @return
	 * @return FinancialAccount
	 * @throws
	 */
	FinancialAccount getFinancialAccountByNo(Integer accountNo);

	/**
	 * 
	 * @Title: update
	 * @Description: 修改资金账户信息
	 * @param @param financialaccount
	 * @return void
	 * @throws
	 */
	void update(FinancialAccount financialaccount);

	/**
	 * 
	* @Title: checkAccountStatus
	* @Description: 检查账户状态
	* @param @param financialaccount   
	* @return void    
	* @throws
	 */
	void checkAccountStatus(FinancialAccount financialaccount);
	/**
	 * 
	* @Title: checkAccount
	* @Description: 检测账户账号密码
	* @param @param financialaccount
	* @param @return   
	* @return void    
	* @throws
	 */
	void checkAccount(FinancialAccount financialaccount);
	

}
