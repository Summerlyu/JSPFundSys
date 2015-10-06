package edu.fjnu.fundtradesys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.fjnu.fundtradesys.exception.DataAccessException;

public class DBUtils {
	
	//������Connection�󶨵���ǰ�߳��ϵı���
	private static ThreadLocal<Connection> threadStore = new ThreadLocal<Connection>();

	private static DBUtils me = new DBUtils();

	private DBUtils() {
	}

	public static DBUtils getInstance() {
		return me;
	}

	static {
		try {

			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * ��֤��ǰֻ��һ���̹߳���
	 * @return
	 */
	public synchronized Connection getConn()
	{
		//�ȴӵ�ǰ�߳���ȡ������ʵ��
		Connection conn=threadStore.get();
		
		//�����ǰ�߳���û��Connection��ʵ��
		if (conn == null) {
			try {
				// �����ӳ���ȡ��һ������ʵ��
				conn = DriverManager.getConnection("proxool.fundtradesys-ds");
				//�����󶨵���ǰ�߳�
				threadStore.set(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DataAccessException();
			}
		}
		
		return conn;
		
	}
	
	/**
	 * �ͷ����ݿ���Դ
	 * @param conn
	 * @param pstmt
	 * @param rset
	 */
	public void ReleaseRes(Connection conn,PreparedStatement pstmt,ResultSet rset)
	{
		try{
		  if(rset!=null) rset.close();
		  if(pstmt!=null) pstmt.close();
		  if(conn!=null)  conn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * ����ҵ�������к��ڣ��ͷ�λ���߳��ϵ�������Դ
	 * @param conn
	 */
	public void releaseConn(Connection conn){
		try {			
			if (null != conn){
				threadStore.remove();//жװ�̰߳�
				conn.close();
			}
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new DataAccessException();
		}
	}
	

}