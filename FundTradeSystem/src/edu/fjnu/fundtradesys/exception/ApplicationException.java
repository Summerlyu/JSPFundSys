/**
 * 
 */
package edu.fjnu.fundtradesys.exception;

/**
 * @author Administrator
 * fundtradesys �Զ����쳣�Ļ��࣬��ϵͳ�Ķ����֮��ֻ�ܴ��͵����Զ����쳣�������ܴ�������ĳ�ּ�����ר���쳣��
 */
public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	public ApplicationException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
