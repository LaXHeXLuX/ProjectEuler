package euler;

import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_128 {

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 2000;
        List<Long> PD3s = PD3UpTo(limit);
        System.out.println(PD3s);
        return PD3s.get(limit-1);
    }

    private static List<Long> PD3UpTo(int n) {
        List<Long> PD3 = new ArrayList<>(List.of(1L, 2L, 8L, 19L));
        if (n <= 3) return PD3;

        long i = 4;
        while (PD3.size() < n) {
            boolean[] PD3s = PD3For(i);
            if (PD3s[0]) PD3.add(start(i));
            if (PD3s[1]) PD3.add(start(i+1)-1);
            i++;
        }

        return PD3;
    }

    private static boolean allPrime(long[] arr) {
        for (long l : arr) {
            if (!Primes.isPrime(l)) return false;
        }
        return true;
    }

    private static boolean[] PD3For(long n) {
        long[] diffs1 = {6*(n-1) - 1, 6*(n-1) + 1, 12*n - 7};
        long[] diffs2 = {6*(n-1) - 1, 12*(n-1) - 7, 6*n - 1};
        return new boolean[] {allPrime(diffs1), allPrime(diffs2)};
    }

    private static long start(long n) {
        return 2 + 3*(n-1)*(n-2);
    }
}
