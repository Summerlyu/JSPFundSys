package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.bo.CusInfo;
import edu.fjnu.fundtradesys.domain.Client;
import edu.fjnu.fundtradesys.utils.Page;

public interface ClientService {
	
	/**
	 * 
	* @Title: createClient
	* @Description: 添加新客户
	* @param @param client   
	* @return void    
	* @throws
	 */
	void createClient(Client client);
	/**
	 * 
	* @Title: loadPagedClients
	* @Description: 分页查询客户信息
	* @param @param page
	* @param @param cusInfo
	* @param @return   
	* @return Page    
	* @throws
	 */
	Page loadPagedClients(Page page,CusInfo cusInfo);
	/**
	 * 
	* @Title: loadPagedClientsDetail
	* @Description: 分页查询客户详细信息
	* @param @param page
	* @param @param cusInfo
	* @param @return   
	* @return Page    
	* @throws
	 */
	Page loadPagedClientsDetail(Page page,CusInfo cusInfo);
	/**
	 * 
	* @Title: removeClient
	* @Description: 删除客户信息
	* @param @param clientNo   
	* @return void    
	* @throws
	 */
	void removeClient(Integer clientNo);
	/**
	 * 
	* @Title: getCusInfoByNo
	* @Description: 根据ClientNo获得业务对象
	* @param @param clientNo
	* @param @return   
	* @return CusInfo    
	* @throws
	 */
	CusInfo getCusInfoByNo(Integer clientNo);
	/**
	 * 
	* @Title: getClientByNo
	* @Description: 根据ClientNo获得客户
	* @param @param clientNo
	* @param @return   
	* @return Client    
	* @throws
	 */
	Client getClientByNo(Integer clientNo);
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
	* @Description: 根据身份证号获取客户信息
	* @param @param idcardNo
	* @param @return   
	* @return Client    
	* @throws
	 */
	Client getClientByIdNo(String idcardNo);

}
