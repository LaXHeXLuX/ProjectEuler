package euler;

import utils.Parser;

public class PE_0102 {

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String file = "src/euler/inputs/PE_0102_triangles.txt";
        int[][] triangles = Parser.parseManyInts(file, ",");
        int count = 0;
        for (int[] triangle : triangles) {
            if (containsOrigin(triangle)) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    private static boolean containsOrigin(int[] t) {
        boolean crossAB = cross(t[0], t[1], t[2], t[3]) > 0;
        boolean crossBC = cross(t[2], t[3], t[4], t[5]) > 0;
        boolean crossCA = cross(t[4], t[5], t[0], t[1]) > 0;
        return crossAB == crossBC && crossBC == crossCA;
    }

    private static int cross(int x1, int y1, int x2, int y2) {
        return x1 * y2 - x2 * y1;
    }
}
