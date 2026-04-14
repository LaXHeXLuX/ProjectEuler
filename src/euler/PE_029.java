package euler;

import utils.Diophantine;

import java.util.Arrays;

public class PE_029 {

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int limitA = 1_000_000;
        int limitB = 1_000_000;
        return String.valueOf(distinctPowerCount0 (limitA, limitB));
    }

    private static long includeExclude(int p, int limitB) {
        long sum = 0;
        int sign = 1;
        for (int i = 1; i <= p; i++) {
            sum += sign * includeExclude(i, p, limitB);
            sign *= -1;
        }
        return sum;
    }

    private static long includeExclude(int count, int p, int limitB) {
        long sum = 0;
        int[] ints = new int[count];
        for (int i = 1; i <= p - count + 1; i++) {
            ints[0] = i;
            sum += includeExclude(ints, 1, i, p, limitB);
        }
        return sum;
    }

    private static long includeExclude(int[] ints, int i, int lcm, int p, int limitB) {
        if (i == ints.length) return intersect(ints, lcm, limitB);
        if (lcm > p*limitB) return 0;

        long sum = 0;
        for (int j = ints[i-1] + 1; j <= p - ints.length + i + 1; j++) {
            ints[i] = j;
            sum += includeExclude(ints, i+1, Diophantine.lcm(lcm, j), p, limitB);
        }

        return sum;
    }

    private static int intersect(int[] ints, int lcm, int limitB) {
        return ints[0]*limitB / lcm - (ints[ints.length-1]*2-1) / lcm;
    }

    private static long distinctPowerCount0(int limitA, int limitB) {
        double s = System.currentTimeMillis();
        int P = 32 - Integer.numberOfLeadingZeros(limitA);
        long[] counts = new long[P];
        for (int p = 1; p < P; p++) {
            counts[p] = includeExclude(p, limitB);
        }
        double e = System.currentTimeMillis();
        System.out.println("prep: " + (e-s) + " ms");
        int sqrt = (int) Math.sqrt(limitA);

        long count = 0;
        boolean[] skip = new boolean[sqrt+1];
        int highSkip = 0;
        for (int a0 = 2; a0 <= sqrt; a0++) {
            if (skip[a0]) continue;
            int max = a0*a0;
            int p = 1;
            while (max <= sqrt) {
                skip[max] = true;
                max *= a0;
                p++;
            }
            while (max <= limitA) {
                max *= a0;
                p++;
                highSkip++;
            }
            count += counts[p];
        }
        count += (limitA - sqrt - highSkip) * (limitA - 1L);
        return count;
    }

    private static int maxPower(int a, int limit) {
        long max = a;
        int pow = 0;
        while (max <= limit) {
            max *= a;
            pow++;
        }
        return pow;
    }

    private static long recurse(long lcm, int index, int sign, long left, long right, int[] list) {
        if (lcm > right) return 0;
        long res = sign * (right / lcm - (left - 1) / lcm);
        for (int i = index + 1; i < list.length; i++) {
            res += recurse(Diophantine.lcm(lcm, list[i]), i, -sign, left, right, list);
        }
        return res;
    }

    private static long dd(long left, long right, int a, int b, boolean[] composite) {
        long res = right / b - (left - 1) / b;
        int[] initialList = new int[b-a];
        int index = 0;
        for (int i = a; i < b; i++) {
            if (!composite[i]) initialList[index++] = i;
        }
        int[] list = Arrays.copyOf(initialList, index);
        for (int i = 0; i < list.length; i++) {
            res -= recurse(Diophantine.lcm(b, list[i]), i, 1, left, right, list);
        }
        return res;
    }

    private static long distinctPowerCount2(int limitA, int limitB) {
        int sqrt = (int) Math.sqrt(limitA);
        int P = maxPower(2, limitA);

        long[] counts = new long[P + 1];
        counts[1] = limitA - 1;

        for (int p = 2; p <= P; p++) {
            // Sieve: mark composite exponents in [p, P]
            boolean[] composite = new boolean[P + 1];
            for (int i = p; i <= P / 2; i++) {
                int k = i*i;
                while (k <= P) {
                    composite[k] = true;
                    k += i;
                }
            }

            long uMin = (long) (p - 1) * limitB + 1;
            long uMax = (long) p * limitB;
            for (int f = p; f <= P; f++) {
                if (!composite[f]) counts[f] += dd(uMin, uMax, p, f, composite);
            }
        }

        for (int c = 2; c <= P; c++) counts[c] += counts[c - 1];

        // Harvest: iterate over bases 2..sqrt(n), skip perfect powers
        boolean[] used = new boolean[sqrt + 1];
        long ans = 0;
        long coll = 0;
        for (int i = 2; i <= sqrt; i++) {
            if (used[i]) continue;

            int c = maxPower(i, limitA);
            ans += counts[c];
            long u = i;
            for (int j = 2; j <= c; j++) {
                u *= i;
                if (u <= sqrt) {
                    used[(int) u] = true;
                } else {
                    coll += c - j + 1;
                    break;
                }
            }
        }

        ans += (long) (limitA - sqrt) * (limitB - 1);
        ans -= coll * (limitA - 1);
        return ans;
    }
}
