package euler;

import utils.Primes;

import java.util.Arrays;

public class PE_407 {
    private static int[] values;

    void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int limit = 10_000_000;
        return String.valueOf(sum(limit));
    }

    private static long sum(int limit) {
        makeValues(limit+1);
        long sum = 0;
        for (int i = 2; i < values.length; i++) {
            sum += values[i];
        }
        return sum;
    }

    private static void makeValues(int limit) {
        values = new int[limit];
        Arrays.fill(values, 1);

        // 1. Linear Sieve to precompute the smallest prime power (spp)
        // and the 'rest' of the number after dividing by that spp.
        int[] spp = new int[limit];
        int[] rest = new int[limit];
        int[] primes = new int[Primes.upperBoundForPrimeCountBelow(limit)];
        int primeCount = 0;

        for (int i = 2; i < limit; i++) {
            if (spp[i] == 0) {
                spp[i] = i;
                rest[i] = 1;
                primes[primeCount++] = i;
            }
            for (int j = 0; j < primeCount && i * primes[j] < limit; j++) {
                int next = i * primes[j];
                if (i % primes[j] == 0) {
                    spp[next] = spp[i] * primes[j];
                    rest[next] = rest[i];
                    break;
                } else {
                    spp[next] = primes[j];
                    rest[next] = i;
                }
            }
        }

        // 2. Main loop: Solve CRT using precomputed factors
        int[] m = new int[10];   // Stores prime power factors Mi
        long[] c = new long[10]; // Stores CRT constants Ci
        int[] idempotents = new int[1 << 10];

        for (int k = 2; k < limit; k++) {
            int mCount = 0;
            int temp = k;
            while (temp > 1) {
                m[mCount++] = spp[temp];
                temp = rest[temp];
            }

            if (mCount < 2) continue; // Prime powers only have trivial idempotents

            // Precompute CRT constants for this k
            for (int i = 0; i < mCount; i++) {
                int Mi = m[i];
                int Ni = k / Mi;
                c[i] = (long) Ni * modInverse(Ni, Mi) % k;
            }

            // Generate all 2^m idempotents using bitmask/subset sums
            int maxA = 1;
            idempotents[0] = 0;
            for (int i = 0; i < mCount; i++) {
                int bit = 1 << i;
                for (int prev = 0; prev < bit; prev++) {
                    int nextA = (int) ((idempotents[prev] + c[i]) % k);
                    idempotents[bit + prev] = nextA;
                    if (nextA < k && nextA > maxA) {
                        maxA = nextA;
                    }
                }
            }
            values[k] = maxA;
        }
    }

    private static int modInverse(int n, int mod) {
        int m0 = mod, y = 0, x = 1;
        while (n > 1) {
            int q = n / mod;
            int t = mod;
            mod = n % mod;
            n = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        return (x < 0) ? x + m0 : x;
    }
}
