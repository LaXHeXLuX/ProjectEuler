package euler;

import utils.Fraction;
import utils.Matrix;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PE_101 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int[] polynome = {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
        BigInteger[] bigPolynome = bigArr(polynome);
        return sumOfFits(bigPolynome).longValue();
    }

    private static BigInteger[] bigArr(int[] arr) {
        BigInteger[] bigArr = new BigInteger[arr.length];
        for (int i = 0; i < bigArr.length; i++) {
            bigArr[i] = BigInteger.valueOf(arr[i]);
        }
        return bigArr;
    }

    private static BigInteger sumOfFits(BigInteger[] polynome) {
        List<BigInteger> results = new ArrayList<>();
        for (int i = 1; i < polynome.length; i++) {
            results.add(polynomeResult(polynome, i));
        }
        Fraction<BigInteger> sum = new Fraction<>(BigInteger.ZERO);
        for (int i = 1; i < polynome.length; i++) {
            Fraction<BigInteger>[] poly = predictPolynome(results.subList(0, i));
            Fraction<BigInteger> next = polynomeResult(poly, i+1);
            sum = sum.add(next);
        }
        sum = sum.simplify();
        if (!sum.den.equals(BigInteger.ONE)) throw new RuntimeException("Bad sum: " + sum);
        return sum.num;
    }

    private static BigInteger[][] matrix(int n) {
        BigInteger[][] matrix = new BigInteger[n][n];
        for (int i = 0; i < n; i++) {
            BigInteger value = BigInteger.ONE;
            for (int j = 0; j < n; j++) {
                matrix[i][j] = value;
                value = value.multiply(BigInteger.valueOf(i+1));
            }
        }
        return matrix;
    }

    private static BigInteger polynomeResult(BigInteger[] polynome, int x) {
        BigInteger bigX = BigInteger.valueOf(x);
        BigInteger answer = polynome[0];
        BigInteger xPow = bigX;
        for (int i = 1; i < polynome.length; i++) {
            answer = answer.add(xPow.multiply(polynome[i]));
            xPow = xPow.multiply(bigX);
        }
        return answer;
    }

    private static Fraction<BigInteger> polynomeResult(Fraction<BigInteger>[] polynome, int x) {
        BigInteger bigX = BigInteger.valueOf(x);
        Fraction<BigInteger> answer = polynome[0];
        Fraction<BigInteger> xf = new Fraction<>(bigX);
        Fraction<BigInteger> xPow = new Fraction<>(xf.num, xf.den);
        for (int i = 1; i < polynome.length; i++) {
            answer = answer.add(xPow.multiply(polynome[i]));
            xPow = xPow.multiply(xf);
        }
        return answer;
    }

    private static Fraction<BigInteger>[] predictPolynome(List<BigInteger> results) {
        BigInteger[][] matrix = matrix(results.size());
        Matrix<BigInteger> m = new Matrix<>(matrix);
        return Matrix.solve(m, results);
    }
}
