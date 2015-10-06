package edu.fjnu.fundtradesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import edu.fjnu.fundtradesys.domain.Client;
import edu.fjnu.fundtradesys.domain.Fund;
import edu.fjnu.fundtradesys.exception.DataAccessException;
import edu.fjnu.fundtradesys.utils.DBUtils;

public class FundDaoJDBCImpl implements FundDao {

	private static final String ADD_FUND = "insert into fund values(seq_fund.nextval,?,?,?,?,?,?)";
	private static final String LOAD_ALL = "select * from fund order by fund_no desc";
	private static final String DEL_FUND_BYNO = "delete from fund where fund_no=?";
	private static final String LOAD_FUND_BYNO = "select * from fund where fund_no=?";
	private static final String UPDATE_FUND = "update fund set fund_name=?,fund_price=?,fund_desc=?,fund_status=?,create_date=?,Oper_code=?  where fund_no=?";

	public void add(Fund fund) {

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(ADD_FUND);
			pstmt.setString(1, fund.getFundName());
			pstmt.setFloat(2, fund.getFundPrice());
			pstmt.setString(3, fund.getFundDesc());
			pstmt.setString(4, fund.getFundStatus());
			pstmt.setDate(5, fund.getCreateDate());
			pstmt.setString(6, fund.getOperCode());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}

	}

	private String generateSQL(Fund fundhelper) {

		String baseSQL = "select * from fund where 1=1 ";

		if (fundhelper.getFundName() != null)
			baseSQL += " and fund_name='" + fundhelper.getFundName() + "'";

		if (StringUtils.isNotEmpty(fundhelper.getFundStatus()))
			baseSQL += " and fund_status='" + fundhelper.getFundStatus() + "'";

		baseSQL += " order by fund_no desc";

		System.out.println("SQL BY fundhelper:" + baseSQL);

		return baseSQL;

	}

	public int cntFunds(Fund fundhelper) {
		String sql = this.generateSQL(fundhelper);

		sql = sql.replace("*", "count(*) fund_cnt");
		sql = sql.substring(0, sql.indexOf("order")).trim();

		System.out.println("cntFunds:" + sql);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int fundCnt = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				fundCnt = rset.getInt("fund_cnt");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fundCnt;
	}

	public List<Fund> getScopedFunds(Fund fundhelper, int begin, int end) {
		String sql = this.generateSQL(fundhelper);
		sql = "select * from (select rownum rn, a.* from (" + sql
				+ ") a where rownum<=? ) where rn>=?";

		System.out.println("get scoped funds:" + sql + "," + end + "," + begin);

		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Fund> fundList = null;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			rset = pstmt.executeQuery();
			fundList = new ArrayList<Fund>();

			while (rset.next()) {
				Fund fund = new Fund();
				fund.setFundNo(rset.getInt("fund_no"));
				fund.setFundName(rset.getString("fund_name"));
				fund.setFundPrice(rset.getFloat("fund_price"));
				fund.setFundDesc(rset.getString("fund_desc"));
				fund.setFundStatus(rset.getString("fund_status"));
				fund.setCreateDate(rset.getDate("create_date"));
				fund.setOperCode(rset.getString("Oper_code"));
				fundList.add(fund);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fundList;
	}

	public List<Fund> loadall() {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Fund> fundList = null;

		try {

			pstmt = conn.prepareStatement(LOAD_ALL);
			rset = pstmt.executeQuery();

			fundList = new ArrayList<Fund>();

			while (rset.next()) {

				Fund fund = new Fund();
				fund.setFundNo(rset.getInt("fund_no"));
				fund.setFundName(rset.getString("fund_name"));
				fund.setFundPrice(rset.getFloat("fund_price"));
				fund.setFundDesc(rset.getString("fund_desc"));
				fund.setFundStatus(rset.getString("fund_status"));
				fund.setCreateDate(rset.getDate("create_date"));
				fund.setOperCode(rset.getString("Oper_code"));
				fundList.add(fund);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fundList;
	}

	public void delete(Integer fundNo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(DEL_FUND_BYNO);
			pstmt.setInt(1, fundNo);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("违反完整约束条件 (FUNDMGR.FK_F_H_F_NO)")) {
				FundDao fundDao = new FundDaoJDBCImpl();
				Fund fund = fundDao.getFundByNo(fundNo);
				throw new DataAccessException("有客户持有" + fund.getFundName()
						+ "，无法删除该基金信息");
			}
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
	}

	public Fund getFundByNo(Integer fundNo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Fund fund = null;

		try {

			pstmt = conn.prepareStatement(LOAD_FUND_BYNO);
			pstmt.setInt(1, fundNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {

				fund = new Fund();
				fund.setFundNo(rset.getInt("fund_no"));
				fund.setFundName(rset.getString("fund_name"));
				fund.setFundPrice(rset.getFloat("fund_price"));
				fund.setFundDesc(rset.getString("fund_desc"));
				fund.setFundStatus(rset.getString("fund_status"));
				fund.setCreateDate(rset.getDate("create_date"));
				fund.setOperCode(rset.getString("Oper_code"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}

		return fund;
	}

	public void update(Fund fund) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(UPDATE_FUND);

			pstmt.setString(1, fund.getFundName());
			pstmt.setFloat(2, fund.getFundPrice());
			pstmt.setString(3, fund.getFundDesc());
			pstmt.setString(4, fund.getFundStatus());
			pstmt.setDate(5, fund.getCreateDate());
			pstmt.setString(6, fund.getOperCode());
			pstmt.setInt(7, fund.getFundNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
	}

}
