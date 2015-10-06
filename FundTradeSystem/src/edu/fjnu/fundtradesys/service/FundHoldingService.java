package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.domain.FundHolding;
import edu.fjnu.fundtradesys.utils.Page;

public interface FundHoldingService {

	/**
	 * 
	* @Title: add
	* @Description: 添加持仓记录
	* @param @param fundholding   
	* @return void    
	* @throws
	 */
	void add(FundHolding fundholding);

	/**
	 * 分页查询持仓信息
	* @Title: loadPagedFundHolding
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param page
	* @param @param fundholding
	* @param @return   
	* @return Page    
	* @throws
	 */
	Page loadPagedFundHolding(Page page, FundHolding fundholding);

	/**
	 * 
	* @Title: getFundHoldingByNo
	* @Description: 根据持仓流水号获得基金持有信息
	* @param @param hid
	* @param @return   
	* @return FundHolding    
	* @throws
	 */
	FundHolding getFundHoldingByNo(int hid);

	void updateFundHolding(FundHolding fundHoldingPO);

}
