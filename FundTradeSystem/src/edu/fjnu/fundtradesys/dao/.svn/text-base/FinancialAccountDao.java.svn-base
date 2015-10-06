package edu.fjnu.fundtradesys.dao;

import java.util.List;

import edu.fjnu.fundtradesys.bo.FinancialAccountBO;
import edu.fjnu.fundtradesys.domain.FinancialAccount;

public interface FinancialAccountDao {
	/**
	 * 
	* @Title: add
	* @Description: 新增资金账户
	* @param @param financialaccount   
	* @return void    
	* @throws
	 */
	void add(FinancialAccount financialaccount);
	/**
	 * 
	* @Title: cntFinancialAccounts
	* @Description: 分页所需
	* @param @param financialaccountBO
	* @param @return   
	* @return int    
	* @throws
	 */
	int cntFinancialAccounts(FinancialAccountBO financialaccountBO);
	/**
	 * 
	* @Title: getScopedFinancialAccounts
	* @Description: 分页所需
	* @param @param financialaccountBO
	* @param @param begin
	* @param @param end
	* @param @return   
	* @return List<FinancialAccountBO>    
	* @throws
	 */
	List<FinancialAccountBO> getScopedFinancialAccounts(FinancialAccountBO financialaccountBO,
			int begin, int end);
	/**
	 * 
	* @Title: getFinancialAccountByNo
	* @Description: 根据账号获得资金账户
	* @param @param financialaccNo
	* @param @return   
	* @return FinancialAccount    
	* @throws
	 */
	FinancialAccount getFinancialAccountByNo(Integer financialaccNo);
	/**
	 * 
	* @Title: update
	* @Description: 修改资金账户信息
	* @param @param fundacc   
	* @return void    
	* @throws
	 */
	void update(FinancialAccount financialacc);

	/**
	 * 
	* @Title: getFinancialAccountByClientNo
	* @Description: 根据客户ID获得账户信息
	* @param @param clientNo
	* @param @return   
	* @return FinancialAccount    
	* @throws
	 */
	FinancialAccount getFinancialAccountByClientNo(Integer clientNo);
	
	/**
	 * 
	* @Title: getFinancialAccountBOByNo
	* @Description: 根据账号获得业务对象
	* @param @param accountNo
	* @param @return   
	* @return FinancialAccountBO    
	* @throws
	 */
	FinancialAccountBO getFinancialAccountBOByNo(Integer accountNo);
	/**
	 * 
	* @Title: updateBO
	* @Description: 修改业务对象信息
	* @param @param financialaccountBo   
	* @return void    
	* @throws
	 */
	void updateBO(FinancialAccountBO financialaccountBo);

}
