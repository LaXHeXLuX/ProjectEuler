package euler;

import utils.Diophantine;

import java.util.HashSet;
import java.util.Set;

public class PE_125 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100_000_000;
        Set<Long> palindromicConsecutiveSquareSums = palindromicConsecutiveSquareSums(limit);
        return sum(palindromicConsecutiveSquareSums);
    }

    private static long sum(Set<Long> set) {
        long sum = 0;
        for (Long i : set) sum += i;
        return sum;
    }
    
    private static Set<Long> palindromicConsecutiveSquareSums(long limit) {
        Set<Long> palindromicConsecutiveSquareSums = new HashSet<>();
        long nLimit = (long) Math.sqrt(limit);
        for (long n = 1; n <= nLimit; n++) {
            long sum = n * n;
            for (long i = n+1; i < nLimit; i++) {
                sum += i*i;
                if (sum > limit) break;
                if (Diophantine.isPalindrome(sum)) palindromicConsecutiveSquareSums.add(sum);
            }
        }
        return palindromicConsecutiveSquareSums;
    }
}
