package edu.fjnu.fundtradesys.domain;

import java.sql.Date;

/**
 * ��financial_account_transinfo ���ʽ��˻����׼�¼��
 * 
 * @author Administrator
 * 
 */
public class AccountTransInfo extends ValueObject{
	/**
	 * ������ˮ��   ��������
	 */
	private Integer trans_id;
	/**
	 * �������ͣ�׷�ӣ�ȡ�����Ϲ�����أ�
	 */
	private String trans_type;
	/**
	 * ���׽��
	 */
	private float trans_amount;
	/**
	 * ���׷���ʱ��
	 */
	private Date trans_time;
	/**
	 * �ʽ��˻��˺�
	 */
	private Integer acc_no;
	/**
	 * ����Ա���
	 */
	private String Oper_code;

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

	public float getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(float trans_amount) {
		this.trans_amount = trans_amount;
	}

	public Date getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(Date trans_time) {
		this.trans_time = trans_time;
	}

	public Integer getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(Integer acc_no) {
		this.acc_no = acc_no;
	}

	public String getOper_code() {
		return Oper_code;
	}

	public void setOper_code(String oper_code) {
		Oper_code = oper_code;
	}

}
