package euler;

import utils.Parser;

public class PE_067 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        String filename = "src/euler/inputs/PE_067_triangle.txt";
        int[][] triangle = Parser.parseManyInts(filename);
        return maximumSum(triangle);
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
