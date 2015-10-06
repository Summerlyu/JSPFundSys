package edu.fjnu.fundtradesys.service;

import java.util.List;

import edu.fjnu.fundtradesys.domain.Fund;
import edu.fjnu.fundtradesys.utils.Page;

public interface FundService {

	/**
	 * 
	 * @Title: createFund
	 * @Description: 创建基金产品
	 * @param @param fund
	 * @return void
	 * @throws
	 */
	void createFund(Fund fund);

	/**
	 * 
	 * @Title: loadPagedFunds
	 * @Description: 分页显示基金产品
	 * @param @param page
	 * @param @param fundhelper
	 * @param @return
	 * @return Page
	 * @throws
	 */
	Page loadPagedFunds(Page page, Fund fundhelper);

	/**
	 * 
	 * @Title: loadall
	 * @Description: 列出所有基金产品
	 * @param @return
	 * @return List<Fund>
	 * @throws
	 */
	List<Fund> loadall();

	/**
	 * 
	 * @Title: removeFund
	 * @Description: 删除基金产品
	 * @param @param FundNo
	 * @return void
	 * @throws
	 */
	void removeFund(Integer FundNo);
	/**
	 * 
	* @Title: getFundByNo
	* @Description: 根据基金产品编号获得基金顿
	* @param @param FundNo
	* @param @return   
	* @return Fund    
	* @throws
	 */
	Fund getFundByNo(Integer FundNo);
	/**
	 * 
	* @Title: update
	* @Description: 修改產品信息
	* @param @param fund   
	* @return void    
	* @throws
	 */
	void update(Fund fund);
	/**
	 * 
	* @Title: checkFundStatus
	* @Description: 检查基金状态
	* @param @param fund   
	* @return void    
	* @throws
	 */
	void checkFundStatus(Integer fundNo);

}
