package euler;

import utils.Diophantine;

import java.util.*;

public class PE_0346 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = Diophantine.pow10[12];
        return String.valueOf(multipleBaseRepunitSum(limit));
    }

    private static long multipleBaseRepunitSum(long limit) {
        long totalSum = 1;

        long l = ((long) Math.sqrt(4*limit - 3) - 1)/2;
        totalSum += l*(l+1)*(2*l+1)/6 + l*(l+1)/2 + l - 3;

        Set<Long> repunits = new HashSet<>();
        
        l = (int) Math.cbrt(limit);
        for (long b = 2; b <= l; b++) {
            long sum = b*b*b + b*b + b + 1;
            while (sum < limit) {
                if (!R3(sum) && repunits.add(sum)) {
                    totalSum += sum;
                }
                sum = sum * b + 1;
            }
        }
        
        return totalSum;
    }

    private static boolean R3(long n) {
        long root = Diophantine.root(4*n - 3);
        if (root < 0) return false;
        return root % 2 == 1;
    }
}
