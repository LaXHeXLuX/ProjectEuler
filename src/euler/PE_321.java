package euler;

import utils.Diophantine;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PE_321 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 40;
        List<BigInteger> solutions = atLeastNFirstTerms(n);
        return sumOfFirst(solutions, n);
    }

    private static long sumOfFirst(List<BigInteger> list, int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += list.get(i).longValueExact();
        }
        return sum;
    }

    private static List<BigInteger> atLeastNFirstTerms(int n) {
        int D = 8;
        BigInteger bigD = BigInteger.valueOf(D);
        long[] baseLong = Diophantine.pell(D);
        BigInteger[] base = {BigInteger.valueOf(baseLong[0]), BigInteger.valueOf(baseLong[1])};
        List<long[]> fundamentalsLong = Diophantine.pell(D, -7);
        List<BigInteger[]> fundamentals = new ArrayList<>();
        for (long[] longs : fundamentalsLong) {
            fundamentals.add(new BigInteger[] {BigInteger.valueOf(longs[0]), BigInteger.valueOf(longs[1])});
        }
        List<BigInteger> solutions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < fundamentals.size(); j++) {
                BigInteger[] f = fundamentals.get(j);
                if (f[1].compareTo(BigInteger.ONE) > 0) solutions.add(f[1].subtract(BigInteger.ONE));
                f = new BigInteger[] {
                        base[0].multiply(f[0]).add(bigD.multiply(base[1]).multiply(f[1])),
                        base[0].multiply(f[1]).add(base[1].multiply(f[0]))
                };
                fundamentals.set(j, f);
            }
        }
        solutions.sort(BigInteger::compareTo);
        return solutions;
    }
}
