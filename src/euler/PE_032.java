package euler;

import utils.Diophantine;

import java.util.HashSet;
import java.util.Set;

public class PE_032 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int base = 10;
        Set<Long> products = pandigitalProductGroupProducts(base);
        return String.valueOf(sum(products));
    }

    private static long sum(Set<Long> set) {
        long sum = 0;
        for (long i : set) sum += i;
        return sum;
    }

    private static Set<Long> pandigitalProductGroupProducts(int base) {
        Set<Long> found = new HashSet<>();

        int cases = base % 2;
        for (int i = 0; i <= cases; i++) {
            int daLimit = (base / 2 + 1) / 2;
            int dc = base / 2 - 1;
            if (i == 1) {
                daLimit = base / 4;
                dc = base / 2;
            }

            long aLimit = Diophantine.pow(base, daLimit);
            for (long a = 2; a < aLimit; a++) {
                long bLimit = Diophantine.pow(base, dc) / a;
                long bStart = Diophantine.pow(base, dc - 1) / a;
                if (bStart < a) bStart = a;
                long used = 1;
                long tempA = a;
                boolean valid = true;
                while (tempA > 0) {
                    int digit = (int) (tempA % base);
                    if ((used & (1L << digit)) != 0) {
                        valid = false;
                        break;
                    }
                    used |= 1L << digit;
                    tempA /= base;
                }
                if (!valid) continue;
                for (long b = bStart; b < bLimit; b++) {
                    if (pandigitalGroup(base, a, b, used)) {
                        found.add(a*b);
                    }
                }
            }
        }

        return found;
    }

    private static boolean pandigitalGroup(int base, long a, long b, long used) {
        long c = a * b;
        while (b > 0) {
            int digit = (int) (b % base);
            if ((used & (1L << digit)) != 0) return false;
            used |= 1L << digit;
            b /= base;
        }
        while (c > 0) {
            int digit = (int) (c % base);
            if ((used & (1L << digit)) != 0) return false;
            used |= 1L << digit;
            c /= base;
        }
        for (int i = 1; i < base; i++) {
            if ((used & (1L << i)) == 0) return false;
        }

        return true;
    }
}