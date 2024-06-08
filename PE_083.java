import java.io.IOException;
import java.util.Arrays;

public class PE_083 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        int[][] matrix = Parser.parseManyInts("PE_083_matrix.txt", ",");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        Graph graph = makeGraph(matrix);
        String endName = STR."\{matrix.length-1}_\{matrix[matrix.length-1].length-1}";
        System.out.println(graph.djikstra("start", endName));

        long end = System.currentTimeMillis();
        System.out.println(STR."Time: \{end - start} ms");
    }

    private static Graph makeGraph(int[][] matrix) {
        Graph graph = new Graph();

        addBody(graph, matrix);
        graph.addNode("start");
        graph.addEdge("start", "0_0", matrix[0][0]);

        return graph;
    }
    private static void addBody(Graph graph, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String name = STR."\{i}_\{j}";
                graph.addNode(name);

                int weight = matrix[i][j];

                // Edges pointing down and up
                if (i != 0) {
                    String upName = STR."\{i-1}_\{j}";
                    graph.addEdge(upName, name, weight);
                    graph.addEdge(name, upName, matrix[i-1][j]);
                }

                // Edge pointing right and left
                if (j != 0) {
                    String leftName = STR."\{i}_\{j-1}";
                    graph.addEdge(leftName, name, weight);
                    graph.addEdge(name, leftName, matrix[i][j-1]);
                }
            }
        }
    }
}
