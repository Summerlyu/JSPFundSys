package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.domain.AccountTransInfo;
import edu.fjnu.fundtradesys.utils.Page;

public interface AccountTransInfoService {

	/**
	 * 
	 * @Title: loadPagedAccountTransInfos
	 * @Description: ��ҳ��ѯ�ʽ��˻�������¼
	 * @param @param page
	 * @param @param accountTransInfo
	 * @param @return
	 * @return Page
	 * @throws
	 */
	Page loadPagedAccountTransInfos(Page page, AccountTransInfo accountTransInfo);

}
