package common.exception;


public class DataAccessRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 0xf152209c6d75fa8fL;

	public DataAccessRuntimeException(String msg) {
		super(msg);
	}

	public DataAccessRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}