package macierze;

/**
 * Klasa do testowania działalności macierzy
 */

public class Main {

	public static void main(String[] args) {
		
		try {
			Macierz test, test1 = new Macierz(4, 4, "ones");
			
			// Macierz identycznosci
			test = new Macierz(4, 4, "identity");
			System.out.println("Macierz identycznosci\n" + test.toString());
			
			// Macierz zerowa
			test = new Macierz(4, 4, "zeros");
			System.out.println("Macierz zerowa\n" + test.toString());
			
			//Macierz jedynek\
			test = new Macierz(4, 4, "ones");
			System.out.println("Macierz jedynek\n" + test.toString());
			
			// Macierz losowa
			Macierz random = new Macierz(4, 4, "random");
			System.out.println("Macierz losowa\n" + random.toString());
			
			// Macierz Hilberta
			test = new Macierz(4, 4, "hilbert");
			System.out.println("Macierz Hilberta\n" + test.toString());
			
			// Test Odwracania Macierzy
			test.flipUD();
			System.out.println("Góra-dól\n" +  test.toString());
			test.flipLR();
			System.out.println("Lewo-prawo\n" + test.toString());
			
			//Test dodawania macierzy
			test = new Macierz(4, 4, "ones");
			Macierz res = Macierz.add(test, test1);
			System.out.println("Dodawanie macierzy\n" + res.toString());
			
			//Test odejmowania macierzy
			test = new Macierz(4, 4, "ones");
			res = Macierz.subtract(test, test1);
			System.out.println("Odejmowanie macierzy\n" + res.toString());
			
			// Test mnorzenia macierzy
			res = Macierz.product(test, test1);
			System.out.println("Mnorzenie macierzy\n" + res.toString());

			// Test mnorzenia skalara i macierzy
			res = Macierz.product(2.5, test1);
			System.out.println("Mnorzenie skalra i macierzy\n" + res.toString());
			
			// Test Transponowania
			res = Macierz.transpose(random);
			System.out.println("Transponowanie wczesniejszej macierzy losowej\n" + res.toString());
			
			// Test odwrotności macierzy
			double[][] tmp = {
						{8,1,6},
						{3,5,7},
						{4,9,1}
					};
			test = new Macierz(3, 3, tmp);
			res = Macierz.inverse(test);
			System.out.println("Odwracanie macierzy\n" + res.toString());
			
			
			// Test equals i HashCode
			System.out.println("Equals i HashCode\n Jedynowa == Jedynkowa\n" + test.equals(test1));
			System.out.println(test.hashCode());
			System.out.println(test1.hashCode());
			
			// Test wyznacznika
			System.out.println("Wyznaczynik Jedynkowa\n" + Macierz.determinant(test1));
		} catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

}

/*
 * >> A = magic(3)
A =

   8   1   6
   3   5   7
   4   9   2

>> inv(A)
ans =

   0.147222  -0.144444   0.063889
  -0.061111   0.022222   0.105556
  -0.019444   0.188889  -0.102778
 * 
 * 
 */
