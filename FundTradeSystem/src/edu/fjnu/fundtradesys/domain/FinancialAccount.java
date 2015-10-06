package edu.fjnu.fundtradesys.domain;

import java.sql.Date;

/**
 * 表financial_account （客户资金账户）
 * 
 * @author Administrator
 * 
 */
public class FinancialAccount extends ValueObject {
	/**
	 * 资金账户账号
	 */
	private Integer acc_no;
	/**
	 * 资金账户密码
	 */
	private String acc_pwd;
	/**
	 * 当前可用资金量
	 */
	private float acc_amount;
	/**
	 * 账户状态
	 */
	private String acc_status;
	/**
	 * 客户编号
	 */
	private Integer client_no;
	/**
	 * 创建日期
	 */
	private Date create_date;
	/**
	 * 操作员编号
	 */
	private String Oper_code;

	public Integer getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(Integer acc_no) {
		this.acc_no = acc_no;
	}

	public String getAcc_pwd() {
		return acc_pwd;
	}

	public void setAcc_pwd(String acc_pwd) {
		this.acc_pwd = acc_pwd;
	}

	public float getAcc_amount() {
		return acc_amount;
	}

	public void setAcc_amount(float acc_amount) {
		this.acc_amount = acc_amount;
	}

	public String getAcc_status() {
		return acc_status;
	}

	public void setAcc_status(String acc_status) {
		this.acc_status = acc_status;
	}

	public Integer getClient_no() {
		return client_no;
	}

	public void setClient_no(Integer client_no) {
		this.client_no = client_no;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getOper_code() {
		return Oper_code;
	}

	public void setOper_code(String oper_code) {
		Oper_code = oper_code;
	}

}
