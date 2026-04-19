package euler;

import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_037 {
    private static List<Integer> allTruncatablePrimes;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        allTruncatablePrimes();
        return String.valueOf(sum());
    }

    private static int sum() {
        int sum = 0;
        for (int i : allTruncatablePrimes) sum += i;
        return sum;
    }

    private static void allTruncatablePrimes() {
        allTruncatablePrimes = new ArrayList<>();
        allTruncatablePrimes(3, 1);
        allTruncatablePrimes(7, 1);
    }

    private static void allTruncatablePrimes(int current, int pow10) {
        pow10 *= 10;

        for (int i = 2; i <= 5; i+=3) {
            int next = i*pow10 + current;
            if (!Primes.isPrime(next)) continue;
            if (leftTruncatable(next)) allTruncatablePrimes.add(next);
        }

        for (int i = 1; i < 10; i+=2) {
            if (i == 5) continue;
            int next = i*pow10 + current;
            if (!Primes.isPrime(next)) continue;
            if ((i == 3 || i == 7) && leftTruncatable(next)) allTruncatablePrimes.add(next);
            allTruncatablePrimes(next, pow10);
        }
    }

    private static boolean leftTruncatable(int current) {
        while (current >= 10) {
            current /= 10;
            if (!Primes.isPrime(current)) return false;
        }
        return true;
    }
}
