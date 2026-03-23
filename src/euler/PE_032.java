package euler;

import utils.Pandigital;

public class PE_032 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        return String.valueOf(sumOfPandigitalProductGroups());
    }

    private static long sumOfPandigitalProductGroups() {
        long sum = 0;
        boolean[] skip = new boolean[100_000];

        for (int iLimit = 10; iLimit <= 100; iLimit*=10) {
            for (int i = iLimit/10; i < iLimit; i++) {
                int jLimit = 10_000 / i;
                for (int j = 10_000 / iLimit; j < jLimit; j++) {
                    int[] productGroup = {i, j, i*j};
                    if (skip[productGroup[2]]) continue;
                    if (Pandigital.groupIsPandigital(productGroup)) {
                        skip[productGroup[2]] = true;
                        sum += productGroup[2];
                    }
                }
            }
        }

        return sum;
    }
}
