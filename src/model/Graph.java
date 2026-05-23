package model;

import java.io.*;
import java.util.*;

import model.FloydResult;

public class Graph {

    // Valor infinito para caminos inexistentes
    private static final double INF = Double.POSITIVE_INFINITY;

    // Mapa ciudad -> índice
    private Map<String, Integer> cities;

    // Lista índice -> ciudad
    private List<String> cityNames;

    // Matriz de adyacencia
    private double[][] adjacencyMatrix;

    // Cantidad máxima inicial de ciudades
    private int capacity;

    // Constructor
    public Graph(int capacity) {
        this.capacity = capacity;

        cities = new HashMap<>();
        cityNames = new ArrayList<>();

        adjacencyMatrix = new double[capacity][capacity];

        initializeMatrix();
    }

    // Inicializar matriz
    private void initializeMatrix() {

        for (int i = 0; i < capacity; i++) {

            for (int j = 0; j < capacity; j++) {

                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                } else {
                    adjacencyMatrix[i][j] = INF;
                }
            }
        }
    }

    // Agregar ciudad
    public void addCity(String city) {

        if (!cities.containsKey(city)) {

            int index = cityNames.size();

            cities.put(city, index);
            cityNames.add(city);
        }
    }

    // Agregar arco
    public void addEdge(String from, String to, double distance) {

        addCity(from);
        addCity(to);

        int i = cities.get(from);
        int j = cities.get(to);

        adjacencyMatrix[i][j] = distance;
    }

    // Eliminar arco
    public void removeEdge(String from, String to) {

        if (cities.containsKey(from) && cities.containsKey(to)) {

            int i = cities.get(from);
            int j = cities.get(to);

            adjacencyMatrix[i][j] = INF;
        }
    }

    public void loadFromFile(String filename) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line;

            while ((line = br.readLine()) != null) {

                // Ignorar líneas vacías
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Separar datos
                String[] parts = line.split(" ");

                String city1 = parts[0];
                String city2 = parts[1];
                double distance = Double.parseDouble(parts[2]);

                // Agregar conexión
                addEdge(city1, city2, distance);
            }

            br.close();

            System.out.println("Archivo cargado correctamente.");

        } catch (IOException e) {

            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
    }

    public FloydResult floyd() {

        int size = cityNames.size();

        // Copia de matriz original
        double[][] dist = new double[size][size];

        // Matriz de caminos
        String[][] path = new String[size][size];

        // Inicializar matrices
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                dist[i][j] = adjacencyMatrix[i][j];
                path[i][j] = "";
            }
        }

        // Algoritmo de Floyd
        for (int k = 0; k < size; k++) {

            for (int i = 0; i < size; i++) {

                for (int j = 0; j < size; j++) {

                    // Evitar infinito + infinito
                    if (dist[i][k] != INF && dist[k][j] != INF) {

                        if (dist[i][k] + dist[k][j] < dist[i][j]) {

                            dist[i][j] = dist[i][k] + dist[k][j];

                            // Guardar ciudad intermedia
                            path[i][j] = cityNames.get(k);
                        }
                    }
                }
            }
        }

        return new FloydResult(dist, path);
    }

    private String buildPath(String[][] path, int i, int j) {

        // No hay ciudad intermedia
        if (path[i][j].equals("")) {
            return "";
        }

        String intermediate = path[i][j];

        int k = cities.get(intermediate);

        // Construcción recursiva
        return buildPath(path, i, k)
                + intermediate + " -> "
                + buildPath(path, k, j);
    }

    public void printShortestPath(
            String from,
            String to,
            FloydResult result) {

        if (!cities.containsKey(from) || !cities.containsKey(to)) {

            System.out.println("Ciudad no encontrada.");
            return;
        }

        int i = cities.get(from);
        int j = cities.get(to);

        double[][] dist = result.getDistances();
        String[][] path = result.getPaths();

        // No existe ruta
        if (dist[i][j] == INF) {

            System.out.println("No existe ruta.");
            return;
        }

        // Construir camino
        String route = from + " -> "
                + buildPath(path, i, j)
                + to;

        System.out.println("\nRuta más corta:");
        System.out.println(route);

        System.out.println("\nDistancia total:");
        System.out.println(dist[i][j] + " KM");
    }

    public String getGraphCenter(FloydResult result) {

        double[][] dist = result.getDistances();

        int size = cityNames.size();

        double minEccentricity = INF;

        String center = "";

        // Revisar cada ciudad
        for (int i = 0; i < size; i++) {

            double maxDistance = 0;

            // Buscar la distancia más grande
            for (int j = 0; j < size; j++) {

                if (dist[i][j] != INF &&
                        dist[i][j] > maxDistance) {

                    maxDistance = dist[i][j];
                }
            }

            // Verificar si es mejor centro
            if (maxDistance < minEccentricity) {

                minEccentricity = maxDistance;

                center = cityNames.get(i);
            }
        }

        return center;
    }

    // Mostrar matriz
    public void printMatrix() {

        int size = cityNames.size();

        System.out.println("\nMATRIZ DE ADYACENCIA:");

        // Encabezados
        System.out.print("\t");

        for (String city : cityNames) {
            System.out.print(city + "\t");
        }

        System.out.println();

        for (int i = 0; i < size; i++) {

            System.out.print(cityNames.get(i) + "\t");

            for (int j = 0; j < size; j++) {

                if (adjacencyMatrix[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(adjacencyMatrix[i][j] + "\t");
                }
            }

            System.out.println();
        }
    }

    // Obtener matriz
    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    // Obtener ciudades
    public List<String> getCityNames() {
        return cityNames;
    }
}