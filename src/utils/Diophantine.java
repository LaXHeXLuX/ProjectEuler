package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static long[] nthTermOfContinuedFraction(int[] continuedFraction, int n) {
        long h1 = continuedFraction[0];
        long k1 = 1;
        if (n == 0) return new long[] {h1, k1};
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
    public static List<long[]> pell(int D, int C) {
        long[] fundamental = Diophantine.pell(D);
        int adder = 1;
        int absC = C;
        if (C < 0) {
            absC = -absC;
            adder = -1;
        }
        long yBound = (long) (fundamental[1] * Math.sqrt((double) absC / (2*(fundamental[0] + adder))));
        List<long[]> fundamentals = new ArrayList<>();
        long y = 0;
        if (C < 0) y = (long) Math.ceil(Math.sqrt((double) -C / D));
        while (y <= yBound) {
            long xx = C + D*y*y;
            long x = Diophantine.root(xx);
            if (x > 0) {
                fundamentals.add(new long[] {x, y});
                fundamentals.add(new long[] {-x, y});
            }
            y++;
        }
        return fundamentals;
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
    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
    public static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
    public static int[] extendedEuclidean(int a, int b) {
        int r1 = a; int r2 = b;
        int s1 = 1; int s2 = 0;
        int t1 = 0; int t2 = 1;

        while (r2 != 0) {
            int q = r1 / r2;
            int r3 = r1 - q*r2;
            int s3 = s1 - q*s2;
            int t3 = t1 - q*t2;
            r1 = r2; s1 = s2; t1 = t2;
            r2 = r3; s2 = s3; t2 = t3;
        }

        return new int[] {s1, t1};
    }
    public static long[] extendedEuclidean(long a, long b) {
        long r1 = a; long r2 = b;
        long s1 = 1; long s2 = 0;
        long t1 = 0; long t2 = 1;

        while (r2 != 0) {
            long q = r1 / r2;
            long r3 = r1 - q*r2;
            long s3 = s1 - q*s2;
            long t3 = t1 - q*t2;
            r1 = r2; s1 = s2; t1 = t2;
            r2 = r3; s2 = s3; t2 = t3;
        }

        return new long[] {s1, t1};
    }
    public static long powMod(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;

        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;

            base = (base * base) % mod;
            exp >>= 1;
        }

        return result;
    }
    public static long pow(long base, int exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1)
                result = result * base;

            base = base * base;
            exp >>= 1;
        }

        return result;
    }
    public static long tetrateMod(long base, long exp, long mod) {
        if (mod == 1) return 0;
        List<Long> chain = new ArrayList<>();
        chain.add(mod);
        while (mod > 1) {
            mod = Primes.eulersTotient(mod);
            chain.add(mod);
        }
        int start = chain.size() - 3;
        if (exp < chain.size()) start = Math.toIntExact(exp) - 1;
        long result = 1;
        for (int i = start; i >= 0; i--) {
            result = Diophantine.powMod(base, result, chain.get(i));
        }
        return result;
    }
    public static int modDivide(int a, int b, int mod) {
        int d = Math.toIntExact(Diophantine.gcd(b, mod));
        if (a % d != 0) return -1;
        a /= d;
        b /= d;
        mod /= d;
        int[] sol = Diophantine.extendedEuclidean(b, mod);
        int result = (sol[0] * a) % mod;
        if (result < 0) result += mod;
        return result;
    }
    public static int crt(int... ints) {
        if (ints.length == 0 || ints.length % 2 != 0 ) throw new IllegalArgumentException("Wrong number of arguments (zero or odd)");

        int n1 = ints[0];
        int a1 = ints[1];
        for (int i = 2; i < ints.length; i+=2) {
            int n2 = ints[i];
            int a2 = ints[i+1];
            int gcd = gcd(n1, n2);
            if ((a1 - a2) % gcd != 0) return -1;
            int[] bezout = extendedEuclidean(n1, n2);
            a1 = (a1*bezout[1]*n2 + a2*bezout[0]*n1) / gcd;
            n1 = n1*n2 / gcd;
            a1 = Math.floorMod(a1, n1);
        }

        return a1;
    }
}
