package utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    static Graph[] graphs = new Graph[4];
    @BeforeAll
    static void makeGraphs() {
        graphs[0] = new Graph();

        graphs[1] = new Graph(1);

        graphs[2] = new Graph(1);
        assertFalse(graphs[2].addNode("0"));
        assertTrue(graphs[2].addNode("1"));
        assertThrows(IllegalArgumentException.class, () -> graphs[2].addEdge("0", "0", -1));
        assertFalse(graphs[2].addEdge("0", "2", 1));
        assertFalse(graphs[2].addEdge("-1", "1", 1));
        assertTrue(graphs[2].addEdge("0", "1", 1));
        assertTrue(graphs[2].addEdge("1", "0", 1));

        int[][] edgeMatrix3 = {{-1, -1, 1}, {-1, -1, -1}, {1, 1, -1}};
        graphs[3] = new Graph(edgeMatrix3);
    }
    @Test
    void equals() {
        Graph temp = new Graph();
        temp.addNode("1");
        temp.addNode("2");
        assertNotEquals(temp, graphs[2]);
        assertNotEquals(new Graph(), new Object());
        assertNotEquals(new Edge(null, null, 0), new Object());
    }
    @Test
    void toStringTest() {
        assertEquals("nodes=[], edges=[]", graphs[0].toString());
        assertEquals("nodes=[0], edges=[]", graphs[1].toString());
        assertEquals("[0->1 (1)]", graphs[2].outgoingEdges("0").toString());
    }
    @Test
    void subgraph() {
        Graph subgraph = new Graph();
        subgraph.addNode("0");
        subgraph.addNode("2");
        subgraph.addEdge("0", "2", 1);
        subgraph.addEdge("2", "0", 1);
        assertEquals(subgraph, graphs[3].subgraph(Set.of("0", "2")));
    }
    @Test
    void nodeCount() {
        int[] nodeCount = {0, 1, 2, 3};
        for (int i = 0; i < graphs.length; i++) {
            assertEquals(nodeCount[i], graphs[i].nodeCount());
        }
    }
    @Test
    void outgoingEdges() {
        int[] outgoingEdges = {0, 1, 2};
        for (int i = 1; i < graphs.length; i++) {
            assertEquals(outgoingEdges[i-1], graphs[i].outgoingEdges(String.valueOf(i-1)).size());
        }
    }
    @Test
    void incomingEdges() {
        int[] incomingEdges = {0, 1, 1};
        for (int i = 1; i < graphs.length; i++) {
            assertEquals(incomingEdges[i-1], graphs[i].incomingEdges(String.valueOf(i-1)).size());
        }
    }
    @Test
    void bothWayEdges() {
        assertEquals(Set.of(), graphs[1].bothWayNodes("0"));
        assertEquals(Set.of("1"), graphs[2].bothWayNodes("0"));
        assertEquals(Set.of("2"), graphs[3].bothWayNodes("0"));
        assertEquals(Set.of("0"), graphs[3].bothWayNodes("2"));
    }
    @Test
    void djikstra() {
        assertEquals(1, graphs[2].djikstra("0", "1"));
        assertEquals(2, graphs[3].djikstra("0", "1"));
        assertEquals(Integer.MAX_VALUE, graphs[3].djikstra("1", "0"));

        int[][] edgeMatrix = {
                {-1, 3, 1, -1, -1},
                {3, -1, -1, 4, -1},
                {1, -1, -1, 2, -1},
                {-1, 4, 2, -1, 5},
                {-1, -1, -1, 5, -1}
        };
        Graph bigGraph = new Graph(edgeMatrix);
        assertEquals(0, bigGraph.djikstra("0", "0"));
        assertEquals(1, bigGraph.djikstra("0", "2"));
        assertEquals(3, bigGraph.djikstra("0", "3"));
        assertEquals(4, bigGraph.djikstra("1", "2"));
        assertEquals(8, bigGraph.djikstra("0", "4"));
    }
    @Test
    void edgeCompare() {
        Edge edge1 = new Edge("a", "b", 1);
        Edge edge2 = new Edge("a", "b", 1);
        Edge edge3 = new Edge("a", "b", 2);
        Edge edge4 = new Edge("a", "c", 2);
        Edge edge5 = new Edge("b", "c", 2);
        assertEquals(edge1, edge2);
        assertNotEquals(edge1, edge3);
        assertNotEquals(edge1, edge4);
        assertNotEquals(edge1, edge5);
    }
    @Test
    void clique() {
        assertThrows(IllegalArgumentException.class, () -> graphs[0].clique(0));
        assertThrows(IllegalArgumentException.class, () -> graphs[0].clique(1));
        assertEquals(Set.of(), graphs[0].clique(2));
        assertEquals(Set.of("0", "1"), graphs[2].clique(2));
        assertEquals(Set.of("0", "2"), graphs[3].clique(2));
        assertEquals(Set.of(), graphs[3].clique(3));

        assertThrows(IllegalArgumentException.class, () -> graphs[0].clique(0, "0"));
        assertThrows(IllegalArgumentException.class, () -> graphs[0].clique(1, "0"));
        assertEquals(Set.of("0", "2"), graphs[3].clique(2, "2"));
        assertEquals(Set.of(), graphs[3].clique(3, "2"));
        Graph bigger3 = new Graph(graphs[3]);
        bigger3.addNode("3");
        bigger3.addEdge("3", "0", 1);
        bigger3.addEdge("0", "3", 1);
        bigger3.addEdge("3", "2", 1);
        bigger3.addEdge("2", "3", 1);
        assertEquals(Set.of("0", "2", "3"), bigger3.clique(3, "2"));

        Graph imitateNumbers = getGraph();
        Set<String> clique = imitateNumbers.clique(3, "19");
        assertEquals(Set.of(), clique);
        clique = imitateNumbers.clique(2, "13");
        assertEquals(Set.of("13", "19"), clique);
    }

    private static Graph getGraph() {
        Graph imitateNumbers = new Graph();
        // nodes=[11, 2, 13, 3, 5, 17, 7, 19]
        imitateNumbers.addNode("2");
        imitateNumbers.addNode("3");
        imitateNumbers.addNode("5");
        imitateNumbers.addNode("7");
        imitateNumbers.addNode("11");
        imitateNumbers.addNode("13");
        imitateNumbers.addNode("17");
        imitateNumbers.addNode("19");
        // edges=[3->11 (1), 13->19 (1), 17->3 (1), 7->3 (1), 19->7 (1), 11->3 (1), 3->17 (1), 3->7 (1), 7->19 (1), 19->13 (1)]
        imitateNumbers.addEdge("3", "7", 1);
        imitateNumbers.addEdge("7", "3", 1);
        imitateNumbers.addEdge("3", "11", 1);
        imitateNumbers.addEdge("11", "3", 1);
        imitateNumbers.addEdge("3", "17", 1);
        imitateNumbers.addEdge("17", "3", 1);
        imitateNumbers.addEdge("7", "19", 1);
        imitateNumbers.addEdge("19", "7", 1);
        imitateNumbers.addEdge("13", "19", 1);
        imitateNumbers.addEdge("19", "13", 1);
        return imitateNumbers;
    }
}