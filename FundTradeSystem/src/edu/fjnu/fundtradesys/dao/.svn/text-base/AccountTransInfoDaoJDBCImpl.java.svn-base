package edu.fjnu.fundtradesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.fundtradesys.domain.AccountTransInfo;
import edu.fjnu.fundtradesys.utils.DBUtils;

public class AccountTransInfoDaoJDBCImpl implements AccountTransInfoDao {
	
	private static final String ADD_ATI_ACCOUNT = "insert into financial_account_transinfo values(seq_finanacctrans.nextval,?,?,?,?,?)";
	
	public void add(AccountTransInfo fund) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(ADD_ATI_ACCOUNT);
			pstmt.setString(1, fund.getTrans_type());
			pstmt.setFloat(2, fund.getTrans_amount());
			pstmt.setDate(3, fund.getTrans_time());
			pstmt.setInt(4, fund.getAcc_no());
			pstmt.setString(5, fund.getOper_code());

			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
	}

	private String generateSQL(AccountTransInfo accountTransInfo) {

		String baseSQL = "select * from financial_account_transinfo where 1=1 ";

		if (accountTransInfo.getAcc_no() != null)
			baseSQL += " and ACC_NO='" + accountTransInfo.getAcc_no() + "'";
		
		if (accountTransInfo.getOper_code() != null)
			baseSQL += " and OPER_CODE='" + accountTransInfo.getOper_code()
					+ "'";
		baseSQL +=" order by trans_time desc";

		System.out.println("SQL BY accountTransInfo:" + baseSQL);

		return baseSQL;

	}
	
	public Integer cntAccountTransInfos(AccountTransInfo accountTransInfo) {
		String sql = this.generateSQL(accountTransInfo);

		sql = sql.replace("*", "count(*) accountTransInfo_cnt");

		System.out.println("cntAccountTransInfo:" + sql);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int accountTransInfoCnt = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				accountTransInfoCnt = rset.getInt("accountTransInfo_cnt");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return accountTransInfoCnt;
	}

	public List<AccountTransInfo> getScopedAccountTransInfo(
			AccountTransInfo accountTransInfo, int begin, int end) {
		String sql = this.generateSQL(accountTransInfo);
		sql = "select * from (select rownum rn, a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped accountTransInfo:" + sql + "," + end
				+ "," + begin);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<AccountTransInfo> accountTransInfoList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			accountTransInfoList = new ArrayList<AccountTransInfo>();

			while (rset.next()) {
				AccountTransInfo accounttransinfo = new AccountTransInfo();
				accounttransinfo.setTrans_id(rset.getInt("trans_id"));
				accounttransinfo.setAcc_no(rset.getInt("acc_no"));
				accounttransinfo.setOper_code(rset.getString("oper_code"));
				accounttransinfo.setTrans_amount(rset.getFloat("trans_amount"));
				accounttransinfo.setTrans_time(rset.getDate("trans_time"));
				accounttransinfo.setTrans_type(rset.getString("trans_type"));

				accountTransInfoList.add(accounttransinfo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return accountTransInfoList;
	}


}
