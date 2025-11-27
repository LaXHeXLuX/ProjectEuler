package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Primes {
    private static final int[] primesTo100 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
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
        List<PF> primeFactors = new ArrayList<>();

        long limit = (long) Math.sqrt(n);
        for (int i : primesTo100) {
            if (i > limit) break;
            if (n % i == 0) {
                primeFactors.add(new PF(i, 1));
                n /= i;
                while (n % i == 0) {
                    primeFactors.getLast().power++;
                    n /= i;
                }
                limit = (long) Math.sqrt(n);
            }
        }

        for (long i = 101; i <= limit; i += 6) {
            if (n % i == 0) {
                primeFactors.add(new PF(i, 1));
                n /= i;
                while (n % i == 0) {
                    primeFactors.getLast().power++;
                    n /= i;
                }
                limit = (long) Math.sqrt(n);
            }
            if (n % (i+2) == 0) {
                primeFactors.add(new PF(i+2, 1));
                n /= i+2;
                while (n % (i+2) == 0) {
                    primeFactors.getLast().power++;
                    n /= i+2;
                }
                limit = (long) Math.sqrt(n);
            }
        }

        if (n > 1) primeFactors.add(new PF(n, 1));

        return Converter.listToArr(primeFactors);
    }
    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n < 100) return Arrays.binarySearch(primesTo100, (int) n) > 0;

        long limit = (long) Math.sqrt(n);
        for (int p : primesTo100) {
            if (n % p == 0) return false;
        }
        for (long i = 101; i <= limit; i+=6) {
            if (n % i == 0 || n % (i+2) == 0) return false;
        }

        return true;
    }
    public static int nthPrime(int n) {
        if (n < 1) return -1;
        int upperBound = upperBoundForNthPrime(n);
        int[] primes = primes(upperBound+1);
        return primes[n-1];
    }
    public static int upperBoundForNthPrime(int n) {
        if (n < 6) return 12;
        double logN = Math.log(n);
        return (int) (n*logN + n*Math.log(logN));
    }
    public static long eulersTotient(long n) {
        if (n == 1) return 0;
        PF[] primesFactors = primeFactors(n);
        return eulersTotient(primesFactors);
    }
    public static long eulersTotient(PF[] primeFactors) {
        long totient = 1;
        for (PF pf : primeFactors) {
            totient *= pf.primeFactor-1;
            for (int i = 1; i < pf.power; i++) {
                totient *= pf.primeFactor;
            }
        }
        return totient;
    }
}
