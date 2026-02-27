package I2026_J15;

public class DecisionTreeFactory implements MLFactory{

	@Override
	public MLModel createModel() {
		// TODO Auto-generated method stub
		return new DecisionTree();
	}

	@Override
	public Preprocessor createPreprocessor() {
		// TODO Auto-generated method stub
		return new NormalizationPreprocesor();
	}


}


