package edu.fjnu.fundtradesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.fundtradesys.domain.FundHolding;
import edu.fjnu.fundtradesys.utils.DBUtils;

public class FundHoldingDaoJDBCImpl implements FundHoldingDao {
	
	private static final String ADD_FUNDHOLDING = "insert into fund_holding values(seq_fundhold.nextval,?,?,?)";
	private static final String GET_FUNDHOLDINGBYNO = "select * from fund_holding where hid=?";
	private static final String UPDATE_FUNDHOLDING = "update fund_holding set acc_no=?,fund_no=?,amount=? where hid=?";
	private static final String UPDATE_FUNDHOLDING_BY_ACCFUNDNO = "update fund_holding set amount=amount+? where acc_no=? and fund_no=?";
	public void add(FundHolding fundholding) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(ADD_FUNDHOLDING);
			pstmt.setInt(1, fundholding.getAccno());
			pstmt.setInt(2, fundholding.getFundno());
			pstmt.setFloat(3, fundholding.getAmount());

			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
		
	}

	private String generateSQL(FundHolding fundholding) {
		String baseSQL = " select * from fund_holding where 1=1 ";

		if (fundholding.getAccno() != null)
			baseSQL += " and acc_no='" + fundholding.getAccno() + "'";
		if (fundholding.getFundno() != null)
			baseSQL += " and fund_no='" + fundholding.getFundno() + "'";
		
		baseSQL +=" order by hid desc";
		System.out.println("SQL BY fundholding : " + baseSQL);
		return baseSQL;
	}

	public int cntFundHolding(FundHolding fundholding) {
		String sql = this.generateSQL(fundholding);

		sql = sql.replace("*", "count(*) fundholding_cnt");

		System.out.println("cntFundHolding : " + sql);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int fundholdingCnt = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				fundholdingCnt = rset.getInt("fundholding_cnt");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return fundholdingCnt;
	}

	public List<FundHolding> getScopedFundHolding(FundHolding fundholding,
			int begin, int end) {
		String sql = this.generateSQL(fundholding);

		sql = "select * from (select rownum rn,a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped fundholding : " + sql + "," + end + ","
				+ begin);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<FundHolding> fundholdingList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			fundholdingList = new ArrayList<FundHolding>();

			while (rset.next()) {
				FundHolding temp = new FundHolding();
				temp.setHid(rset.getInt("hid"));
				temp.setAccno(rset.getInt("acc_no"));
				temp.setFundno(rset.getInt("fund_no"));
				temp.setAmount(rset.getFloat("amount"));
				
				fundholdingList.add(temp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fundholdingList;

	}

	public FundHolding getFundHoldingByNo(int hid) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		FundHolding fundHolding = new FundHolding();
		try {
			pstmt = conn.prepareStatement(GET_FUNDHOLDINGBYNO);
			pstmt.setInt(1, hid);
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()){
				fundHolding.setAccno(rset.getInt("acc_no"));
				fundHolding.setAmount(rset.getFloat("amount"));
				fundHolding.setFundno(rset.getInt("fund_no"));
				fundHolding.setHid(rset.getInt("hid"));
			}
			return fundHolding;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
		return null;
	}

	public void updateFundHolding(FundHolding fundHolding) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(UPDATE_FUNDHOLDING);
			
			pstmt.setInt(1, fundHolding.getAccno());
			pstmt.setInt(2, fundHolding.getFundno());
			pstmt.setFloat(3, fundHolding.getAmount());
			pstmt.setInt(4, fundHolding.getHid());
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
		
	}

	public void updateFundHolding2(FundHolding fundHolding) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(UPDATE_FUNDHOLDING_BY_ACCFUNDNO);
			
			pstmt.setFloat(1, fundHolding.getAmount());
			pstmt.setFloat(2, fundHolding.getAccno());
			pstmt.setInt(3, fundHolding.getFundno());
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
		
	}

}
