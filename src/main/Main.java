package main;

import model.Graph;
import model.FloydResult;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Graph graph = new Graph(50);

        graph.loadFromFile("guategrafo.txt");

        FloydResult result = graph.floyd();

        int option = 0;

        while (option != 4) {

            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Ruta más corta");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");

            System.out.print("\nSeleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1:

                    System.out.print("Ciudad origen: ");
                    String origin = scanner.nextLine();

                    System.out.print("Ciudad destino: ");
                    String destination = scanner.nextLine();

                    graph.printShortestPath(
                            origin,
                            destination,
                            result);

                    break;

                case 2:

                    String center = graph.getGraphCenter(result);

                    System.out.println(
                            "\nCentro del grafo:");

                    System.out.println(center);

                    break;

                case 3:

                    System.out.println("\n1. Eliminar conexión");
                    System.out.println("2. Agregar conexión");

                    int modification = scanner.nextInt();
                    scanner.nextLine();

                    if (modification == 1) {

                        System.out.print("Ciudad origen: ");
                        String from = scanner.nextLine();

                        System.out.print("Ciudad destino: ");
                        String to = scanner.nextLine();

                        graph.removeEdge(from, to);

                        System.out.println(
                                "Conexión eliminada.");

                    } else if (modification == 2) {

                        System.out.print("Ciudad origen: ");
                        String from = scanner.nextLine();

                        System.out.print("Ciudad destino: ");
                        String to = scanner.nextLine();

                        System.out.print("Distancia KM: ");
                        double distance = scanner.nextDouble();

                        graph.addEdge(
                                from,
                                to,
                                distance);

                        System.out.println(
                                "Conexión agregada.");
                    }

                    // Recalcular Floyd
                    result = graph.floyd();

                    break;

                case 4:

                    System.out.println(
                            "\nPrograma finalizado.");

                    break;

                default:

                    System.out.println(
                            "\nOpción inválida.");
            }
        }

        scanner.close();
    }
}