package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.domain.FundHolding;
import edu.fjnu.fundtradesys.utils.Page;

public interface FundHoldingService {

	/**
	 * 
	* @Title: add
	* @Description: ��ӳֲּ�¼
	* @param @param fundholding   
	* @return void    
	* @throws
	 */
	void add(FundHolding fundholding);

	/**
	 * ��ҳ��ѯ�ֲ���Ϣ
	* @Title: loadPagedFundHolding
	* @Description: TODO(������һ�仰�����������������)
	* @param @param page
	* @param @param fundholding
	* @param @return   
	* @return Page    
	* @throws
	 */
	Page loadPagedFundHolding(Page page, FundHolding fundholding);

	/**
	 * 
	* @Title: getFundHoldingByNo
	* @Description: ���ݳֲ���ˮ�Ż�û��������Ϣ
	* @param @param hid
	* @param @return   
	* @return FundHolding    
	* @throws
	 */
	FundHolding getFundHoldingByNo(int hid);

	void updateFundHolding(FundHolding fundHoldingPO);

}
