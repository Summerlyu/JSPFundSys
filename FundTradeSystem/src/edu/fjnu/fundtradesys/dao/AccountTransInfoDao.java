package edu.fjnu.fundtradesys.dao;


import java.util.List;

import edu.fjnu.fundtradesys.domain.AccountTransInfo;

public interface AccountTransInfoDao {
	/**
	 * 
	 * @Title: add
	 * @Description: ���ӽ��׼�¼
	 * @param @param fund
	 * @return void
	 * @throws
	 */
	void add(AccountTransInfo fund);

	/**
	 * 
	* @Title: cntAccountTransInfos
	* @Description: ��ҳ����
	* @param @param accountTransInfo
	* @param @return   
	* @return Integer    
	* @throws
	 */
	Integer cntAccountTransInfos(AccountTransInfo accountTransInfo);
	/**
	 * 
	* @Title: getScopedAccountTransInfo
	* @Description: ��ҳ����
	* @param @param accountTransInfo
	* @param @param startIndex
	* @param @param endIndex
	* @param @return   
	* @return List<AccountTransInfo>    
	* @throws
	 */
	List<AccountTransInfo> getScopedAccountTransInfo(
			AccountTransInfo accountTransInfo, int begin, int end);

}
