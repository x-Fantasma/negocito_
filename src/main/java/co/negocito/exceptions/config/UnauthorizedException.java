package co.negocito.exceptions.config;

public class UnauthorizedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPCION = "Unauthorized Exception 401";
	
	public UnauthorizedException(String detail) {
		super(DESCRIPCION + ", " + detail);
	}
	
}
