package I2026_J15;

public class LinearRegression implements MLModel {

	private double[] weights;
	private double bias = 0.0;
	private double learningRate = 0.1;
	private int iterations = 1000;

	@Override
	public void train(double[][] X, int[] y) {
		int numSamples = X.length;
		int numFeatures = X[0].length;
		weights = new double[numFeatures];

		for (int i = 0; i < iterations; i++) {
			for (int j = 0; j < numSamples; j++) {
				double linearModel = bias;
				for (int f = 0; f < numFeatures; f++) {
					linearModel += weights[f] * X[j][f];
				}

				double prediction = sigmoid(linearModel);
				double error = prediction - y[j];
				for (int f = 0; f < numFeatures; f++) {
					weights[f] -= learningRate * error * X[j][f];
				}
				bias -= learningRate * error;
			}
		}
		System.out.println("Antrenare Logistic Regression finalizată.");
	}

	@Override
	public int predict(double[] x) {
		double linearModel = bias;
		for (int i = 0; i < weights.length; i++) {
			linearModel += weights[i] * x[i];
		}

		return sigmoid(linearModel) >= 0.5 ? 1 : 0;
	}

	private double sigmoid(double z) {
		return 1.0 / (1.0 + Math.exp(-z));

	}
}
