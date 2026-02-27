package I2026_J15;

public class Client {
	private MLModel model;
	private Preprocessor preprocessor;
	
	public Client(MLFactory factory) {
		this.model = factory.createModel();
		this.preprocessor = factory.createPreprocessor();
	}
	
	public void run(double[][] X,int[] y) {
		double[][] processedX = preprocessor.process(X);
		model.train(processedX, y);
		System.out.println("Model pregatit");
		
	}
	
	public MLModel getModel() {
		return this.model;
	}
}
