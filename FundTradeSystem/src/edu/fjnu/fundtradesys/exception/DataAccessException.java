/**
 * 
 */
package edu.fjnu.fundtradesys.exception;



/**
 * @ClassName: DataAccessException
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wuhm 吴弘明
 * @date 2012-12-27 下午11:05:01
 *
 */
public class DataAccessException extends ApplicationException {

	/**
	 * 
	 */
	public DataAccessException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}

