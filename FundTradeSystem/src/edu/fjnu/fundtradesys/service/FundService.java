package edu.fjnu.fundtradesys.service;

import java.util.List;

import edu.fjnu.fundtradesys.domain.Fund;
import edu.fjnu.fundtradesys.utils.Page;

public interface FundService {

	/**
	 * 
	 * @Title: createFund
	 * @Description: ���������Ʒ
	 * @param @param fund
	 * @return void
	 * @throws
	 */
	void createFund(Fund fund);

	/**
	 * 
	 * @Title: loadPagedFunds
	 * @Description: ��ҳ��ʾ�����Ʒ
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
	 * @Description: �г����л����Ʒ
	 * @param @return
	 * @return List<Fund>
	 * @throws
	 */
	List<Fund> loadall();

	/**
	 * 
	 * @Title: removeFund
	 * @Description: ɾ�������Ʒ
	 * @param @param FundNo
	 * @return void
	 * @throws
	 */
	void removeFund(Integer FundNo);
	/**
	 * 
	* @Title: getFundByNo
	* @Description: ���ݻ����Ʒ��Ż�û����
	* @param @param FundNo
	* @param @return   
	* @return Fund    
	* @throws
	 */
	Fund getFundByNo(Integer FundNo);
	/**
	 * 
	* @Title: update
	* @Description: �޸ĮaƷ��Ϣ
	* @param @param fund   
	* @return void    
	* @throws
	 */
	void update(Fund fund);
	/**
	 * 
	* @Title: checkFundStatus
	* @Description: ������״̬
	* @param @param fund   
	* @return void    
	* @throws
	 */
	void checkFundStatus(Integer fundNo);

}
