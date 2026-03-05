package euler;

import utils.Graph;
import utils.Parser;

public class PE_083 {
    private static int r;
    private static int c;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[][] matrix = Parser.parseManyInts("src/euler/inputs/PE_083_matrix.txt", ",");
        r = matrix.length;
        c = matrix[0].length;
        Graph graph = makeGraph(matrix);
        return String.valueOf(graph.djikstra(-1, (r-1)*c + c-1));
    }

    private static Graph makeGraph(int[][] matrix) {
        Graph graph = new Graph();

        addBody(graph, matrix);
        graph.addNode(-1);
        graph.addEdge(-1, 0, matrix[0][0]);

        return graph;
    }
    private static void addBody(Graph graph, int[][] matrix) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int n = i*c + j;
                graph.addNode(n);

                int weight = matrix[i][j];

                // Edges pointing down and up
                if (i != 0) {
                    int upN = (i-1)*c + j;
                    graph.addEdge(upN, n, weight);
                    graph.addEdge(n, upN, matrix[i-1][j]);
                }

                // Edge pointing right and left
                if (j != 0) {
                    int leftN = i*c + j-1;
                    graph.addEdge(leftN, n, weight);
                    graph.addEdge(n, leftN, matrix[i][j-1]);
                }
            }
        }
    }
}
