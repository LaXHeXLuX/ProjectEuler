package euler;

import utils.PolygonalNumber;

import java.util.Arrays;

public class PE_045 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] sides = {3, 5, 6};
        long lowerLimit = 40755 + 1;
        return String.valueOf(nextNumberPolygonalIn(sides, lowerLimit));
    }

    private static long nextNumberPolygonalIn(int[] sides, long lowerLimit) {
        Arrays.sort(sides);

        for (int i = 0; true; i++) {
            long maxPolygonal = PolygonalNumber.polygonalNumber(sides[sides.length-1], i);
            if (maxPolygonal < lowerLimit) continue;

            boolean isPolygonal = true;
            for (int j = sides.length-2; j >= 0; j--) {
                if (!PolygonalNumber.isPolygonalNumber(sides[j], maxPolygonal)) {
                    isPolygonal = false;
                    break;
                }
            }

            if (isPolygonal) return maxPolygonal;
        }
    }
}
