package edu.fjnu.fundtradesys.dao;

import java.util.List;

import edu.fjnu.fundtradesys.bo.CusInfo;
import edu.fjnu.fundtradesys.domain.Client;

public interface ClientDao {
	
	/**
	 * 
	 * @Title: add
	 * @Description: �����ͻ���Ϣ
	 * @param @param client
	 * @return void
	 * @throws
	 */
	void add(Client client);
	/**
	 * 
	* @Title: cntCusInfos
	* @Description: �������
	* @param @param cusInfo
	* @param @return   
	* @return int    
	* @throws
	 */
	int cntCusInfos(CusInfo cusInfo);

	/**
	 * 
	* @Title: getScopedCusInfos
	* @Description: ��ҳ����
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
	* @Description: ��ҳ����
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
	 * @Description: ɾ���ͻ���Ϣ
	 * @param @param clientNo
	 * @return void
	 * @throws
	 */
	void delete(Integer clientNo);
	
	/**
	 * 
	 * @Title: getClientByNo
	 * @Description: ����ID��ÿͻ���Ϣ
	 * @param @param clientNo
	 * @param @return
	 * @return Client
	 * @throws
	 */
	Client getClientByNo(Integer clientNo);
	/**
	 * 
	 * @Title: getCusInfoByIdNo
	 * @Description: ����clientNo��ȡ�ͻ���Ϣ
	 * @param @param idcardNo
	 * @param @return
	 * @return CusInfo
	 * @throws
	 */
	CusInfo getCusInfoByIdNo(Integer clientNo);
	/**
	 * 
	 * @Title: update
	 * @Description: �޸Ŀͻ���Ϣ
	 * @param @param client
	 * @return void
	 * @throws
	 */
	void update(Client client);
	/**
	 * 
	 * @Title: getClientByIdNo
	 * @Description: �������֤�Ż�ÿͻ���Ϣ
	 * @param @param idcardNo
	 * @param @return
	 * @return Client
	 * @throws
	 */
	Client getClientByIdNo(String idcardNo);

}
