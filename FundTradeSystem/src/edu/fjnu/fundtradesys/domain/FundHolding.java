/**
 * 
 */
package edu.fjnu.fundtradesys.domain;

/**
 * @ClassName: FundHolding
 * @Description: ��Fund_Holding (�ʽ��˻��ֲ���Ϣ)
 * @author wuhm �����
 * @date 2013-1-1 ����5:36:12
 * 
 */
public class FundHolding {
	/**
	 * ���
	 */
	private Integer hid;
	/**
	 * �ʽ��˻��˺�
	 */
	private Integer accno;
	/**
	 * ������
	 */
	private Integer fundno;
	/**
	 * ��������
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
