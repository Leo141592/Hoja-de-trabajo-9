package test;

import model.Graph;
import model.FloydResult;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

}

@Test
public void testAddEdge() {

    Graph graph = new Graph(10);

    graph.addEdge("Mixco", "Antigua", 30);

    double[][] matrix = graph.getAdjacencyMatrix();

    assertEquals(30, matrix[0][1]);
}