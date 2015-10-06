package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.bo.FundAccountInfoBO;
import edu.fjnu.fundtradesys.bo.FundRedemptionInfoBO;
import edu.fjnu.fundtradesys.domain.FinancialAccount;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.utils.Page;

public interface FundAccountService {

	/**
	 * 
	 * @Title: buyFund
	 * @Description: 买基金
	 * @param @param f
	 * @param @param money
	 * @param @param o
	 * @return void
	 * @throws
	 */
	void buyFund(FinancialAccount f, float money, Operator o);

	/**
	 * 
	* @Title: saleFund
	* @Description: 卖基金
	* @param @param f
	* @param @param money
	* @param @param o   
	* @return void    
	* @throws
	 */
	void saleFund(FinancialAccount f, float money, Operator o);
	/**
	 * 
	 * @Title: loadPagedFundAccountInfo
	 * @Description: 分页查询基金账户信息
	 * @param @param page
	 * @param @param fundaccountinfoBO
	 * @param @return
	 * @return Page
	 * @throws
	 */
	Page loadPagedFundAccountInfo(Page page, FundAccountInfoBO fundaccountinfoBO);

	/**
	 * 
	* @Title: loadPagedfundRedemptionInfoBO
	* @Description: 分页显示赎回基金页面
	* @param @param page
	* @param @param fundredemptionBO
	* @param @return   
	* @return Page    
	* @throws
	 */
	Page loadPagedfundRedemptionInfoBO(Page page,
			FundRedemptionInfoBO fundredemptionBO);

}
