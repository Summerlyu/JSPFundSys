package edu.fjnu.fundtradesys.dao;

import java.util.List;

import edu.fjnu.fundtradesys.bo.CusInfo;
import edu.fjnu.fundtradesys.domain.Client;

public interface ClientDao {
	
	/**
	 * 
	 * @Title: add
	 * @Description: 新增客户信息
	 * @param @param client
	 * @return void
	 * @throws
	 */
	void add(Client client);
	/**
	 * 
	* @Title: cntCusInfos
	* @Description: 分頁所需
	* @param @param cusInfo
	* @param @return   
	* @return int    
	* @throws
	 */
	int cntCusInfos(CusInfo cusInfo);

	/**
	 * 
	* @Title: getScopedCusInfos
	* @Description: 分页所需
	* @param @param cusInfo
	* @param @param begin
	* @param @param end
	* @param @return   
	* @return List<CusInfo>    
	* @throws
	 */
	List<CusInfo> getScopedCusInfos(CusInfo cusInfo, int begin, int end);

	/**
	 * 
	* @Title: getScopedCusInfosDetail
	* @Description: 分页所需
	* @param @param cusInfo
	* @param @param begin
	* @param @param end
	* @param @return   
	* @return List<CusInfo>    
	* @throws
	 */
	List<CusInfo> getScopedCusInfosDetail(CusInfo cusInfo, int begin, int end);
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除客户信息
	 * @param @param clientNo
	 * @return void
	 * @throws
	 */
	void delete(Integer clientNo);
	
	/**
	 * 
	 * @Title: getClientByNo
	 * @Description: 根据ID获得客户信息
	 * @param @param clientNo
	 * @param @return
	 * @return Client
	 * @throws
	 */
	Client getClientByNo(Integer clientNo);
	/**
	 * 
	 * @Title: getCusInfoByIdNo
	 * @Description: 根据clientNo获取客户信息
	 * @param @param idcardNo
	 * @param @return
	 * @return CusInfo
	 * @throws
	 */
	CusInfo getCusInfoByIdNo(Integer clientNo);
	/**
	 * 
	 * @Title: update
	 * @Description: 修改客户信息
	 * @param @param client
	 * @return void
	 * @throws
	 */
	void update(Client client);
	/**
	 * 
	 * @Title: getClientByIdNo
	 * @Description: 根据身份证号获得客户信息
	 * @param @param idcardNo
	 * @param @return
	 * @return Client
	 * @throws
	 */
	Client getClientByIdNo(String idcardNo);

}
