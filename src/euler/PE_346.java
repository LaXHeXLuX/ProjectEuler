package euler;

import java.util.*;

public class PE_346 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 1_000_000_000_000L;
        return String.valueOf(multipleBaseRepunits(limit));
    }

    private static long multipleBaseRepunits(long limit) {
        Set<Long> repunits = new HashSet<>();
        long totalSum = 1;
        int baseLimit = (int) Math.sqrt(limit);
        for (int i = 2; i <= baseLimit; i++) {
            long pow = (long) i * i;
            long sum = pow + i+1;
            while (sum < limit) {
                if (repunits.add(sum)) totalSum += sum;
                pow *= i;
                sum += pow;
            }
        }
        return totalSum;
    }
}
