package edu.fjnu.fundtradesys.service;

import java.util.List;
import edu.fjnu.fundtradesys.dao.FundDao;
import edu.fjnu.fundtradesys.dao.FundDaoJDBCImpl;
import edu.fjnu.fundtradesys.domain.Fund;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.utils.Page;
import edu.fjnu.fundtradesys.utils.TransactionManager;

public class FundServiceImpl implements FundService {

	public void createFund(Fund fund) {

		TransactionManager manager = new TransactionManager();

		try {
			manager.beginTransaction();
			FundDao fundDao = new FundDaoJDBCImpl();
			fundDao.add(fund);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public Page loadPagedFunds(Page page, Fund fundhelper) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			FundDao fundDao = new FundDaoJDBCImpl();
			page.setTotalRecNum(fundDao.cntFunds(fundhelper));
			page.setPageContent(fundDao.getScopedFunds(fundhelper,
					page.getStartIndex(), page.getEndIndex()));
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}

	public List<Fund> loadall() {
		TransactionManager manager = new TransactionManager();
		List<Fund> fundList = null;
		try {
			manager.beginTransaction();
			FundDao fundDao = new FundDaoJDBCImpl();
			fundList = fundDao.loadall();
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return fundList;
	}

	public void removeFund(Integer fundNo) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			FundDao fundDao = new FundDaoJDBCImpl();
			fundDao.delete(fundNo);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public Fund getFundByNo(Integer fundNo) {
		TransactionManager manager = new TransactionManager();
		Fund fund = null;
		try {
			manager.beginTransaction();
			FundDao fundDao = new FundDaoJDBCImpl();
			fund = fundDao.getFundByNo(fundNo);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return fund;
	}

	public void update(Fund fund) {
		TransactionManager manager = new TransactionManager();

		try {
			manager.beginTransaction();
			FundDao fundDao = new FundDaoJDBCImpl();
			fundDao.update(fund);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public void checkFundStatus(Integer fundNo) {

		TransactionManager manager = new TransactionManager();

		try {
			FundDao fundDao = new FundDaoJDBCImpl();
			Fund fund = fundDao.getFundByNo(fundNo);
			if(fund.getFundStatus().equals("c"))
				throw new ApplicationException("这支基金为封闭式基金，无法赎回.");
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}

	}

}
