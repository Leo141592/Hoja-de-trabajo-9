package model;

public class FloydResult {

    private double[][] distances;
    private String[][] paths;

    public FloydResult(double[][] distances, String[][] paths) {
        this.distances = distances;
        this.paths = paths;
    }

    public double[][] getDistances() {
        return distances;
    }

    public String[][] getPaths() {
        return paths;
    }
}