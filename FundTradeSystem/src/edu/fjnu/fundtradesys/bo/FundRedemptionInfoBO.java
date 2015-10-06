package edu.fjnu.fundtradesys.bo;

/**
 * 基金赎回列表
 * 
 * @author Administrator
 * 
 */
public class FundRedemptionInfoBO {
	private Integer acc_no;
	private Integer fund_no;
	private String fund_name;
	private float fund_price;
	private String fund_desc;
	private String fund_status;
	private Integer hid;
	private float amount;

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

	public String getFund_name() {
		return fund_name;
	}

	public void setFund_name(String fund_name) {
		this.fund_name = fund_name;
	}

	public float getFund_price() {
		return fund_price;
	}

	public void setFund_price(float fund_price) {
		this.fund_price = fund_price;
	}

	public String getFund_desc() {
		return fund_desc;
	}

	public void setFund_desc(String fund_desc) {
		this.fund_desc = fund_desc;
	}

	public String getFund_status() {
		return fund_status;
	}

	public void setFund_status(String fund_status) {
		this.fund_status = fund_status;
	}

	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
