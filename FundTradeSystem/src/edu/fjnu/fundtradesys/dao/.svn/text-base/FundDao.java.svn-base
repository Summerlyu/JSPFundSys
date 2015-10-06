package edu.fjnu.fundtradesys.dao;

import java.util.List;

import edu.fjnu.fundtradesys.domain.Fund;

public interface FundDao {

	/**
	 * 
	 * @Title: add
	 * @Description: 新增基金产品信息
	 * @param @param fund
	 * @return void
	 * @throws
	 */
	void add(Fund fund);

	/**
	 * 
	 * @Title: cntFunds
	 * @Description: 分页所需方法
	 * @param @param fundhelper
	 * @param @return
	 * @return int
	 * @throws
	 */
	int cntFunds(Fund fundhelper);

	/**
	 * 
	 * @Title: getScopedFunds
	 * @Description: 分页所需方法
	 * @param @param fundhelper
	 * @param @param begin
	 * @param @param end
	 * @param @return
	 * @return List<Fund>
	 * @throws
	 */
	List<Fund> getScopedFunds(Fund fundhelper, int begin, int end);

	/**
	 * 
	 * @Title: loadall
	 * @Description: 获得所有基金产品信息
	 * @param @return
	 * @return List<Fund>
	 * @throws
	 */
	List<Fund> loadall();

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除基金产品信息
	 * @param @param fundNo
	 * @return void
	 * @throws
	 */
	void delete(Integer fundNo);
	
	/**
	 * 
	 * @Title: getFundByNo
	 * @Description: 根据ID获得基金产品信息
	 * @param @param fundNo
	 * @param @return
	 * @return Fund
	 * @throws
	 */
	Fund getFundByNo(Integer fundNo);
	
	/**
	 * 
	 * @Title: update
	 * @Description: 修改基金产品信息
	 * @param @param fund
	 * @return void
	 * @throws
	 */
	void update(Fund fund);

}
