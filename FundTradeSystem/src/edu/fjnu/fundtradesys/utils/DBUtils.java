package edu.fjnu.fundtradesys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.fjnu.fundtradesys.exception.DataAccessException;

public class DBUtils {
	
	//用来把Connection绑定到当前线程上的变量
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
	 * 获取数据库连接
	 * 保证当前只有一个线程工作
	 * @return
	 */
	public synchronized Connection getConn()
	{
		//先从当前线程上取出连接实例
		Connection conn=threadStore.get();
		
		//如果当前线程上没有Connection的实例
		if (conn == null) {
			try {
				// 从连接池中取出一个连接实例
				conn = DriverManager.getConnection("proxool.fundtradesys-ds");
				//把它绑定到当前线程
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
	 * 释放数据库资源
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
	 * 在商业方法运行后期，释放位于线程上的连接资源
	 * @param conn
	 */
	public void releaseConn(Connection conn){
		try {			
			if (null != conn){
				threadStore.remove();//卸装线程绑定
				conn.close();
			}
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new DataAccessException();
		}
	}
	

}