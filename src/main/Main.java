package main;

import model.Graph;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph(20);

        graph.addEdge("Mixco", "Antigua", 30);
        graph.addEdge("Antigua", "Escuintla", 25);
        graph.addEdge("Escuintla", "SantaLucia", 15);

        graph.printMatrix();
    }
}