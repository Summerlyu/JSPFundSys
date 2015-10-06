package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.dao.FundHoldingDao;
import edu.fjnu.fundtradesys.dao.FundHoldingDaoJDBCImpl;
import edu.fjnu.fundtradesys.domain.FundHolding;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.utils.Page;
import edu.fjnu.fundtradesys.utils.TransactionManager;

public class FundHoldingServiceImpl implements FundHoldingService {

	public void add(FundHolding fundholding) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			FundHoldingDao dao = new FundHoldingDaoJDBCImpl();
			if(dao.cntFundHolding(fundholding)==0)
				dao.add(fundholding);
			else
				dao.updateFundHolding2(fundholding);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public Page loadPagedFundHolding(Page page, FundHolding fundholding) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			FundHoldingDao fundholdingDao = new FundHoldingDaoJDBCImpl();
			page.setTotalRecNum(fundholdingDao.cntFundHolding(fundholding));
			page.setPageContent(fundholdingDao.getScopedFundHolding(fundholding,
					page.getStartIndex(), page.getEndIndex()));
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}

	public FundHolding getFundHoldingByNo(int hid) {
		TransactionManager manager=new TransactionManager();
		FundHolding fundHolding=null;
		try{
			manager.beginTransaction();
			FundHoldingDao dao = new FundHoldingDaoJDBCImpl();
			fundHolding=dao.getFundHoldingByNo(hid);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return fundHolding;
	}

	public void updateFundHolding(FundHolding fundHoldingPO) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			FundHoldingDao dao = new FundHoldingDaoJDBCImpl();
			dao.updateFundHolding(fundHoldingPO);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

}
