package edu.fjnu.fundtradesys.service;

import java.sql.Date;

import edu.fjnu.fundtradesys.bo.FinancialAccountBO;
import edu.fjnu.fundtradesys.dao.AccountTransInfoDao;
import edu.fjnu.fundtradesys.dao.AccountTransInfoDaoJDBCImpl;
import edu.fjnu.fundtradesys.dao.ClientDao;
import edu.fjnu.fundtradesys.dao.ClientDaoJDBCImpl;
import edu.fjnu.fundtradesys.dao.FinancialAccountDao;
import edu.fjnu.fundtradesys.dao.FinancialAccountDaoJDBCImpl;
import edu.fjnu.fundtradesys.domain.AccountTransInfo;
import edu.fjnu.fundtradesys.domain.Client;
import edu.fjnu.fundtradesys.domain.FinancialAccount;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.utils.Page;
import edu.fjnu.fundtradesys.utils.TransactionManager;

public class FinancialAccountServiceImpl implements FinancialAccountService {

	public void createFinancialAccount(FinancialAccount financialaccount,
			Operator operator) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			FinancialAccountDao financialDao = new FinancialAccountDaoJDBCImpl();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			// 为了满足外键关联而得到客户对象
			Client c = clientDao.getClientByNo(financialaccount.getClient_no());
			// 新建客户账户对象
			FinancialAccount f = new FinancialAccount();
			f.setAcc_pwd(financialaccount.getAcc_pwd());
			f.setAcc_amount(financialaccount.getAcc_amount());
			f.setAcc_status(financialaccount.getAcc_status());
			f.setCreate_date(new Date(new java.util.Date().getTime()));
			f.setClient_no(c.getClientNo());
			f.setOper_code(financialaccount.getOper_code());
			financialDao.add(f);

			// 往资金变动记录表中加入一条追加资金的记录
			FinancialAccount fa = financialDao.getFinancialAccountByClientNo(c
					.getClientNo());
			AccountTransInfo a = new AccountTransInfo();
			a.setOper_code(operator.getOperCode());
			a.setTrans_amount(financialaccount.getAcc_amount());
			a.setTrans_time(new Date(new java.util.Date().getTime()));
			a.setTrans_type("a");// a代表追加
			a.setAcc_no(fa.getAcc_no());
			// 添加记录到数据库中
			AccountTransInfoDao atiDao = new AccountTransInfoDaoJDBCImpl();
			atiDao.add(a);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public Page loadPagedFinancialAccounts(Page page,
			FinancialAccountBO financialaccountBO) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			FinancialAccountDao financialaccountDao = new FinancialAccountDaoJDBCImpl();
			page.setTotalRecNum(financialaccountDao
					.cntFinancialAccounts(financialaccountBO));
			page.setPageContent(financialaccountDao.getScopedFinancialAccounts(
					financialaccountBO, page.getStartIndex(),
					page.getEndIndex()));
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}

	public void addFund(FinancialAccount f, float money, Operator o) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			FinancialAccountDao faccDao = new FinancialAccountDaoJDBCImpl();
			FinancialAccount myf = faccDao.getFinancialAccountByNo(f
					.getAcc_no());
			String pass = faccDao.getFinancialAccountByNo(f.getAcc_no())
					.getAcc_pwd();
			if (f.getAcc_pwd().equals(pass)) {
				myf.setAcc_amount(myf.getAcc_amount() + money);
				faccDao.update(myf);
				// 增加资金变动记录
				AccountTransInfo a = new AccountTransInfo();
				a.setOper_code(o.getOperCode());
				a.setTrans_amount(money);
				a.setTrans_time(new Date(new java.util.Date().getTime()));
				a.setTrans_type("a");// a代表追加
				a.setAcc_no(myf.getAcc_no());
				// 添加记录到数据库中
				AccountTransInfoDao atiDao = new AccountTransInfoDaoJDBCImpl();
				atiDao.add(a);
			} else {
				throw new ApplicationException("密码错误.请重新输入.");
			}
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public void getFund(FinancialAccount f, float money, Operator o) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			FinancialAccountDao faccDao = new FinancialAccountDaoJDBCImpl();
			FinancialAccount myf = faccDao.getFinancialAccountByNo(f
					.getAcc_no());
			String pass = faccDao.getFinancialAccountByNo(f.getAcc_no())
					.getAcc_pwd();
			if (f.getAcc_pwd().equals(pass)) {
				if (myf.getAcc_amount() < money) {
					throw new ApplicationException("资金不足，操作失败.");
				} else {
					myf.setAcc_amount(myf.getAcc_amount() - money);
					faccDao.update(myf);
					// 增加资金变动记录
					AccountTransInfo a = new AccountTransInfo();
					a.setOper_code(o.getOperCode());
					a.setTrans_amount(money);
					a.setTrans_time(new Date(new java.util.Date().getTime()));
					a.setTrans_type("o");// o代表取出
					a.setAcc_no(myf.getAcc_no());
					// 添加记录到数据库中
					AccountTransInfoDao atiDao = new AccountTransInfoDaoJDBCImpl();
					atiDao.add(a);
				}
			} else {
				throw new ApplicationException("密码错误.请重新输入.");
			}
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public FinancialAccountBO getAccountByNo(Integer accountNo) {
		TransactionManager manager = new TransactionManager();
		FinancialAccountBO financialAccountBO = null;
		try {
			manager.beginTransaction();
			FinancialAccountDao financialaccountDao = new FinancialAccountDaoJDBCImpl();
			financialAccountBO = financialaccountDao
					.getFinancialAccountBOByNo(accountNo);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return financialAccountBO;
	}

	public void updateFinancialAccountBO(FinancialAccountBO financialaccountBo) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			FinancialAccountDao dao = new FinancialAccountDaoJDBCImpl();
			dao.updateBO(financialaccountBo);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public FinancialAccount getFinancialAccountByNo(Integer accountNo) {
		TransactionManager manager = new TransactionManager();
		FinancialAccount financialAccount = null;
		try {
			manager.beginTransaction();
			FinancialAccountDao financialaccountDao = new FinancialAccountDaoJDBCImpl();
			financialAccount = financialaccountDao
					.getFinancialAccountByNo(accountNo);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return financialAccount;
	}

	public void update(FinancialAccount financialaccount) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			FinancialAccountDao dao = new FinancialAccountDaoJDBCImpl();
			dao.update(financialaccount);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public void checkAccountStatus(FinancialAccount financialaccount) {
		TransactionManager manager = new TransactionManager();
		FinancialAccount f = null;

		try {
			manager.beginTransaction();
			FinancialAccountDao finanaccDao = new FinancialAccountDaoJDBCImpl();
			f = finanaccDao.getFinancialAccountByNo(financialaccount
					.getAcc_no());
			if (f.getAcc_status().equals("b"))
				throw new ApplicationException("账户已冻结，无法进行操作，请向管理员咨询。");
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public void checkAccount(FinancialAccount financialaccount) {
		TransactionManager manager = new TransactionManager();
		FinancialAccount f = null;

		try {
			manager.beginTransaction();
			FinancialAccountDao finanaccDao = new FinancialAccountDaoJDBCImpl();
			if (finanaccDao.getFinancialAccountByNo(financialaccount
					.getAcc_no()) == null)
				throw new ApplicationException("账户不存在，请重新输入.");
			else {
				f = finanaccDao.getFinancialAccountByNo(financialaccount
						.getAcc_no());
				if (!f.getAcc_pwd().equals(financialaccount.getAcc_pwd())) {
					throw new ApplicationException("密码错误，请重新输入.");
				}
			}
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}

	}
}
