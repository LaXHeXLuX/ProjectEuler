package euler;

import utils.Primes;

import java.util.Arrays;

public class PE_347 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int N = 10_000_000;
        long result = S(N);
        return String.valueOf(result);
    }

    private static long S(int N) {
        int[] primes = Primes.primes(N/2);
        long sum = 0;
        boolean[] found = new boolean[N+1];
        for (int i = 0; i < primes.length; i++) {
            if ((long) primes[i] * primes[i] >= N) break;
            int jLimit = Arrays.binarySearch(primes, N / primes[i]) + 1;
            if (jLimit < 0) jLimit = -jLimit;
            for (int j = i+1; j < jLimit; j++) {

                int result = M(primes[i], primes[j], N);
                if (found[result]) continue;
                found[result] = true;
                sum += result;
            }
        }
        return sum;
    }

    private static int M(int p, int q, int N) {
        int maxProduct = p * q;
        long prod = (long) p * q;
        while (prod <= N) {
            long prod2 = prod;
            while (prod2 <= N) {
                if (prod2 > maxProduct) maxProduct = Math.toIntExact(prod2);
                prod2 *= q;
            }
            prod *= p;
        }
        return maxProduct;
    }
}
