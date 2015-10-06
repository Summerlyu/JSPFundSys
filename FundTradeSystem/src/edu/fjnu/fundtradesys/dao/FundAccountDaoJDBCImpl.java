package edu.fjnu.fundtradesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.fundtradesys.bo.FinancialAccountBO;
import edu.fjnu.fundtradesys.bo.FundAccountInfoBO;
import edu.fjnu.fundtradesys.bo.FundRedemptionInfoBO;
import edu.fjnu.fundtradesys.utils.DBUtils;

public class FundAccountDaoJDBCImpl implements FundAccountDao {

	private String generateSQL(FundAccountInfoBO fundaccountinfoBO) {

		String baseSQL = "select * from v_fundaccountinfo where 1=1 ";

		if (fundaccountinfoBO.getAcc_no() != null)
			baseSQL += " and acc_no='" + fundaccountinfoBO.getAcc_no() + "'";
		if (fundaccountinfoBO.getClient_name() != null)
			baseSQL += " and client_name='"
					+ fundaccountinfoBO.getClient_name() + "'";

		baseSQL += " order by acc_no desc";

		System.out.println("SQL BY fundAccountBO:" + baseSQL);

		return baseSQL;
	}

	public Integer cntFundAccounts(FundAccountInfoBO fundaccountinfoBO) {
		String sql = this.generateSQL(fundaccountinfoBO);

		sql = sql.replace("*", "count(*) fundAccount_cnt");

		System.out.println("cntFundAccounts:" + sql);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int fundAccountCnt = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				fundAccountCnt = rset.getInt("fundAccount_cnt");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return fundAccountCnt;
	}

	public List<FundAccountInfoBO> getScopedFundAccounts(
			FundAccountInfoBO fundaccountinfoBO, int begin, int end) {
		String sql = this.generateSQL(fundaccountinfoBO);
		sql = "select * from (select rownum rn, a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped fundAccounts:" + sql + "," + end + ","
				+ begin);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<FundAccountInfoBO> fundAccountList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			fundAccountList = new ArrayList<FundAccountInfoBO>();

			while (rset.next()) {
				FundAccountInfoBO facc = new FundAccountInfoBO();
				facc.setAcc_no(rset.getInt("acc_no"));
				facc.setIdcard_no(rset.getString("idcard_no"));
				facc.setClient_name(rset.getString("client_name"));
				facc.setFund_name(rset.getString("fund_name"));
				facc.setAmount(rset.getFloat("amount"));
				fundAccountList.add(facc);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fundAccountList;
	}

	private String generateSQLfundredemption(
			FundRedemptionInfoBO fundredemptionBO) {

		String baseSQL = "select * from v_redeption where acc_no=' "
				+ fundredemptionBO.getAcc_no() + "'";

		baseSQL += " order by fund_no desc";

		System.out.println("SQL BY fundredemptionBO:" + baseSQL);

		return baseSQL;
	}

	public Integer cntFundRedemptions(FundRedemptionInfoBO fundredemptionBO) {
		String sql = this.generateSQLfundredemption(fundredemptionBO);

		sql = sql.replace("*", "count(*) Fundredemptions_cnt");

		System.out.println("cntFundredemptions:" + sql);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int FundredemptionsCnt = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				FundredemptionsCnt = rset.getInt("Fundredemptions_cnt");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return FundredemptionsCnt;
	}

	public List<FundRedemptionInfoBO> getScopedFundRedemptions(
			FundRedemptionInfoBO fundredemptionBO, int begin, int end) {
		
		String sql = this.generateSQLfundredemption(fundredemptionBO);
		
		sql = "select * from (select rownum rn, a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped FundRedemptions:" + sql + "," + end + ","
				+ begin);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<FundRedemptionInfoBO> fundredemptionList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			fundredemptionList = new ArrayList<FundRedemptionInfoBO>();

			while (rset.next()) {
				FundRedemptionInfoBO facc = new FundRedemptionInfoBO();
				facc.setFund_no(rset.getInt("fund_no"));
				facc.setAcc_no(rset.getInt("acc_no"));
				facc.setAmount(rset.getFloat("amount"));
				facc.setFund_desc(rset.getString("fund_desc"));
				facc.setFund_name(rset.getString("fund_name"));
				facc.setFund_price(rset.getFloat("fund_price"));
				facc.setFund_status(rset.getString("fund_status"));
				facc.setHid(rset.getInt("hid"));
				fundredemptionList.add(facc);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fundredemptionList;
	}

}
