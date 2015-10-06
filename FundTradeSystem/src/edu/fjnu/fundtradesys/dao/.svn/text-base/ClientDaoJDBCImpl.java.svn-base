package edu.fjnu.fundtradesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.fundtradesys.bo.CusInfo;
import edu.fjnu.fundtradesys.domain.Client;
import edu.fjnu.fundtradesys.exception.DataAccessException;
import edu.fjnu.fundtradesys.utils.DBUtils;

public class ClientDaoJDBCImpl implements ClientDao {
	
	private static final String ADD_CLIENT = "insert into client values(seq_client.nextval,?,?,?,?,?,?,?,?,?)";
	private static final String DEL_CLIENT_BYNO = "delete from client where client_no=?";
	private static final String LOAD_CLIENT_BYNO = "select * from client where client_no=?";
	private static final String LOAD_CUSINFO_BYNO = "select * from v_cusinfodetail where client_no=?";
	private static final String UPDATE_CLIENT = "update client set idcard_no=?,client_name=?,"
			+ "client_sex=?,client_phone=?,client_email=?,client_address=?,"
			+ "client_hobby=?,create_date=?,Oper_code=? where client_no=?";
	private static final String LOAD_CLIENT_BYIDNO = "select * from client where idcard_no=?";
	
	public void add(Client client) {

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(ADD_CLIENT);
			pstmt.setString(1, client.getIdcardNo());
			pstmt.setString(2, client.getClientName());
			pstmt.setString(3, client.getClientSex());
			pstmt.setString(4, client.getClientPhone());
			pstmt.setString(5, client.getClientEmail());
			pstmt.setString(6, client.getClientAddress());
			pstmt.setString(7, client.getClientHobby());
			pstmt.setDate(8, client.getCreateDate());
			pstmt.setString(9, client.getOperCode());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}

	}

	private String generateSQL(CusInfo cusInfo) {

		String baseSQL = "select * from v_cusinfodetail where 1=1 ";

		if (cusInfo.getIdcardNo() != null)
			baseSQL += " and idcard_no='" + cusInfo.getIdcardNo() + "'";

		if (cusInfo.getClientName() != null)
			baseSQL += " and client_name='" + cusInfo.getClientName() + "'";

		System.out.println("SQL BY client:" + baseSQL);

		return baseSQL;

	}

	private String generateSQLDetail(CusInfo cusInfo) {

		String baseSQL = "select * from v_cusinfodetail where 1=1 ";

		if (cusInfo.getIdcardNo() != null)
			baseSQL += " and idcard_no='" + cusInfo.getIdcardNo() + "'";

		if (cusInfo.getClientName() != null)
			baseSQL += " and client_name='" + cusInfo.getClientName() + "'";

		System.out.println("SQL BY client:" + baseSQL);

		return baseSQL;

	}

	public int cntCusInfos(CusInfo cusInfo) {
		String sql = this.generateSQL(cusInfo);

		sql = sql.replace("*", "count(*)  cusInfo_cnt");

		System.out.println("cntCusInfos:" + sql);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int cusInfoCnt = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				cusInfoCnt = rset.getInt("cusInfo_cnt");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return cusInfoCnt;
	}

	public List<CusInfo> getScopedCusInfos(CusInfo cusInfo, int begin, int end) {
		String sql = this.generateSQL(cusInfo);
		sql = "select * from (select rownum rn, a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped cusInfos:" + sql + "," + end + ","
				+ begin);
		
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CusInfo> cusInfotList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			cusInfotList = new ArrayList<CusInfo>();

			while (rset.next()) {
				CusInfo cusinfo = new CusInfo();
				cusinfo.setAccountNo(rset.getInt("acc_no"));
				cusinfo.setClientName(rset.getString("client_name"));
				cusinfo.setIdcardNo(rset.getString("idcard_no"));
				cusinfo.setAccountAmount(rset.getFloat("acc_amount"));
				cusinfo.setTotalAmount(rset.getFloat("acc_amount")
						+ rset.getFloat("amount"));

				cusInfotList.add(cusinfo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return cusInfotList;
	}

	public List<CusInfo> getScopedCusInfosDetail(CusInfo cusInfo, int begin,
			int end) {
		String sql = this.generateSQLDetail(cusInfo);
		sql = "select * from (select rownum rn, a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped cusInfos:" + sql + "," + end + ","
				+ begin);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CusInfo> cusInfotList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			cusInfotList = new ArrayList<CusInfo>();

			while (rset.next()) {
				CusInfo cusinfo = new CusInfo();
				cusinfo.setClientNo(rset.getInt("client_no"));
				cusinfo.setClientName(rset.getString("client_name"));
				cusinfo.setClientSex(rset.getString("client_sex"));
				cusinfo.setIdcardNo(rset.getString("idcard_no"));
				cusinfo.setClientPhone(rset.getString("client_phone"));
				cusinfo.setClientEmail(rset.getString("client_email"));
				cusinfo.setClientAddress(rset.getString("client_address"));
				cusinfo.setClientHobby(rset.getString("client_hobby"));
				cusinfo.setCreateDate(rset.getDate("create_date"));
				cusinfo.setAccountAmount(rset.getFloat("acc_amount"));
				cusinfo.setTotalAmount(rset.getFloat("acc_amount")
						+ rset.getFloat("amount"));

				cusInfotList.add(cusinfo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return cusInfotList;
	}

	public void delete(Integer clientNo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(DEL_CLIENT_BYNO);
			pstmt.setInt(1, clientNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("违反完整约束条件 (FUNDMGR.FK_F_A_C_CODE)")) {
				ClientDao clientDao = new ClientDaoJDBCImpl();
				Client client = clientDao.getClientByNo(clientNo);
				throw new DataAccessException(client.getClientName()
						+ "拥有资金账户，无法删除该客户信息");
			}
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}

	}

	public Client getClientByNo(Integer clientNo) {
		Connection conn = DBUtils.getInstance().getConn();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Client client = null;

		try {
			pstmt = conn.prepareStatement(LOAD_CLIENT_BYNO);
			pstmt.setInt(1, clientNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {

				client = new Client();
				client.setClientNo(rset.getInt("client_no"));
				client.setIdcardNo(rset.getString("idcard_no"));
				client.setClientName(rset.getString("client_name"));
				client.setClientSex(rset.getString("client_sex"));
				client.setClientPhone(rset.getString("client_phone"));
				client.setClientEmail(rset.getString("client_email"));
				client.setClientAddress(rset.getString("client_address"));
				client.setClientHobby(rset.getString("client_hobby"));
				client.setCreateDate(rset.getDate("create_date"));
				client.setOperCode(rset.getString("Oper_code"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return client;
	}

	public CusInfo getCusInfoByIdNo(Integer clientNo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CusInfo cusinfo = null;

		try {
			pstmt = conn.prepareStatement(LOAD_CUSINFO_BYNO);
			pstmt.setInt(1, clientNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {

				cusinfo = new CusInfo();
				cusinfo.setClientNo(rset.getInt("client_no"));
				cusinfo.setIdcardNo(rset.getString("idcard_no"));
				cusinfo.setClientName(rset.getString("client_name"));
				cusinfo.setClientSex(rset.getString("client_sex"));
				cusinfo.setClientPhone(rset.getString("client_phone"));
				cusinfo.setClientEmail(rset.getString("client_email"));
				cusinfo.setClientAddress(rset.getString("client_address"));
				cusinfo.setClientHobby(rset.getString("client_hobby"));
				cusinfo.setCreateDate(rset.getDate("create_date"));
				cusinfo.setOperCode(rset.getString("Oper_code"));
				cusinfo.setAccountAmount(rset.getFloat("acc_amount"));
				cusinfo.setTotalAmount(rset.getFloat("acc_amount")
						+ rset.getFloat("amount"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return cusinfo;
	}
	
	public void update(Client client) {
		Connection conn = DBUtils.getInstance().getConn();

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(UPDATE_CLIENT);

			pstmt.setString(1, client.getIdcardNo());
			pstmt.setString(2, client.getClientName());
			pstmt.setString(3, client.getClientSex());
			pstmt.setString(4, client.getClientPhone());
			pstmt.setString(5, client.getClientEmail());
			pstmt.setString(6, client.getClientAddress());
			pstmt.setString(7, client.getClientHobby());
			pstmt.setDate(8, client.getCreateDate());
			pstmt.setString(9, client.getOperCode());
			pstmt.setInt(10, client.getClientNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}

	}

	public Client getClientByIdNo(String idcardNo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Client client = null;

		try {
			pstmt = conn.prepareStatement(LOAD_CLIENT_BYIDNO);
			pstmt.setString(1, idcardNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {

				client = new Client();
				client.setClientNo(rset.getInt("client_no"));
				client.setIdcardNo(rset.getString("idcard_no"));
				client.setClientName(rset.getString("client_name"));
				client.setClientSex(rset.getString("client_sex"));
				client.setClientPhone(rset.getString("client_phone"));
				client.setClientEmail(rset.getString("client_email"));
				client.setClientAddress(rset.getString("client_address"));
				client.setClientHobby(rset.getString("client_hobby"));
				client.setCreateDate(rset.getDate("create_date"));
				client.setOperCode(rset.getString("Oper_code"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return client;
	}

}
