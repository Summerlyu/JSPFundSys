package edu.fjnu.fundtradesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.fundtradesys.bo.FinancialAccountBO;
import edu.fjnu.fundtradesys.domain.FinancialAccount;
import edu.fjnu.fundtradesys.utils.DBUtils;

public class FinancialAccountDaoJDBCImpl implements FinancialAccountDao {

	private static final String ADD_FUND_ACCOUNT = "insert into financial_account values(seq_finacc.nextval,?,?,?,?,?,?)";
	private static final String LOAD_ACCOUNT_BYNO = "select * from financial_account where ACC_NO=?";
	private static final String LOAD_ACCOUNT_BYCNO = "select * from financial_account where client_no=?";
	private static final String UPDATE_ACCOUNT = "update financial_account set ACC_PWD=?,ACC_AMOUNT=?,ACC_STATUS=?,CLIENT_NO=?,CREATE_DATE=?,OPER_CODE=?  where ACC_NO=?";
	private static final String LOAD_ACCOUNTBO_BYNO = "select * from v_financialaccount where acc_no=?";
	private static final String UPDATE_STATUS = "update financial_account set ACC_STATUS=?,OPER_CODE=?  where acc_no=?";
	
	public void add(FinancialAccount financialaccount) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(ADD_FUND_ACCOUNT);
			pstmt.setString(1, financialaccount.getAcc_pwd());
			pstmt.setFloat(2, financialaccount.getAcc_amount());
			pstmt.setString(3, financialaccount.getAcc_status());
			pstmt.setInt(4, financialaccount.getClient_no());
			pstmt.setDate(5, financialaccount.getCreate_date());
			pstmt.setString(6, financialaccount.getOper_code());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
	}

	private String generateSQL(FinancialAccountBO financialaccountBO) {

		String baseSQL = "select * from v_financialaccount where 1=1 ";

		if (financialaccountBO.getAcc_no() != null)
			baseSQL += " and ACC_NO='" + financialaccountBO.getAcc_no() + "'";

		if (financialaccountBO.getStatus() != null)
			baseSQL += " and ACC_STATUS='" + financialaccountBO.getStatus()
					+ "'";

		System.out.println("SQL BY financialAccountBO:" + baseSQL);

		return baseSQL;
	}

	public int cntFinancialAccounts(FinancialAccountBO financialaccountBO) {
		String sql = this.generateSQL(financialaccountBO);

		sql = sql.replace("*", "count(*) financialAccount_cnt");

		System.out.println("cntFinancialAccounts:" + sql);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int financialAccountCnt = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				financialAccountCnt = rset.getInt("financialAccount_cnt");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return financialAccountCnt;
	}

	public List<FinancialAccountBO> getScopedFinancialAccounts(
			FinancialAccountBO financialaccountBO, int begin, int end) {
		String sql = this.generateSQL(financialaccountBO);
		sql = "select * from (select rownum rn, a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped financialAccounts:" + sql + "," + end
				+ "," + begin);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<FinancialAccountBO> financialAccountList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			financialAccountList = new ArrayList<FinancialAccountBO>();

			while (rset.next()) {
				FinancialAccountBO facc = new FinancialAccountBO();
				facc.setAcc_no(rset.getInt("ACC_NO"));
				facc.setMoney(rset.getFloat("acc_amount"));
				facc.setPass(rset.getString("acc_pwd"));
				facc.setIdcard_no(rset.getString("idcard_no"));
				facc.setStatus(rset.getString("acc_status"));
				facc.setClientno(rset.getInt("client_no"));
				facc.setDate(rset.getDate("create_date"));
				facc.setOpercode(rset.getString("oper_code"));
				financialAccountList.add(facc);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return financialAccountList;
	}

	public FinancialAccount getFinancialAccountByNo(Integer financialaccNo) {
		Connection conn = DBUtils.getInstance().getConn(); // DBUtilsOld和DBUtils有什么区别？
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FinancialAccount facc = null;
		try {
			pstmt = conn.prepareStatement(LOAD_ACCOUNT_BYNO);
			pstmt.setInt(1, financialaccNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				facc = new FinancialAccount();
				facc.setAcc_no(rset.getInt("acc_no"));
				facc.setAcc_amount(rset.getFloat("acc_amount"));
				facc.setAcc_pwd(rset.getString("acc_pwd"));
				facc.setAcc_status(rset.getString("acc_status"));
				facc.setClient_no(rset.getInt("client_no"));
				facc.setCreate_date(rset.getDate("create_date"));
				facc.setOper_code(rset.getString("oper_code"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return facc;
	}

	public void update(FinancialAccount financialacc) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(UPDATE_ACCOUNT);
			pstmt.setString(1, financialacc.getAcc_pwd());
			pstmt.setFloat(2, financialacc.getAcc_amount());
			pstmt.setString(3, financialacc.getAcc_status());
			pstmt.setInt(4, financialacc.getClient_no());
			pstmt.setDate(5, financialacc.getCreate_date());
			pstmt.setString(6, financialacc.getOper_code());
			pstmt.setInt(7, financialacc.getAcc_no());

			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}

	}

	public FinancialAccount getFinancialAccountByClientNo(Integer clientNo) {
		Connection conn = DBUtils.getInstance().getConn(); // DBUtilsOld和DBUtils有什么区别？
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FinancialAccount facc = null;
		try {
			pstmt = conn.prepareStatement(LOAD_ACCOUNT_BYCNO);
			pstmt.setInt(1, clientNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				facc = new FinancialAccount();
				facc.setAcc_no(rset.getInt("acc_no"));
				facc.setAcc_amount(rset.getFloat("acc_amount"));
				facc.setAcc_pwd(rset.getString("acc_pwd"));
				facc.setAcc_status(rset.getString("acc_status"));
				facc.setClient_no(rset.getInt("client_no"));
				facc.setCreate_date(rset.getDate("create_date"));
				facc.setOper_code(rset.getString("oper_code"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return facc;
	}

	
	public FinancialAccountBO getFinancialAccountBOByNo(Integer accountNo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FinancialAccountBO bo = null;
		try {
			pstmt = conn.prepareStatement(LOAD_ACCOUNTBO_BYNO);
			pstmt.setInt(1, accountNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				bo = new FinancialAccountBO();
				bo.setAcc_no(rset.getInt("acc_no"));
				bo.setClientno(rset.getInt("client_no"));
				bo.setIdcard_no(rset.getString("idcard_no"));
				bo.setMoney(rset.getFloat("acc_amount"));
				bo.setPass(rset.getString("acc_pwd"));
				bo.setStatus(rset.getString("acc_status"));
				bo.setDate(rset.getDate("create_date"));
				bo.setOpercode(rset.getString("oper_code"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return bo;
	}

	
	public void updateBO(FinancialAccountBO financialaccountBo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(UPDATE_STATUS);
			pstmt.setString(1, financialaccountBo.getStatus());
			pstmt.setString(2, financialaccountBo.getOpercode());
			pstmt.setInt(3, financialaccountBo.getAcc_no());

			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
		
	}

}
