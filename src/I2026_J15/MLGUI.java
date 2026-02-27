package I2026_J15;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MLGUI extends JFrame {
    private JComboBox<String> factoryChooser;
    private JTextField inputX, inputY;
    private JLabel resultLabel;

    public MLGUI() {
        setTitle("ML Classifier GUI");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        factoryChooser = new JComboBox<>(new String[]{"Linear Regression", "Decision Tree", "kNN"});
        add(new JLabel("Alege Modelul:"));
        add(factoryChooser);

        JPanel inputPanel = new JPanel();
        inputX = new JTextField(5);
        inputY = new JTextField(5);
        inputPanel.add(new JLabel("X:"));
        inputPanel.add(inputX);
        inputPanel.add(new JLabel("Y:"));
        inputPanel.add(inputY);
        add(inputPanel);

        JButton predictBtn = new JButton("Predict");
        predictBtn.addActionListener(e -> runPrediction());
        add(predictBtn);

        resultLabel = new JLabel("Rezultat: -", SwingConstants.CENTER);
        add(resultLabel);
    }

    private void runPrediction() {
        try {
            MLFactory factory;
            String selected = (String) factoryChooser.getSelectedItem();
            
            if (selected.equals("Tree Based")) factory = new DecisionTreeFactory();
            else if (selected.equals("Distance Based")) factory = new kNNFactory();
            else factory = new LinearRegressionFactory();

            double[][] X = {{1, 1}, {2, 2}, {5, 5}, {6, 6}};
            int[] y = {0, 0, 1, 1};

            Client app = new Client(factory);
            app.run(X, y);

            double px = Double.parseDouble(inputX.getText());
            double py = Double.parseDouble(inputY.getText());
            
            int prediction = app.getModel().predict(new double[]{px, py});
            resultLabel.setText("Rezultat: Clasa " + (prediction == 0 ? "A" : "B"));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Eroare la datele de intrare!");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MLGUI().setVisible(true));
    }
}