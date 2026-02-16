package euler;

import utils.Parser;

public class PE_081 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        String filename = "src/euler/inputs/PE_081_matrix.txt";
        int[][] matrix = parser(filename);
        return minimumSum(matrix);
    }

    private static int[][] parser(String filename) {
        int[][] rows = Parser.parseManyInts(filename, ",");

        int[][] ints = new int[rows.length + rows[0].length - 1][];
        for (int i = 0; i < rows.length; i++) {
            int[] rowInts = new int[Math.min(1 + i, rows[0].length)];
            for (int j = 0; j < rowInts.length; j++) {
                rowInts[j] = rows[i - j][j];
            }

            ints[i] = rowInts;
        }
        for (int i = rows.length; i < rows.length + rows[0].length - 1; i++) {
            int startingRow = rows.length - 1;
            int startingColumn = i - rows.length + 1;

            int[] rowInts = new int[Math.min(rows.length, rows[0].length - startingColumn)];
            for (int j = 0; j < rowInts.length; j++) {
                rowInts[j] = rows[startingRow - j][startingColumn + j];
            }

            ints[i] = rowInts;
        }

        return ints;
    }

    private static int minimumSum(int[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];
        int[][] newMatrix = new int[matrix.length-1][];
        System.arraycopy(matrix, 0, newMatrix, 0, newMatrix.length);
        for (int i = 0; i < newMatrix[newMatrix.length-1].length; i++) {
            int adder = 1;
            if (matrix[matrix.length-1].length <= matrix[matrix.length-2].length) adder = -1;

            if (i >= matrix[newMatrix.length].length)
                newMatrix[newMatrix.length-1][i] += matrix[newMatrix.length][i+adder];
            else if (i == 0 && adder == -1)
                newMatrix[newMatrix.length-1][i] += matrix[newMatrix.length][i];
            else
                newMatrix[newMatrix.length-1][i] += Math.min(matrix[newMatrix.length][i], matrix[newMatrix.length][i+adder]);

        }
        return minimumSum(newMatrix);
    }
}
