package I2026_J15;

public class Main {
	public static void main(String[] args) {
//		MLFactory factory = new DecisionTreeFactory();
//		
//		MLAplication app = new MLAplication(factory);
//		app.run(new double[][] {{1.2,2.3}}, new int[] {1});

		double[][] trainingData = { { 1.0, 1.0 }, { 1.5, 1.8 }, { 0.8, 1.2 }, // Grupul A (valori mici)
				{ 5.0, 5.0 }, { 6.2, 4.8 }, { 5.5, 6.0 } // Grupul B (valori mari)
		};

		// Etichete: 0 pentru Grupul A, 1 pentru Grupul B
		int[] labels = { 0, 0, 0, 1, 1, 1 };

		// 2. Alegem ce Factory vrem să folosim
		// Dacă vrei Decision Tree, alegi TreeBasedFactory
		MLFactory factory = new DecisionTreeFactory();

		// 3. Inițializăm aplicația folosind fabrica aleasă
		Client app = new Client(factory);

		// 4. Rulăm antrenarea (Aici se construiește arborele CART sau se calculează
		// ponderile)
		System.out.println("--- Pornire proces ML ---");
		app.run(trainingData, labels);

		// 5. Testăm o predicție pentru un punct nou
		// Un punct "sus", aproape de Grupul B
		double[] newPoint = { 6, 7 };

		// Obținem modelul din aplicație pentru a face predicția
		// (Notă: Ar trebui să ai un getter getModel() în MLApplication)
		int result = app.getModel().predict(newPoint);

		System.out.println("Rezultat predicție pentru ["+ newPoint[0]+ " , " + newPoint[1] + (result == 1 ? "] Clasa B (Corect)" : "Clasa A"));
	}
}
