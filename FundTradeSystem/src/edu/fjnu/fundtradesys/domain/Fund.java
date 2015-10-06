package edu.fjnu.fundtradesys.domain;

import java.sql.Date;
/**
 * 
* @ClassName: Fund
* @Description: �� Fund �������Ʒ��
* @author wuhm �����
* @date 2013-1-1 ����5:27:09
*
 */
public class Fund extends ValueObject{
	/**
	 * ������
	 */
	private Integer fundNo;
	/**
	 * ������
	 */
	private String fundName;
	/**
	 * ����۸�
	 */
	private Float fundPrice;
	/**
	 * ��ע
	 */
	private String fundDesc;
	/**
	 * ����״̬
	 */
	private String fundStatus;
	/**
	 * ��������
	 */
	private Date createDate;
	/**
	 * ����Ա���
	 */
	private String operCode;
	
	public Integer getFundNo() {
		return fundNo;
	}
	public void setFundNo(Integer fundNo) {
		this.fundNo = fundNo;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public Float getFundPrice() {
		return fundPrice;
	}
	public void setFundPrice(Float fundPrice) {
		this.fundPrice = fundPrice;
	}
	public String getFundDesc() {
		return fundDesc;
	}
	public void setFundDesc(String fundDesc) {
		this.fundDesc = fundDesc;
	}
	public String getFundStatus() {
		return fundStatus;
	}
	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date date) {
		this.createDate = (Date) date;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	
	
	
}
