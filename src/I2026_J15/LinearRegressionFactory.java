package I2026_J15;

public class LinearRegressionFactory implements MLFactory{

	@Override
	public MLModel createModel() {
		// TODO Auto-generated method stub
		return new LinearRegression();
	}

	@Override
	public Preprocessor createPreprocessor() {
		// TODO Auto-generated method stub
		return new StandardizationPreprocesor();
	}

}

