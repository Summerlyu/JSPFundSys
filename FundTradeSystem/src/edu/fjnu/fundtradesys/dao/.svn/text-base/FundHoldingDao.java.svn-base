package edu.fjnu.fundtradesys.dao;

import java.util.Collection;
import java.util.List;

import edu.fjnu.fundtradesys.domain.FundHolding;

public interface FundHoldingDao {

	/**
	 * 
	* @Title: add
	* @Description: 插入持仓记录
	* @param @param fundholding   
	* @return void    
	* @throws
	 */
	void add(FundHolding fundholding);

	/**
	 * 
	* @Title: cntFundHolding
	* @Description: 分页所需
	* @param @param fundholding
	* @param @return   
	* @return int    
	* @throws
	 */
	int cntFundHolding(FundHolding fundholding);

	/**
	 * 
	* @Title: getScopedFundHolding
	* @Description: 分页所需
	* @param @param fundholding
	* @param @param begin
	* @param @param end
	* @param @return   
	* @return List<FundHolding>    
	* @throws
	 */
	List<FundHolding> getScopedFundHolding(FundHolding fundholding, int begin,
			int end);

	/**
	 * 
	* @Title: getFundHoldingByNo
	* @Description: 根据持仓流水号获得持仓信息
	* @param @param hid
	* @param @return   
	* @return FundHolding    
	* @throws
	 */
	FundHolding getFundHoldingByNo(int hid);

	/**
	 * 
	* @Title: updateFundHolding
	* @Description: 根据hid更新持仓记录
	* @param @param fundHolding   
	* @return void    
	* @throws
	 */
	void updateFundHolding(FundHolding fundHolding);
	/**
	 * 
	* @Title: updateFundHolding2
	* @Description: 根据fundno和accno更新持仓记录
	* @param @param fundHolding   
	* @return void    
	* @throws
	 */
	void updateFundHolding2(FundHolding fundHolding);

}
