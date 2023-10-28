import javax.swing.*;
import java.math.BigInteger;

public class PE15 {
    public static void main(String[] args) {
        int n = 50_000;
        /*
        long start = System.currentTimeMillis();
        System.out.println(recLatticePaths2(n+1, n+1));
        long end = System.currentTimeMillis();
        System.out.println("AEG: " + (end-start));
        long start = System.currentTimeMillis();
        System.out.println(recLatticePaths(n, n));
        long end = System.currentTimeMillis();
        System.out.println("AEG: " + (end-start));
         */
        long start = System.currentTimeMillis();
        System.out.println(nChooseM(n*2, n));
        long end = System.currentTimeMillis();
        System.out.println("AEG: " + (end-start));
    }
    private static BigInteger recLatticePaths(int n, int m) {
        n++;
        m++;
        BigInteger[][] solutions = new BigInteger[n+1][m+1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < m+1; j++) {
                solutions[i][j] = BigInteger.valueOf(-1);
            }
        }
        return recLatticePaths(n, m, solutions);
    }
    private static BigInteger recLatticePaths(int n, int m, BigInteger[][] solutions) {
        BigInteger negOne = BigInteger.valueOf(-1);
        if (n == 1 || m == 1) return BigInteger.ONE;
        if (solutions[n][m].compareTo(negOne) != 0) return solutions[n][m];
        if (solutions[n-1][m].compareTo(negOne) == 0) solutions[n-1][m] = recLatticePaths(n-1, m, solutions);
        if (solutions[n][m-1].compareTo(negOne) == 0) solutions[n][m-1] = recLatticePaths(n, m-1, solutions);
        solutions[n][m] = solutions[n-1][m].add(solutions[n][m-1]);
        return solutions[n][m];
    }
    private static BigInteger recLatticePaths2(int n, int m) {
        if (n == 1 || m == 1) return BigInteger.ONE;
        return recLatticePaths2(n-1, m).add(recLatticePaths2(n, m-1));
    }
    private static BigInteger factorial(int n) {
        if (n < 0) return BigInteger.ZERO;
        if (n < 2) return BigInteger.ONE;
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
    private static BigInteger nChooseM(int n, int m) {
        BigInteger factorial1 = factorial(n);
        BigInteger factorial2 = factorial(m);
        BigInteger factorial3 = factorial(n-m);
        return factorial1.divide(factorial2.multiply(factorial3));
    }
}
