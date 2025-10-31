package src.euler;

import util.Graph;
import util.Parser;

public class PE_082 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[][] matrix = Parser.parseManyInts("src/euler/inputs/PE_082_matrix.txt", ",");
        Graph graph = makeGraph(matrix);
        return graph.djikstra("start", "end");
    }

    private static Graph makeGraph(int[][] matrix) {
        Graph graph = new Graph();
        graph.addNode("start");
        graph.addNode("end");

        addStartAndEnd(graph, matrix);
        addBody(graph, matrix);

        return graph;
    }

    private static void addStartAndEnd(Graph graph, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            String name = i + "_0";
            graph.addNode(name);
            graph.addEdge("start", name, matrix[i][0]);
        }

        for (int i = 0; i < matrix.length; i++) {
            String name = i + "_" + (matrix[i].length-1);
            graph.addNode(name);
            graph.addEdge(name, "end", 0);
        }
    }
    private static void addBody(Graph graph, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                String name = i + "_" + j;
                graph.addNode(name);

                int weight = matrix[i][j];

                // Edges pointing down and up
                if (i != 0) {
                    String upName = (i-1) + "_" + j;
                    graph.addEdge(upName, name, weight);
                    graph.addEdge(name, upName, matrix[i-1][j]);
                }

                // Edge pointing right
                String leftName = i + "_" + (j-1);
                graph.addEdge(leftName, name, weight);
            }
        }
    }
}
