package I2026_J15;

import java.util.ArrayList;
import java.util.List;

public class DecisionTree implements MLModel {

    private Node root;
    private final int maxDepth = 5;

    private static class Node {
        int featureIndex = -1;
        double threshold;
        Node left, right;
        Integer leafClass;

        Node(int classLabel) { this.leafClass = classLabel; }
        Node() {}
    }

    @Override
    public void train(double[][] X, int[] y) {
        this.root = buildTree(X, y, 0);
    }

    

    @Override
    public int predict(double[] x) {
        if (root == null) return -1;
        Node current = root;
        while (current.leafClass == null) {
            if (x[current.featureIndex] <= current.threshold) {
                if (current.left == null) break;
                current = current.left;
            } else {
                if (current.right == null) break;
                current = current.right;
            }
        }
        return (current.leafClass != null) ? current.leafClass : mostFrequentInPredict(current);
    }
    
    
    private Node buildTree(double[][] X, int[] y, int depth) {
        // Condiții de oprire
        if (X.length == 0) return null;
        if (allSameClass(y) || depth >= maxDepth || X.length < 2) {
            return new Node(mostFrequent(y));
        }

        double bestGini = 1.0;
        int bestFeature = -1;
        double bestThreshold = 0;

        // Găsirea celui mai bun split
        for (int f = 0; f < X[0].length; f++) {
            for (double[] row : X) {
                double threshold = row[f];
                double currentGini = calculateSplitGini(X, y, f, threshold);
                
                if (currentGini < bestGini) {
                    bestGini = currentGini;
                    bestFeature = f;
                    bestThreshold = threshold;
                }
            }
        }

        if (bestFeature == -1) return new Node(mostFrequent(y));

        // ÎMPĂRȚIREA DATELOR (Aici lipsea codul tău)
        List<Integer> leftIndices = new ArrayList<>();
        List<Integer> rightIndices = new ArrayList<>();

        for (int i = 0; i < X.length; i++) {
            if (X[i][bestFeature] <= bestThreshold) leftIndices.add(i);
            else rightIndices.add(i);
        }

        // Dacă un split nu separă nimic, returnăm o frunză
        if (leftIndices.isEmpty() || rightIndices.isEmpty()) {
            return new Node(mostFrequent(y));
        }

        Node node = new Node();
        node.featureIndex = bestFeature;
        node.threshold = bestThreshold;

        // Construim sub-matricele pentru copii
        node.left = buildTree(getSubX(X, leftIndices), getSubY(y, leftIndices), depth + 1);
        node.right = buildTree(getSubX(X, rightIndices), getSubY(y, rightIndices), depth + 1);

        return node; 
    }

    // Metode Helper pentru manipularea matricelor
    private double[][] getSubX(double[][] X, List<Integer> indices) {
        double[][] subX = new double[indices.size()][X[0].length];
        for (int i = 0; i < indices.size(); i++) subX[i] = X[indices.get(i)];
        return subX;
    }

    private int[] getSubY(int[] y, List<Integer> indices) {
        int[] subY = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) subY[i] = y[indices.get(i)];
        return subY;
    }

    private double calculateSplitGini(double[][] X, int[] y, int feature, double threshold) {
        int leftTrue = 0, leftTotal = 0, rightTotal = 0, rightTrue = 0;
        for (int i = 0; i < X.length; i++) {
            if (X[i][feature] <= threshold) {
                leftTotal++;
                if (y[i] == 1) leftTrue++;
            } else {
                rightTotal++;
                if (y[i] == 1) rightTrue++;
            }
        }
        if (leftTotal == 0 || rightTotal == 0) return 1.0;

        double gL = 1.0 - Math.pow((double)leftTrue/leftTotal, 2) - Math.pow((double)(leftTotal-leftTrue)/leftTotal, 2);
        double gR = 1.0 - Math.pow((double)rightTrue/rightTotal, 2) - Math.pow((double)(rightTotal-rightTrue)/rightTotal, 2);

        return (double)leftTotal/y.length * gL + (double)rightTotal/y.length * gR;
    }

    private int mostFrequent(int[] y) {
        int count1 = 0;
        for (int val : y) if (val == 1) count1++;
        return (count1 > y.length / 2) ? 1 : 0;
    }

    private boolean allSameClass(int[] y) {
        for (int i = 1; i < y.length; i++) if (y[i] != y[0]) return false;
        return true;
    }

    private int mostFrequentInPredict(Node n) { return 0; } // Fallback
}