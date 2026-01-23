package euler;

import utils.Graph;
import utils.Parser;

public class PE_083 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[][] matrix = Parser.parseManyInts("src/euler/inputs/PE_083_matrix.txt", ",");
        Graph graph = makeGraph(matrix);
        String endName = (matrix.length-1) + "_" + (matrix[matrix.length-1].length-1);
        return graph.djikstra("start".hashCode(), endName.hashCode());
    }

    private static Graph makeGraph(int[][] matrix) {
        Graph graph = new Graph();

        addBody(graph, matrix);
        graph.addNode("start".hashCode());
        graph.addEdge("start".hashCode(), "0_0".hashCode(), matrix[0][0]);

        return graph;
    }
    private static void addBody(Graph graph, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String name = i + "_" + j;
                graph.addNode(name.hashCode());

                int weight = matrix[i][j];

                // Edges pointing down and up
                if (i != 0) {
                    String upName = (i-1) + "_" + j;
                    graph.addEdge(upName.hashCode(), name.hashCode(), weight);
                    graph.addEdge(name.hashCode(), upName.hashCode(), matrix[i-1][j]);
                }

                // Edge pointing right and left
                if (j != 0) {
                    String leftName = i + "_" + (j-1);
                    graph.addEdge(leftName.hashCode(), name.hashCode(), weight);
                    graph.addEdge(name.hashCode(), leftName.hashCode(), matrix[i][j-1]);
                }
            }
        }
    }
}
