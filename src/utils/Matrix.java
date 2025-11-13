package utils;

import java.util.Arrays;
import java.util.List;

public class Matrix<T> {
    private final T[][] grid;
    private final int n;
    private final int m;
    private final Arithmetics.Arithmetic<T> op;

    private void validate(T[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Can't generate empty grid");
        }
        int length = matrix[0].length;
        for (T[] row : matrix) {
            if (row.length == 0) {
                throw new IllegalArgumentException("Can't generate grid with empty rows");
            }
            if (row.length != length) {
                throw new IllegalArgumentException("Can't generate grid with varying length rows");
            }
        }
    }
    private T[][] copyOf(T[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        T[][] matrixCopy = Arrays.copyOf(matrix, matrix.length);
        for (int i = 0; i < rows; i++) {
            matrixCopy[i] = Arrays.copyOf(matrix[i], cols);
        }
        return matrixCopy;
    }
    public Matrix(T[][] grid) {
        validate(grid);
        this.op = Arithmetics.of(grid[0][0].getClass());
        this.grid = copyOf(grid);
        this.n = grid.length;
        this.m = grid[0].length;
    }

    public T[][] getGrid() {
        return copyOf(this.grid);
    }
    @SuppressWarnings("unchecked")
    private static <T> T[][] blank(int n, int m) {
        return (T[][]) new Object[n][m];
    }
    private static <T> T[][] blank(int n, int m, T el) {
        T[][] empty = blank(n, m);
        for (int i = 0; i < n; i++) {
            Arrays.fill(empty[i], el);
        }
        return empty;
    }
    public Matrix<T> zeroes(int n) {
        return zeroes(n, n);
    }
    public Matrix<T> zeroes(int n, int m) {
        return new Matrix<>(blank(n, m, op.zero()));
    }
    public Matrix<T> ones(int n) {
        return ones(n, n);
    }
    public Matrix<T> ones(int n, int m) {
        return new Matrix<>(blank(n, m, op.one()));
    }
    public Matrix<T> eye(int n) {
        T[][] matrix = blank(n, n, op.zero());
        for (int i = 0; i < n; i++) {
            matrix[i][i] = op.one();
        }
        return new Matrix<>(matrix);
    }
    public Matrix<T> copy() {
        return new Matrix<>(this.getGrid());
    }
    public Matrix<T> scale(T scalar) {
        T[][] matrix = blank(this.n, this.m);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = op.mul(this.grid[i][j], scalar);
            }
        }
        return new Matrix<>(matrix);
    }
    public Matrix<T> transpose() {
        T[][] matrix = blank(this.grid[0].length, this.grid.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = this.grid[j][i];
            }
        }
        return new Matrix<>(matrix);
    }
    public boolean equals(Matrix<T> matrix) {
        return Arrays.deepEquals(this.grid, matrix.grid);
    }
    public boolean isSquare() {
        return this.n == this.m;
    }
    public Matrix<T> submatrix(int row, int col) {
        return this.submatrix(new int[] {row}, new int[] {col});
    }
    public Matrix<T> submatrix(int[] rows, int[] cols) {
        if (this.n - rows.length <= 0 || this.m - cols.length <= 0) {
            throw new IllegalArgumentException("Can't make an empty matrix submatrix");
        }
        T[][] matrix = blank(this.n - rows.length, this.m - cols.length);
        int rowsIndex = 0;
        for (int i = 0; i < this.n; i++) {
            if (rowsIndex < rows.length && rows[rowsIndex] == i) {
                rowsIndex++;
                continue;
            }
            int colsIndex = 0;
            for (int j = 0; j < this.m; j++) {
                if (colsIndex < cols.length && cols[colsIndex] == j) {
                    colsIndex++;
                    continue;
                }
                matrix[i-rowsIndex][j-colsIndex] = this.grid[i][j];
            }
        }
        return new Matrix<>(matrix);
    }
    private T[][] bareiss() {
        T[][] grid = copyOf(this.grid);
        T prevPivot = op.one();
        for (int k = 0; k < this.n-1; k++) {
            T pivot = grid[k][k];
            if (pivot == op.zero()) {
                int swapRow = -1;
                for (int i = k+1; i < this.n; i++) {
                    if (grid[i][k] != op.zero()) {
                        swapRow = i;
                        break;
                    }
                }
                if (swapRow == -1) return blank(0, 0);

                T[] tmp = grid[k];
                grid[k] = grid[swapRow];
                grid[swapRow] = tmp;
            }
            for (int i = k+1; i < this.n; i++) {
                for (int j = k+1; j < this.m; j++) {
                    T numerator = op.sub(op.mul(grid[k][k], grid[i][j]), op.mul(grid[i][k], grid[k][j]));
                    grid[i][j] = op.div(numerator, prevPivot);
                }
            }
            prevPivot = pivot;
        }
        return grid;
    }
    public T determinant() {
        if (!this.isSquare())
            throw new IllegalArgumentException("Can't compute the determinant of a non-square matrix");
        if (this.n == 1) return this.grid[0][0];
        T[][] bareiss = bareiss();
        if (bareiss.length == 0) return op.zero();
        return bareiss[this.n-1][this.n-1];
    }
    @SuppressWarnings("unchecked")
    private static <T> Fraction<T>[] solveHelper(Matrix<T> A, T[][] grid) {
        T[][] bareiss = new Matrix<>(grid).bareiss();
        Fraction<T>[] result = (Fraction<T>[]) new Fraction[A.n];
        for (int i = A.n - 1; i >= 0; i--) {
            Fraction<T> sum = new Fraction<>(bareiss[i][A.n]);
            for (int j = i + 1; j < A.n; j++) {
                Fraction<T> toSubtract = new Fraction<>(bareiss[i][j]).multiply(result[j]);
                sum = sum.subtract(toSubtract);
            }
            result[i] = sum.divide(new Fraction<>(bareiss[i][i])).simplify();
        }
        return result;
    }
    public static <T> Fraction<T>[] solve(Matrix<T> A, T[] b) {
        if (!A.isSquare() || b.length != A.n) {
            throw new IllegalArgumentException("Matrix A must be square and match vector length");
        }
        T[][] grid = blank(A.n, A.n+1);
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(A.grid[i], 0, grid[i], 0, A.m);
            grid[i][A.n] = b[i];
        }
        return solveHelper(A, grid);
    }
    public static <T> Fraction<T>[] solve(Matrix<T> A, List<T> b) {
        if (!A.isSquare() || b.size() != A.n) {
            throw new IllegalArgumentException("Matrix A must be square and match vector length");
        }
        T[][] grid = blank(A.n, A.n+1);
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(A.grid[i], 0, grid[i], 0, A.m);
            grid[i][A.n] = b.get(i);
        }
        return solveHelper(A, grid);
    }
    public Matrix<T> add(Matrix<T> matrix) {
        if (this.n != matrix.n || this.m != matrix.m)
            throw new IllegalArgumentException("Matrices must be the same size!");
        T[][] grid = blank(this.n, this.m);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                grid[i][j] = op.add(this.grid[i][j], matrix.grid[i][j]);
            }
        }
        return new Matrix<>(grid);
    }
    public Matrix<T> subtract(Matrix<T> matrix) {
        if (this.n != matrix.n || this.m != matrix.m)
            throw new IllegalArgumentException("Matrices must be the same size!");
        T[][] grid = blank(this.n, this.m);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                grid[i][j] = op.sub(this.grid[i][j], matrix.grid[i][j]);
            }
        }
        return new Matrix<>(grid);
    }
    public Matrix<T> multiply(Matrix<T> matrix) {
        if (this.m != matrix.n)
            throw new IllegalArgumentException("Can't multiply with matrix dimensions");

        T[][] result = blank(this.n, matrix.m);

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < matrix.m; j++) {
                T sum = op.zero();
                for (int k = 0; k < this.m; k++) {
                    sum = op.add(sum, op.mul(this.grid[i][k], matrix.grid[k][j]));
                }
                result[i][j] = sum;
            }
        }

        return new Matrix<>(result);
    }
//    abstract Matrix<T> divide(Matrix<T> grid);
//    abstract int rank();
//    abstract Matrix<T> inverse();

    @Override
    public String toString() {
        int[] widths = new int[m];
        for (T[] row : this.grid) {
            for (int i = 0; i < this.m; i++) {
                int len = String.valueOf(row[i]).length();
                if (len > widths[i]) widths[i] = len;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.n; i++) {
            sb.append("| ");
            for (int j = 0; j < this.m; j++) {
                sb.append(String.format("%" + widths[j] + "s", this.grid[i][j]));
                if (j < this.m - 1) sb.append(" ");
            }
            sb.append(" |");
            if (i < this.n - 1) sb.append("\n");
        }
        return sb.toString();
    }
}
