package euler;

import utils.ArrayFunctions;

import java.util.Comparator;

public class PE_124 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 100_000;
        int[][] rads = rads(n);
        int[][] sorted = sort(rads);
        return sorted[10_000][0];
    }

    private static int[][] sort(int[][] rads) {
        Comparator<int[]> c = Comparator.comparingInt(a -> a[1]);
        return ArrayFunctions.mergeSort(rads, c);
    }

    private static int[][] rads(int n) {
        int[][] rads = new int[n+1][];
        for (int i = 0; i <= n; i++) {
            rads[i] = new int[] {i, 1};
        }

        for (int p = 2; p <= n; p++) {
            if (rads[p][1] > 1) continue;
            for (int k = p; k <= n; k += p) {
                rads[k][1] *= p;
            }
        }

        return rads;
    }
}
