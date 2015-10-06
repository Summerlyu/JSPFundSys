package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.domain.AccountTransInfo;
import edu.fjnu.fundtradesys.utils.Page;

public interface AccountTransInfoService {

	/**
	 * 
	 * @Title: loadPagedAccountTransInfos
	 * @Description: 分页查询资金账户进出记录
	 * @param @param page
	 * @param @param accountTransInfo
	 * @param @return
	 * @return Page
	 * @throws
	 */
	Page loadPagedAccountTransInfos(Page page, AccountTransInfo accountTransInfo);

}
