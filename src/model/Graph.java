package model;

import java.io.*;

import java.util.*;

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