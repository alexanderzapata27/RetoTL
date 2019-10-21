package co.com.ceiba.domain.exception;

public class TLException extends Exception{
	private static final long serialVersionUID = 3096080056225292624L;

	public TLException() {
		super();
	}
	public TLException(String message) {
		super(message);
	}
	public TLException(String message, Exception e) {
		super(message,e);
	}
	
}
