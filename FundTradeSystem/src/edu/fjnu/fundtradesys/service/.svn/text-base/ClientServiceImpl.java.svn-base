package edu.fjnu.fundtradesys.service;

import edu.fjnu.fundtradesys.bo.CusInfo;
import edu.fjnu.fundtradesys.dao.ClientDao;
import edu.fjnu.fundtradesys.dao.ClientDaoJDBCImpl;
import edu.fjnu.fundtradesys.domain.Client;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.utils.Page;
import edu.fjnu.fundtradesys.utils.TransactionManager;

public class ClientServiceImpl implements ClientService {

	public void createClient(Client client) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			clientDao.add(client);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public Page loadPagedClients(Page page, CusInfo cusInfo) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			page.setTotalRecNum(clientDao.cntCusInfos(cusInfo));
			page.setPageContent(clientDao.getScopedCusInfos(cusInfo,
					page.getStartIndex(), page.getEndIndex()));
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}

	public Page loadPagedClientsDetail(Page page, CusInfo cusInfo) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			page.setTotalRecNum(clientDao.cntCusInfos(cusInfo));
			page.setPageContent(clientDao.getScopedCusInfosDetail(cusInfo,
				page.getStartIndex(), page.getEndIndex()));
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return page;
	}

	public void removeClient(Integer clientNo) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			clientDao.delete(clientNo);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
	}

	public CusInfo getCusInfoByNo(Integer clientNo) {
		TransactionManager manager=new TransactionManager();
		CusInfo cusInfo=null;
		try{
			manager.beginTransaction();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			cusInfo=clientDao.getCusInfoByIdNo(clientNo);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}
		return cusInfo;
	}

	public Client getClientByNo(Integer clientNo) {
		TransactionManager manager=new TransactionManager();
		Client client=null;
		try{
			manager.beginTransaction();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			client=clientDao.getClientByNo(clientNo);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}	
		return client;
	}
	
	public void update(Client client) {
		TransactionManager manager=new TransactionManager();
		try{
			manager.beginTransaction();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			clientDao.update(client);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}	
	}

	public Client getClientByIdNo(String idcardNo) {
		TransactionManager manager=new TransactionManager();
		Client client=null;
		try{
			manager.beginTransaction();
			ClientDao clientDao = new ClientDaoJDBCImpl();
			client=clientDao.getClientByIdNo(idcardNo);
			manager.commitAndClose();
		}catch(Exception e){
			manager.rollbackAndClose();
			throw new ApplicationException(e.getMessage());
		}	
		return client;
	}

}
