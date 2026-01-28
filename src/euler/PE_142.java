package euler;

import utils.Diophantine;

import java.math.BigInteger;

public class PE_142 {
    private static long smallestSum = Long.MAX_VALUE >> 1;
    private static long[] xyz = {0, 0, 0};

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static long PE() {
        int limit = 10_000;
        findSquares2(limit);
        /*
        1920580114
        7682320456
        17285221026
        30729281824
        48014502850
        */
        return -1;
    }

    private static boolean check(int m, int n, int k, long z) {
        long mm = (long) m * m;
        long nn = (long) n * n;
        long a = k*(mm - nn);
        long b = 2L*k*m*n;
        long c = k*(mm + nn);
        long dd = a*a + 2*z;
        long ee =  c*c + 2*z;
        long ff = a*a + c*c + 2*z;
        if (Diophantine.root(dd) < 0) return false;
        if (Diophantine.root(ee) < 0) return false;
        return Diophantine.root(ff) > 0;
    }

    private static void findSquares2(int limit) {
        // TEMP
        int startM = 5;
        for (int m = startM; (long) m * m*m*m < smallestSum; m++) {
            if (m % 100 == 0) System.out.println("m: " + m);
            for (int n = 1; n < m; n++) {
                if (m % 2 == n % 2) continue;
                if (Diophantine.gcd(m, n) > 1) continue;
                long mm = (long) m * m;
                long nn = (long) n * n;
                for (int k = 1; k < limit; k++) {
                    long minFFBy2 = k*k*(mm*mm + nn*nn);
                    if (minFFBy2 > smallestSum) break;
                    long a = k*(mm - nn);
                    long b = 2L * k * m * n;
                    long c = k*(mm + nn);
                    for (long k2 = 1; k2 <= b/2; k2++) {
                        if (b % k2 != 0) continue;
                        long rem = b / k2;
                        if (rem % 4 == 2) continue;
                        long a1Limit = (long) Math.sqrt(rem);
                        for (long a1 = 1; a1 <= a1Limit; a1++) {
                            if (rem % a1 != 0) continue;
                            long a2 = rem / a1;
                            if (a1 >= a2 || a1%2 != a2%2) continue;
                            long m2 = (a1+a2) / 2;
                            long n2 = a2 - m2;
                            if (m2 % 2 == n2 % 2) continue;
                            if (Diophantine.gcd(m2, n2) > 1) continue;
                            long e = k2*(m2*m2 + n2*n2);
                            if (e <= c) continue;
                            BigInteger bigZ2 = BigInteger.valueOf(e).pow(2).subtract(BigInteger.valueOf(c).pow(2));
                            long z2;
                            try {
                                z2 = bigZ2.longValueExact();
                            } catch (ArithmeticException _) {
                                continue;
                            }
                            if (z2 < 0) {
                                System.out.println("e: " + e + ", c: " + c);
                            }
                            if (z2 > 2*(smallestSum/3)) continue;
                            if (z2 % 2 != 0) {
                                //System.out.println("z2 odd skip: m: " + m + ", n: " + n + ", k: " + k + ", a: " + a + ", b: " + b + ", c: " + c);
                                continue;
                            }
                            long z = z2 / 2;
                            long ffBy2 = minFFBy2 + z;
                            if (ffBy2 < 0) {
                                System.out.println("ffBy2: " + ffBy2 + ", minFFBy2: " + minFFBy2 + ", z: " + z);
                            }
                            if (ffBy2 >= (Long.MAX_VALUE >> 1)) continue;
                            long ff = 2*ffBy2;
                            long f = Diophantine.root(ff);
                            if (f < 0) continue;
                            /*if (!check(m, n, k, z)) {
                                System.out.println("x: " + (c*c + z) + ", y: " + (a*a + z) + ", z: " + z);
                                System.out.println("m: " + m + ", n: " + n + ", k: " + k + ", a: " + a + ", b: " + b + ", c: " + c);
                                System.out.println("m2: " + m2 + ", n2: " + n2 + ", k2: " + k2 + ", e: " + e + ", f: " + f);
                                throw new RuntimeException("False positive: " + m + ", " + n + ", " + k + ", " + z);
                            }*/
                            System.out.println();
                            long sum = sum(k, m, n, z);
                            if (sum >= smallestSum) continue;
                            smallestSum = sum;
                            xyz = new long[] {c*c + z, a*a + z, z};
                            System.out.println("x: " + (c*c + z) + ", y: " + (a*a + z) + ", z: " + z);
                            System.out.println("WIN: k: " + k + ", m: " + m + ", n: " + n + ", z: " + z + ", SUM: " + sum);
                        }
                    }
                }
            }
        }
    }

    private static long sum(int k, int m, int n, long z) {
        return 2L*k*k*((long) m * m*m*m + (long) n * n*n*n) + 3*z;
    }
}