package euler;

import utils.Parser;

public class PE_081 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String filename = "src/euler/inputs/PE_081_matrix.txt";
        int[][] matrix = Parser.parseManyInts(filename, ",");
        return String.valueOf(minimumSum(matrix));
    }

    private static int minimumSum(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (i == 0 && j == 0) continue;
                int add;
                if (i == 0) add = m[i][j-1];
                else {
                    add = m[i-1][j];
                    if (j > 0 && m[i][j-1] < add) add = m[i][j-1];
                }
                m[i][j] += add;
            }
        }
        return m[m.length-1][m[0].length-1];
    }
}
