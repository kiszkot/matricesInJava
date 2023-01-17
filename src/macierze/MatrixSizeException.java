package macierze;

/**
 * Exception do oddania gdy nie ma możliwości tworzenia macierzy w danych rozmiarach
 */

public class MatrixSizeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor błędu bez wiadomości
	 */
	public MatrixSizeException() {
		super("");
	}
	
	/**
	 * Konstruktor błedu z wiadomością
	 * @param message Wiadomośc do wyświetlania
	 */
	public MatrixSizeException(String message) {
		super(message);
	}
}
