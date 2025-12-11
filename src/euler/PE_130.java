package euler;

import utils.Primes;

public class PE_130 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int n = 25;
        int[] compositeValues = firstNCompositeValuesWithRepunitProperty(n);
        return sum(compositeValues);
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) sum += i;
        return sum;
    }

    private static int[] firstNCompositeValuesWithRepunitProperty(int n) {
        int[] values = new int[n];

        int index = 0;
        int x = 1;
        while (index < n) {
            x += 2;
            if (x % 5 == 0) continue;
            if (Primes.isPrime(x)) continue;
            if (hasRepunitProperty(x)) {
                values[index] = x;
                index++;
            }
        }

        return values;
    }

    private static boolean hasRepunitProperty(int n) {
        int leastK = leastKFor(n);
        return (n-1) % leastK == 0;
    }

    private static int leastKFor(int n) {
        int k = 1;
        int remainder = 1;
        while (remainder != 0) {
            k++;
            remainder = (remainder * 10 + 1) % n;
        }
        return k;
    }
}
