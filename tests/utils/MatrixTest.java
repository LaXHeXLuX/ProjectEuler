package utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    private static final Integer[][] matrix1_0 = {{0}};
    private static final Integer[][] matrix1_1 = {{1}};
    private static final Integer[][] row_vector2_0 = {{3, 3}};
    private static final Integer[][] row_vector2_1 = {{-1, -2}};
    private static final Integer[][] matrix2_0 = {{1, 2}, {3, 4}};
    private static final Integer[][] matrix2_1 = {{1, 1}, {3, 5}};
    private static final Integer[][] matrix3_0 = {{0, 0, 0}, {1, 1, 1}, {2, 2, 2}};
    private static final Integer[][] matrix3_1 = {{1, 2, 3}, {3, 2, 1}, {1, 0, 1}};
    private static final Matrix<Integer> M1_0 = new Matrix<>(matrix1_0);
    private static final Matrix<Integer> M1_1 = new Matrix<>(matrix1_1);
    private static final Matrix<Integer> RV2_0 = new Matrix<>(row_vector2_0);
    private static final Matrix<Integer> RV2_1 = new Matrix<>(row_vector2_1);
    private static final Matrix<Integer> M2_0 = new Matrix<>(matrix2_0);
    private static final Matrix<Integer> M2_1 = new Matrix<>(matrix2_1);
    private static final Matrix<Integer> M3_0 = new Matrix<>(matrix3_0);
    private static final Matrix<Integer> M3_1 = new Matrix<>(matrix3_1);
    private static final Integer[][] zeroes2 = {{0, 0}, {0, 0}};
    private static final Integer[][] ones2 = {{1, 1}, {1, 1}};
    private static final Integer[][] eye2 = {{1, 0}, {0, 1}};

    @Test
    void createMatrix() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(new Integer[][] {}));
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(new Integer[][] {{}}));
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(new Integer[][] {{1}, {1, 2}}));
        assertDoesNotThrow(() -> new Matrix<>(matrix1_0));
    }
    @Test
    void getMatrix() {
        assertArrayEquals(matrix1_0, M1_0.getGrid());
        assertArrayEquals(matrix1_1, M1_1.getGrid());
        assertArrayEquals(matrix2_0, M2_0.getGrid());
        assertArrayEquals(matrix2_1, M2_1.getGrid());
    }
    @Test
    void constants() {
        assertArrayEquals(zeroes2, M1_0.zeroes(2).getGrid());
        assertArrayEquals(ones2, M1_0.ones(2).getGrid());
        assertArrayEquals(eye2, M1_0.eye(2).getGrid());
    }
    @Test
    void copy() {
        assertArrayEquals(matrix3_0, M3_0.copy().getGrid());
   }
    @Test
    void scale() {
        assertArrayEquals(new Integer[][] {{0, 0}, {0, 0}}, M2_0.scale(0).getGrid());
        assertArrayEquals(matrix2_0, M2_0.scale(1).getGrid());
        assertArrayEquals(new Integer[][] {{2, 4}, {6, 8}}, M2_0.scale(2).getGrid());
    }
    @Test
    void transpose() {
        assertArrayEquals(matrix1_0, M1_0.transpose().getGrid());
        assertArrayEquals(new Integer[][] {{1, 3}, {2, 4}}, M2_0.transpose().getGrid());
    }
    @Test
    void equals() {
        assertTrue(M1_0.equals(new Matrix<>(matrix1_0)));
    }
    @Test
    void add() {
        assertThrows(IllegalArgumentException.class, () -> M1_0.add(M2_0));
        assertThrows(IllegalArgumentException.class, () -> M1_0.add(RV2_0));
        assertArrayEquals(matrix1_1, M1_0.add(M1_1).getGrid());
        assertArrayEquals(new Integer[][] {{2, 3}, {6, 9}}, M2_0.add(M2_1).getGrid());
    }
    @Test
    void subtract() {
        assertThrows(IllegalArgumentException.class, () -> M1_0.subtract(M2_0));
        assertThrows(IllegalArgumentException.class, () -> M1_0.subtract(RV2_0));
        assertArrayEquals(matrix1_1, M1_1.subtract(M1_0).getGrid());
        assertArrayEquals(new Integer[][] {{0, -1}, {0, 1}}, M2_1.subtract(M2_0).getGrid());
    }
    @Test
    void multiply() {
        assertThrows(IllegalArgumentException.class, () -> M1_0.multiply(M2_0));
        assertArrayEquals(matrix1_0, M1_0.multiply(M1_0).getGrid());
        assertArrayEquals(matrix1_0, M1_0.multiply(M1_1).getGrid());
        assertArrayEquals(matrix1_1, M1_1.multiply(M1_1).getGrid());
        assertArrayEquals(new Integer[][] {{7, 11}, {15, 23}}, M2_0.multiply(M2_1).getGrid());
    }
    @Test
    void isSquare() {
        assertTrue(M1_0.isSquare());
        assertTrue(M2_0.isSquare());
        assertFalse(RV2_0.isSquare());
    }
    @Test
    void submatrix() {
        assertThrows(IllegalArgumentException.class, () -> M1_0.submatrix(0, 0));
        assertThrows(IllegalArgumentException.class, () -> M2_0.submatrix(new int[] {}, new int[] {0, 1}));
        assertArrayEquals(new Integer[][] {{4}}, M2_0.submatrix(0, 0).getGrid());
        assertArrayEquals(new Integer[][] {{1}}, M2_0.submatrix(1, 1).getGrid());
    }
    @Test
    void determinant() {
        assertThrows(IllegalArgumentException.class, RV2_1::determinant);
        assertEquals(0, M1_0.determinant());
        assertEquals(1, M1_1.determinant());
        assertEquals(-2, M2_0.determinant());
        assertEquals(0, M3_0.determinant());
        assertEquals(-8, M3_1.determinant());

        Matrix<Integer> eye12 = M1_0.eye(12);
        assertEquals(1, eye12.determinant());
    }
    private void assertFractionArrayEquals(int[][] intArr, Fraction<Integer>[] fractions) {
        assertEquals(intArr.length, fractions.length);
        for (int i = 0; i < intArr.length; i++) {
            assertEquals(intArr[i][0], fractions[i].num);
            if (intArr[i].length > 1) assertEquals(intArr[i][1], fractions[i].den);
        }
    }
    @Test
    void solve() {
        Integer[][] e1 = {{1, 1}};
        Integer[] a1 = {1};
        assertThrows(IllegalArgumentException.class, () -> Matrix.solve(new Matrix<>(e1), a1));
        Integer[][] e2 = {{1}};
        Integer[] a2 = {1, 1};
        assertThrows(IllegalArgumentException.class, () -> Matrix.solve(new Matrix<>(e2), a2));
        Integer[][] equation = {{1}};
        Integer[] answers = {1};
        Fraction<Integer>[] result = Matrix.solve(new Matrix<>(equation), answers);
        assertFractionArrayEquals(new int[][] {{1}}, result);
        equation = new Integer[][] {{2}};
        answers = new Integer[] {4};
        result = Matrix.solve(new Matrix<>(equation), answers);
        assertFractionArrayEquals(new int[][] {{2}}, result);
        equation = new Integer[][] {{6}};
        answers = new Integer[] {2};
        result = Matrix.solve(new Matrix<>(equation), answers);
        assertFractionArrayEquals(new int[][] {{1, 3}}, result);
        equation = new Integer[][] {{1, 1}, {2, -1}};
        answers = new Integer[] {3, 3};
        result = Matrix.solve(new Matrix<>(equation), answers);
        assertFractionArrayEquals(new int[][] {{2}, {1}}, result);
        equation = new Integer[][] {{1, 1, 1}, {2, -3, 0}, {-1, 4, -1}};
        answers = new Integer[] {7, 6, 2};
        result = Matrix.solve(new Matrix<>(equation), answers);
        assertFractionArrayEquals(new int[][] {{57, 10}, {9, 5}, {-1, 2}}, result);

        List<Integer> a1List = List.of(1);
        assertThrows(IllegalArgumentException.class, () -> Matrix.solve(new Matrix<>(e1), a1List));
        List<Integer> a2List = List.of(1, 1);
        assertThrows(IllegalArgumentException.class, () -> Matrix.solve(new Matrix<>(e2), a2List));
        List<Integer> answersList = List.of(7, 6, 2);
        result = Matrix.solve(new Matrix<>(equation), answersList);
        assertFractionArrayEquals(new int[][] {{57, 10}, {9, 5}, {-1, 2}}, result);
    }
    @Test
    void toStringTest() {
        assertEquals("| 0 |", new Matrix<>(matrix1_0).toString());
        assertEquals("| 1 2 |\n| 3 4 |", new Matrix<>(matrix2_0).toString());
    }
}