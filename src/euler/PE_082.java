package euler;

import utils.Graph;
import utils.Parser;

public class PE_082 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int[][] matrix = Parser.parseManyInts("src/euler/inputs/PE_082_matrix.txt", ",");
        Graph graph = makeGraph(matrix);
        return graph.djikstra("start".hashCode(), "end".hashCode());
    }

    private static Graph makeGraph(int[][] matrix) {
        Graph graph = new Graph();
        graph.addNode("start".hashCode());
        graph.addNode("end".hashCode());

        addStartAndEnd(graph, matrix);
        addBody(graph, matrix);

        return graph;
    }

    private static void addStartAndEnd(Graph graph, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            String name = i + "_0";
            graph.addNode(name.hashCode());
            graph.addEdge("start".hashCode(), name.hashCode(), matrix[i][0]);
        }

        for (int i = 0; i < matrix.length; i++) {
            String name = i + "_" + (matrix[i].length-1);
            graph.addNode(name.hashCode());
            graph.addEdge(name.hashCode(), "end".hashCode(), 0);
        }
    }
    private static void addBody(Graph graph, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                String name = i + "_" + j;
                graph.addNode(name.hashCode());

                int weight = matrix[i][j];

                // Edges pointing down and up
                if (i != 0) {
                    String upName = (i-1) + "_" + j;
                    graph.addEdge(upName.hashCode(), name.hashCode(), weight);
                    graph.addEdge(name.hashCode(), upName.hashCode(), matrix[i-1][j]);
                }

                // Edge pointing right
                String leftName = i + "_" + (j-1);
                graph.addEdge(leftName.hashCode(), name.hashCode(), weight);
            }
        }
    }
}
