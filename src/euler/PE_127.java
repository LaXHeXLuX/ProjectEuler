package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_127 {
    private static int[] rads;
    private static int[][] reverseLookUp;

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 120_000;
        rads = rads(limit);
        reverseLookUp = reverseLookUp(rads);
        List<Integer> hits = abcHits(limit);
        return sum(hits);
    }

    private static long sum(List<Integer> list) {
        long sum = 0;
        for (Integer i : list) sum += i;
        return sum;
    }

    private static int[][] reverseLookUp(int[] rads) {
        int[] counts = new int[rads.length];
        for (int rad : rads) {
            counts[rad]++;
        }
        int[][] reverseLookUp = new int[counts.length][];
        for (int i = 0; i < reverseLookUp.length; i++) {
            reverseLookUp[i] = new int[counts[i]];
        }
        for (int i = 0; i < rads.length; i++) {
            reverseLookUp[rads[i]][reverseLookUp[rads[i]].length - counts[rads[i]]] = i;
            counts[rads[i]]--;
        }
        return reverseLookUp;
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
            if (rads[c-1] < c / rads[c]) hits.add(c);
        }

        for (int c = 3; c < limit; c++) {
            int m = c / rads[c];
            if (m < 6) continue;
            for (int bRad = 2; bRad < m; bRad++) {
                int[] bValues = reverseLookUp[bRad];
                if (bValues.length == 0) continue;
                int maxIndex = Arrays.binarySearch(bValues, c-1);
                if (maxIndex < 0) maxIndex = -maxIndex - 1;
                int minIndex = Arrays.binarySearch(bValues, 0, maxIndex, c/2);
                if (minIndex < 0) minIndex = -minIndex - 1;
                for (int i = minIndex; i < maxIndex; i++) {
                    int b = bValues[i];
                    int a = c - b;
                    if (Diophantine.gcd(a, b) != 1) continue;
                    long rad = (long) rads[a] * rads[b];
                    if (rad < m) hits.add(c);
                }
            }
        }
        return hits;
    }
}
