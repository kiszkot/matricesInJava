package macierze;

/**
 * Exception do oddania gdy dotarcie do indeksu nie jest możelwe ponieważ jest po za wymiarami
 */

public class MatrixIndexOutOfBoundsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor błędu bez wiadomości
	 */
	public MatrixIndexOutOfBoundsException() {
		super();
	}
	
	/**
	 * Konstruktor błedu z wiadomością
	 * @param message Wiadomośc do wyświetlania
	 */
	public MatrixIndexOutOfBoundsException(String message) {
		super(message);
	}
	
}
