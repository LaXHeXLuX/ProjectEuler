package utils;

import java.util.Arrays;

public class Primes {
    private static final int[] primesTo100 = {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
            43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
    };
    public static int[] primes(int limit) {
        if (limit < 3) {
            if (limit < 2) return new int[0];
            else return new int[] {2};
        }
        int count = 2;
        boolean[] composites = new boolean[limit >> 1];
        composites[0] = true;
        for (int i = 4; i < composites.length; i+=3) {
            composites[i] = true;
        }
        int l = ((int) Math.sqrt(limit)) >> 1;
        for (int i = 2; i <= l; i+=3) {
            if (!composites[i]) {
                count++;
                int x = (i << 1) | 1;
                for (int j = x*x / 2; j < composites.length; j += x) {
                    composites[j] = true;
                }
            }
            if (!composites[i+1]) {
                count++;
                int x = ((i+1) << 1) | 1;
                for (int j = x*x / 2; j < composites.length; j += x) {
                    composites[j] = true;
                }
            }
        }
        int start = l+1;
        if (l % 3 != 1) start++;
        if (l % 3 == 2) {
            start++;
        }
        int i;
        for (i = start; i < composites.length-1; i+=3) {
            if (!composites[i]) count++;
            if (!composites[i+1]) count++;
        }
        if (i < composites.length && !composites[i]) count++;

        int[] primes = new int[count];
        primes[0] = 2;
        primes[1] = 3;
        int primeIndex = 2;
        for (i = 2; i < composites.length-1; i+=3) {
            if (!composites[i]) {
                primes[primeIndex] = (i << 1) | 1;
                primeIndex++;
            }
            if (!composites[i+1]) {
                primes[primeIndex] = ((i+1) << 1) | 1;
                primeIndex++;
            }
        }
        if (i < composites.length && !composites[i]) primes[primeIndex] = (i << 1) | 1;
        return primes;
    }
    public static int[] primes(boolean[] composites) {
        if (composites.length < 2) return new int[] {2};
        int count = 1;
        for (boolean composite : composites) {
            if (!composite) count++;
        }
        int[] primes = new int[count];
        primes[0] = 2;
        primes[1] = 3;
        int primeIndex = 2;
        int i;
        for (i = 2; i < composites.length-1; i+=3) {
            if (!composites[i]) {
                primes[primeIndex] = (i << 1) | 1;
                primeIndex++;
            }
            if (!composites[i+1]) {
                primes[primeIndex] = ((i+1) << 1) | 1;
                primeIndex++;
            }
        }
        if (i < composites.length && !composites[i]) primes[primeIndex] = (i << 1) | 1;
        return primes;
    }
    public static boolean[] sieve(int limit) {
        if (limit < 2) return new boolean[limit];
        boolean[] primes = new boolean[limit];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 4; i < limit; i+=2) {
            primes[i] = false;
        }
        int iLimit = (int) Math.sqrt(limit);
        for (int i = 3; i <= iLimit; i+=2) {
            if (!primes[i]) continue;
            int k = i*i;
            while (k < limit) {
                primes[k] = false;
                k += i;
            }
        }
        return primes;
    }
    public static boolean[] compositeSieve(int limit) {
        if (limit < 2) return new boolean[] {true};
        boolean[] composites = new boolean[limit >> 1];
        composites[0] = true;
        for (int i = 4; i < composites.length; i+=3) {
            composites[i] = true;
        }
        int l = ((int) Math.sqrt(limit)) >> 1;
        for (int i = 2; i <= l; i+=3) {
            if (!composites[i]) {
                int x = (i << 1) | 1;
                for (int j = (x*x) >> 1; j < composites.length; j += x) {
                    composites[j] = true;
                }
            }
            if (!composites[i+1]) {
                int x = ((i+1) << 1) | 1;
                for (int j = (x*x) >> 1; j < composites.length; j += x) {
                    composites[j] = true;
                }
            }
        }
        return composites;
    }
    public static class PF {
        public long primeFactor;
        public int power;
        public PF(long primeFactor, int power) {
            this.primeFactor = primeFactor;
            this.power = power;
        }
        public PF(long primeFactor) {
            this.primeFactor = primeFactor;
            this.power = 1;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof PF oPF)) return false;
            return primeFactor == oPF.primeFactor && power == oPF.power;
        }
        @Override
        public String toString() {
            return "[" + primeFactor + " ^ " + power + "]";
        }
    }
    public static PF[] primeFactors(long n) {
        if (n < 2) return new PF[0];
        int index = 0;
        PF[] primeFactors = new PF[maxPrimorial(n)];

        long limit = (long) Math.sqrt(n);
        for (int i : primesTo100) {
            if (i > limit) break;
            if (n % i == 0) {
                primeFactors[index] = new PF(i);
                n /= i;
                while (n % i == 0) {
                    primeFactors[index].power++;
                    n /= i;
                }
                index++;
                limit = (long) Math.sqrt(n);
            }
        }

        for (long i = 101; i <= limit; i += 6) {
            if (n % i == 0) {
                primeFactors[index] = new PF(i);
                n /= i;
                while (n % i == 0) {
                    primeFactors[index].power++;
                    n /= i;
                }
                index++;
                limit = (long) Math.sqrt(n);
            }
            if (n % (i+2) == 0) {
                primeFactors[index] = new PF(i+2);
                n /= i+2;
                while (n % (i+2) == 0) {
                    primeFactors[index].power++;
                    n /= i+2;
                }
                index++;
                limit = (long) Math.sqrt(n);
            }
        }

        if (n > 1) {
            primeFactors[index] = new PF(n);
            index++;
        }
        return Arrays.copyOf(primeFactors, index);
    }
    public static PF[] primeFactors(long n, int power) {
        PF[] pfs = primeFactors(n);
        for (PF pf : pfs) {
            pf.power *= power;
        }
        return pfs;
    }
    public static PF[] primeFactorsProduct(long n, int power, long m) {
        PF[] nPfs = primeFactors(n, power);
        PF[] mPfs = primeFactors(m);

        PF[] pfs = new PF[nPfs.length + mPfs.length];
        int ni = 0;
        int mi = 0;
        int i = 0;
        while (ni < nPfs.length && mi < mPfs.length) {
            PF nPf = nPfs[ni];
            PF mPf = mPfs[mi];
            if (nPf.primeFactor == mPf.primeFactor) {
                pfs[i++] = new PF(nPf.primeFactor, nPf.power + mPf.power);
                ni++;
                mi++;
            }
            else if (nPf.primeFactor < mPf.primeFactor) {
                pfs[i++] = new PF(nPf.primeFactor, nPf.power);
                ni++;
            }
            else {
                pfs[i++] = new PF(mPf.primeFactor, mPf.power);
                mi++;
            }
        }
        while (ni < nPfs.length) {
            PF nPf = nPfs[ni++];
            pfs[i++] = new PF(nPf.primeFactor, nPf.power);
        }
        while (mi < mPfs.length) {
            PF mPf = mPfs[mi++];
            pfs[i++] = new PF(mPf.primeFactor, mPf.power);
        }

        return Arrays.copyOf(pfs, i);
    }
    public static PF[][] primeFactorSieve(int limit) {
        int[] spf = new int[limit];
        for (int i = 2; i < limit; i++) {
            if (spf[i] > 0) continue;
            for (int j = i; j < limit; j += i) {
                if (spf[j] == 0) spf[j] = i;
            }
        }

        PF[][] result = new PF[limit][];
        result[0] = new PF[0];
        result[1] = new PF[0];

        for (int i = 2; i < limit; i++) {
            int temp = i;

            int uniqueCount = 0;
            while (temp > 1) {
                int p = spf[temp];
                uniqueCount++;
                while (temp % p == 0) temp /= p;
            }

            result[i] = new PF[uniqueCount];

            temp = i;
            int index = 0;
            while (temp > 1) {
                int p = spf[temp];
                int count = 0;
                while (temp % p == 0) {
                    temp /= p;
                    count++;
                }
                result[i][index] = new PF(p, count);
                index++;
            }
        }

        return result;
    }
    public static int maxPrimorial(long n) {
        if (n <= 2) return 1;
        long prod = 2;
        int i = 1;
        while (prod < n) {
            prod *= primesTo100[i];
            i++;
        }
        return i;
    }
    public static boolean isPrime(int n) {
        if (n < 100) {
            return Arrays.binarySearch(primesTo100, n) >= 0;
        }
        for (int p : primesTo100) {
            if (n % p == 0) return false;
        }
        if (n < 1_000_000) {
            int limit = (int) Math.sqrt(n);
            for (int i = 101; i <= limit; i+=6) {
                if (n % i == 0 || n % (i+2) == 0) return false;
            }
            return true;
        }

        return
                millerRabin(n, 2) &&
                millerRabin(n, 3) &&
                millerRabin(n, 5) &&
                millerRabin(n, 7);
    }
    public static boolean millerRabin(int n, int a) {
        int d = n - 1;
        int r = 0;
        while ((d & 1) == 0) {
            d >>= 1;
            r++;
        }

        long x = Diophantine.powMod(a, d, n);
        if (x == 1 || x == n - 1) return true;
        for (int i = 0; i < r - 1; i++) {
            x = (x*x) % n;
            if (x == n - 1) return true;
        }
        return false;
    }
    public static boolean isPrime(long n) {
        if (n < Integer.MAX_VALUE) return isPrime((int) n);
        for (int p : primesTo100) {
            if (n % p == 0) return false;
        }

        boolean probablePrime =
                millerRabin(n, 2) &&
                millerRabin(n, 3) &&
                millerRabin(n, 5) &&
                millerRabin(n, 7) &&
                millerRabin(n, 11);

        if (!probablePrime) return false;
        if (n < 2_152_302_898_747L) return true;

        return
                millerRabin(n, 13) &&
                millerRabin(n, 17) &&
                millerRabin(n, 19) &&
                millerRabin(n, 23) &&
                millerRabin(n, 29) &&
                millerRabin(n, 31) &&
                millerRabin(n, 37);
    }
    public static boolean millerRabin(long n, int a) {
        long d = n - 1;
        int r = 0;
        while ((d & 1) == 0) {
            d >>= 1;
            r++;
        }

        long x = Diophantine.powModExact(a, d, n);
        if (x == 1 || x == n - 1) return true;
        for (int i = 0; i < r - 1; i++) {
            x = Diophantine.mulModExact(x, x, n);
            if (x == n - 1) return true;
        }
        return false;
    }
    public static int nthPrime(int n) {
        if (n < 1) return -1;
        if (n < 25) return primesTo100[n-1];
        int upperBound = upperBoundForNthPrime(n);
        int[] primes = primes(upperBound+1);
        return primes[n-1];
    }
    public static int upperBoundForNthPrime(int n) {
        if (n < 6) return 12;
        double logN = Math.log(n);
        double logLogN = Math.log(logN);
        if (n < 688_383) return (int) (n * (logN + Math.log(logN)));
        return (int) (n * (logN + logLogN - 1 + (logLogN-2)/logN));
    }
    public static int upperBoundForPrimeCountBelow(int n) {
        double logN = Math.log(n);
        if (n < 355_991) return (int) (1.25506*n/logN);
        return (int) (n/logN * (1 + 1/logN + 2.51/logN/logN));
    }
    public static long eulersTotient(long n) {
        if (n <= 1) return 0;
        return eulersTotient(primeFactors(n));
    }
    public static long eulersTotient(PF[] primeFactors) {
        long totient = 1;
        for (PF pf : primeFactors) {
            totient *= pf.primeFactor-1;
            totient *= Diophantine.pow(pf.primeFactor, pf.power-1);
        }
        return totient;
    }
}
