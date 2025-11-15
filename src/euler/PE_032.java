package euler;

import utils.Pandigital;

public class PE_032 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        return sumOfPandigitalProductGroups();
    }

    private static long sumOfPandigitalProductGroups() {
        long sum = 0;
        boolean[] skip = new boolean[100_000];

        for (int i = 0; i < 100; i++) {
            int jLimit = i < 10 ? 10_000 : 1_000;
            for (int j = i+1; j < jLimit; j++) {
                int[] productGroup = {i, j, i*j};
                if (skip[productGroup[2]]) continue;
                if (Pandigital.groupIsPandigital(productGroup)) {
                    skip[productGroup[2]] = true;
                    sum += productGroup[2];
                }
            }
        }

        return sum;
    }
}
