package main;

import model.Graph;
import model.FloydResult;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph(50);

        graph.loadFromFile("guategrafo.txt");

        FloydResult result = graph.floyd();

        graph.printShortestPath(
                "Mixco",
                "SantaLucia",
                result);
    }
}