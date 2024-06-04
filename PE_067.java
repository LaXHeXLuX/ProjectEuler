import java.io.IOException;

public class PE_067 {
    public static void main(String[] args) throws IOException {
        String filename = "PE_067_triangle.txt";
        int[][] triangle = Parser.parseManyInts(filename);

        System.out.println(maximumSum(triangle));
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
