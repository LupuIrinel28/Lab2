package I2026_J15;

import java.util.*;

class kNN implements MLModel {
    private List<double[]> dataPoints = new ArrayList<>();
    private List<Integer> labels = new ArrayList<>();
    private int k = 3; // k-ul ales

    @Override
    public void train(double[][] X, int[] y) {
        // kNN doar stochează datele
        for (int i = 0; i < X.length; i++) {
            dataPoints.add(X[i]);
            labels.add(y[i]);
        }
    }

    @Override
    public int predict(double[] x) {
        List<Neighbor> neighbors = new ArrayList<>();
        for (int i = 0; i < dataPoints.size(); i++) {
            double dist = calculateDistance(x, dataPoints.get(i));
            neighbors.add(new Neighbor(dist, labels.get(i)));
        }

        Collections.sort(neighbors, Comparator.comparingDouble(n -> n.distance));

        int countA = 0, countB = 0;
        for (int i = 0; i < Math.min(k, neighbors.size()); i++) {
            if (neighbors.get(i).label == 0) countA++; 
            else countB++; // 1 = B
        }

        return (countB > countA) ? 1 : 0;
    }

    private double calculateDistance(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

    private static class Neighbor {
        double distance;
        int label;
        Neighbor(double d, int l) { this.distance = d; this.label = l; }
    }
}