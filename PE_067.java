import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PE_067 {
    public static void main(String[] args) throws IOException {
        String filename = "PE_067_triangle.txt";
        int[][] triangle = parser(filename);

        System.out.println(maximumSum(triangle));
    }

    private static int[][] parser(String filename) throws IOException {
        List<String> rows = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();

        while (line != null) {
            rows.add(line);
            line = br.readLine();
        }

        int[][] triangle = new int[rows.size()][];
        for (int i = 0; i < triangle.length; i++) {
            String[] rowElements = rows.get(i).split(" ");
            triangle[i] = new int[rowElements.length];
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] = Integer.parseInt(rowElements[j]);
            }
        }

        return triangle;
    }

    private static int maximumSum(int[][] triangle) {
        if (triangle.length == 1) return triangle[0][0];
        int[][] newTriangle = new int[triangle.length-1][];
        System.arraycopy(triangle, 0, newTriangle, 0, newTriangle.length);
        for (int i = 0; i < newTriangle[newTriangle.length-1].length; i++) {
            newTriangle[newTriangle.length-1][i] += Math.max(triangle[newTriangle.length][i], triangle[newTriangle.length][i+1]);
        }
        return maximumSum(newTriangle);
    }
}
