package I2026_J15;

public interface MLModel {
	void train(double[][] x, int[] y);
	int predict(double[] x);
}

