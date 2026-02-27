package I2026_J15;

public class kNNFactory implements MLFactory{

	@Override
	public MLModel createModel() {
		// TODO Auto-generated method stub
		return new kNN();
	}

	@Override
	public Preprocessor createPreprocessor() {
		// TODO Auto-generated method stub
		return new StandardizationPreprocesor();
	}


}


