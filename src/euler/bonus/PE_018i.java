package euler.bonus;

import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_018i {
    private static final long[][] matrix = {
            {0, 0, -4},
            {1, 0, 3},
            {0, 1, 0}
    };
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        boolean[] primes = Primes.sieveOfPrimes(100_000_000);
        System.out.println("sieve done");
        long sum = 0;
        for (int p = 100; p < 1000; p++) {
            if (!primes[p]) continue;
            long prod = productMod(p);
            if (prod != 0) System.out.println(p + ": " + prod);
            else {
                List<Integer> zeroes = zeroes(p);
                System.out.println("\t" + p + ": " + zeroes(p));
                if (zeroes.isEmpty()) throw new RuntimeException("zeroes is empty for " + p);
                if (zeroes.size() > 1 && sum(zeroes) != p) System.out.println("\t^^^^^^^^^^^^^^^^");
            }
            sum += prod;
        }
        return sum;
    }

    private static int sum(List<Integer> l) {
        int sum = 0;
        for (Integer i : l) sum += i;
        return sum;
    }

    private static List<Integer> zeroes(int p) {
        List<Integer> zeroes = new ArrayList<>();
        for (int x = 0; x < p; x++) {
            long equation = equation(x, p);
            if (equation == 0) zeroes.add(x);
        }
        return zeroes;
    }

    private static long productMod(int p) {
        long product = 1;
        for (int x = 0; x < p; x++) {
            long equation = equation(x, p);
            if (equation == 0) return 0;
            product = (product * equation) % p;
        }
        return product;
    }

    private static long equation(int x, int mod) {
        long x2 = ((long) x * x) % mod;
        long x3 = (x2 * x) % mod;
        long res = (x3 - 3L*x + 4) % mod;
        if (res < 0) res += mod;
        return res;
    }

    private static long[][] powMod(long[][] m, int pow, long mod) {
        if (pow == 1) return m;
        long[][] square = squareMod(powMod(m, pow/2, mod), mod);
        if (pow % 2 == 0) {
            return square;
        }
        else {
            return new long[][] {
                    {(m[0][0]*square[0][0] + m[0][1]*square[1][0] + m[0][2]*square[2][0]) % mod, (m[0][0]*square[0][1] + m[0][1]*square[1][1] + m[0][2]*square[2][1]) % mod, (m[0][0]*square[0][2] + m[0][1]*square[1][2] + m[0][2]*m[2][2]) % mod},
                    {(m[1][0]*square[0][0] + m[1][1]*square[1][0] + m[1][2]*square[2][0]) % mod, (m[1][0]*square[0][1] + m[1][1]*square[1][1] + m[1][2]*square[2][1]) % mod, (m[1][0]*square[0][2] + m[1][1]*square[1][2] + m[1][2]*m[2][2]) % mod},
                    {(m[2][0]*square[0][0] + m[2][1]*square[1][0] + m[2][2]*square[2][0]) % mod, (m[2][0]*square[0][1] + m[2][1]*square[1][1] + m[2][2]*square[2][1]) % mod, (m[2][0]*square[0][2] + m[2][1]*square[1][2] + m[2][2]*m[2][2]) % mod}
            };
        }
    }

    private static long[][] squareMod(long[][] m, long mod) {
        return new long[][] {
                {(m[0][0]*m[0][0] + m[0][1]*m[1][0] + m[0][2]*m[2][0]) % mod, (m[0][0]*m[0][1] + m[0][1]*m[1][1] + m[0][2]*m[2][1]) % mod, (m[0][0]*m[0][2] + m[0][1]*m[1][2] + m[0][2]*m[2][2]) % mod},
                {(m[1][0]*m[0][0] + m[1][1]*m[1][0] + m[1][2]*m[2][0]) % mod, (m[1][0]*m[0][1] + m[1][1]*m[1][1] + m[1][2]*m[2][1]) % mod, (m[1][0]*m[0][2] + m[1][1]*m[1][2] + m[1][2]*m[2][2]) % mod},
                {(m[2][0]*m[0][0] + m[2][1]*m[1][0] + m[2][2]*m[2][0]) % mod, (m[2][0]*m[0][1] + m[2][1]*m[1][1] + m[2][2]*m[2][1]) % mod, (m[2][0]*m[0][2] + m[2][1]*m[1][2] + m[2][2]*m[2][2]) % mod}
        };
    }
}
