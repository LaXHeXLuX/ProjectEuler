package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE_348 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int ways = 4;
        int n = 5;
        List<Long> first = firstNWithWaysToExpress(ways, n);
        return String.valueOf(sum(first));
    }

    private static long sum(List<Long> list) {
        long sum = 0;
        for (Long l : list) sum += l;
        return sum;
    }

    private static List<Long> firstNWithWaysToExpress(int ways, int n) {
        List<Long> first = new ArrayList<>();
        List<Long> second  = new ArrayList<>();
        int i = 10;
        int iLimit = Integer.MAX_VALUE;
        while (first.size() < n && (first.size() + second.size() < n || i < iLimit)) {
            long palindrome = palindromeFrom(i, false);
            if (palindrome % 4 != 3 && waysToExpress(palindrome) == ways) {
                first.add(palindrome);
            }
            palindrome = palindromeFrom(i, true);
            if (palindrome % 4 != 3 && waysToExpress(palindrome) == ways) {
                second.add(palindrome);
                iLimit = i*10;
            }
            i++;
        }
        first.addAll(second);
        Collections.sort(first);
        return first.subList(0, n);
    }

    private static int waysToExpress(long n) {
        int ways = 0;
        int cubeStep = Math.toIntExact(2 - (n % 2));
        long cubeLimit = (long) Math.cbrt(n);
        for (long cube = 2; cube < cubeLimit; cube+=cubeStep) {
            long rem = n - cube*cube*cube;
            long root = Diophantine.root(rem);
            if (root > 0) ways++;
        }
        return ways;
    }

    private static long palindromeFrom(int n, boolean extra) {
        long palindrome = n;
        if (extra) palindrome = palindrome*10 + n%10;
        n /= 10;
        while (n > 0) {
            palindrome = palindrome*10 + n%10;
            n /= 10;
        }
        return palindrome;
    }
}
