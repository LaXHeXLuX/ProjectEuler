package euler;

import utils.Graph;
import utils.Parser;

import java.util.Set;

public class PE_107 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        String filename = "src/euler/inputs/PE_107_network.txt";
        Graph graph = parser(filename);
        int sum1 = edgeSum(graph);
        Graph mst = graph.mst();
        int sum2 = edgeSum(mst);
        return sum1-sum2;
    }
    
    private static int edgeSum(Graph graph) {
        int sum = 0;
        Set<Graph.Edge> edges = graph.getEdges();
        for (Graph.Edge edge : edges) {
            sum += edge.getWeight();
        }
        return sum / 2;
    }

    private static Graph parser(String filename) {
        String[][] cells = Parser.parseManyStrings(filename, ",");
        int[][] edgeMatrix = new int[cells.length][];
        for (int i = 0; i < cells.length; i++) {
            edgeMatrix[i] = new int[cells[i].length];
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].equals("-")) edgeMatrix[i][j] = -1;
                else edgeMatrix[i][j] = Integer.parseInt(cells[i][j]);
            }
        }
        return new Graph(edgeMatrix);
    }
}
