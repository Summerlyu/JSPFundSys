package edu.fjnu.fundtradesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.fundtradesys.domain.AccountTransInfo;
import edu.fjnu.fundtradesys.domain.FundTransInfo;
import edu.fjnu.fundtradesys.utils.DBUtils;

public class FundTransDaoJDBCImpl implements FundTransDao {

	private static final String ADD_FUNDTRANS = "insert into fund_transinfo values(seq_fundtrans.nextval,?,?,?,?,?,?)";

	public void add(FundTransInfo fundtransinfo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(ADD_FUNDTRANS);
			pstmt.setString(1, fundtransinfo.getTrans_type());
			pstmt.setInt(2, fundtransinfo.getAcc_no());
			pstmt.setInt(3, fundtransinfo.getFund_no());
			pstmt.setFloat(4, fundtransinfo.getAmount());
			pstmt.setFloat(5, fundtransinfo.getPrice());
			pstmt.setDate(6, fundtransinfo.getTrans_date());

			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}

	}

	private String generateSQL(FundTransInfo fundtransinfo) {
		String baseSQL = "select * from fund_transinfo where 1=1";

		if (fundtransinfo.getAcc_no() != null)
			baseSQL += " and acc_no='" + fundtransinfo.getAcc_no() + "'";

		if (fundtransinfo.getFund_no() != null)
			baseSQL += " and fund_no='" + fundtransinfo.getFund_no() + "'";

		baseSQL += " order by trans_date desc";

		System.out.println("SQL BY fundTransInfo:" + baseSQL);
		return baseSQL;
	}

	public int cntFundTransInfos(FundTransInfo fundtransinfo) {
		String sql = this.generateSQL(fundtransinfo);

		sql = sql.replace("*", "count(*) fundTransInfo_cnt");

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int fundTransInfoCnt = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				fundTransInfoCnt = rset.getInt("fundTransInfo_cnt");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fundTransInfoCnt;
	}

	public List<FundTransInfo> getScopedFundTransInfo(
			FundTransInfo fundtransinfo, int begin, int end) {

		String sql = this.generateSQL(fundtransinfo);
		sql = "select * from (select rownum rn,a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped fundTransInfo:" + sql + "," + end + ","
				+ begin);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<FundTransInfo> fundTransInfoList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			fundTransInfoList = new ArrayList<FundTransInfo>();

			while (rset.next()) {
				FundTransInfo fundTransInfo = new FundTransInfo();
				fundTransInfo.setTrans_id(rset.getInt("trans_id"));
				fundTransInfo.setTrans_type(rset.getString("trans_type"));
				fundTransInfo.setAcc_no(rset.getInt("acc_no"));
				fundTransInfo.setFund_no(rset.getInt("fund_no"));
				fundTransInfo.setAmount(rset.getFloat("amount"));//ÊýÁ¿
				fundTransInfo.setPrice(rset.getFloat("price"));
				fundTransInfo.setTrans_date(rset.getDate("trans_date"));

				fundTransInfoList.add(fundTransInfo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fundTransInfoList;
	}

}
