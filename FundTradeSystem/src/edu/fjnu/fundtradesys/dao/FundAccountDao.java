package edu.fjnu.fundtradesys.dao;

import java.util.List;

import edu.fjnu.fundtradesys.bo.FundAccountInfoBO;
import edu.fjnu.fundtradesys.bo.FundRedemptionInfoBO;

public interface FundAccountDao {

	/**
	 * 
	 * @Title: cntFundAccounts
	 * @Description: ��ҳ����-�����˻���ѯ
	 * @param @param fundaccountinfoBO
	 * @param @return
	 * @return Integer
	 * @throws
	 */
	Integer cntFundAccounts(FundAccountInfoBO fundaccountinfoBO);

	/**
	 * 
	 * @Title: getScopedFundAccounts
	 * @Description: ��ҳ����-�����˻���ѯ
	 * @param @param fundaccountinfoBO
	 * @param @param beign
	 * @param @param end
	 * @param @return
	 * @return List<FundAccountInfoBO>
	 * @throws
	 */
	List<FundAccountInfoBO> getScopedFundAccounts(
			FundAccountInfoBO fundaccountinfoBO, int begin, int end);

	/**
	 * 
	* @Title: cntFundRedemptions
	* @Description: ��ҳ����-��ػ����Ʒ�б�
	* @param @param fundredemptionBO
	* @param @return   
	* @return Integer    
	* @throws
	 */
	Integer cntFundRedemptions(FundRedemptionInfoBO fundredemptionBO);

	/**
	 * 
	* @Title: getScopedFundRedemptions
	* @Description: ��ҳ����-��ػ����Ʒ�б�
	* @param @param fundredemptionBO
	* @param @param startIndex
	* @param @param endIndex
	* @param @return   
	* @return List<FundRedemptionInfoBO>    
	* @throws
	 */
	List<FundRedemptionInfoBO> getScopedFundRedemptions(
			FundRedemptionInfoBO fundredemptionBO, int begin, int end);

}
