package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.dao.AccountTransInfoDao;
import edu.fjnu.fundtradesys.dao.AccountTransInfoDaoJDBCImpl;
import edu.fjnu.fundtradesys.domain.AccountTransInfo;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.utils.Page;
import edu.fjnu.fundtradesys.utils.TransactionManager;

public class AccountTransInfoServiceImpl implements AccountTransInfoService {
	
	public Page loadPagedAccountTransInfos(Page page,AccountTransInfo accountTransInfo) {
		try{
			AccountTransInfoDao accountTransInfoDao = new AccountTransInfoDaoJDBCImpl();
			page.setTotalRecNum(accountTransInfoDao.cntAccountTransInfos(accountTransInfo));
			page.setPageContent(accountTransInfoDao.getScopedAccountTransInfo(accountTransInfo, page.getStartIndex(), page.getEndIndex()));
		}catch(Exception e){
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}

}
