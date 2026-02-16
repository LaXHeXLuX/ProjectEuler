package euler;

import utils.ArrayFunctions;
import utils.PolygonalNumber;

public class PE_045 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int[] sides = {3, 5, 6};
        int lowerLimit = 40755 + 1;
        return nextNumberPolygonalIn(sides, lowerLimit);
    }

    private static long nextNumberPolygonalIn(int[] sides, int lowerLimit) {
        sides = ArrayFunctions.mergeSort(sides);

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
