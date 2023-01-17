package macierze;

import java.util.*;
import java.time.*;

/**
 * Klasa z podstawowymi działaniami na macierzach
 */

public class Macierz {
	
	/**
	 * Macierz jako tablica dwu wymiarowa zmiennych przecinkowych
	 */
	private double[][] matrix;
	
	/**
	 * Liczba kolumn macierzy
	 */
	private int length;
	
	/**
	 * Liczba wierszy macierzy
	 */
	private int height;
	
	/**
	 * Konstruktor zerowej macerzy 3x3
	 */
	public Macierz() {
		zeros(3, 3);
	}
	
	/**
	 * Konstruktor zerowej macierzy kwadratowej NxN
	 * @param n Wymiar macierzy NxN
	 */
	public Macierz(int n) {
		double[][] matrix = (n == 0) ? null : new double[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				matrix[i][j] = 0;
			}
		}
		this.matrix = matrix;
		this.length = n;
		this.height = n;
	}
	
	/**
	 * Konstruktor zerowej macierzy NxM
	 * @param n Liczba wierszy
	 * @param m Liczba kolumn
	 */
	public Macierz(int n, int m) {
		zeros(n, m);
	}
	
	/**
	 * Konstruktor dla macierzy z daną zawartoscią w postaci tablicy dwuwymiarowej
	 * @param n Liczba wierszy
	 * @param m Liczba kolumn
	 * @param matrix Tablica dwuwimiarowa wartości macierzy
	 * @throws MatrixSizeException Gdy podane wymiary nie zgadzają się w wymiarami macierzy danych
	 */
	public Macierz(int n, int m, double[][] matrix) throws MatrixSizeException{
		if(n == 0 || m == 0 || matrix.length == 0) {
			this.matrix = null;
			this.height = 0;
			this.length = 0;
		} else if(n != matrix.length || m != matrix[0].length) {
			throw new MatrixSizeException(String.format("Incorrect size %d x %d for size %d x %d", n, m, matrix.length, matrix[0].length));
		}
		else {
			this.matrix = matrix;
			this.height = n;
			this.length = m;
		}
	}
	
	/**
	 * Konstructor do towrzenia macierzy według podanego wzoru
	 * @param n Liczba wierszy
	 * @param m Liczba kolumn
	 * @param type "identity" macierz identyczności, "ones" macierz jedynek, "zeros" macierz zer, "hilbert" macierz Hilberta, "random" macerz liczb losowych
	 * @throws MatrixSizeException Gdy macierz Hilberta nie jest kwadratowa
	 */
	public Macierz(int n, int m, String type) throws MatrixSizeException{
		switch(type) {
		case "identity":
			identity(n, m);
			break;
		case "ones":
			ones(n, m);
			break;
		case "zeros":
			zeros(n, m);
			break;
		case "hilbert":
			hilbert(n, m);
			break;
		case "random":
			random(n, m);
			break;
		default:
			zeros(n, m);
			break;
		}
	}
	
	/**
	 * Funckja pomocnicza tworząca maciez jednostkową
	 * @param n Liczba wierszy
	 * @param m Liczba kolumn
	 */
	private void identity(int n, int m) {
		double[][] matrix = (n == 0 || m == 0) ? null : new double[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(i == j) matrix[i][j] = 1;
				else matrix[i][j] = 0;
			}
		}
		this.matrix = matrix;
		this.length = m;
		this.height = n;
	}
	
	/**
	 * Fukcja pomocnicza tworząca macierz jedynek
	 * @param n Liczba wierszy
	 * @param m Liczba kolumn
	 */
	private void ones(int n, int m) {
		double[][] matrix = (n == 0 || m == 0) ? null : new double[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				matrix[i][j] = 1;
			}
		}
		this.matrix = matrix;
		this.length = m;
		this.height = n;
	}
	
	/**
	 * Funkcja pomocnicza do tworzenia macierzy zer
	 * @param n Liczba wierszy
	 * @param m Liczba kolumn
	 */
	private void zeros(int n, int m) {
		double[][] matrix = (n == 0 || m == 0) ? null : new double[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				matrix[i][j] = 0;
			}
		}
		this.matrix = matrix;
		this.length = m;
		this.height = n;
	}
	
	/**
	 * Funckja pomocnicza do tworzenia kwadratowej macierzy Hilberta
	 * @param n Liczba wierszy
	 * @param m Liczba kolumn
	 * @throws MatrixSizeException Gdy macierz Hilberta nie jest kwadratowa
	 */
	private void hilbert(int n, int m) throws MatrixSizeException{
		if(n != m) throw new MatrixSizeException(String.format("Size %d x %d not square", n, m));
		double[][] matrix = (n == 0 || m == 0) ? null : new double[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				matrix[i][j] = (double)1/(i+j+1);
			}
		}
		this.matrix = matrix;
		this.length = m;
		this.height = n;
	}
	
	/**
	 * Funckja pomocnicza do towrzenia macierzy z liczbami losowymi
	 * @param n Liczba wierszy
	 * @param m Liczba kolumn
	 */
	private void random(int n, int m) {
		Clock seed = Clock.systemUTC();
		Random rand = new Random(seed.millis());
		double[][] matrix = (n == 0 || m == 0) ? null : new double[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				matrix[i][j] = (double)rand.nextGaussian();
			}
		}
		this.matrix = matrix;
		this.length = m;
		this.height = n;
	}
	
	/**
	 * Funkcja zwracająca dany element macierzy indeksy od 0
	 * @param n Wiersz
	 * @param m Kolumna
	 * @return double
	 * @throws MatrixIndexOutOfBoundsException Gdy próbuje się zamienic kolumne po za wymiarami
	 */
	private double get(int n, int m) throws MatrixIndexOutOfBoundsException{
		if(n < 0 || n >= this.height || m < 0 || m >= this.length) {
			throw new MatrixIndexOutOfBoundsException(String.format("Index %d or %d out of bound for size %d x %d", n, m, this.height, this.length));
		}
		return this.matrix[n][m];
	}
	
	/**
	 * Funkcja zwaracająca kolumne macierzy indeksy od 0
	 * @param n Kolumna macierzy do zwrócenia
	 * @return Macierz kokumna wyrana
	 * @throws MatrixIndexOutOfBoundsException Gdy wybrana kolumna jest po za wymiarem
	 */
	private Macierz getColumn(int n) throws MatrixIndexOutOfBoundsException{
		if(n < 0 || n >= this.length) {
			throw new MatrixIndexOutOfBoundsException(String.format("Index %d out of bound for lenght %d", n, this.length));
		}
		Macierz res = new Macierz(height, 1);
		for(int i=0; i<height; i++) {
			res.matrix[i][0] = this.matrix[i][n];
		}
		return res;
	}
	
	/**
	 * Funkcja do zamiany kolumn indeksy od 0
	 * @param a Indeks pierwszej kolumny do zamiany
	 * @param b Indeks drugiej kolumny do zamiany
	 * @throws MatrixIndexOutOfBoundsException Gdy próbuje się zamienic kolumne po za wymiarami
	 */
	public void swapColumn(int a, int b) throws MatrixIndexOutOfBoundsException{
		if(a < 0 || a >= this.length || b < 0 || b >= this.length) {
			throw new MatrixIndexOutOfBoundsException(String.format("Index %d or %d out of bound for lenght %d", a, b, this.length));
		}
		double tmp = 0;
		for(int i=0; i<this.height; i++) {
			tmp = this.matrix[i][a];
			this.matrix[i][a] = this.matrix[i][b];
			this.matrix[i][b] = tmp;
		}
	}
	
	/**
	 * Fukncja odwracająca aktualną macierz lewo-prawo
	 */
	public void flipLR() {
		double tmp = 0;
		for(int j=0; j<height; j++) {
			for(int i=0; i<(int)length/2; i++) {
				tmp = matrix[j][i];
				matrix[j][i] = matrix[j][length-i-1];
				matrix[j][length-i-1] = tmp;
			}
		}
	}
	
	/**
	 * Funkcja do zamiany wierszy macierzy indeksy od 0
	 * @param a Indeks pierwszego wiersza do zamiany
	 * @param b Indeks drugiego wiersza do zamiany
	 * @throws MatrixIndexOutOfBoundsException Gdy próbuje się zamienic kolumne po za wymiarami
	 */
	public void swapRow(int a, int b) throws MatrixIndexOutOfBoundsException{
		if(a < 0 || a >= this.height || b < 0 || b >= this.height) {
			throw new MatrixIndexOutOfBoundsException(String.format("Index %d or %d out of bound for height %d", a, b, this.height));
		}
		double[] tmp = new double[this.length];
		tmp = this.matrix[a];
		this.matrix[a] = this.matrix[b];
		this.matrix[b] = tmp;
	}
	
	/**
	 * Funkcja odwracająca aktualną macierz góra-dół
	 */
	public void flipUD() {
		double[] tmp;
		for(int j=0; j<(int)height/2; j++) {
			tmp = matrix[j];
			matrix[j] = matrix[height-j-1];
			matrix[height-j-1] = tmp;
		}
	}
	
	/**
	 * Funkcja do dodawania macierzy
	 * @param a Macierz po lewej stronie
	 * @param b Macierz po prawej stronie
	 * @return Macierz z wyniku dodania macierzy
	 * @throws MatrixSizeException Gdy wymiary dodawanych macierzy się nie zgadzają
	 */
	public static Macierz add(Macierz a, Macierz b) throws MatrixSizeException {
		int n = a.height, p = b.height;
		int m = a.length, q = b.length;
		double[][] ret = new double[n][m];
		if(m != q || n != p) throw new MatrixSizeException(String.format("Incorrect size, a is %d x %d, b id %d x %d", n, m, p, q));
		else {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					ret[i][j] = a.matrix[i][j] + b.matrix[i][j];
				}
			}
		}
		return new Macierz(n, m, ret);
	}
	
	/**
	 * Funkcja do odejmowania od macierzy po lewej macierz po prawej
	 * @param a Macierz po lewej stronie
	 * @param b Macierz po prawej stronie
	 * @return Macierz z wyniku dodania macierzy
	 * @throws MatrixSizeException Gdy wymiary dodawanych macierzy się nie zgadzają
	 */
	public static Macierz subtract(Macierz a, Macierz b) throws MatrixSizeException {
		int n = a.height, p = b.height;
		int m = a.length, q = b.length;
		double[][] ret = new double[n][m];
		if(m != q || n != p) throw new MatrixSizeException(String.format("Incorrect size, a is %d x %d, b id %d x %d", n, m, p, q));
		else {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					ret[i][j] = a.matrix[i][j] - b.matrix[i][j];
				}
			}
		}
		return new Macierz(n, m, ret);
	}
	
	/**
	 * Funkcja do mnorzenia dwóch macierzy
	 * @param a Macierz po lewej stronie mnożenia
	 * @param b Macierz po prawej stronie mnożenia
	 * @return Macierz
	 * @throws MatrixSizeException Gdy nie zgadzają się wymiary mnożenia
	 */
	public static Macierz product(Macierz a, Macierz b) throws MatrixSizeException{
		int n = a.height, p = b.height;
		int m = a.length, q = b.length;
		double[][] ret = new double[n][q];
		double sum = 0;
		if(n == 1 && m == 1) return product(a.matrix[0][0], b);
		else if(m != p) throw new MatrixSizeException(String.format("Incorrect size, a is %d x %d, b id %d x %d", n, m, p, q));
		else {
			for(int i=0; i<n; i++) {
				for(int j=0; j<q; j++) {
					sum = 0;
					for(int k=0; k<m; k++) sum = sum + a.matrix[i][k]*b.matrix[k][j];
					ret[i][j] = sum;
				}
			}
		}
		return new Macierz(n, q, ret);
	}
	
	/**
	 * Funkcja do mnorzenia skalar z macierzą
	 * @param a Macierz po lewej stronie mnożenia
	 * @param b Macierz po prawej stronie mnożenia
	 * @return Macierz
	 * @throws MatrixSizeException Gdy nie zgadzają się wymiary mnożenia
	 */
	public static Macierz product(double a, Macierz b) throws MatrixSizeException{
		int p = b.height;
		int q = b.length;
		double[][] ret = new double[p][q];
		for(int i=0; i<p; i++) {
			for(int j=0; j<q; j++) {
				ret[i][j] = a*b.matrix[i][j];
			}
		}
		return new Macierz(p, q, ret);
	}
	
	/**
	 * Funkcja zwracająca widok String aktualnej macierzy w postaci naukowej
	 * @return String
	 */
	@Override
	public String toString() {
		String ret = "";
		for(int i=0; i<height; i++) {
			ret = ret + "| ";
			for(int j=0; j<length; j++) {
				ret = ret + String.format("%.5e",matrix[i][j]) + " ";
			}
			ret = ret + "|\n";
		}
		return ret;
	}
	
	/**
	 * Funkcja sprawdzająca dana macierz jest równa aktualnej macierzy
	 * @param b Macierz do porównania
	 * @return boolean
	 */
	@Override
	public boolean equals(Object b) {
		Macierz a = (Macierz)b;
		try {
			if(a == null) return false;
			else {
				if(a.length == this.length && a.height == this.height) {
					for(int i=0; i<this.height; i++) {
						for(int j=0; j<this.length; j++) {
							if(this.get(i, j) != a.get(i, j)) return false;
						}
					}
					return true;
				} else return false;
			}
		} catch(MatrixIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Funckja do obliczenia HashCode dla Macierzy
	 * @return int
	 */
	@Override
	public int hashCode() {
		long a = 0;
		for(int i=0; i<this.height; i++) {
			for(int j=0; j<this.length; j++) {
				a = a + (long)(this.length*this.height*matrix[i][j]);
			}
		}
		return (int) a;
	}
	
	/**
	 * Funkcja do obliczania wyznacznika macierzy
	 * @param a Macierz z której obliczyc wyznacznik
	 * @return double
	 * @throws MatrixSizeException Gdy podana macierz nie jest kwadratowa
	 */
	public static double determinant(Macierz a) throws MatrixSizeException{
		if(a.length != a.height) throw new MatrixSizeException(String.format("Size %d x %d not square", a.height, a.length));
		if(a == null || a.length == 0) return 1;
		double ret = 0;
		double[][] tmp = new double[a.height-1][a.length-1];
		int p = 0;
		for(int i=0; i<a.height; i++) {
			p = 0;
			for(int j=0; j<a.height; j++) {
				if(j==i) {
					p = 1;
					continue;
				}
				for(int k=1; k<a.length; k++) {
					tmp[j-p][k-1] = a.matrix[j][k];
				}
			}
			p = ((i+1)%2 == 0) ? -1 : 1;
			ret = ret + p*a.matrix[i][0]*determinant(new Macierz(a.height-1, a.length-1, tmp));
		}
		return ret;
	}
	
	/**
	 * Funkcja do transponowania podanej macierzy
	 * @param a Macierz do transponowania
	 * @return Macierz transponowana
	 * @throws MatrixSizeException Gdy nie zgadzają się wymiary mnożenia
	 */
	public static Macierz transpose(Macierz a) throws MatrixSizeException{
		double[][] ret = new double[a.length][a.height];
		for(int i=0; i<a.height; i++) {
			for(int j=0; j<a.length; j++) {
				ret[j][i] = a.matrix[i][j];
			}
		}
		return new Macierz(a.length, a.height, ret);
	}
	
	/**
	 * Funkcja do oblicenia macierzy odwrotnej korzystając z rozkładu LU
	 * @param a Macierz do odwrócenia
	 * @return Macierz odwrotna
	 * @throws MatrixSizeException Gdy macierz nie jest kwadratowa
	 * @throws MatrixIndexOutOfBoundsException Gdy kolumna jest po za zakresem
	 */
	public static Macierz inverse(Macierz a) throws MatrixSizeException, MatrixIndexOutOfBoundsException{
		if(a.length != a.height) throw new MatrixSizeException(String.format("Size %d x %d not square", a.height, a.length));
		double[][] res = new double[a.height][a.length];
		Macierz[] lup = partialLU(a);
		Macierz tmp = new Macierz(a.height, 1), y;
		for(int i=0; i<a.length; i++) {
			tmp = lup[2].getColumn(i);
			y = forwardRow(lup[0], tmp);
			tmp = backwardRow(lup[1], y);
			for(int j=0; j<a.height; j++) res[j][i] = tmp.matrix[j][0];
		}
		return new Macierz(a.height, a.length, res);
	}
	
	/**
	 * Funkcja do wykonania podstawiania w przód
	 * @param a Macierz współczynników
	 * @param b Macierz wyrazów wolnych
	 * @return Macierz rozwiązań
	 */
	private static Macierz forwardRow(Macierz a, Macierz b) {
		Macierz x = new Macierz(a.height, 1);
		x.matrix[0][0] = b.matrix[0][0]/a.matrix[0][0];
		double t = 0;
		int n = a.length;
		
		for(int i=1; i<n; i++) {
			t = 0;
			for(int j=0; j<i; j++) t = t + x.matrix[j][0]*a.matrix[i][j];
			x.matrix[i][0] = (b.matrix[i][0] - t)/a.matrix[i][i];
		}
		return x;
	}
	
	/**
	 * Funkcja do wykonania podstawiania w tył
	 * @param a Macierz współczynników
	 * @param b Macierz wyrazów wolnych
	 * @return Macierz rozwiązań
	 */
	private static Macierz backwardRow(Macierz a, Macierz b) {
		Macierz x = new Macierz(a.height, 1);
		int n = a.length;
		x.matrix[n-1][0] = b.matrix[n-1][0]/a.matrix[n-1][n-1];
		double t = 0;
		
		for(int i=n-1; i>=0; i--) {
			t = 0;
			for(int j=n-1; j>i; j--) t = t + x.matrix[j][0]*a.matrix[i][j];
			x.matrix[i][0] = (b.matrix[i][0] - t)/a.matrix[i][i];
		}
		return x;
	}
	
	/**
	 * Funkcja do wykonania rozkładu LU z częsciowym wyborem elementów
	 * @param a Macierz na której wykonac rozkład
	 * @return Macierz[] 0 - L, 1 - U, 2 - P
	 * @throws MatrixSizeException Gdy nie zgadzają się wymairy macierzy
	 * @throws MatrixIndexOutOfBoundsException Gdy dany indeks jest po za wymiarami macierzy
	 */
	private static Macierz[] partialLU(Macierz a) throws MatrixSizeException, MatrixIndexOutOfBoundsException{
		Macierz[] lup = new Macierz[3];
		Macierz u = Macierz.copyOf(a);
		int n = a.length;
		Macierz l = new Macierz(n, n, "identity");
		Macierz p = new Macierz(n, n, "identity");
		int swap = 1;
		double piwot = 0, b = 0;
		
		for(int i=0; i<n-1; i++) {
			// Wybór piwota
			piwot = u.matrix[i][i];
			swap = 0;
			for(int k=i; k<n; k++) {
				if(Math.abs(u.matrix[k][i]) > Math.abs(piwot)) {
					piwot = u.matrix[k][i];
					swap = k;
				}
			}
			
			// Permutacja
			u.swapRow(swap, i);
			p.swapRow(i, swap);
			
			// Wyzerowanie elementów
			for(int k=i+1; k<n; k++) {
				b = -u.matrix[k][i]/piwot;
				for(int j=i; j<n; j++) u.matrix[k][j] = u.matrix[k][j] + b*u.matrix[i][j];
				u.matrix[k][i] = -b;
			}
		}
		
		// Split
		for(int i=1; i<n; i++) {
			for(int j=0; j<i; j++) {
				l.matrix[i][j] = u.matrix[i][j];
				u.matrix[i][j] = 0;
			}
		}
		
		lup[0] = Macierz.copyOf(l);
		lup[1] = Macierz.copyOf(u);
		lup[2] = Macierz.copyOf(p);
		
		return lup;
	}
	
	/**
	 * Funkcja do utworzenia kopii macierzy 
	 * @param a Macierz do kopiowania
	 * @return Macierz kopia macierzy
	 */
	public static Macierz copyOf(Macierz a) {
		Macierz res = new Macierz(a.height, a.length);
		for(int i=0; i<a.height; i++) {
			for(int j=0; j<a.length; j++) {
				res.matrix[i][j] = a.matrix[i][j];
			}
		}
		return res;
	}
}