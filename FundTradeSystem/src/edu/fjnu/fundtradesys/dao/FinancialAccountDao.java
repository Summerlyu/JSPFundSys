package edu.fjnu.fundtradesys.dao;

import java.util.List;

import edu.fjnu.fundtradesys.bo.FinancialAccountBO;
import edu.fjnu.fundtradesys.domain.FinancialAccount;

public interface FinancialAccountDao {
	/**
	 * 
	* @Title: add
	* @Description: �����ʽ��˻�
	* @param @param financialaccount   
	* @return void    
	* @throws
	 */
	void add(FinancialAccount financialaccount);
	/**
	 * 
	* @Title: cntFinancialAccounts
	* @Description: ��ҳ����
	* @param @param financialaccountBO
	* @param @return   
	* @return int    
	* @throws
	 */
	int cntFinancialAccounts(FinancialAccountBO financialaccountBO);
	/**
	 * 
	* @Title: getScopedFinancialAccounts
	* @Description: ��ҳ����
	* @param @param financialaccountBO
	* @param @param begin
	* @param @param end
	* @param @return   
	* @return List<FinancialAccountBO>    
	* @throws
	 */
	List<FinancialAccountBO> getScopedFinancialAccounts(FinancialAccountBO financialaccountBO,
			int begin, int end);
	/**
	 * 
	* @Title: getFinancialAccountByNo
	* @Description: �����˺Ż���ʽ��˻�
	* @param @param financialaccNo
	* @param @return   
	* @return FinancialAccount    
	* @throws
	 */
	FinancialAccount getFinancialAccountByNo(Integer financialaccNo);
	/**
	 * 
	* @Title: update
	* @Description: �޸��ʽ��˻���Ϣ
	* @param @param fundacc   
	* @return void    
	* @throws
	 */
	void update(FinancialAccount financialacc);

	/**
	 * 
	* @Title: getFinancialAccountByClientNo
	* @Description: ���ݿͻ�ID����˻���Ϣ
	* @param @param clientNo
	* @param @return   
	* @return FinancialAccount    
	* @throws
	 */
	FinancialAccount getFinancialAccountByClientNo(Integer clientNo);
	
	/**
	 * 
	* @Title: getFinancialAccountBOByNo
	* @Description: �����˺Ż��ҵ�����
	* @param @param accountNo
	* @param @return   
	* @return FinancialAccountBO    
	* @throws
	 */
	FinancialAccountBO getFinancialAccountBOByNo(Integer accountNo);
	/**
	 * 
	* @Title: updateBO
	* @Description: �޸�ҵ�������Ϣ
	* @param @param financialaccountBo   
	* @return void    
	* @throws
	 */
	void updateBO(FinancialAccountBO financialaccountBo);

}
