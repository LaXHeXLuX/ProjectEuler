package euler;

import java.util.Arrays;

public class PE_029 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 100;
        return String.valueOf(distinctPowerCount(limit, limit));
    }

    private static long distinctPowerCount(int limitA, int limitB) {
        long counter = 0;

        int[][] roots = new int[limitA+1][2];
        int[] starts = new int[limitA+1];
        Arrays.fill(starts, 2);
        for (int a = 2; a <= limitA; a++) {
            int aPow = a * a;
            int power = 2;
            while (aPow <= limitA) {
                if (roots[a][0] == 0) roots[aPow] = new int[] {a, power};
                starts[aPow] = limitB / power + 1;
                power++;
                aPow *= a;
            }
            for (int b = starts[a]; b <= limitB; b++) {
                if (roots[a][0] == 0) {
                    counter++;
                    continue;
                }
                int rootPow = b * roots[a][1];
                boolean pass = true;
                int iStart = rootPow / limitB;
                if (rootPow % limitB != 0) iStart++;
                for (int i = iStart; i < roots[a][1]; i++) {
                    if (rootPow % i == 0) {
                        pass = false;
                        break;
                    }
                }
                if (pass) counter++;
            }
        }
        return counter;
    }
}
