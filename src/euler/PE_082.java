package euler;

import utils.Graph;
import utils.Parser;

public class PE_082 {
    private static final int S = -5;
    private static final int E = -3;
    private static int r;
    private static int c;
    
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[][] matrix = Parser.parseManyInts("src/euler/inputs/PE_082_matrix.txt", ",");
        r = matrix.length;
        c = matrix[0].length;
        Graph graph = makeGraph(matrix);
        return String.valueOf(graph.djikstra(S, E));
    }

    private static Graph makeGraph(int[][] matrix) {
        Graph graph = new Graph();
        graph.addNode(S);
        graph.addNode(E);

        addStartAndEnd(graph, matrix);
        addBody(graph, matrix);

        return graph;
    }

    private static void addStartAndEnd(Graph graph, int[][] matrix) {
        for (int i = 0; i < r; i++) {
            int n1 = i*c;
            graph.addNode(n1);
            graph.addEdge(S, n1, matrix[i][0]);
        }

        for (int i = 0; i < r; i++) {
            int n1 = i*c + r-1;
            graph.addNode(n1);
            graph.addEdge(n1, E, 0);
        }
    }
    private static void addBody(Graph graph, int[][] matrix) {
        for (int i = 0; i < r; i++) {
            for (int j = 1; j < c; j++) {
                int n = i*c + j;
                graph.addNode(n);

                int weight = matrix[i][j];

                // Edges pointing down and up
                if (i != 0) {
                    int upN = (i-1)*c + j;
                    graph.addEdge(upN, n, weight);
                    graph.addEdge(n, upN, matrix[i-1][j]);
                }

                // Edge pointing right
                int leftN = i*c + j-1;
                graph.addEdge(leftN, n, weight);
            }
        }
    }
}
