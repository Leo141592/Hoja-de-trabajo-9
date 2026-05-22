package main;

import model.Graph;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph(50);

        graph.loadFromFile("guategrafo.txt");

        graph.printMatrix();
    }
}