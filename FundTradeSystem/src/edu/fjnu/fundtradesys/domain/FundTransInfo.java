package edu.fjnu.fundtradesys.domain;

import java.sql.Date;

public class FundTransInfo {
	/**
	 * 交易流水号  自增序列
	 */
	private Integer trans_id;
	/**
	 * 交易类型  （认购b，赎回r）
	 */
	private String trans_type;
	/**
	 * 资金账户账号
	 */
	private Integer acc_no;
	/**
	 * 基金编号
	 */
	private Integer fund_no;
	/**
	 * 数量
	 */
	private float amount;
	/**
	 * 交易价位（基金单价）
	 */
	private float price;
	/**
	 * 交易日期
	 */
	private Date trans_date;
	public Integer getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(Integer trans_id) {
		this.trans_id = trans_id;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public Integer getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(Integer acc_no) {
		this.acc_no = acc_no;
	}
	public Integer getFund_no() {
		return fund_no;
	}
	public void setFund_no(Integer fund_no) {
		this.fund_no = fund_no;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(Date trans_date) {
		this.trans_date = trans_date;
	}
	
}
