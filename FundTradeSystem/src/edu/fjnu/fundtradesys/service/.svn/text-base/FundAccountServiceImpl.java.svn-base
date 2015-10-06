package edu.fjnu.fundtradesys.service;

import java.sql.Date;

import edu.fjnu.fundtradesys.bo.FundAccountInfoBO;
import edu.fjnu.fundtradesys.bo.FundRedemptionInfoBO;
import edu.fjnu.fundtradesys.dao.AccountTransInfoDao;
import edu.fjnu.fundtradesys.dao.AccountTransInfoDaoJDBCImpl;
import edu.fjnu.fundtradesys.dao.FinancialAccountDao;
import edu.fjnu.fundtradesys.dao.FinancialAccountDaoJDBCImpl;
import edu.fjnu.fundtradesys.dao.FundAccountDao;
import edu.fjnu.fundtradesys.dao.FundAccountDaoJDBCImpl;
import edu.fjnu.fundtradesys.domain.AccountTransInfo;
import edu.fjnu.fundtradesys.domain.FinancialAccount;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.utils.Page;
import edu.fjnu.fundtradesys.utils.TransactionManager;

public class FundAccountServiceImpl implements FundAccountService {

	public void buyFund(FinancialAccount f, float money, Operator o) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			//更新資金帳戶
			FinancialAccountDao faccDao = new FinancialAccountDaoJDBCImpl();
			FinancialAccount myf = faccDao.getFinancialAccountByNo(f.getAcc_no());
			if(myf.getAcc_amount() < money)
				throw new ApplicationException("账户资金不足，无法购买.");
			myf.setAcc_amount(myf.getAcc_amount() - money);
			faccDao.update(myf);
			// 增加资金变动记录
			AccountTransInfo a = new AccountTransInfo();
			a.setOper_code(o.getOperCode());
			a.setTrans_amount(money);
			a.setTrans_time(new Date(new java.util.Date().getTime()));
			a.setTrans_type("b");// a代表追加
			a.setAcc_no(myf.getAcc_no());
			// 添加记录到数据库中
			AccountTransInfoDao atiDao = new AccountTransInfoDaoJDBCImpl();
			atiDao.add(a);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public Page loadPagedFundAccountInfo(Page page,FundAccountInfoBO fundaccountinfoBO) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			FundAccountDao fundaccountDao = new FundAccountDaoJDBCImpl();
			page.setTotalRecNum(fundaccountDao
					.cntFundAccounts(fundaccountinfoBO));
			page.setPageContent(fundaccountDao.getScopedFundAccounts(
					fundaccountinfoBO, page.getStartIndex(), page.getEndIndex()));
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}

	public Page loadPagedfundRedemptionInfoBO(Page page,FundRedemptionInfoBO fundredemptionBO) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			FundAccountDao fundaccountDao = new FundAccountDaoJDBCImpl();
			page.setTotalRecNum(fundaccountDao
					.cntFundRedemptions(fundredemptionBO));
			page.setPageContent(fundaccountDao.getScopedFundRedemptions(
					fundredemptionBO, page.getStartIndex(), page.getEndIndex()));
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}

	public void saleFund(FinancialAccount f, float money, Operator o) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			//更新資金帳戶
			FinancialAccountDao faccDao = new FinancialAccountDaoJDBCImpl();
			FinancialAccount myf = faccDao.getFinancialAccountByNo(f.getAcc_no());
			myf.setAcc_amount(myf.getAcc_amount() + money);
			faccDao.update(myf);
			// 增加资金变动记录
			AccountTransInfo a = new AccountTransInfo();
			a.setOper_code(o.getOperCode());
			a.setTrans_amount(money);
			a.setTrans_time(new Date(new java.util.Date().getTime()));
			a.setTrans_type("r");// a代表追加
			a.setAcc_no(myf.getAcc_no());
			// 添加记录到数据库中
			AccountTransInfoDao atiDao = new AccountTransInfoDaoJDBCImpl();
			atiDao.add(a);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		
	}
}
