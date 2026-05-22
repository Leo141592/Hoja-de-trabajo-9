package main;

import model.Graph;
import model.FloydResult;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph(50);

        graph.loadFromFile("guategrafo.txt");

        FloydResult result = graph.floyd();

        double[][] dist = result.getDistances();

        System.out.println("\nMATRIZ DE DISTANCIAS MÍNIMAS:");

        for (int i = 0; i < graph.getCityNames().size(); i++) {

            for (int j = 0; j < graph.getCityNames().size(); j++) {

                if (dist[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }

            System.out.println();
        }
    }
}