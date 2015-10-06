package edu.fjnu.fundtradesys.dao;

import java.util.List;

import edu.fjnu.fundtradesys.domain.FundTransInfo;

public interface FundTransDao {

	/**
	 * 
	 * @Title: add
	 * @Description: 插入基金交易记录
	 * @param @param fundtransinfo
	 * @return void
	 * @throws
	 */
	void add(FundTransInfo fundtransinfo);

	/**
	 * 
	* @Title: cntFundTransInfos
	* @Description: 分页所需
	* @param @param fundtransinfo
	* @param @return   
	* @return int    
	* @throws
	 */
	int cntFundTransInfos(FundTransInfo fundtransinfo);

	/**
	 * 
	* @Title: getScopedFundTransInfo
	* @Description: 分页所需
	* @param @param fundtransinfo
	* @param @param begin
	* @param @param end
	* @param @return   
	* @return List<FundTransInfo>    
	* @throws
	 */
	List<FundTransInfo> getScopedFundTransInfo(FundTransInfo fundtransinfo,
			int begin, int end);

}
