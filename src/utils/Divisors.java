package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Divisors {
    public static long[] divisors(long n) {
        if (n < 1) throw new IllegalArgumentException("n must be positive!");
        Primes.PF[] pfs = Primes.primeFactors(n);
        return divisors(pfs);
    }
    public static long[] divisors(long n, int power) {
        if (n < 1 || power < 0) throw new IllegalArgumentException("n must be positive and power must be non-negative!");
        Primes.PF[] pfs = Primes.primeFactors(n, power);
        return divisors(pfs);
    }
    public static long[] divisorsFactor(long n, int power, long factor) {
        if (n < 1 || factor < 1 || power < 0) throw new IllegalArgumentException("n and factor must be positive and power must be non-negative!");
        Primes.PF[] pfs = Primes.primeFactorsProduct(n, power, factor);
        return divisors(pfs);
    }
    public static long[] divisors(Primes.PF[] pfs) {
        long[] divisors = {1};

        for (Primes.PF pf : pfs) {
            long[] powers = new long[pf.power + 1];
            powers[0] = 1;
            for (int i = 1; i <= pf.power; i++) powers[i] = powers[i-1] * pf.primeFactor;

            long[] nextDivisors = new long[divisors.length * (pf.power + 1)];
            int i = 0;
            for (long d : divisors)
                for (long p : powers)
                    nextDivisors[i++] = d*p;

            divisors = nextDivisors;
        }

        Arrays.sort(divisors);
        return divisors;
    }
    public static long sumOfDivisors(long n) {
        if (n < 1) throw new IllegalArgumentException("n must be positive and power must be non-negative!");
        if (n == 1) return 0;

        long sum = -n;
        int two = 0;
        while (n % 2 == 0) {
            two++;
            n /= 2;
        }
        int three = 0;
        while (n % 3 == 0) {
            three++;
            n /= 3;
        }

        List<Long> divisorsRec = divisorsRec(n, 5, 1);
        for (int i2 = 0; i2 <= two; i2++) {
            long pow2 = Diophantine.pow(2, i2);
            for (int i3 = 0; i3 <= three; i3++) {
                long pow3 = Diophantine.pow(3, i3);
                long p = pow2*pow3;
                sum += p;
                for (Long l : divisorsRec) {
                    sum += l*p;
                }
            }
        }
        return sum;
    }
    public static int[] divisorSums(int limit) {
        return divisorSums(limit, true);
    }
    public static int[] divisorSums(int limit, boolean includeOne) {
        int[] sums = new int[limit];
        if (includeOne) {
            Arrays.fill(sums, 1);
            sums[0] = 0;
        }
        for (int i = 2; i*2 < limit; i++) {
            for (int j = 2; i*j < limit; j++) {
                sums[i*j] += i;
            }
        }
        return sums;
    }
    private static List<Long> divisorsRec(long n, long start, int power) {
        if (n < 2) return List.of();
        List<Long> divisors = new ArrayList<>();

        long limit = (long) Math.sqrt(n);
        long i;
        boolean found = false;
        for (i = start; i <= limit; i += 6) {
            if (n % i == 0) {
                found = true;
                break;
            }
            if (n % (i+2) == 0) {
                found = true;
                break;
            }
        }

        if (!found) {
            for (int p = 1; p <= power; p++) {
                divisors.add(Diophantine.pow(n, p));
            }
            return divisors;
        }

        int exp1 = 0;
        while (n % i == 0) {
            exp1++;
            n /= i;
        }
        int exp2 = 0;
        while (n % (i+2) == 0) {
            exp2++;
            n /= i+2;
        }

        List<Long> divisorsRec = divisorsRec(n, i+6, power);

        for (int e1 = 0; e1 <= exp1*power; e1++) {
            long p1 = Diophantine.pow(i, e1);
            for (int e2 = 0; e2 <= exp2*power; e2++) {
                long p2 = Diophantine.pow(i+2, e2);
                long p = p1*p2;
                if (p > 1) divisors.add(p);
                for (Long l : divisorsRec) {
                    divisors.add(l * p);
                }
            }
        }

        return divisors;
    }
    public static int[] divisors(int n) {
        if (n < 1) throw new IllegalArgumentException("Argument must be positive!");
        if (n == 1) return new int[] {1};
        List<Integer> divisors = new ArrayList<>();
        divisors.add(1);
        List<Integer> biggerDivisors = new ArrayList<>();

        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                divisors.add(i);
                int counterPart = n/i;
                if (counterPart != i) biggerDivisors.add(counterPart);
            }
        }

        for (int i = biggerDivisors.size()-1; i >= 0; i--) {
            divisors.add(biggerDivisors.get(i));
        }

        divisors.add(n);
        return Converter.listToArr(divisors);
    }
    public static int divisorCount(long n) {
        return divisorCount(Primes.primeFactors(n));
    }
    public static int divisorCount(Primes.PF[] pfs) {
        int count = 1;
        for (Primes.PF pf : pfs) {
            count *= pf.power+1;
        }
        return count;
    }
}
