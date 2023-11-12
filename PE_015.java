import UsefulFunctions.Combinations;

import java.math.BigInteger;

public class PE_015 {
    public static void main(String[] args) {
        int n = 15;

        long start1 = System.currentTimeMillis();
        System.out.println(recLatticePaths2(n+1, n+1));
        long end1 = System.currentTimeMillis();
        System.out.println("AEG: " + (end1-start1));

        long start2 = System.currentTimeMillis();
        System.out.println(recLatticePaths(n, n));
        long end2 = System.currentTimeMillis();
        System.out.println("AEG: " + (end2-start2));

        long start3 = System.currentTimeMillis();
        System.out.println(Combinations.nChooseMBigInteger(n*2, n));
        long end3 = System.currentTimeMillis();
        System.out.println("AEG: " + (end3-start3));
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
}
