import org.junit.jupiter.api.Test;

import main.model.FloydResult;
import main.model.Graph;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testAddEdge() {

        Graph graph = new Graph(10);

        graph.addEdge("Mixco", "Antigua", 30);

        double[][] matrix = graph.getAdjacencyMatrix();

        assertEquals(30, matrix[0][1]);
    }

    @Test
    public void testRemoveEdge() {

        Graph graph = new Graph(10);

        graph.addEdge("Mixco", "Antigua", 30);

        graph.removeEdge("Mixco", "Antigua");

        double[][] matrix = graph.getAdjacencyMatrix();

        assertEquals(
                Double.POSITIVE_INFINITY,
                matrix[0][1]);
    }

    @Test
    public void testFloyd() {

        Graph graph = new Graph(10);

        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 3);

        FloydResult result = graph.floyd();

        double[][] dist = result.getDistances();

        assertEquals(8, dist[0][2]);
    }

    @Test
    public void testGraphCenter() {

        Graph graph = new Graph(10);

        graph.addEdge("A", "B", 2);
        graph.addEdge("B", "C", 2);
        graph.addEdge("A", "C", 10);

        FloydResult result = graph.floyd();

        String center = graph.getGraphCenter(result);

        assertEquals("B", center);
    }
}