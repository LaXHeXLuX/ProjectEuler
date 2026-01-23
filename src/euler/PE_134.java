package euler;

import utils.Diophantine;
import utils.Primes;

public class PE_134 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        int[] primes = Primes.primes(2*limit);

        long sum = 0;
        int index = 2;
        int p1 = primes[index];
        int p2 = primes[index+1];
        while (p1 <= limit) {
            long n = smallestNForP1AndP2(p1, p2);
            sum += n;

            index++;
            p1 = p2;
            p2 = primes[index+1];
        }
        return sum;
    }

    private static int modulusMultiplier(int a, int b, int c) {
        int d = Math.toIntExact(Diophantine.gcd(a, c));
        if (b % d != 0) return -1;
        a /= d;
        b /= d;
        c /= d;
        long[] sol = Diophantine.extendedEuclidean(a, c);
        int r = Math.toIntExact((sol[0] * b) % c);
        if (r < 0) r += c;
        return r;
    }

    private static long smallestNForP1AndP2(int p1, int p2) {
        int p1Size = (int) Math.log10(p1) + 1;
        int p1Mod = Math.toIntExact(Diophantine.powMod(10, p1Size, p2));
        int diff = p2 - p1;
        long n = modulusMultiplier(p1Mod, diff, p2);
        if (n == -1) return -1;
        for (int i = 0; i < p1Size; i++) n *= 10;
        return n + p1;
    }
}
