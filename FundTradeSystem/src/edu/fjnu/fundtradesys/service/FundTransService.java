package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.domain.FundTransInfo;
import edu.fjnu.fundtradesys.utils.Page;

public interface FundTransService {

	/**
	 * 
	* @Title: add
	* @Description: 增加基金交易记录
	* @param @param fundtransinfo   
	* @return void    
	* @throws
	 */
	void add(FundTransInfo fundtransinfo);
	/**
	 * 
	* @Title: loadPagedTradeRecord
	* @Description: 分页查询基金交易记录
	* @param @param page
	* @param @param fundtransinfo
	* @param @return   
	* @return Page    
	* @throws
	 */
	Page loadPagedTradeRecord(Page page,FundTransInfo fundtransinfo);

}
