package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.dao.FundTransDao;
import edu.fjnu.fundtradesys.dao.FundTransDaoJDBCImpl;
import edu.fjnu.fundtradesys.domain.FundTransInfo;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.utils.Page;
import edu.fjnu.fundtradesys.utils.TransactionManager;

public class FundTransServiceImpl implements FundTransService {

	public void add(FundTransInfo fundtransinfo) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			FundTransDao fundtransDao = new FundTransDaoJDBCImpl();
			fundtransDao.add(fundtransinfo);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public Page loadPagedTradeRecord(Page page, FundTransInfo fundtransinfo) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			FundTransDao fundtransinfoDao = new FundTransDaoJDBCImpl();
			page.setTotalRecNum(fundtransinfoDao
					.cntFundTransInfos(fundtransinfo));
			page.setPageContent(fundtransinfoDao.getScopedFundTransInfo(
					fundtransinfo, page.getStartIndex(), page.getEndIndex()));
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}
}
