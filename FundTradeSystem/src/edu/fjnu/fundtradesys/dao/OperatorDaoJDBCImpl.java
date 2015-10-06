package edu.fjnu.fundtradesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.utils.DBUtils;

public class OperatorDaoJDBCImpl implements OperatorDao {
	
	private static final String LOAD_Operator_BYNO = "select * from bank_operator where Oper_code=?";

	public Operator getOperatorByNo(Integer opeNo) {
		Connection conn = DBUtils.getInstance().getConn();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Operator o = null;
		try {
			pstmt = conn.prepareStatement(LOAD_Operator_BYNO);
			pstmt.setInt(1, opeNo);
			rset = pstmt.executeQuery();
			o = new Operator();
			if (rset.next()) {
				o.setOperCode(rset.getString("oper_Code"));
				o.setOperContact(rset.getString("oper_Contact"));
				o.setOperName(rset.getString("oper_Name"));
				o.setOperPwd(rset.getString("oper_Pwd"));
			}
			return o;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return null;
	}

}
