package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.dao.OperatorDao;
import edu.fjnu.fundtradesys.dao.OperatorDaoJDBCImpl;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.utils.TransactionManager;

public class OperatorServiceImpl implements OperatorService {

	public Operator checkOperator(Integer operCode, String operPwd) {
		TransactionManager manager=new TransactionManager();
		Operator operator=null;
		OperatorDao operatorDao=new OperatorDaoJDBCImpl();
		operator=operatorDao.getOperatorByNo(operCode);
		if(!operator.getOperPwd().equals(operPwd))
			 throw new ApplicationException("”√ªß√‹¬Î¥ÌŒÛ£¨«ÎºÏ≤È!");
		return operator;
	}

}
