package euler;

import utils.ArrayFunctions;
import utils.Diophantine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PE_127 {
    private static int[] rads;
    //private static int[][] sortedRads;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int limit = 120_000;
        double s = System.currentTimeMillis();
        rads = rads(limit);
        //sortedRads = sorted(rads);
        //System.out.println(Arrays.deepToString(sortedRads));
        double e = System.currentTimeMillis();
        System.out.println("rads: " + (e-s) + " ms");
        List<Integer> hits = abcHits(limit);
        return sum(hits);
    }

    private static long sum(List<Integer> list) {
        long sum = 0;
        for (Integer i : list) sum += i;
        return sum;
    }

    private static int[][] sorted(int[] rads) {
        int[][] rads2 = new int[rads.length][2];
        for (int i = 0; i < rads.length; i++) {
            rads2[i] = new int[] {i, rads[i]};
        }
        Comparator<int[]> c = Comparator.comparingInt(a -> a[1]);
        return ArrayFunctions.mergeSort(rads2, c);
    }

    private static int[] rads(int limit) {
        int[] rads = new int[limit];
        Arrays.fill(rads, 1);

        for (int i = 2; i < limit; i+=2) {
            rads[i] *= 2;
        }

        for (int i = 3; i < limit; i+=2) {
            if (rads[i] > 1) continue;
            for (int k = i; k < limit; k+=i) {
                rads[k] *= i;
            }
        }

        return rads;
    }

    private static List<Integer> abcHits(int limit) {
        List<Integer> hits = new ArrayList<>();

        for (int c = 3; c < limit; c++) {
            int m = c / rads[c];
            if (rads[c-1] < m) hits.add(c);
            if (m < 6) continue;
            for (int b = (c+1)/2; b < c-1; b++) {
                if (rads[b] >= m) continue;
                int a = c - b;
                if (Diophantine.gcd(a, b) != 1) continue;
                long rad = (long) rads[a] * rads[b];
                if (rad >= m) continue;
                hits.add(c);
            }
        }
        return hits;
    }
}
