/**
 * 
 */
package edu.fjnu.fundtradesys.domain;

/**
 * @ClassName: FundHolding
 * @Description: 表Fund_Holding (资金账户持仓信息)
 * @author wuhm 吴弘明
 * @date 2013-1-1 下午5:36:12
 * 
 */
public class FundHolding {
	/**
	 * 编号
	 */
	private Integer hid;
	/**
	 * 资金账户账号
	 */
	private Integer accno;
	/**
	 * 基金编号
	 */
	private Integer fundno;
	/**
	 * 持有数量
	 */
	private float amount;

	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public Integer getAccno() {
		return accno;
	}

	public void setAccno(Integer accno) {
		this.accno = accno;
	}

	public Integer getFundno() {
		return fundno;
	}

	public void setFundno(Integer fundno) {
		this.fundno = fundno;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
