package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Diophantine {
    public static int[] continuedFraction(int n) {
        int a0 = (int) Math.sqrt(n);
        if (a0*a0 == n) return new int[] {a0};
        int d0 = 1;
        List<Integer> continuedFraction = new ArrayList<>();
        continuedFraction.add(a0);
        int m1 = d0*a0;
        int d1 = (n - m1*m1)/d0;
        int a1 = (a0 + m1)/d1;
        continuedFraction.add(a1);
        int m = d1*a1 - m1;
        int d = (n - m*m)/d1;
        int a = (a0 + m)/d;
        while (!(m == m1 && d == d1)) {
            continuedFraction.add(a);
            m = d*a - m;
            d = (n - m*m)/d;
            a = (a0 + m)/d;
        }

        return Converter.listToArr(continuedFraction, Integer.class);
    }
    private static long[] nthTermOfContinuedFraction(int[] continuedFraction, int n) {
        long h1 = continuedFraction[0];
        long k1 = 1;
        long h2 = (long) continuedFraction[0] * continuedFraction[1] + 1;
        long k2 = continuedFraction[1];
        int i = 1;
        while (i < n) {
            i++;
            long tempH = h2;
            long tempK = k2;
            long ai = continuedFraction[(i-1) % (continuedFraction.length-1) + 1];
            h2 = ai*h2 + h1;
            k2 = ai*k2 + k1;
            h1 = tempH;
            k1 = tempK;
        }
        return new long[] {h2, k2};
    }
    public static long[] pell(int D) {
        int[] continuedFraction = continuedFraction(D);
        int l = continuedFraction.length;
        if (l < 2) return new long[0];
        return nthTermOfContinuedFraction(
                continuedFraction,
                (l-1) * (1 + (l-1)%2) - 1
        );
    }
    private static BigInteger[] nthTermOfContinuedFractionBig(int[] continuedFraction, int n) {
        BigInteger[] bigCF = new BigInteger[continuedFraction.length];
        for (int i = 0; i < continuedFraction.length; i++) {
            bigCF[i] = BigInteger.valueOf(continuedFraction[i]);
        }
        BigInteger h1 = bigCF[0];
        BigInteger k1 = BigInteger.ONE;
        BigInteger h2 = bigCF[0].multiply(bigCF[1]).add(BigInteger.ONE);
        BigInteger k2 = bigCF[1];
        int i = 1;
        while (i < n) {
            i++;
            BigInteger tempH = h2;
            BigInteger tempK = k2;
            BigInteger ai = bigCF[(i-1) % (continuedFraction.length-1) + 1];
            h2 = ai.multiply(h2).add(h1);
            k2 = ai.multiply(k2).add(k1);
            h1 = tempH;
            k1 = tempK;
        }
        return new BigInteger[] {h2, k2};
    }
    public static BigInteger[] pellBig(int D) {
        int[] continuedFraction = continuedFraction(D);
        int l = continuedFraction.length;
        if (l < 2) return new BigInteger[0];
        return nthTermOfContinuedFractionBig(
                continuedFraction,
                (l-1) * (1 + (l-1)%2) - 1
        );
    }
    public static int root(int n) {
        if (n < 0) throw new IllegalArgumentException("Can't find root of negative n");
        int root = (int)Math.sqrt(n);
        if (root*root == n) return root;
        return -root;
    }
    public static long root(long n) {
        if (n < 0) throw new IllegalArgumentException("Can't find root of negative n");
        long root = (long)Math.sqrt(n);
        if (root * root == n) return root;
        return -root;
    }
    public static int[] quadratic(int a, int b, int c) {
        long sqrt = (long) b * b - 4L*a*c;
        if (sqrt < 0) return new int[0];
        long root = root(sqrt);
        if (root < 0) return new int[0];
        long x1 = -b - root;
        long x2 = -b + root;
        long div = 2L * a;
        long rem1 = x1 % div;
        long rem2 = x2 % div;
        int result1 = Math.toIntExact(x1/div);
        int result2 = Math.toIntExact(x2/div);
        if (rem1 != 0 && rem2 != 0) return new int[0];
        if (rem1 == 0 && rem2 == 0 && x1 != x2) return new int[] {result1, result2};
        if (rem1 == 0) return new int[] {result1};
        return new int[] {result2};
    }
    public static int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
    public static int digitSum(long n) {
        int sum = 0;
        while (n > 0) {
            sum += (int) (n % 10);
            n /= 10;
        }
        return sum;
    }
    public static int digitSum(BigInteger n) {
        int sum = 0;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = n.divideAndRemainder(BigInteger.TEN);
            sum += divRem[1].intValue();
            n = divRem[0];
        }
        return sum;
    }
    private static boolean isPalindromeInBase2(int n) {
        int left = 31 - Integer.numberOfLeadingZeros(n);
        int right = 0;
        while (right < left) {
            if (((n >> left) & 1) != ((n >> right) & 1))
                return false;
            left--;
            right++;
        }
        return true;
    }
    private static boolean isPalindromeInBase2(long n) {
        int left = 63 - Long.numberOfLeadingZeros(n);
        int right = 0;
        while (right < left) {
            if (((n >> left) & 1) != ((n >> right) & 1))
                return false;
            left--;
            right++;
        }
        return true;
    }
    public static boolean isPalindromeInBase(int n, int base) {
        if (n == 0) return true;
        if (n < 0) n = -n;
        if (base == 2) return isPalindromeInBase2(n);
        int[] convertedDigits = new int[(int)(Math.log(n)/Math.log(base))+1];
        int limit = convertedDigits.length / 2;
        for (int i = 0; i < limit; i++) {
            convertedDigits[convertedDigits.length-i-1] = n%base;
            n /= base;
        }
        if (convertedDigits.length % 2 == 1) n /= base;
        limit = convertedDigits.length - limit;
        for (int i = limit; i < convertedDigits.length; i++) {
            if (n%base != convertedDigits[i]) return false;
            n /= base;
        }
        return true;
    }
    public static boolean isPalindrome(int n) {
        return isPalindromeInBase(n, 10);
    }
    public static boolean isPalindromeInBase(long n, int base) {
        if (n == 0) return true;
        if (n < 0) n = -n;
        if (base == 2) return isPalindromeInBase2(n);
        int[] convertedDigits = new int[(int)(Math.log(n)/Math.log(base))+1];
        int limit = convertedDigits.length / 2;
        for (int i = 0; i < limit; i++) {
            convertedDigits[convertedDigits.length-i-1] = (int) (n%base);
            n /= base;
        }
        if (convertedDigits.length % 2 == 1) n /= base;
        limit = convertedDigits.length - limit;
        for (int i = limit; i < convertedDigits.length; i++) {
            if ((int) (n%base) != convertedDigits[i]) return false;
            n /= base;
        }
        return true;
    }
    public static boolean isPalindrome(long n) {
        return isPalindromeInBase(n, 10);
    }
}
