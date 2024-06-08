import java.io.IOException;
import java.util.Arrays;

public class PE_082 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        int[][] matrix = Parser.parseManyInts("PE_082_matrix.txt", ",");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        Graph graph = makeGraph(matrix);

        System.out.println(graph.djikstra("start", "end"));

        long end = System.currentTimeMillis();
        System.out.println(STR."Time: \{end - start} ms");
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
            String name = STR."\{i}_0";
            graph.addNode(name);
            graph.addEdge("start", name, matrix[i][0]);
        }

        for (int i = 0; i < matrix.length; i++) {
            String name = STR."\{i}_\{matrix[i].length-1}";
            graph.addNode(name);
            graph.addEdge(name, "end", 0);
        }
    }
    private static void addBody(Graph graph, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                String name = STR."\{i}_\{j}";
                graph.addNode(name);

                int weight = matrix[i][j];

                // Edges pointing down and up
                if (i != 0) {
                    String upName = STR."\{i-1}_\{j}";
                    graph.addEdge(upName, name, weight);
                    graph.addEdge(name, upName, matrix[i-1][j]);
                }

                // Edge pointing right
                String leftName = STR."\{i}_\{j-1}";
                graph.addEdge(leftName, name, weight);
            }
        }
    }
}
